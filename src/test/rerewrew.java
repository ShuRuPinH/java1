package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class rerewrew {
    static int nu = 0;

    public rerewrew(int i) {
        this.nu = i;
    }

    void runn(Consumer<rerewrew> con) {

        con.accept(this);

    }

    String prt() {
        return String.valueOf(nu);
    }


    public static void main(String[] args) {
        rerewrew tttt = new rerewrew(1);


        ArrayList<rerewrew> test = new ArrayList();
        tttt.runn(test::add);

        for (rerewrew x : test
        ) {
            System.out.println(x.prt());

        }


    }


}

