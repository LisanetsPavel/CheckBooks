package checkbooks.dao;

import checkbooks.entity.BookWork;
import checkbooks.entity.Employee;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created by pc8 on 05.08.15.
 */
public interface EmployeeHibDao {

    Employee getEmployee(Integer id);
    List<BookWork> getBookWorkByDate(String date);
    List<BookWork> getBookWorkByDate(String dateStart, String dateEnd);
}
