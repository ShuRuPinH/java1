package test;

public class BiTt {
 static int ltEnd(byte value){
     int result= value & 1;
return result;
 }

    public static void main(String[] args) {
        System.out.println(ltEnd((byte) 81));
        System.out.println(ltEnd((byte) 1));
        System.out.println(ltEnd((byte) 2));
    }
}
