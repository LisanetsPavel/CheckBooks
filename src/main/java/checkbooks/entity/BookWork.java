package checkbooks.entity;


import checkbooks.service.MyDate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pavel on 18.05.15.
 */

@Entity
@Table(name = "book_work")
public class BookWork {

    private Employee employee;

    private Integer bookId;
    private Integer employeeId;
    private Date edate;
    private Integer bookSize;
    private Integer countPageOfEmployee;


    public BookWork() {

    }

    public BookWork(Integer bookId, Integer employeeId, Date edate, Integer bookSize, Employee employee) {
        this.bookId = bookId;
        this.employeeId = employeeId;
        this.edate = edate;
        this.bookSize = bookSize;
        this.employee = employee;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    public Employee getEmployee() {
        return employee;
    }


    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "book_id")
    public Integer getBookId() {
        return bookId;
    }

    @Column(name = "employee_id")
    public Integer getEmployeeId() {
        return employeeId;
    }

    @Column(name = "edate")
    public Date getEdate() {

        return new MyDate(edate);
    }

    @Column(name = "booksize")
    public Integer getBookSize() {
        return bookSize;
    }


    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public void setBookSize(Integer bookSize) {
        this.bookSize = bookSize;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCountPageOfEmployee(Integer countPageOfEmployee) {
        this.countPageOfEmployee = countPageOfEmployee;
    }


    public Integer getCountPageOfEmployee() {

        countPageOfEmployee = employee.getCountPageByDate(edate);
        return countPageOfEmployee;
    }

    public Integer getCountPageOfEmployee(Date dateStart, Date dateEnd) {

        countPageOfEmployee = employee.getCountPageByDate(dateStart, dateEnd);
        return countPageOfEmployee;
    }

    @Override
    public String toString() {
        return "BookWork{" +
                "employee=" + employee.getEname() +
                ", countPage=" + employee.getCountPageByDate(edate) +
                ", bookId=" + bookId +
                ", employeeId=" + employeeId +
                ", edate='" + edate + '\'' +
                ", bookSize=" + bookSize +

                '}';
    }
}
