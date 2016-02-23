package checkbooks.service;

import checkbooks.dao.EmployeesDaoImpl;
import checkbooks.entity.Book;
import checkbooks.entity.BookWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by pavel on 09.07.15.
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeesDaoImpl employeesDao;
    @Autowired
    BookService bookService;


    public void setWork(int bookId, int employeeId) {
        Book book = bookService.getBook(bookId);
        employeesDao.setWork(bookId, employeeId, Integer.parseInt(book.getPageCount()));
    }

    public List<List<BookWork>> separateListBookWorks(List<BookWork> list) {

        List<List<BookWork>> resultList = new ArrayList<>();
        Set<Integer> set = new HashSet();
        for (BookWork bookWork : list) {
            set.add(bookWork.getEmployeeId());
        }
        for (Integer id : set) {
            List<BookWork> listTemp = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getEmployeeId().equals(id)) {
                    listTemp.add(list.get(j));
                }
            }

            resultList.add(listTemp);
        }
        return resultList;

    }

    public List<Integer> getCountPage(List<List<BookWork>> list) {

        List<Integer> countPages = new ArrayList<>();
        for (List<BookWork> bookWorkList : list)
            countPages.add(bookWorkList.get(0).getCountPageOfEmployee());
        return countPages;
    }

    public List<Integer> getCountPageForRange(List<List<BookWork>> list, String dateStart, String dateEnd) {

        Date dateStartFormatted = new DateService().convertDateForHiber(dateStart);
        Date dateEndFormatted = new DateService().convertDateForHiber(dateEnd);

        List<Integer> countPages = new ArrayList<>();
        for (List<BookWork> bookWorkList : list)
            countPages.add(bookWorkList.get(0).getCountPageOfEmployee(dateStartFormatted, dateEndFormatted));
        return countPages;
    }

}
