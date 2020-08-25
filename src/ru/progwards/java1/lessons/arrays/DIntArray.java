package ru.progwards.java1.lessons.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DIntArray {
    private int[] mas={};

    public DIntArray() {
    }

    public void add(int num){
        mas=Arrays.copyOf(mas, mas.length+1);
        mas[mas.length-1]=num;
    }

    public void atInsert(int pos, int num){
        int [] temp=new int[mas.length+1];
        System.arraycopy(mas, 0, temp, 0, pos);
        temp[pos]=num;
//        System.out.println(pos+","+num+"-ins1="+Arrays.toString(temp));
        System.arraycopy(mas, pos, temp, pos+1, temp.length-(pos+1));
//        System.out.println("    ins2="+Arrays.toString(temp));
        mas=temp;
    }

    public void atDelete(int pos){
        int [] temp=new int[mas.length-1];
        System.arraycopy(mas, 0, temp, 0, pos);
//        System.out.println(pos+"-del1="+Arrays.toString(temp));
        System.arraycopy(mas, pos+1, temp, pos, temp.length-(pos));
//        System.out.println("  del2="+Arrays.toString(temp));
        mas=temp;
    }
    public int at(int pos){

        return mas[pos];
    }



/*    public String prnt(){
        return Arrays.toString(mas);
    }  */

    public static void main(String[] args) {
        DIntArray test= new DIntArray();
        test.add(0);
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);

        int [] fd= new int[3];

   //     System.out.println(test.prnt());
        test.atInsert(2,8);
        test.atInsert(2,8);

        test.atDelete(6);
   //     System.out.println("end="+test.prnt());

        System.out.println(Arrays.toString(test.mas));
    }

}
