package ru.progwards.java2.lessons.ATM.bankomat.src.app.service.impl;

import ru.progwards.java2.lessons.ATM.bankomat.src.app.Store;
import ru.progwards.java2.lessons.ATM.bankomat.src.app.model.Account;
import ru.progwards.java2.lessons.ATM.bankomat.src.app.service.AccountService;
import ru.progwards.java2.lessons.ATM.bankomat.src.app.service.StoreService;

import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StoreServiceImpl implements StoreService {


    Lock lock = new ReentrantLock();


    @Override
    public Account get(String id) {
        Account account = Store.getStore().get(id);
        if (account == null) {
            throw new RuntimeException("Account not found by id:" + id);
        }
        return account;
    }

    @Override
    public Collection<Account> get() {
        if (Store.getStore().size() == 0) {
            throw new RuntimeException("Store is empty");
        }
        return Store.getStore().values();
    }

    @Override
    public void delete(String id) {
        lock.lock();
        if (Store.getStore().get(id) == null) {
            throw new RuntimeException("Account not found by id:" + id);
        }
        Store.getStore().remove(id);
        lock.unlock();
    }

    @Override
    public void insert(Account account) {
        lock.lock();
        Store.getStore().put(account.getId(), account);
        lock.unlock();
    }

    @Override
    public void update(Account account) {
        lock.lock();
        if (Store.getStore().get(account.getId()) == null) {
            throw new RuntimeException("Account not found by id:" + account.getId());
        }
        this.insert(account);
        lock.unlock();
    }
}