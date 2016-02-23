package checkbooks.dao;

import checkbooks.entity.Book;

import java.util.List;

/**
 * Created by pc8 on 05.08.15.
 */
public interface BookDao {

    Book getBook(int id);

    List<Book> getBookByState(int state);

    void setDescription(String desc, int id);

    List<Book> getBooksGood();

    List<Book> getBooksNonChecked();

    List<Book> getDescBooksFail();

    void setCoreState(int coreState, int bookId);


}
