package test;
import java.awt.*;
import javax.swing.*;
public class Frame {


    public static void openFrame() {
        /* Задание заголовка окна*/
        JFrame w=new JFrame("Окно с изображением");
        /*Задание размеров окна*/
        w.setSize(400, 400);

        /* 	Если у окна не будет функции закрытия,
         *	при нажатии крестика окно не закроется.*/
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setVisible(true);
        
    }


    // enter
    public static void main(String[] args) {
       openFrame();

    }
}