package checkbooks.service;


import checkbooks.dao.EmployeeHibDaoImpl;

/**
 * Created by pavel on 18.05.15.
 */
public class Factory {

    private static EmployeeHibDaoImpl studentDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public EmployeeHibDaoImpl getEmployeeDAO(){
        if (studentDAO == null){
            studentDAO = new EmployeeHibDaoImpl();
        }
        return studentDAO;
    }
}
