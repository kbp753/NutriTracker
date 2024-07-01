package com.bhanu.nutritracker.controllers;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/csrf")
public class CsrfController {
    @GetMapping("/getCsrfToken")
    public CsrfToken csrf(CsrfToken token)
    {
        return token;
    }
}
