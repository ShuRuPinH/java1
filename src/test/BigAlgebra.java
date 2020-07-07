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
        BigAlgebra cadabra = new BigAlgebra();
        System.out.println(cadabra.fastPow(BigDecimal.valueOf(3), 2).intValue() == 9); // true
        System.out.println(cadabra.fastPow(BigDecimal.valueOf(2), 1).intValue() == 2);  // true
        System.out.println(cadabra.fastPow(BigDecimal.valueOf(2), 2).intValue() == 4); // true
    }
}