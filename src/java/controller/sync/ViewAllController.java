/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.sync;

import dto.CategoryDTO;
import dto.ProductDTO;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

/**
 *
 * @author PCASUS
 */
@WebServlet(name = "ViewAllController", urlPatterns = {"/ViewAllController"})
public class ViewAllController extends HttpServlet {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ViewAllController() {
        this.productService = new ProductServiceImpl();
        this.categoryService = new CategoryServiceImpl();
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
            final int PAGE_SIZE = 3;
            HttpSession session = request.getSession();
            session.setAttribute("destPage", "plant");
            String category = request.getParameter("category");
            String pagenumber = request.getParameter("pagenumber");
            int page = 1;
            if (pagenumber != null) {
                page = Integer.parseInt(pagenumber);
            }
            if (category == null || category.isEmpty()) {
                List<ProductDTO> listPlants = productService.getAllPlantsWithPaging(page, PAGE_SIZE);
                int totalProducts = productService.getTotalProducts();
                int totalPage = totalProducts / PAGE_SIZE;
                List<CategoryDTO> categorys = categoryService.getAllCategorys();
                if (totalProducts % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listPlants", listPlants);
                request.setAttribute("categorys", categorys);
                session.setAttribute("urlHistory", "ViewAllController"
                                                 + ((pagenumber != null) ? ("?pagenumber=" + pagenumber) : ""));
                request.getRequestDispatcher("shopPage.jsp").forward(request, response);
            } else {
                
            }
                

        } catch (Exception e) {
            log("Error at ViewAllController: " + e.toString());
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
