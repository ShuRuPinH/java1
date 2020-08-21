package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Collection {

    public List<Integer> listAction(List<Integer> list) {

        list.remove(Collections.min(list));
        list.add(0, list.size());
        list.add(2,Collections.max(list));


        return list;

    }

/*
������� ����������� ������� ���������
�� ������� 0 ��������� ����� ������ ���������� ���������
�� ������� 2 ��������� ������������ ������� �� list
���������� list ��� ��������� ������
 */

    /*
    ��������� �������� ���� ��������� ������
������� �� ������ ��������, �������� ������� ������ �����, �������� �� 100 (������������� �������)
���������� �������������� ������
     */
    public List<Integer> filter(List<Integer> list){
        long sum=0;
        for (int i=0 ; i< list.size(); i++){
            sum +=list.get( i);
        }
        sum/=100;
        List <Integer> fltr= new ArrayList();
        for (int i=0 ; i< list.size(); i++){
            if (list.get( i)>sum) fltr.add(list.get( i));
        }
        list.removeAll(fltr);
        return  list;
    }
/*
�������� ����� � ���������� public void iterator3(ListIterator<Integer> iterator)
������� �������� �������� ������� ��������,
 ������� ������ 3 �� �������� ��� �������.

 */
public void iterator3(ListIterator<Integer> iterator){
    for (ListIterator<Integer> it = iterator; it.hasNext(); ) {
        Integer i = iterator.next();
        if (i%3==0 && i!=0){
        it.set(iterator.previousIndex());
        }

    }
}

    public static void main(String[] args) {
        Collection test = new Collection();
        List<Integer> ll = new ArrayList();
        for (int i = 0; i < 10; i++) {
            ll.add(i -1);
        }
        System.out.println(ll.toString());
        test.iterator3(ll.listIterator());
        System.out.println(ll.toString());




    }
}