package ru.progwards.java2.lessons.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerBank {

    void startSB() {
        //Установка сокета на стороне сервера


        try (ServerSocket s = new ServerSocket(8777)) {
            //Ожидание подключения клиента

            while (true) {
                System.out.println("server wait");
                try {
    /* Runnable r = new ServerThread(s.accept());
                      Thread t1 = new Thread(r);
                      t1.start();*/
                    new ServerThread(s.accept()).start();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new ServerBank().startSB();
    }

}

