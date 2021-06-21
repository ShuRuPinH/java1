package ru.progwards.java2.lessons.ATM.bankomat.src.app.service.impl;

import ru.progwards.java2.lessons.ATM.bankomat.src.app.model.Account;
import ru.progwards.java2.lessons.ATM.bankomat.src.app.service.AccountService;
import ru.progwards.java2.lessons.ATM.bankomat.src.app.service.StoreService;

import java.nio.file.Files;

public class AccountServiceImpl implements AccountService {


    private StoreService service;

    public AccountServiceImpl() {

    }

    public AccountServiceImpl(StoreService service) {
        this.service = service;
    }

    @Override
    public synchronized double balance(Account account) {
        return account.getAmount();
    }

    @Override
    public synchronized void deposit(Account account, double amount) {
        double sum = account.getAmount() + amount;
        account.setAmount(sum);
        service.update(account);
    }

    @Override
    public synchronized void withdraw(Account account, double amount) {

        double sum = account.getAmount() - amount;
        if (sum < 0) {
            throw new RuntimeException("Not enough money");
        }
        account.setAmount(sum);
        service.update(account);
    }

    @Override
    public synchronized void transfer(Account from, Account to, double amount) {
        boolean trans = false;

        double fromSum = from.getAmount() - amount;
        double toSum = to.getAmount() + amount;
        if (fromSum < 0) {
            throw new RuntimeException("Not enough money");
        }
        from.setAmount(fromSum);
        to.setAmount(toSum);

        while (!trans) {
            service.update(from);
            service.update(to);

            trans = true;
        }


    }

}
