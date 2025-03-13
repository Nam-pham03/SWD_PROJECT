/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import dto.AccountDTO;
import dto.ProductDTO;
import enity.Product;
import java.sql.Date;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PCASUS
 */
public class ProductServiceImpl implements service.ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl() {
        this.productDAO = new ProductDAOImpl();
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productList = new ArrayList<>();
        try {
            List<Product> product = new ArrayList<>();
            product = productDAO.getAllPlants();
            for (Product product1 : product) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setAuthor(product1.getAuthor());
                productDTO.setDescription(product1.getDescription());
                productDTO.setImg(product1.getImg());
                productDTO.setPrice(product1.getPrice());
                productDTO.setProduct_id(product1.getProduct_id());
                productDTO.setProduct_name(product1.getProduct_name());
                productDTO.setQuantity(product1.getQuantity());
                productDTO.setRelease_date(product1.getRelease_date());
                productDTO.setStatus(product1.getStatus());
                productDTO.setCategory_id(product1.getCategory_id());
                productList.add(productDTO);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public int insertProduct(String name, int price, String filePath1, String description, Date birth, String author, int quantity, int status) {
        int insertedId = -1;
        try {
            insertedId = productDAO.insertProduct(name, price, filePath1, description, birth, author, quantity, status);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertedId;
    }

    @Override
    public boolean insertProduct_Accessary(int check1, int parseInt) {
        boolean check = false;
        try {

            check = productDAO.insertProduct_Accessary(check1, parseInt);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean updateProductInfo(int pid, String name, int price, String description, Date birth, String author, int quantity) {
        boolean check = false;
        try {
            check = productDAO.updateProductInfo(pid, name, price, description, birth, author, quantity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean delete(int pid) {
        boolean check = false;
        try {
            check = productDAO.delete(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean updateProduct(int pid, String name, int price, String filePath1, String description, Date birth, String author, int quantity) {
        boolean check = false;
        try {
            check = productDAO.updateProduct(pid, name, price, filePath1, description, birth, author, quantity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean changeStatusProduct(ProductDTO id) {
        boolean check = false;
        try {
            Product product = new Product();
            product.setProduct_id(id.getProduct_id());
            product.setStatus(id.getStatus());
            check = productDAO.changeStatusProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public ProductDTO getProductByID(String id) {
        ProductDTO acc = new ProductDTO();
        try {
            Product product = productDAO.getProductByID(id);
            if (product != null) {
                acc.setProduct_id(product.getProduct_id());
                acc.setStatus(product.getStatus());

            } else {
                acc = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    @Override
    public boolean changeStatusProduct1(ProductDTO id) {
        boolean check = false;
        try {
            Product product = new Product();
            product.setProduct_id(id.getProduct_id());
            product.setStatus(id.getStatus());
            check = productDAO.changeStatusProduct1(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public List<ProductDTO> getAllPlantsWithPaging(int page, int PAGE_SIZE) {
        List<ProductDTO> list = new ArrayList<>();
        try {
            // Lấy danh sách sản phẩm từ DAO
            List<Product> products = productDAO.getAllPlantsWithPaging(page, PAGE_SIZE);

            // Chuyển đổi từng sản phẩm trong danh sách sang ProductDTO
            for (Product product : products) {
                ProductDTO dto = new ProductDTO();
                dto.setProduct_id(product.getProduct_id());
                dto.setProduct_name(product.getProduct_name());
                dto.setPrice(product.getPrice());
                dto.setImg(product.getImg());
                dto.setDescription(product.getDescription());
                dto.setRelease_date(product.getRelease_date());
                dto.setAuthor(product.getAuthor());
                dto.setQuantity(product.getQuantity());
                dto.setStatus(product.getStatus());
                dto.setCategory_id(product.getCategory_id());

                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getTotalProducts() {
        int total = 0;
        try {
            total = productDAO.getTotalProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public List<ProductDTO> search(int price1, int price2, int[] cid, int page, int PAGE_SIZE, String name) {
        List<ProductDTO> list = new ArrayList<>();
        try {
            // Lấy danh sách sản phẩm từ DAO
            List<Product> products = productDAO.search(price1, price2, cid,page, PAGE_SIZE, name);

            // Chuyển đổi từng sản phẩm trong danh sách sang ProductDTO
            for (Product product : products) {
                ProductDTO dto = new ProductDTO();
                dto.setProduct_id(product.getProduct_id());
                dto.setProduct_name(product.getProduct_name());
                dto.setPrice(product.getPrice());
                dto.setImg(product.getImg());
                dto.setDescription(product.getDescription());
                dto.setRelease_date(product.getRelease_date());
                dto.setAuthor(product.getAuthor());
                dto.setQuantity(product.getQuantity());
                dto.setStatus(product.getStatus());
                dto.setCategory_id(product.getCategory_id());

                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
