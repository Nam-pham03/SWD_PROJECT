package service.impl;

import static com.sun.activation.registries.LogSupport.log;
import dao.CustomerDAO;
import dao.impl.CustomerDAOImpl;


public class CustomerServiceImpl implements service.CustomerService {

    private final CustomerDAO customerDAO;

  
    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

  
    public CustomerServiceImpl() {
        this.customerDAO = new CustomerDAOImpl(); 
    }

    @Override
    public boolean insertCustomer(int account_id, int customerType) {
        boolean check = false;
        try {
           
            check = customerDAO.insertCustomer(account_id, customerType);
        } catch (Exception e) {
            log("Error at CustomerServiceImpl.insertCustomer: " + e.getMessage());
            System.err.println("Exception in insertCustomer: " + e.getMessage());
        }
        return check;
    }
}
