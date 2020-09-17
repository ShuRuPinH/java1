package test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class nio {

    String createFolder(String name) {
        String temp = name;
        Path path1 = Paths.get(temp);
        File g = new File(path1.toString());
        g.mkdir();
        path1 = path1.toAbsolutePath();
        path1 = path1.resolve("..").normalize();

        return String.valueOf(path1.getParent());
    }

    boolean replaceF(String name) {
        Path temp = Paths.get(name);
        String str = "";

        try {
            str = Files.readString(temp);
        } catch (IOException e) {
            return false;
        }

        str = str.replace('F', 'f');


        try {
            Files.writeString(temp, str);
        } catch (IOException e) {
            return false;
        }


        return true;
    }

    public static void main(String[] args) {
        nio n = new nio();

        Path path1 = Paths.get(".\\name");
        File g = new File(path1.toString());
        g.mkdir();

        path1 = path1.toAbsolutePath();
        path1 = path1.resolve("..").normalize();
        System.out.println(path1.getParent());

        System.out.println(n.replaceF("545"));

/*

Создайте метод с сигнатурой String createFolder(String name),
который создает каталог name (один уровень) в текущей папке и
возвращает полный родителя текущего каталога
 */
    }

}
