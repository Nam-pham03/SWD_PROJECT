/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dto.ProductDTO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author PCASUS
 */
public interface ProductService {

    public List<ProductDTO> getAllProducts();

    public int insertProduct(String name, int price, String filePath1, String description, Date birth, String author, int quantity, int status);

    public boolean insertProduct_Accessary(int check1, int parseInt);

    public boolean updateProductInfo(int pid, String name, int price, String description, Date birth, String author, int quantity);

    public boolean delete(int pid);

    public boolean updateProduct(int pid, String name, int price, String filePath1, String description, Date birth, String author, int quantity);

    public boolean changeStatusProduct(ProductDTO id);

    public ProductDTO getProductByID(String id);

    public boolean changeStatusProduct1(ProductDTO product);

    public List<ProductDTO> getAllPlantsWithPaging(int page, int PAGE_SIZE);

    public int getTotalProducts();

    public List<ProductDTO> search(int price1, int price2, int[] cid, int page, int PAGE_SIZE, String name);
    
}
