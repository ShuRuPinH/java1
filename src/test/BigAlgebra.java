package test;
import java.math.BigDecimal;

public class BigAlgebra {
    BigDecimal fastPow(BigDecimal num, int pow) {
        if (pow == 0)
            return BigDecimal.valueOf(1);        //проверка нулевой степени
        String BYNPOW = Integer.toString(pow, 2);      //представление степени двоичным числом
        BigDecimal res = (num);             //инициализация результата НУЛЕМ
        System.out.println(res);
        for (int i = BYNPOW.length() - 1; i > 0; i--) {    //цикл перебора от СТАРШЕГО бита к МЛАДШЕМУ
            if (BYNPOW.charAt(i) == '1') {                  //считывание значения бита
                res = (res.multiply(res)).multiply(num);
            } else res = res.multiply(res);
        }
        return res;
    }

    public static void main(String[] args) {
        int r = 0b00;
        for (int i = 0; i < 10; i++) {
            r = ~r;
            System.out.println("r=" + r + "       " + Math.abs(r - 1));


        }
    }
}