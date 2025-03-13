/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dto.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import service.AccountService;
import service.CustomerService;
import service.impl.AccountServiceImpl;
import service.impl.CustomerServiceImpl;

/**
 *
 * @author PCASUS
 */
@WebServlet(name = "ChangeStatusCustomerController", urlPatterns = {"/ChangeStatusCustomerController"})
public class ChangeStatusCustomerController extends HttpServlet {
    
     private final AccountService accountService;
    private final CustomerService customerService;

    public ChangeStatusCustomerController() {
        this.accountService = new AccountServiceImpl();
        this.customerService = new CustomerServiceImpl();
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
        try {
            String email = request.getParameter("email");
            AccountDTO account = accountService.getAccountInfoByEmail(email);

            if (account.getStatus() == 1) {

                account.setStatus(0);
                accountService.updateAccount(account);
                request.setAttribute("MSG_SUCCESS", "Account blocked successfully.");

            } else if (account.getStatus() == 0) {

                account.setStatus(1);
                accountService.updateAccount(account);
                request.setAttribute("MSG_SUCCESS", "Account unblocked successfully.");

            }

        } catch (Exception e) {
            log("Error at UpdateAccountController: " + e.toString());
        } finally {
            

            request.getRequestDispatcher("AdminManageAccountController").forward(request, response);
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
