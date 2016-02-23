package checkbooks.service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pavel on 21.05.15.
 */
public class MyDate extends Date {
    Date date;

    public MyDate() {
    }

    public MyDate(Date date) {
        this.date = date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public long getTime() {
        return date.getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyDate)) return false;


        MyDate myDate = (MyDate) o;

        if (myDate.getTime() == this.getTime()) return  true;
        else return false;

    }

    @Override
    public int hashCode() {
      return super.hashCode();
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date);
    }
}
