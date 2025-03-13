/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;



/**
 *
 * @author PCASUS
 */

public class ProductDTO {

    private int product_id;
    private String product_name;
    private int price;
    private String img;
    private String description;
    private Date release_date;
    private String author;
    private int quantity;
    private int status;
    String[] category_id = null;

    public ProductDTO() {
    }

    public ProductDTO(int product_id, String product_name, int price, String img, String description, Date release_date, String author, int quantity, int status) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.img = img;
        this.description = description;
        this.release_date = release_date;
        this.author = author;
        this.quantity = quantity;
        this.status = status;
    }
    
     public ProductDTO(int product_id, String product_name, int price, String img, String description, Date release_date, String author, int quantity, int status, String[] category_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.img = img;
        this.description = description;
        this.release_date = release_date;
        this.author = author;
        this.quantity = quantity;
        this.status = status;
        this.category_id = category_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String[] getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String[] category_id) {
        this.category_id = category_id;
    }
     
    public List<String> getCategory_ids() {
        return Arrays.asList(category_id);
    }
    
    
    
    

}
