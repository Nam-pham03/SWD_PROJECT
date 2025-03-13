/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import enity.Product;

/**
 *
 * @author PCASUS
 */
public class ProductDAOImpl extends dao.DBContext implements dao.ProductDAO {

    public List<Product> getTop4Plants() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT TOP(4) product_id, product_name, price, "
                + "img, description, status_pro, release_date,author,quantity  FROM Product ORDER BY product_id";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                int price = rs.getInt("price");
                String img = rs.getString("img");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                Date release_date = rs.getDate("release_date");
                String author = rs.getString("author");
                int status = rs.getInt("status_pro");

                // Assuming you have a constructor in your Product class that accepts all these parameters
                Product product = new Product(productId, productName, price, img, description, release_date, author, quantity, status);
                productList.add(product);
            }

        } catch (SQLException e) {
            // Handle the exception appropriately, e.g., log or rethrow
            System.err.println("Error retrieving products: " + e.getMessage());
        }

        return productList;
    }

    public List<Product> getAllPlants() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    p.product_id, \n"
                + "    p.product_name, \n"
                + "    p.img, \n"
                + "    p.price, \n"
                + "    p.description, \n"
                + "    p.release_date, \n"
                + "    p.author, \n"
                + "    p.quantity, \n"
                + "    p.status_pro,\n"
                + "    STRING_AGG(pc.category_id, ', ') AS category_ids \n"
                + "FROM \n"
                + "    Product AS p \n"
                + "JOIN \n"
                + "    Product_Category AS pc \n"
                + "ON \n"
                + "    p.product_id = pc.product_id\n"
                + "GROUP BY \n"
                + "    p.product_id, \n"
                + "    p.product_name, \n"
                + "    p.img, \n"
                + "    p.price, \n"
                + "    p.description, \n"
                + "    p.release_date, \n"
                + "    p.author, \n"
                + "    p.quantity, \n"
                + "    p.status_pro;\n"
                + "";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                int price = rs.getInt("price");
                String imgPath = rs.getString("img");
                String description = rs.getString("description");
                Date date = rs.getDate("release_date");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                int status_pro = rs.getInt("status_pro");
                String category_ids = rs.getString("category_ids");
                String[] category_id = category_ids.split(", ");

                Product product = new Product(productId, productName, price, imgPath, description, date, author, quantity, status_pro, category_id);
                productList.add(product);
            }

        } catch (SQLException e) {
            // Handle the exception appropriately, e.g., log or rethrow
            System.err.println("Error retrieving products: " + e.getMessage());
        }

        return productList;
    }

    public int insertProduct(String name, int price, String filePath1, String description, Date birth, String author, int quantity, int status) {
        int insertedId = -1;

        String sql = "INSERT INTO [dbo].[Product] "
                + "(product_name, price, img, description, release_date , author , quantity, status_pro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, name);
            stm.setInt(2, price);
            stm.setString(3, filePath1);
            stm.setString(4, description);
            stm.setDate(5, birth);
            stm.setString(6, author);
            stm.setInt(7, quantity);

            if (quantity == 0) {
                status = 2;
                stm.setInt(8, status);
            } else {
                stm.setInt(8, status);
            }

            int affectedRows = stm.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = stm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    insertedId = generatedKeys.getInt(1); // Lấy giá trị ID tự tăng từ ResultSet
                }
                generatedKeys.close(); // Đóng ResultSet sau khi sử dụng
            }
            stm.close(); // Đóng PreparedStatement sau khi sử dụng
        } catch (SQLException e) {
            // Xử lý ngoại lệ
            System.out.println("Error occurred while inserting accessory: " + e.getMessage());
        }
        return insertedId;
    }

    public boolean insertProduct_Accessary(int check1, int parseInt) {
        boolean check = false;
        String sql = "INSERT INTO [dbo].[Product_Category]\n"
                + "           ([category_id]\n"
                + "           ,[product_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, parseInt);
            stm.setInt(2, check1);
            check = stm.executeUpdate() > 0;
        } catch (Exception ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    @Override
    public boolean updateProductInfo(int pid, String name, int price, String description, Date birth, String author, int quantity) {
        boolean check = false;
        String sql = "UPDATE Product SET product_name = ? , price = ? , description= ?, release_date=?,  author=?, quantity=? WHERE product_id = ? \n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setInt(2, price);

            stm.setString(3, description);
            stm.setDate(4, birth);
            stm.setString(5, author);
            stm.setInt(6, quantity);
            stm.setInt(7, pid);
            check = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return check;
    }

    @Override
    public boolean delete(int pid) {
        boolean check = false;
        String sql = "delete from Product_Category where product_id= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            check = st.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return check;
    }

    @Override
    public boolean updateProduct(int pid, String name, int price, String filePath1, String description, Date birth, String author, int quantity) {
        boolean check = false;
        String sql = "UPDATE Product SET product_name = ? , price = ?, img = ?, description= ?, release_date=?,  author=?, quantity=? WHERE product_id = ? \n";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setInt(2, price);
            stm.setString(3, filePath1);
            stm.setString(4, description);
            stm.setDate(5, birth);
            stm.setString(6, author);
            stm.setInt(7, quantity);
            stm.setInt(8, pid);
            check = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return check;
    }

    @Override
    public Product getProductByID(String id) {
        Product acc = null;
        try {
            String sql = "SELECT product_id, Status_pro FROM Product WHERE product_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                acc = new Product();

                int product_id = rs.getInt("product_id");
                int status = rs.getInt("Status_pro");

                acc.setProduct_id(product_id);
                acc.setStatus(status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return acc;
    }

    @Override
    public boolean changeStatusProduct(Product product) {
        boolean check = false;
        String sql = "UPDATE Product SET status_pro = 1 WHERE product_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, product.getProduct_id());
            check = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error executing SQL: " + e.getMessage());
        }

        return check;
    }

    @Override
    public boolean changeStatusProduct1(Product product) {
        boolean check = false;
        String sql = "UPDATE Product SET status_pro = 0 WHERE product_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, product.getProduct_id());
            check = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error executing SQL: " + e.getMessage());
        }

        return check;
    }

    @Override
    public List<Product> getAllPlantsWithPaging(int page, int PAGE_SIZE) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT p.product_id, \n"
                + "                    p.product_name, \n"
                + "                    p.img, \n"
                + "                    p.price, \n"
                + "                    p.description, \n"
                + "                    p.release_date, \n"
                + "                    p.author, \n"
                + "                    p.quantity, \n"
                + "                    p.status_pro,\n"
                + "                    STRING_AGG(pc.category_id, ', ') AS category_ids \n"
                + "                FROM \n"
                + "                    Product AS p \n"
                + "                JOIN \n"
                + "                    Product_Category AS pc \n"
                + "                ON \n"
                + "                    p.product_id = pc.product_id\n"
                + "                GROUP BY \n"
                + "                    p.product_id, \n"
                + "                    p.product_name, \n"
                + "                    p.img, \n"
                + "                    p.price, \n"
                + "                    p.description, \n"
                + "                    p.release_date, \n"
                + "                    p.author, \n"
                + "                    p.quantity, \n"
                + "                    p.status_pro ORDER BY product_id ASC OFFSET ((? - 1) * ?) ROW FETCH NEXT ? ROWS ONLY;";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, page);
            st.setInt(2, PAGE_SIZE);
            st.setInt(3, PAGE_SIZE);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("product_id");
                    String productName = rs.getString("product_name");
                    int price = rs.getInt("price");
                    String img = rs.getString("img");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    Date release_date = rs.getDate("release_date");
                    String author = rs.getString("author");
                    int status = rs.getInt("status_pro");
                    String[] categoryIds = rs.getString("category_ids").split(", ");

                    // Khởi tạo đối tượng Product và thêm vào danh sách
                    Product product = new Product(productId, productName, price, img, description, release_date, author, quantity, status, categoryIds);
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products: " + e.getMessage());
        }

        return productList;
    }

    @Override
    public int getTotalProducts() {
        int total = 0;
        String sql = "SELECT COUNT(product_id) AS total FROM Product";
        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            // Kiểm tra và lấy giá trị từ cột "total" trong kết quả truy vấn
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products: " + e.getMessage());
        }
        return total;
    }

    @Override
    public List<Product> search(int price1, int price2, int[] cid, int page, int PAGE_SIZE, String name) {
        List<Product> productList = new ArrayList<>();
        String sql = buildQuery(price1, price2, cid, name);

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            int paramIndex = 1;

            // Set price range parameters if provided
            if (price1 >= 0 && price2 >= 0) {
                st.setInt(paramIndex++, price1);
                st.setInt(paramIndex++, price2);
            } else if (price1 >= 0) {
                st.setInt(paramIndex++, price1);
            } else if (price2 >= 0) {
                st.setInt(paramIndex++, price2);
            }

            // Set category IDs if provided
            if (cid != null && cid.length > 0) {
                for (int id : cid) {
                    st.setInt(paramIndex++, id);
                }
            }

            // Set name keyword if provided
            if (name != null && !name.isEmpty()) {
                st.setString(paramIndex++, "%" + name + "%");
                st.setString(paramIndex++, "%" + name + "%");
            }

            // Set pagination parameters
            st.setInt(paramIndex++, page);
            st.setInt(paramIndex++, PAGE_SIZE);
            st.setInt(paramIndex++, PAGE_SIZE);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("product_id");
                    String productName = rs.getString("product_name");
                    int price = rs.getInt("price");
                    String img = rs.getString("img");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    Date release_date = rs.getDate("release_date");
                    String author = rs.getString("author");
                    int status = rs.getInt("status_pro");
                    String[] categoryIds = rs.getString("category_ids").split(", ");

                    // Create Product object and add to list
                    Product product = new Product(productId, productName, price, img, description, release_date, author, quantity, status, categoryIds);
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products: " + e.getMessage());
        }

        return productList;
    }

    public static String buildQuery(int price1, int price2, int[] cid, String name) {
        StringBuilder sql = new StringBuilder("SELECT p.product_id, "
                + "p.product_name, "
                + "p.img, "
                + "p.price, "
                + "p.description, "
                + "p.release_date, "
                + "p.author, "
                + "p.quantity, "
                + "p.status_pro, "
                + "STRING_AGG(pc.category_id, ', ') AS category_ids "
                + "FROM Product AS p "
                + "JOIN Product_Category AS pc ON p.product_id = pc.product_id "
                + "WHERE 1=1 ");

        // Price filtering
        if (price1 >= 0 && price2 >= 0) {
            sql.append("AND p.price BETWEEN ? AND ? ");
        } else if (price1 >= 0) {
            sql.append("AND p.price >= ? ");
        } else if (price2 >= 0) {
            sql.append("AND p.price <= ? ");
        }

        // Category filtering
        if (cid != null && cid.length > 0) {
            sql.append("AND pc.category_id IN (");
            for (int i = 0; i < cid.length; i++) {
                sql.append("?");
                if (i < cid.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(") ");
        }

        // Name keyword filtering
        if (name != null && !name.isEmpty()) {
            sql.append("AND (p.product_name LIKE ? OR p.author LIKE ?) ");
        }

        // Grouping, ordering, and pagination
        sql.append("GROUP BY p.product_id, "
                + "p.product_name, "
                + "p.img, "
                + "p.price, "
                + "p.description, "
                + "p.release_date, "
                + "p.author, "
                + "p.quantity, "
                + "p.status_pro "
                + "ORDER BY p.product_id ASC "
                + "OFFSET ((? - 1) * ?) ROWS FETCH NEXT ? ROWS ONLY;");

        return sql.toString();
    }

}
