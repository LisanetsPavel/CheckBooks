package checkbooks;


import checkbooks.entity.BookWork;
import checkbooks.service.EmployeeService;
import checkbooks.service.Factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by pavel on 18.05.15.
 */
public class HibernateSpringExample {



    public static void main(String[] args) {


         GregorianCalendar gregorianCalendar;
        Date date;
        gregorianCalendar = new GregorianCalendar(2015,04,18);
        date = new Date(gregorianCalendar.getTimeInMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println( simpleDateFormat.format(date));

        try {
            Date date1 = simpleDateFormat.parse("18-05-2015");
            System.out.println(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

       List<BookWork> bookWorkList  = Factory.getInstance().getEmployeeDAO().getBookWorkByDate("18-05-2015");

        System.out.println(bookWorkList);
        List<List<BookWork>> listList ;
        listList = new EmployeeService().separateListBookWorks(bookWorkList);




    }


}
