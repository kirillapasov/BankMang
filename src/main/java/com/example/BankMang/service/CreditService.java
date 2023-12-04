package com.example.BankMang.service;

import com.example.BankMang.model.Credit;
import com.example.BankMang.repository.CreditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditService {

    private final CreditDao creditDao;

    @Autowired
    public CreditService(CreditDao creditDao) {
        this.creditDao = creditDao;
    }

    public List<Credit> getAllCredits() {
        return creditDao.getAllCredits();
    }

    public Optional<Credit> getCreditById(long creditId) {
        return creditDao.getCreditById(creditId);
    }

    public Credit createCredit(Credit credit) {
        return creditDao.createCredit(credit);
    }

    public Optional<Credit> updateCredit(long creditId, Credit updatedCredit) {
        return creditDao.updateCredit(creditId, updatedCredit);
    }

    public boolean deleteCredit(long creditId) {
        return creditDao.deleteCredit(creditId);
    }
}