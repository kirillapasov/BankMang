package com.example.BankMang.controller;

import com.example.BankMang.service.CreditService;
import model.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credits")
public class CreditController {
    private final CreditService creditService;
    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public List<Credit> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/{creditId}")
    public ResponseEntity<Credit> getCreditById(@PathVariable long creditId) {
        return creditService.getCreditById(creditId)
                .map(credit -> new ResponseEntity<>(credit, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Credit> createCredit(@RequestBody Credit credit) {
        Credit createdCredit = creditService.createCredit(credit);
        return new ResponseEntity<>(createdCredit, HttpStatus.CREATED);
    }

    @PutMapping("/{creditId}")
    public ResponseEntity<Credit> updateCredit(@PathVariable long creditId, @RequestBody Credit updatedCredit) {
        return creditService.updateCredit(creditId, updatedCredit)
                .map(credit -> new ResponseEntity<>(credit, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{creditId}")
    public ResponseEntity<Void> deleteCredit(@PathVariable long creditId) {
        boolean deleted = creditService.deleteCredit(creditId);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
