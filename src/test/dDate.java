package test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class dDate {


  /* Напишите метод с сигнатурой Date createDate(), который возвращает дату - 28 февраля 1986,
   0 часов, 0 минут, 0 секунд, 0 миллисекунд
   */

    Date createDate() {
        Calendar n = Calendar.getInstance();

        n.setTimeInMillis(0);
        n.set(1986, 01, 28, 0, 0, 0);
        return n.getTime();
    }

    Instant createInstant() {

        ZonedDateTime res = ZonedDateTime.of(2020, 1, 1, 15, 0, 0, 1, ZoneId.of("Europe/Moscow"));
        return res.toInstant();
    }


    public static void main(String[] args) {
        dDate rr = new dDate();
        System.out.println(rr.createDate());

        SimpleDateFormat format =
                new SimpleDateFormat("'От\''езд - 'EEEE dd MMMM в Ka");
        //   "От'езд - суббота 04 января в 4PM".
        System.out.println(format.format(rr.createDate()));

        LocalDateTime ldt2 = LocalDateTime.of(2019, 05, 05, 22, 24);
        System.out.println(ldt2);

        DateTimeFormatter dtf =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss:S.n'Z'");
        /*
        Укажите формат для правильного вывода java.time.Instant стандартного вида "2020-01-04T13:21:42.173042400Z".
         */
        System.out.println(dtf.format(LocalDateTime.now()));


        SimpleDateFormat format1 =
                new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
        //   "04.01.2020 15:58:37.346"
        System.out.println(format1.format(new Date()));

        ZoneId zid1 = ZoneId.of("Europe/Moscow");
        System.out.println(zid1.getRules().getOffset(Instant.now()));
    }
}
