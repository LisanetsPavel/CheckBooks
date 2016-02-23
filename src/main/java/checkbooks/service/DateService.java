package checkbooks.service;

import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by pavel on 29.04.15.
 */

@Repository
public class DateService {

    public String convertDate(String str) {

        String[] arrStr = str.split("-");
        return arrStr[2] + "-" + arrStr[1] + "-" + arrStr[0];
    }


    public Date convertDateForHiber(String str) {

        String[] arrStr = str.split("-");
        GregorianCalendar gregorianCalendar = new GregorianCalendar(Integer.parseInt(arrStr[2]), Integer.parseInt(arrStr[1]) - 1, Integer.parseInt(arrStr[0]));

        return new Date(gregorianCalendar.getTimeInMillis());
    }


    public String convertDateForView(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date);
    }


}
