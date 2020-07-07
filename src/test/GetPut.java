package test;

public class GetPut {
    String full="";

public String str_full (){
    return full;
}

public String getPart(int i) {
    String [] full_in_part=full.split("_");
    return full_in_part[i];
}
public String putPart (String clFROM, String clTO, int i){
String temp = null;
    if (clFROM=="Alfa") {
         temp= new Alfa().getPart(i) ;
    }
    if (clFROM=="Beta") {
         temp= new Beta().getPart(i) ;
    }
    if (clTO=="Alfa"){ return new Alfa().str_full()+"_"+temp;

    }
    if (clTO=="Beta"){ return new Beta().str_full()+"_"+temp;

    }
return "НЕВЕРНЫЕ ДАННЫЕ";
}}
class Alfa extends GetPut{
    Alfa(){
        full="sun_run_fun";
    }


    public static void main(String[] args) {
        System.out.println(new Alfa().getPart(0));
        System.out.println(new Beta().getPart(2));
        System.out.println(new GetPut().putPart("Alfa","Beta",1));
        System.out.println(new GetPut().putPart("Beta","Beta",0));
    }
}
class Beta extends GetPut{
        Beta(){
            full="son_rom_lom";
        }}