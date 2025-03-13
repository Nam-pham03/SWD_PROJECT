/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enity;

import java.sql.Date;


/**
 *
 * @author PCASUS
 */

public class Account {
    private int account_id;
    private String email;
    private String password;
    private String fullname;
    private String phone;
    private int status;
    private String address;
    private Date dob;
    private int gender;
    private String token;
    private int role;
    private int staff_role;

    public Account(int account_id, String email, String password, String fullname, String phone, int status, String address, Date dob, int gender, String token, int role) {
        this.account_id = account_id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.status = status;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.token = token;
        this.role = role;
    }

    public Account(int account_id, String email, String password, String fullname, String phone, int status, String address, Date dob, int gender, String token, int role, int staff_role) {
        this.account_id = account_id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.status = status;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.token = token;
        this.role = role;
        this.staff_role = staff_role;
    }

    public Account() {
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStaff_role() {
        return staff_role;
    }

    public void setStaff_role(int staff_role) {
        this.staff_role = staff_role;
    }
    
    
    
    
}
