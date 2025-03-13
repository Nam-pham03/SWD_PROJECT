/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import java.util.List;
import enity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PCASUS
 */
public class CategoryDAOImpl extends dao.DBContext implements dao.CategoryDAO{

    @Override
    public List<Category> getAllCategorys() {
        List<Category> productList = new ArrayList<>();
        String sql = "SELECT * FROM Category";

        try ( PreparedStatement st = connection.prepareStatement(sql);  ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int productId = rs.getInt("category_id");
                String productName = rs.getString("category_name");
               

                // Assuming you have a constructor in your Product class that accepts all these parameters
                Category product = new Category(productId, productName);
                productList.add(product);
            }

        } catch (SQLException e) {
            // Handle the exception appropriately, e.g., log or rethrow
            System.err.println("Error retrieving products: " + e.getMessage());
        }

        return productList;
    }
    
}
