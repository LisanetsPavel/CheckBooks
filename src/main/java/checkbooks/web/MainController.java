package checkbooks.web;

import checkbooks.entity.Book;
import checkbooks.service.BookService;
import checkbooks.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {


    @Value("${items.on.page}")
    private int itemsOnPage;

    @Value("${workers}")
    private String workers;

    @Autowired
    private BookService bookService;
    @Autowired
    private EmployeeService employeeService;

    private static final Logger logger = Logger.getLogger(Book.class);

    @RequestMapping(value = "/viewAllPage")
    public String viewAllPage(@RequestParam(value = "bookId", required = true) int bookId,
                              @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                              Model map, HttpSession session) {
        if (page < 0) {
            page = 0;
        }
        bookService.setProcessing(bookId, true);
        Book book = bookService.getBook(bookId);
        boolean check = false;
        session.setAttribute("bookId", String.valueOf(bookId));
        List<String> firstPageList = new ArrayList<>();
        List<String> pageList = new ArrayList<>(book.getPageSet());
        int start = page * itemsOnPage;
        int end = itemsOnPage * (page + 1);
        for (int i = start; i < end && i < pageList.size(); i++) {
            firstPageList.add(pageList.get(i));
            if (i == pageList.size() - 1) {
                check = true;
            }
        }

        List<String> listWorkers = bookService.parseWorkers(workers);
        

        map.addAttribute("listWorkers", listWorkers);
        map.addAttribute("pageList", firstPageList);
        map.addAttribute("bookId", book.getId());
        map.addAttribute("nextPage", page + 1);
        map.addAttribute("check", check);
        map.addAttribute("countPage", pageList.size());
        return "book";

    }

    @RequestMapping(value = "/fail")
    public String viewFail(
            @RequestParam(value = "bookId", required = true) Integer bookId,
            @RequestParam(value = "desc", required = true) String desc,
            @RequestParam(value = "worker", required = true) Integer worker,
            Model map) {

        employeeService.setWork(bookId, worker);
        bookService.setDescription(desc, bookId);
        viewReport(map);
        return "report";
    }

    @RequestMapping(value = "/good")
    public String viewGood(@RequestParam(value = "bookId", required = true) Integer bookId,
                           @RequestParam(value = "worker", required = true) Integer worker,
                           @RequestParam(value = "desc", required = true) String desc,
                           Model map) {

        bookService.setDescription("GOOD | " + desc , bookId);
        employeeService.setWork(bookId, worker);
        viewReport(map);
        return "report";
    }

    @RequestMapping(value = {"/report", "/"})
    public String viewReport(Model map) {

        List<Book> listGood = bookService.getBooksGood();
        List<Book> listNoCheck = bookService.getBooksNonChecked();
        List<Book> listFail = bookService.getDescBooksFail();

        map.addAttribute("listGood", listGood);
        map.addAttribute("countGood", listGood.size());
        map.addAttribute("listNoCheck", listNoCheck);
        map.addAttribute("countNoCheck", listNoCheck.size());
        map.addAttribute("listFail", listFail);
        map.addAttribute("countFail", listFail.size());

        return "report";
    }


    @RequestMapping(value = "/menu")
    public String viewIndex(HttpSession session, Model map) {
        try {
            Object bookIdObj = session.getAttribute("bookId");
            if (bookIdObj != null) {
                int bookId = Integer.valueOf(bookIdObj.toString());
                bookService.setProcessing(bookId, false);
            }
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        viewReport(map);
        return "report";
    }

    @RequestMapping(value = "/index")
    public String select() {
        return "index";
    }

}
