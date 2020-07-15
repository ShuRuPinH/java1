package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Eratosthenes {
     boolean[] sieve;

    public Eratosthenes(int N){
         sieve=new boolean[N+1];
        Arrays.fill(sieve, true);
        sift();
    }

    private void sift(){

            for (int i=2; i<=sieve.length-1; i++){
                    for (int j=2; j<=sieve.length; j++){

                        if (i*j <sieve.length) sieve[i*j]=false;
            }}
            sieve [0]=false;
            sieve[1]=false;
            sieve[2]=true;


    }

    public boolean isSimple(int n){
        return sieve[n];
    }



    public static void main(String[] args) {
        Eratosthenes er= new Eratosthenes(500);


        System.out.println(er.isSimple(25));

    }

}
