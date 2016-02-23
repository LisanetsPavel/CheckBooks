package checkbooks.dao;


import checkbooks.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class BookDaoImpl implements BookDao {


    private static final String GET_BOOK_BY_ID = "Select * from books where book_id = ?";
    private static final String GET_BOOK_BY_COR_STATE = "Select * from books where cor_state_id = ?";
    private static final String UPDATE_DESC = "update books set description = ? , checked = true where book_id = ?";
    private static final String GET_GOOD_BOOK = "select * from books where checked = true and description like 'GOOD%' and cor_state_id = '10'  order by book_id ";
    private static final String GET_NON_CHECKED_BOOK = "select * from books where checked = false and cor_state_id = '10' order by page_count desc";
    private static final String GET_DESC_FAIL_BOOK = "select * from books where checked = true and description != 'CUT' and cor_state_id != 20 and description not like 'GOOD%'order by book_id";
    private static final String UPDATE_CORE_STATE = "update books set cor_state_id = ? where book_id = ?";

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public Book getBook(int id) {
        return jdbcTemplate.queryForObject(GET_BOOK_BY_ID, new BookRowMapper(), id);
    }

    public List<Book> getBookByState(int state) {
        return jdbcTemplate.query(GET_BOOK_BY_COR_STATE, new BookRowMapper(), state);
    }

    public void setDescription(String desc, int id) {
        jdbcTemplate.update(UPDATE_DESC, desc, id);
    }

    public List<Book> getBooksGood() {
        return jdbcTemplate.query(GET_GOOD_BOOK, new BookRowMapper());
    }

    public List<Book> getBooksNonChecked() {

        return jdbcTemplate.query(GET_NON_CHECKED_BOOK, new BookRowMapper());
    }

    public List<Book> getDescBooksFail() {
        return jdbcTemplate.query(GET_DESC_FAIL_BOOK, new BookRowMapper());
    }

    public void setCoreState (int coreState, int bookId){
            jdbcTemplate.update(UPDATE_CORE_STATE, coreState, bookId);
    }


    private class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getInt("book_id"),
                    rs.getString("ename"),
                    rs.getString("eyear"),
                    rs.getString("author"),
                    rs.getString("cor_state_id"),
                    rs.getString("description"),
                    rs.getString("page_count"));
        }
    }
}
