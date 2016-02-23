package checkbooks.service;

import checkbooks.dao.BookDaoImpl;
import checkbooks.entity.Book;
import checkbooks.entity.ClientPageData;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class BookService {

    private static final int FOLDER_TEN = 10;
    private static final int FOLDER_TWENTY = 20;

    @Autowired
    private BookDaoImpl bookDao;
    @Autowired
    private DownloadingService downloadingService;
    @Autowired
    private FileService fileService;
    @Autowired
    private BooksCache booksCache;
    @Autowired
    FtpService ftpService;


    @Value("${limit}")
    private String LIMIT;

    private static Log log = LogFactory.getLog(BookService.class.getName());

    private static Map<Integer, Integer> countesPageMap = new HashMap<>();

    public List<Book> getListBook(int corState) {
        return bookDao.getBookByState(corState);
    }

    public Book getBook(int bookId) {
        Book book = booksCache.get(bookId);
        if (book != null) {
            return book;
        }
        return bookDao.getBook(bookId);
    }

    public void setDescription(String desc, int id) {
        bookDao.setDescription(desc, id);
        setProcessing(id, false);
    }

    public List<Book> getBooksNonChecked() {

        List<Book> nonChecked = removeOverLimit(bookDao.getBooksNonChecked());
        nonChecked.removeAll(booksCache.getProcessed());

        return nonChecked;
    }

    public List<Book> getDescBooksFail() {
        List<Book> fails = bookDao.getDescBooksFail();
        fails.removeAll(booksCache.getProcessed());
        return fails;
    }

    public List<Book> getBooksGood() {
        List<Book> good = bookDao.getBooksGood();
        good.removeAll(booksCache.getProcessed());
        return good;
    }

    public void setProcessing(int id, boolean proc) {
        Book book = booksCache.get(id);
        if (proc) {

            if (book == null) {

                book = bookDao.getBook(id);
                booksCache.add(book);
                book.getPageSet().addAll(downloadingService.getBookPages(id, book.getCorState()));
            }
        } else {
            fileService.deleteFiles(id);
            booksCache.remove(book);
        }
    }

    public ClientPageData getClientPageData(Integer bookId, Integer page) throws IOException {
        int countPage;
        try (InputStream inputStream = downloadingService.getInput(String.valueOf(bookId), String.valueOf(page))) {
            if (countesPageMap.containsKey(bookId)) {
                countPage = countesPageMap.get(bookId);
            } else {
                countPage = downloadingService.getCountFileImg();
            }

            byte[] bytes = IOUtils.toByteArray(inputStream);
            String base64String = Base64.getEncoder().encodeToString(bytes);
            return new ClientPageData(bookId, countPage, base64String);
        }
    }

    public List<String> parseWorkers(String workers) {
        String workersArr[] = workers.split(",");
        return Arrays.asList(workersArr);
    }

    public List<Book> removeOverLimit(List<Book> list) {
        List<Book> resultList = new ArrayList<>();
        int limit = Integer.parseInt(LIMIT);
        for (Book book : list) {
            if (Integer.parseInt(book.getPageCount()) < limit) {
                resultList.add(book);
            }
        }
        return resultList;
    }

    public void changeStateGoodBook() throws FTPException, IOException, FTPIllegalReplyException {
     List<Book> listBook = new ArrayList<>();
            listBook.addAll(getBooksGood());

      for (Book book : listBook) {

          bookDao.setCoreState(FOLDER_TWENTY, book.getId());
          ftpService.moveAllGoodBooks(FOLDER_TEN, FOLDER_TWENTY, book.getId());
        }



    }
}
