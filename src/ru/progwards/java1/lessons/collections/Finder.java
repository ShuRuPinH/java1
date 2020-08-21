package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Finder {
    /*
    Задача 2, класс Finder

2.1 Реализовать метод
public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) - найти 2 соседних числа в коллекции сумма
 которых минимальна, вернуть коллекцию, содержащую индексы этих чисел
*/
    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {
        ArrayList<Integer> arl = new ArrayList<>(numbers);
        Integer tempsum = 0;
        List<Integer> res = new ArrayList<>();

        for (ListIterator<Integer> itr = arl.listIterator(); itr.hasNext(); ) {
            Integer temp1 = itr.next();
            int ind1 = itr.previousIndex();

            Integer temp2 = itr.next();
            int ind2 = itr.previousIndex();

            if (temp1 + temp2 < tempsum || tempsum == 0) {
                tempsum = temp1 + temp2;
                res.clear();
                res.add(ind1);
                res.add(ind2);
            }
        }
        return res;
    }

    /*
2.2 Реализовать метод
public static Collection<Integer> findLocalMax(Collection<Integer> numbers) - найти локальные максимумы - числа, которые
 больше соседа справа и слева. Первый и последний элемент коллекции не может являться локальным  максимумом,
  вернуть коллекцию, содержащую значения этих максимумов*/
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


    /*
    2.3 Реализовать метод
    public static boolean findSequence(Collection<Integer> numbers) - проверить, содержит ли коллекция все числа от 1 до size(),
     порядок может быть произвольный
    */
    public static boolean findSequence(Collection<Integer> numbers) {
        ArrayList<Integer> arl = new ArrayList<>(numbers);


        boolean test = true;
        for (ListIterator<Integer> itr = arl.listIterator(); itr.hasNext(); ) {
            Integer temp = itr.next();

            for (int i = 1; i <= numbers.size(); i++) {
                if (temp == i) {
                    test = true;
                    break;
                }
                test = false;
            }
            if (test == false) break;

        }
        return test;
    }


    /*
2.4 Реализовать метод

public static String findSimilar(Collection<String> names) - найдите максимальное количество повторяющихся подряд элементов.
Результат вернуть в виде строки <элемент>:<количество>, например Василий:5.
При равенстве максимального количества у разных повторяющихся элементов, вернуть результат для элемента,
повторяющаяся последовательность которого началась с наименьшего индекса.
     */

    public static String findSimilar(Collection<String> names){
        List <String> arl = new ArrayList(names);
        String temp="stop"; String maxTemp=null;
        int count=1; int maxCount=0;


        for (ListIterator<String> itr = arl.listIterator(); itr.hasNext(); ) {
            String t = itr.next();
          if(t.equals(temp)) count++; else count=1;
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


        List <String> ls= new ArrayList();
        boolean i =true;
        while (i){

            try {
                System.out.println("arr string:");
                String t=in.nextLine();

                if (t.equals("stop")){
                    i=false;break;
                } else ls.add(t);
            } catch (Exception e) {


                break;
            }}
        System.out.println(findSimilar(ls));

    }
}
