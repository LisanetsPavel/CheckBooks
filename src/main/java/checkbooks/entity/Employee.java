package checkbooks.entity;


import checkbooks.service.MyDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pavel on 18.05.15.
 */

@Entity
@Table(name = "employee")
public class Employee {

    private Set<BookWork> bookWorks = new HashSet<>();

    private Integer employeeId;
    private String ename;
    private Integer status;
    private String login;
    private String password;
    private Integer countPage;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    public Set<BookWork> getBookWorks() {
        return bookWorks;
    }


    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    @Column(name = "pass")
    public String getPassword() {
        return password;
    }

    public Employee() {
    }


    public Employee(Integer employeeId, Set<BookWork> bookWorks, Integer status, String ename, String login, String password) {
        this.employeeId = employeeId;
        this.bookWorks = bookWorks;
        this.status = status;
        this.ename = ename;
        this.login = login;
        this.password = password;
    }

    @Id
    @Column(name = "employee_id")
    public Integer getEmployeeId() {
        return employeeId;
    }

    @Column(name = "ename")
    public String getEname() {
        return ename;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountPage(Integer countPage) {
        this.countPage = countPage;
    }

    public void setBookWorks(Set<BookWork> bookWorks) {
        this.bookWorks = bookWorks;
    }


    public Integer getCountPageByDate(Date date) {
        Integer countPage = 0;

        date = new MyDate(date);

        for (BookWork bookWork : bookWorks) {
            if (date.equals(bookWork.getEdate())) {
                countPage += bookWork.getBookSize();
            }
        }

        return countPage;
    }

    public Integer getCountPageByDate(Date dateStart, Date dateEnd) {
        Integer countPage = 0;

        for (BookWork bookWork : bookWorks) {
            if (bookWork.getEdate().getTime() > dateStart.getTime() && bookWork.getEdate().getTime() < dateEnd.getTime()) {
                countPage += bookWork.getBookSize();
            }
        }

        return countPage;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", ename='" + ename + '\'' +
                ", status=" + status +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", countPage='" + countPage +
                '}';
    }


}
