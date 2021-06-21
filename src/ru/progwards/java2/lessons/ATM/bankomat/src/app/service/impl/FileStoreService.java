package ru.progwards.java2.lessons.ATM.bankomat.src.app.service.impl;

import ru.progwards.java2.lessons.ATM.bankomat.src.app.model.Account;

import ru.progwards.java2.lessons.ATM.bankomat.src.app.service.StoreService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileStoreService implements StoreService {

    final Path path = Paths.get("src/ru/progwards/java2/lessons/ATM/bankomat/store.csv");
    private static Map<String, Account> store;
    ReadWriteLock lock = new ReentrantReadWriteLock();


    /////// R E A D //////////////////
    void readStore() {
        store = new HashMap<>();

        try {
            lock.readLock().lock();
            List<String> storeList = Files.readAllLines(path);
            lock.readLock().unlock();
            for (String st : storeList) {
                String[] acc = st.split(",");
                Account account = new Account();

                account.setId(acc[0]);
                account.setHolder(acc[1]);
                account.setDate(Date.from(Instant.parse(acc[2])));
                account.setAmount(Double.parseDouble(acc[3]));
                account.setPin(Integer.parseInt(acc[4]));

                store.put(account.getId(), account);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //////////  W R I T E  /////////////////////////////////
    void writeStore() {
        lock.writeLock().lock();
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (Map.Entry ent : store.entrySet()) {
            Account acc = (Account) ent.getValue();
            try {
                Files.writeString(path, acc.getId() + "," + acc.getHolder() + "," + acc.getDate().toInstant() + "," + acc.getAmount() +
                        "," + acc.getPin() + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        lock.writeLock().unlock();
    }


/////////////////////////////////////////////////////////


    @Override
    public Account get(String id) {

        readStore();

        Account account = store.get(id);
        if (account == null) {
            throw new RuntimeException("Account not found by id:" + id);
        }
        return account;
    }

    @Override
    public Collection<Account> get() {
        readStore();
        if (store.size() == 0) {
            throw new RuntimeException("Store is empty");
        }
        return store.values();
    }

    @Override
    public void delete(String id) {
        readStore();
        if (store.get(id) == null) {
            throw new RuntimeException("Account not found by id:" + id);
        }
        store.remove(id);
        writeStore();
    }

    @Override
    public void insert(Account account) {
        readStore();
        store.put(account.getId(), account);
        writeStore();
    }

    @Override
    public void update(Account account) {
        readStore();
        if (store.get(account.getId()) == null) {
            throw new RuntimeException("Account not found by id:" + account.getId());
        }
        store.put(account.getId(), account);
        writeStore();
    }


    ///////////////////////////  M A I N ////////////////////

    public static void main(String[] args) {
        FileStoreService tt = new FileStoreService();
        AccountServiceImpl aa = new AccountServiceImpl(tt);

/*        for (int i = 0; i < 10; i++) {
            Account acc = new Account();
            String id = UUID.randomUUID().toString();
            acc.setId(id);
            acc.setPin(1000 + i);
            acc.setHolder("Account_" + i);
            acc.setDate(new Date(System.currentTimeMillis() + 365 * 24 * 3600 * 1000));
            acc.setAmount(Math.random() * 1_000_000);

            tt.store.put(acc.getId(), acc);
        }*/

        Account acc = tt.get("d375cb4d-6fba-4892-a395-aed01b2c74bd");

        String yuyu = String.valueOf(acc.getAmount());

        System.out.println(yuyu);

        aa.withdraw(acc, 5);


        yuyu = String.valueOf(tt.get("d375cb4d-6fba-4892-a395-aed01b2c74bd").getAmount());
        System.out.println(yuyu);
    }


}
