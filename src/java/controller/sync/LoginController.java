/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sync;

import dto.AccountDTO;
import dto.StaffDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.AccountService;
import service.StaffService;
import service.impl.AccountServiceImpl;
import service.impl.StaffServiceImpl;
import util.SecurityUtils;

/**
 *
 * @author PCASUS
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String INVALID = "invalid.jsp";
    private static final String HOME = "HomeController";
    private static final String LOGIN = "login.jsp";
    private static final String ADMIN_PAGE = "AdminManageAccountController";
    private static final int STAFF = 1;
    private static final int CUSTOMER = 0;
    private static final String USER_PAGE = "UserHomeController";
    private static final int USER = 0;
    private static final int ADMIN = 0;
    private static final int MANAGER = 1;
    private static final int SHIPPER = 2;
    private static final int SELLER = 3;

    private final AccountService accountService;
    private final StaffService staffService;

    public LoginController() {
        this.accountService = new AccountServiceImpl();
        this.staffService = new StaffServiceImpl();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = INVALID;
        try {
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            if (email == null) {
                session.setAttribute("destPage", "home");
                url = HOME;
            } else {
                String password = SecurityUtils.hashMd5(request.getParameter("password"));
                String remember = request.getParameter("remember");
                AccountDTO accountDTO = accountService.getAccount(email, password);
      
                if (accountDTO != null) {
                    if (accountDTO.getStatus() == 0) {
                        request.setAttribute("ERROR_MASSEGE", "Your account has been locked! Please contact admin to get it unlocked!");
                        url = LOGIN;
                    } else {
                        session.setAttribute("LOGIN_USER", accountDTO);
                        if (STAFF == accountDTO.getRole()) {
                            StaffDTO staffDTO = staffService.getStaff(accountDTO.getAccount_id());
                            if (ADMIN == staffDTO.getRole()) {
                                session.setAttribute("destPage", "admin");
                                url = ADMIN_PAGE;
                            } else if (MANAGER == staffDTO.getRole()) {
                                session.setAttribute("destPage", "manager");
                                url = "ManageManageSellerController";
                            } else if (SHIPPER == staffDTO.getRole()) {
                                session.setAttribute("destPage", "shipper");
                                url = "ShipperHomeController";
                            } else {
                                session.setAttribute("destPage", "seller");
                                url = "SellerManageProductController";
                            }

                        } else if (USER == accountDTO.getRole()) {
                            session.setAttribute("destPage", "user");
                            url = USER_PAGE;
                        } else {
                            request.setAttribute("ERROR_MASSEGE", "Your role is not supported!");
                        }
                    }

                } else {
                    url = LOGIN;
                    request.setAttribute("email", email);
                    request.setAttribute("ERROR_MASSEGE", "Incorrect email or password!");
                }
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
        } finally {
            System.out.println(url);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}