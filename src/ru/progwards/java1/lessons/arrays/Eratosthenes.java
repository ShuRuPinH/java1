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

            for (int p=2; p<=sieve.length-1; p++){
                    if (sieve[p]==true) {
                        for (int j = 2; j <= sieve.length; j++) {
                            if (j * p < sieve.length) sieve[j * p] = false;
                        }
                    }
            }
            sieve[0]=false;
            sieve[1]=false;
            sieve[2]=true;
    }

    public boolean isSimple(int n){
        return sieve[n];
    }

    public static void main(String[] args) {
        Eratosthenes er= new Eratosthenes(5000);
        System.out.println(er.isSimple(1021));
    }

}
