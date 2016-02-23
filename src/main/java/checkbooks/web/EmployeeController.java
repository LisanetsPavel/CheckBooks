package checkbooks.web;

import checkbooks.entity.BookWork;
import checkbooks.service.EmployeeService;
import checkbooks.service.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by pavel on 28.04.15.
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    List<BookWork> listBookWork;

    @RequestMapping(value = "/date", method = RequestMethod.POST)
    public String viewDate(@RequestParam (value = "date") String date,
                           Model map){

        listBookWork = Factory.getInstance().getEmployeeDAO().getBookWorkByDate(date);
        List<List<BookWork>> listSeparate = employeeService.separateListBookWorks(listBookWork);
        List<Integer> countPages = employeeService.getCountPage(listSeparate);

        map.addAttribute("date", date);
        map.addAttribute("countPages", countPages);
        map.addAttribute("workAndEmployees", listSeparate);


       return "dateNew";
    }

    @RequestMapping(value = "/range")
    public String viewRange(@RequestParam (value = "dateStart") String dateStart,
                            @RequestParam (value = "dateEnd") String dateEnd,
                           Model map){

        listBookWork = Factory.getInstance().getEmployeeDAO().getBookWorkByDate(dateStart, dateEnd);
        List<List<BookWork>> listSeparate = employeeService.separateListBookWorks(listBookWork);
        List<Integer> countPages = employeeService.getCountPageForRange(listSeparate, dateStart, dateEnd);

        map.addAttribute("countPages", countPages);
        map.addAttribute("workAndEmployees", listSeparate);
        map.addAttribute("dateStart", dateStart);
        map.addAttribute("dateEnd", dateEnd);

        return "dateNew";
    }

    @RequestMapping(value="/select")
    public String select(){
        return "select";
    }



}
