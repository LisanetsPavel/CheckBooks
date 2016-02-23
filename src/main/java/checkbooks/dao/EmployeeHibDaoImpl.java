package checkbooks.dao;


import checkbooks.entity.BookWork;
import checkbooks.entity.Employee;
import checkbooks.service.DateService;
import checkbooks.service.HibernateService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by pavel on 18.05.15.
 */
public class EmployeeHibDaoImpl extends HibernateDaoSupport implements EmployeeHibDao {


    public Employee getEmployee(Integer id) {
        return (Employee) getSession().get(Employee.class, id);
    }

    private static final Logger logger = Logger.getLogger(EmployeeHibDaoImpl.class);

    @Override
    protected HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
        HibernateTemplate result = super.createHibernateTemplate(sessionFactory);
        result.setAllowCreate(false);
        return result;
    }


    public List<BookWork> getBookWorkByDate(String date) {

        List<BookWork> employeeList = null;
        Date dateConverted = new DateService().convertDateForHiber(date);

        try (CloseableSession closeableSession = new CloseableSession(HibernateService.createSessionFactory().openSession())) {
            employeeList = closeableSession.delegate().createCriteria(BookWork.class).add(Restrictions.eq("edate", dateConverted)).addOrder(Order.asc("employeeId")).list();
        } catch (Exception e) {
            logger.error(e);
        }

        return employeeList;
    }


    public List<BookWork> getBookWorkByDate(String dateStart, String dateEnd) {

        List<BookWork> employeeList = null;

        Date dateStartConverted = new DateService().convertDateForHiber(dateStart);
        Date dateEndConverted = new DateService().convertDateForHiber(dateEnd);

        try (CloseableSession closeableSession = new CloseableSession(HibernateService.createSessionFactory().openSession())) {
            employeeList = closeableSession.delegate().createCriteria(BookWork.class).add(Restrictions.between("edate", dateStartConverted, dateEndConverted)).addOrder(Order.asc("employeeId")).list();
        } catch (Exception e) {
            logger.error(e);
        }

        return employeeList;
    }


}
