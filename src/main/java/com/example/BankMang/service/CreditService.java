package com.example.BankMang.service;

import model.Credit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditService {

    private final List<Credit> credits = new ArrayList<>();

    public List<Credit> getAllCredits() {
        return credits;
    }

    public Optional<Credit> getCreditById(long creditId) {
        return credits.stream()
                .filter(credit -> credit.getId() == creditId)
                .findFirst();
    }

    public Credit createCredit(Credit credit) {
        credit.setId(System.currentTimeMillis());
        credits.add(credit);
        return credit;
    }

    public Optional<Credit> updateCredit(long creditId, Credit updatedCredit) {
        Optional<Credit> existingCredit = getCreditById(creditId);
        existingCredit.ifPresent(credit -> {
            credit.setCreditLimit(updatedCredit.getCreditLimit());
            credit.setInterestRate(updatedCredit.getInterestRate());
        });
        return existingCredit;
    }

    public boolean deleteCredit(long creditId) {
        Optional<Credit> creditToRemove = getCreditById(creditId);
        if (creditToRemove.isPresent()) {
            credits.remove(creditToRemove.get());
            return true;
        }
        return false;
    }
}