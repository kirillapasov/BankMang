package com.example.BankMang.controller;

import com.example.BankMang.service.CreditService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credits")
public class CreditController {
    private final CreditService creditService;

}
