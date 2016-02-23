package checkbooks.service;

import checkbooks.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class BooksCache {

    private List<Book> books = new CopyOnWriteArrayList<>();

    public void add(Book book) {
        books.add(book);
       }

    public void remove(Book book) {
        books.remove(book);
    }

    public List<Book> getProcessed() {

        return new ArrayList<>(books);

    }

    public Book get(long id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

}
