package ru.progwards.java2.lessons.http;

import ru.progwards.java2.lessons.ATM.bankomat.src.app.model.Account;
import ru.progwards.java2.lessons.ATM.bankomat.src.app.service.StoreService;
import ru.progwards.java2.lessons.ATM.bankomat.src.app.service.impl.AccountServiceImpl;
import ru.progwards.java2.lessons.ATM.bankomat.src.app.service.impl.FileStoreService;
import ru.progwards.java2.lessons.ATM.bankomat.src.app.service.impl.StoreServiceImpl;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class ServerThread extends Thread {
    Socket incoming;
    String[] reqArr = {"null", "null", "null", "null"};
    String html;


    @Override
    public void run() {
        try {
            process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process() throws IOException {
        var is = incoming.getInputStream();


        BufferedReader reader = new BufferedReader(new
                InputStreamReader(is));

        System.out.println(reader.ready());

        String str = "";
        str = reader.readLine();
        System.out.println(str);


        String tmp2 = str.split(" ")[1].replace("/?", "").replace("&", "=");

        int i = 0;
        for (String s : tmp2.split("=")) {
            reqArr[i++] = s;
        }


        for (String s : reqArr) {
            System.out.println(s);
        }

        answer(operation());
    }

    private void answer(String answer) {
        System.out.println("answer");
        try {

            OutputStream os = incoming.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));


            html = "<!DOCTYPE html><html><body><br><br><h1>" +
                    answer +
                    "</h1></body></html>";

            writer.write("HTTP/1.1 200 OK\n" +
                    "Content-Type: text/html; charset=utf-8\n" +
                    "Content-Length:\n" +
                    "\n" +
                    html +
                    "\r\n");


            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String operation() {

        StoreService storeService = new FileStoreService();
        AccountServiceImpl accountService = new AccountServiceImpl(storeService);

        try {


            switch (reqArr[0]) {
                case "balance":
                    if (!reqArr[1].equals("null") && reqArr[2].equals("null") && reqArr[3].equals("null")) {
                        Double bal = accountService.balance(storeService.get(reqArr[1]));
                        return "Account balance ID : *" + reqArr[1].substring(33) + " = " + bal + " bucks.";
                    }
                    break;
                case "transfer":
                    if (!reqArr[1].equals("null") && !reqArr[2].equals("null") && !reqArr[3].equals("null")) {
                        Account from = storeService.get(reqArr[2]);

                        System.out.println("from =" + from);
                        accountService.transfer(from, storeService.get(reqArr[3]), Double.parseDouble(reqArr[1]));
                        return "Successful transfer of" + reqArr[1] + " from ID : *" + reqArr[2].substring(33) + " to ID : *" + reqArr[3].substring(33) + ".";
                    }
                    break;
                case "deposit":
                    if (!reqArr[1].equals("null") && reqArr[2].equals(reqArr[3])) {
                        accountService.deposit(storeService.get(reqArr[2]), Double.parseDouble(reqArr[1]));
                        return "Successful deposit of" + reqArr[1] + " to ID : *" + reqArr[2].substring(33) + ".";
                    }
                    break;
                case "withdraw":
                    if (!reqArr[1].equals("null") && reqArr[2].equals(reqArr[3])) {
                        accountService.withdraw(storeService.get(reqArr[2]), Double.parseDouble(reqArr[1]));
                        return "Successful withdraw of" + reqArr[1] + " from ID : *" + reqArr[2].substring(33) + ".";
                    }
                    break;
                default:
                    return "Unsuccessful operation. Check details";
            }
        } catch (Exception e) {
            // e.printStackTrace();
            return e.getMessage();
        }

        return "Unsuccessful operation. Check details";
    }

    public ServerThread(Socket incoming) throws SocketException {
        this.incoming = incoming;


        // System.out.println(incoming.getReceiveBufferSize());


    }


}