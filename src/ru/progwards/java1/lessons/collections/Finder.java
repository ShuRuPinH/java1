package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Finder {

    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {
        ArrayList<Integer> arl = new ArrayList<>(numbers);
        Integer tempsum = 0;
        List<Integer> res = new ArrayList<>();

        for (ListIterator<Integer> itr = arl.listIterator(); itr.hasNext(); ) {
            Integer temp1 = itr.next();
            int ind1 = itr.previousIndex();
            if (itr.hasNext()==false)break;
            Integer temp2 = itr.next();
            int ind2 = itr.previousIndex();
           // System.out.println("int 1,2:"+temp1+","+temp2+"      ind 1,2:"+ind1+","+ind2);

            if (temp1 + temp2 < tempsum || tempsum == 0) {
                tempsum = temp1 + temp2;

                res.clear();
                res.add(ind1);
                res.add(ind2);
            }
            itr.previous();
        }
        return res;
    }

    public static Collection<Integer> findLocalMax(Collection<Integer> numbers) {
        ArrayList<Integer> arl = new ArrayList<>(numbers);

        List<Integer> res = new ArrayList<>();

        for (ListIterator<Integer> itr = arl.listIterator(); itr.hasNext(); ) {
            Integer temp1 = itr.next();
            if (itr.hasNext() == false) break;
            Integer temp2 = itr.next();
            if (itr.hasNext() == false) break;
            Integer temp3 = itr.next();
            if (temp1 < temp2 && temp2 > temp3) {
                res.add(temp2);
            }

            itr.previous();
            itr.previous();
        }
        return res;
    }

    public static boolean findSequence(Collection<Integer> numbers) {
        ArrayList<Integer> arl = new ArrayList<>(numbers);
        for (int i = 1; i <= numbers.size(); i++) {
          arl.add(i);
        }

        return numbers.containsAll(arl);
    }




    public static String findSimilar(Collection<String> names){
        List <String> arl = new ArrayList(names);
        String temp="stop"; String maxTemp=null;
        int count=1; int maxCount=0;


        for (ListIterator<String> itr = arl.listIterator(); itr.hasNext(); ) {
            String t = itr.next();
          if(t.equals(temp) || t.equals("stop")) count++; else count=1;
          if (count>maxCount){maxCount=count; maxTemp=temp;}
        temp=t;

    }return maxTemp+":" +maxCount;}


    public static void main(String[] args) {
        List<Integer> ll = new ArrayList();
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            ll.add((i + 0));
        }

        System.out.println(ll.toString());
        System.out.println(findLocalMax(ll));
        System.out.println(findSequence(ll));


        List <Integer> ls= new ArrayList();
        boolean i =true;
        while (i){

            try {
                System.out.println("arr string:");
                Integer t=in.nextInt();

                if (t.equals("stop")){
                    i=false;break;
                } else ls.add(t);
            } catch (Exception e) {


                break;
            }}
        System.out.println(findMinSumPair(ls));

    }
}//60,87,72,34,29,-99,-26,-5,89,8,-47
