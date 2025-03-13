/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.seller;

import dto.CategoryDTO;
import dto.ProductDTO;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 *
 * @author PCASUS
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5)
@WebServlet(name = "SellerManageProductController", urlPatterns = {"/SellerManageProductController"})
public class SellerManageProductController extends HttpServlet {

    private final ProductService productService;
    private final CategoryService categoryService;

    public SellerManageProductController() {
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

            List<ProductDTO> listPlants = productService.getAllProducts();
            List<CategoryDTO> categorys = categoryService.getAllCategorys();
            request.setAttribute("listPlants", listPlants);
            request.setAttribute("categorys", categorys);
            request.setAttribute("destPage", "managePlant");

        } catch (Exception e) {
            log("Error at AdminManagePlantController: " + e.toString());
        } finally {
            request.getRequestDispatcher("sellerDashboard.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        try {
            if (action != null) {
                String name = request.getParameter("name");
                int price = Integer.parseInt(request.getParameter("price"));
                String description = request.getParameter("description");
                Date birth = Date.valueOf(request.getParameter("date"));
                String author = request.getParameter("Author");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String[] categorys = request.getParameterValues("cid");
                Part listItem = request.getPart("file");

                switch (action) {
                    case "updatePlant":
                        if (listItem == null || listItem.getSize() == 0) {
                            int pid = Integer.parseInt(request.getParameter("pid"));
                            boolean check1 = productService.updateProductInfo(pid, name, price, description, birth, author, quantity);
                            boolean check2 = productService.delete(pid);
                            for (int i = 0; i < categorys.length; i++) {
                                boolean check3 = productService.insertProduct_Accessary(pid, Integer.parseInt(categorys[i]));
                            }
                            if (check1 && check2) {
                                request.setAttribute("MSG_SUCCESS", "You have successfully updated the plant information!");
                            } else {
                                request.setAttribute("MSG_ERROR", "You have failed to update plant information!");
                            }

                            break;
                        } else {
                            String avatar = listItem.getSubmittedFileName();
                            ServletContext context = getServletContext();
                            String realPath = context.getRealPath("/");
                            realPath = realPath.replace("build\\", "") + "img";
                            String filePath = realPath + File.separator + avatar;
                            String filePath1 = "./img/" + avatar;

                            try (InputStream fileInputStream = listItem.getInputStream(); FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {

                                int data;
                                while ((data = fileInputStream.read()) != -1) {
                                    fileOutputStream.write(data);
                                }

                                int pid = Integer.parseInt(request.getParameter("pid"));
                                boolean check4 = productService.updateProduct(pid, name, price, filePath1, description, birth, author, quantity);
                                boolean check5 = productService.delete(pid);
                                for (int i = 0; i < categorys.length; i++) {
                                    boolean check6 = productService.insertProduct_Accessary(pid, Integer.parseInt(categorys[i]));
                                }
                                if (check4 && check5) {
                                    request.setAttribute("MSG_SUCCESS", "You have successfully updated the plant information!");
                                } else {
                                    request.setAttribute("MSG_ERROR", "You have failed to update plant information!");
                                }

                            } catch (Exception e) {
                                log("Error while updating plant: " + e.toString());
                                request.setAttribute("MSG_ERROR", "Oops, something went wrong while updating plant!");
                            }
                        }
                    case "createPlant":
                       
                        String avatar = listItem.getSubmittedFileName();
                        ServletContext context = getServletContext();
                        String realPath = context.getRealPath("/");
                        realPath = realPath.replace("build\\", "") + "img";
                        String filePath = realPath + File.separator + avatar;
                        String filePath1 = "./img/" + avatar;
                        try (InputStream fileInputStream = listItem.getInputStream(); FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                            int data;
                            while ((data = fileInputStream.read()) != -1) {
                                fileOutputStream.write(data);
                            }
                            int check1 = productService.insertProduct(name, price, filePath1, description, birth, author, quantity, 1);
                            for (int i = 0; i < categorys.length; i++) {
                                boolean check2 = productService.insertProduct_Accessary(check1, Integer.parseInt(categorys[i]));
                                if (check2) {
                                    request.setAttribute("MSG_SUCCESS", "You have successfully created new plant!");
//                                
                                } else {
                                    request.setAttribute("MSG_ERROR", "You have failed to create new plant!");
                                }
                            }
                            break;

                        } catch (Exception e) {
                            log("Error while creating plant: " + e.toString());
                            request.setAttribute("MSG_ERROR", "Oops, something went wrong while creating plant!");
                        }
                }

            } else {
               
            }
        } catch (Exception e) {
            log("Error at UpdatePlantController: " + e.toString());
            
        } finally {

            List<ProductDTO> listPlants = productService.getAllProducts();
            List<CategoryDTO> categorys = categoryService.getAllCategorys();
            request.setAttribute("listPlants", listPlants);
            request.setAttribute("categorys", categorys);
            request.setAttribute("destPage", "managePlant");
            request.getRequestDispatcher("sellerDashboard.jsp").forward(request, response);
        }
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
