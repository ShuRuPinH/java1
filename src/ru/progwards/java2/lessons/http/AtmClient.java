package ru.progwards.java2.lessons.http;


import ru.progwards.java2.lessons.ATM.bankomat.src.app.model.Account;
import ru.progwards.java2.lessons.ATM.bankomat.src.app.service.AccountService;
import ru.progwards.java2.lessons.ATM.bankomat.src.app.service.impl.FileStoreService;


import java.net.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Deque;
import java.util.Scanner;


public class AtmClient implements AccountService {
    @Override
    public double balance(Account account) {
        send("balance",account.getId(),"null","null");


        return 0;
    }

    @Override
    public void deposit(Account account, double amount) {
        send("deposit",String.valueOf(amount),account.getId(),account.getId());
    }

    @Override
    public void withdraw(Account account, double amount) {
        send("withdraw",String.valueOf(amount),account.getId(),account.getId());
    }

    @Override
    public void transfer(Account from, Account to, double amount) {
        send("transfer",String.valueOf(amount),from.getId(),to.getId());
    }

    static String serverName = "192.168.0.145";  //  SERVER IP
    static int port = 8777;  // SERVER PORT


    public static void send(String fst, String snd, String thrd, String four) {


        try {  //Thread.sleep(7*60*1000);
            System.out.println("Connecting to " + serverName
                    + " on port " + port + " at : " + LocalDateTime.now());
            Socket client = new Socket(serverName, port);

            OutputStream outToServer = client.getOutputStream();
            InputStream inToClient = client.getInputStream();


            DataOutputStream out =
                    new DataOutputStream(outToServer);
            String temp = "GET /?" + fst + "=" + snd + "&" + thrd + "=" + four + " HTTP/1.1\n" +
                    "hostname: localhost\n" +
                    "\n" +
                    "\r\n";

            out.writeUTF(temp);


            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(inToClient));

            String str = "";

            reader.lines().forEach(x -> System.out.println(x));
            ;


            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileStoreService tt = new FileStoreService();
        AtmClient atmClient = new AtmClient();

        atmClient.transfer(tt.get("56702b2e-4a67-4fbc-b90f-a177ce21a437"),tt.get("a7674132-f472-4e3c-ab2e-5dd79d6b2b5b"),25000);

      //  send("transfer", "478288.35431955743d", "56702b2e-4a67-4fbc-b90f-a177ce21a437",
       //         "a7674132-f472-4e3c-ab2e-5dd79d6b2b5b");


    }


}

