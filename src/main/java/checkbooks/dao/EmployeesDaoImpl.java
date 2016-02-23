package checkbooks.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pavel on 08.07.15.
 */
@Repository
public class EmployeesDaoImpl implements EmployeesDao {

    @Autowired
    @Qualifier("employeesJdbcTemplate")
    public JdbcTemplate jdbcTemplate;

    private static final String SET_WORK = "INSERT into book_work(book_id,employee_id, edate ,sta_date ,booksize) VALUES (?, ?, CAST(? AS DATE), ? , ?)";
    private static final String UPDATE_WORK = "UPDATE book_work SET book_id = ?, employee_id = ?, edate =  CAST(? AS DATE), sta_date = ?, booksize = ? WHERE book_id = ?";

    public void setWork(int bookId, int employeeId, int pageCount) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            jdbcTemplate.update(SET_WORK, bookId, employeeId, dateFormat.format(date), timestamp, pageCount);
        } catch (DuplicateKeyException e) {
            jdbcTemplate.update(UPDATE_WORK, bookId, employeeId, dateFormat.format(date), timestamp, pageCount, bookId);
        }
    }


}
