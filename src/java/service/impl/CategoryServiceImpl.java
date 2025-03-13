/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.CategoryDAO;
import dao.impl.CategoryDAOImpl;
import dto.CategoryDTO;
import enity.Category;
import java.util.ArrayList;
import java.util.List;
import service.*;

/**
 *
 * @author PCASUS
 */
public class CategoryServiceImpl implements service.CategoryService{
    
    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl() {
        this.categoryDAO = new CategoryDAOImpl();
    }

    @Override
    public List<CategoryDTO> getAllCategorys() {
         List<CategoryDTO> productList = new ArrayList<>();
        try {
            List<Category> product = new ArrayList<>();
            product = categoryDAO.getAllCategorys();
            for (Category product1 : product) {
                CategoryDTO productDTO = new CategoryDTO();
                productDTO.setCategory_id(product1.getCategory_id());
                productDTO.setCategory_name(product1.getCategory_name());
                productList.add(productDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
        
    }
    
}
