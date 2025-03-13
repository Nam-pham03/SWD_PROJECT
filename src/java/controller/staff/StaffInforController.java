/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.staff;

import dto.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import service.AccountService;
import service.impl.AccountServiceImpl;
import util.SecurityUtils;

/**
 *
 * @author PCASUS
 */
@WebServlet(name="StaffInforController", urlPatterns={"/StaffInforController"})
public class StaffInforController extends HttpServlet {
   private static final String ADMIN_PAGE = "staffinfo.jsp";
    private final AccountService accountService;

    public StaffInforController() {
        this.accountService = new AccountServiceImpl();

    }
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        try {
            response.sendRedirect(ADMIN_PAGE);
        } catch (Exception e) {
            log("Error at AdminInfo: " + e.toString());
        }
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         try {
            HttpSession session = request.getSession();
            AccountDTO acc = new AccountDTO();
            acc = (AccountDTO) session.getAttribute("LOGIN_USER");
            String action = request.getParameter("action");
            if (action != null) {
                switch (action) {
                    case "updateInfo":
                        String name = request.getParameter("name");
                        String phone = request.getParameter("phone");
                        String address = request.getParameter("address");
                        Date birth = Date.valueOf(request.getParameter("birth"));
                        int gender = Integer.parseInt(request.getParameter("gender"));
                        boolean check = accountService.changeAccount(acc.getEmail(), name, phone, address, birth, gender);
                        if (check) {
                            acc = accountService.getAccountInfoByEmail(acc.getEmail());
                            session.setAttribute("LOGIN_USER", acc);
                            request.setAttribute("MSG_SUCCESS", "Update profile information successfully!");
                            request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
                        } else {
                            request.setAttribute("MSG_ERROR", "Oops! Something went wrong! Try later!");
                            request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
                        }
                        break;
                    case "changePassword":
                        String oldPassword = SecurityUtils.hashMd5(request.getParameter("oldPassword"));
                        boolean checkOldPsw = accountService.checkOldPassword(acc.getAccount_id(), oldPassword);
                        if (checkOldPsw) {
                            String newPasword = SecurityUtils.hashMd5(request.getParameter("newPassword"));
                            boolean checkNewPsw = accountService.updateAccountPassword(acc.getAccount_id(), newPasword);
                            request.setAttribute("MSG_SUCCESS", "Change password successfully!");
                            request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);

                        } else {
                            request.setAttribute("MSG_ERROR", "You entered the wrong old password! Please try again!");
                            request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
                        }
                        break;
                }

            } else {
               
                request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
            }
        } catch (Exception e) {
            log("Error at AdminInfoController: " + e.toString());
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
