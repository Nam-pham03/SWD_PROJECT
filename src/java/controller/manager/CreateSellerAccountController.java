/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dto.AccountDTO;
import enity.Email;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.sql.Date;
import service.AccountService;
import service.StaffService;
import service.impl.AccountServiceImpl;
import service.impl.StaffServiceImpl;
import util.SecurityUtils;

/**
 *
 * @author PCASUS
 */
@WebServlet(name = "CreateSellerAccountController", urlPatterns = {"/CreateSellerAccountController"})
public class CreateSellerAccountController extends HttpServlet {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
    private static final int PASSWORD_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String MSG_ERROR = "MSG_ERROR";
    private static final String MSG_SUCCESS = "MSG_SUCCESS";

    private final AccountService accountService;
    private final StaffService customerService;

    public CreateSellerAccountController() {
        this.accountService = new AccountServiceImpl();
        this.customerService = new StaffServiceImpl();
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
        Email emailSender = new Email();
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String birthdateStr = request.getParameter("date");
        String address = request.getParameter("address");
        int gender = Integer.parseInt(request.getParameter("gender"));
        Date birth = Date.valueOf(birthdateStr);
        String a = generateRandomPassword();
        String password = SecurityUtils.hashMd5(a);
        boolean emailSent = emailSender.sendEmail(email,
                "[Show Room] YOUR PASSWORD",
                "<html><body>"
                + "<p>Dear " + name + ",</p>"
                + "<p>Your Password is: <strong>" + a + "</strong></p>"
                + "<p>Thank you for using our services.</p>"
                + "<p>Best regards,<br/>FPT DriveSign.</p>"
                + "</body></html>");
        System.out.println(emailSent);

        try {

            AccountDTO accountDTO = accountService.checkExistedAccount(email);

            if (accountDTO != null) {
                request.setAttribute(MSG_ERROR, "Tài khoản đã tồn tại! Vui lòng đăng nhập bằng email này.");

                request.getRequestDispatcher("ManageManageSellerController").forward(request, response);
            } else {
                AccountDTO acc = accountService.insertAccount(email, password, name, phone, 1, 1, address, birth, gender);
                boolean check = customerService.insertStaff(acc.getAccount_id(), 3);

                if (check) {

                    request.setAttribute(MSG_SUCCESS, "Đăng ký tài khoản thành công!");
                    request.getRequestDispatcher("ManageManageSellerController").forward(request, response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(MSG_ERROR, "Đã xảy ra lỗi trong quá trình đăng ký, vui lòng thử lại.");
            request.getRequestDispatcher("ManageManageSellerController").forward(request, response);
        }

    }

    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }
        return password.toString();
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
