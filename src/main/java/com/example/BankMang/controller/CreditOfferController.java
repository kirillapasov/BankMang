package com.example.BankMang.controller;
import com.example.BankMang.model.CreditOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BankMang.service.CreditOfferService;

import java.util.List;

@RestController
@RequestMapping("/credit-offers")
public class CreditOfferController {

    private final CreditOfferService creditOfferService;

    @Autowired
    public CreditOfferController(CreditOfferService creditOfferService) {
        this.creditOfferService = creditOfferService;
    }

    @GetMapping
    public List<CreditOffer> getAllCreditOffers() {
        return creditOfferService.getAllCreditOffers();
    }

    @GetMapping("/{creditOfferId}")
    public ResponseEntity<CreditOffer> getCreditOfferById(@PathVariable long creditOfferId) {
        return creditOfferService.getCreditOfferById(creditOfferId)
                .map(creditOffer -> new ResponseEntity<>(creditOffer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CreditOffer> createCreditOffer(@RequestBody CreditOffer creditOffer) {
        CreditOffer createdCreditOffer = creditOfferService.createCreditOffer(creditOffer);
        return new ResponseEntity<>(createdCreditOffer, HttpStatus.CREATED);
    }

    @PutMapping("/{creditOfferId}")
    public ResponseEntity<CreditOffer> updateCreditOffer(@PathVariable long creditOfferId, @RequestBody CreditOffer updatedCreditOffer) {
        return creditOfferService.updateCreditOffer(creditOfferId, updatedCreditOffer)
                .map(creditOffer -> new ResponseEntity<>(creditOffer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{creditOfferId}")
    public ResponseEntity<Void> deleteCreditOffer(@PathVariable long creditOfferId) {
        boolean deleted = creditOfferService.deleteCreditOffer(creditOfferId);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}