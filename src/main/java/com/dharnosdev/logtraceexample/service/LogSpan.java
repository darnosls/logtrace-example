package com.dharnosdev.logtraceexample.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogSpan {

    public void logar() {
        log.info("logar()");
    }
}
