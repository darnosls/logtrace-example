package com.dharnosdev.logtraceexample.controller;

import com.dharnosdev.logtraceexample.service.LogSpan;
import io.micrometer.context.ContextSnapshot;
import io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor;
import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
public class helloController {

    private final Tracer tracer;
    private final LogSpan logSpan;

    helloController(Tracer tracer, LogSpan logSpan) {
        this.tracer = tracer;
        this.logSpan = logSpan;
    }
    @GetMapping("/trace")
    public Mono<String> trace() {
        log.info("Inciando teste de trace");
        return Mono.just("First trace")
                .doFirst(() -> log.info("Escreva algo antes de tudo"));
    }

    @RequestMapping("/span")
    public Mono<String> span() {

        log.info("inicio");
        return Mono.deferContextual(contextView -> {
            try (ContextSnapshot.Scope scope = ContextSnapshot.setThreadLocalsFrom(contextView,
                    ObservationThreadLocalAccessor.KEY)) {
                String traceId = this.tracer.currentSpan().context().traceId();
                log.info("<ACCEPTANCE_TEST> <TRACE:{}> Hello from producer", traceId);
                log.info("testar");
                this.logSpan.logar();
                return Mono.just(traceId);
            }
        });
    }
}
