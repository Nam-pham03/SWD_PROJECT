package controller.sync;

import dto.AccountDTO;
import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AccountService;
import service.CustomerService;
import service.impl.AccountServiceImpl;
import service.impl.CustomerServiceImpl;
import util.SecurityUtils;

@WebServlet(name = "RegistrationController", urlPatterns = {"/RegistrationController"})
public class RegistrationController extends HttpServlet {

    private static final String MSG_ERROR = "MSG_ERROR";
    private static final String MSG_SUCCESS = "MSG_SUCCESS";

    private final AccountService accountService;
    private final CustomerService customerService;

    public RegistrationController() {
        this.accountService = new AccountServiceImpl();
        this.customerService = new CustomerServiceImpl();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("registrationPage.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = SecurityUtils.hashMd5(request.getParameter("password"));
        String birthdateStr = request.getParameter("birthdate");
        String address = request.getParameter("address");
        boolean isMale = Boolean.parseBoolean(request.getParameter("gender"));
        int gender = isMale ? 1 : 0;

        try {
            Date birth = Date.valueOf(birthdateStr);

            AccountDTO accountDTO = accountService.checkExistedAccount(email);

            if (accountDTO != null) {
                request.setAttribute(MSG_ERROR, "Tài khoản đã tồn tại! Vui lòng đăng nhập bằng email này.");
                
                request.getRequestDispatcher("registrationPage.jsp").forward(request, response);
            } else {
                AccountDTO acc = accountService.insertAccount(email, password, name, phone, 1, 0, address, birth, gender);
                boolean check = customerService.insertCustomer(acc.getAccount_id(), 0);

                if (check) {
                    request.setAttribute(MSG_SUCCESS, "Đăng ký tài khoản thành công!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(MSG_ERROR, "Đã xảy ra lỗi trong quá trình đăng ký, vui lòng thử lại.");
            request.getRequestDispatcher("registrationPage.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
