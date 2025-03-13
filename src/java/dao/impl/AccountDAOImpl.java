/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;


import java.sql.Date;
import java.util.List;
import enity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PCASUS
 */
public class AccountDAOImpl extends dao.DBContext implements dao.AccountDAO {

    @Override
    public Account getAccountInfoByEmail(String email) {
        Account acc = null;
        try {
            String sql = "SELECT * FROM Account WHERE email = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setString(1, email);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        int AccId = rs.getInt("account_id");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String FullName = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int Status = rs.getInt("status_acc");
                        int Role = rs.getInt("role");
                        String address = rs.getString("address");
                        Date birth = rs.getDate("dob");
                        int gender = rs.getInt("gender");
                        String token = rs.getString("token");

                        acc = new Account(AccId, Email, Password, FullName, Phone, Status, address, birth, gender, token, Role);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return acc;
    }

    @Override
    public Account insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newStatus, int newRole, String newAddress, Date newBirth, int newGender) {
        Account newAccount = null;
        String sql = "INSERT INTO Account (email, password, fullname, phone, status_acc, role, address, dob, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, newEmail);
            stm.setString(2, newPassword);
            stm.setString(3, newFullname);
            stm.setString(4, newPhone);
            stm.setInt(5, newStatus);
            stm.setInt(6, newRole);
            stm.setString(7, newAddress);
            stm.setDate(8, newBirth);
            stm.setInt(9, newGender);

            int affectedRows = stm.executeUpdate();

            if (affectedRows > 0) {
                // Lấy ID vừa được sinh ra (nếu có)
                ResultSet generatedKeys = stm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int accountId = generatedKeys.getInt(1);
                    // Tạo đối tượng Account và gán các giá trị cho nó
                    newAccount = new Account(accountId, newEmail, newPassword, newFullname, newPhone, newStatus, newAddress, newBirth, newGender, "", newRole);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return newAccount;
    }

    @Override
    public Account getAccount(String email, String password) {
        Account acc = null;
        try {
            String sql = "SELECT account_id, email, password, fullname, phone, status_acc, role,address, dob, gender, token FROM Account WHERE email = ? AND password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int AccId = rs.getInt("account_id");

                String Email = rs.getString("email");
                String FullName = rs.getString("fullname");
                String Phone = rs.getString("phone");
                int Status = rs.getInt("status_acc");
                int Role = rs.getInt("role");
                String address = rs.getString("address");
                String token = rs.getString("token");
                Date birth = rs.getDate("dob");
                int gender = rs.getInt("gender");

                acc = new Account(AccId, Email, password, FullName, Phone, Status, address, birth, gender, token, Role);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return acc;
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Account";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int AccId = rs.getInt("account_id");
                String Email = rs.getString("email");
                String FullName = rs.getString("fullname");
                String Phone = rs.getString("phone");
                int Status = rs.getInt("status_acc");
                int Role = rs.getInt("role");
                String address = rs.getString("address");
                String token = rs.getString("token");
                Date birth = rs.getDate("dob");
                int gender = rs.getInt("gender");
                String password = rs.getString("password");
                Account acc = new Account(AccId, Email, password, FullName, Phone, Status, address, birth, gender, token, Role);
                list.add(acc);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public static void main(String[] args) {
        AccountDAOImpl a= new AccountDAOImpl();
        List<Account> b=a.getAccounts();
    }

    @Override    
    public boolean updateAccount(Account account) {
        boolean check = false;
        try {
            String sql = "UPDATE Account SET password = ?, fullname = ?, phone = ?, status_acc = ?, address = ?, gender = ?,dob = ?  WHERE account_id = ?";
            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setString(1, account.getPassword());
                stm.setString(2, account.getFullname());
                stm.setString(3, account.getPhone());
                stm.setInt(4, account.getStatus());
                stm.setString(5, account.getAddress());
                stm.setInt(6, account.getGender());
                stm.setDate(7, account.getDob());

                stm.setInt(8, account.getAccount_id());

                check = stm.executeUpdate() > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean changeAccount(String email, String name, String phone, String address, Date birth, int gender) {
        boolean check = false;
        String sql = "UPDATE Account SET fullname = ?, phone = ?, address = ?, dob = ?, gender = ? WHERE email = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, phone);
            stm.setString(3, address);
            stm.setDate(4, birth);
            stm.setInt(5, gender);
            stm.setString(6, email);

            check = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }
    
    
    

    @Override
    public boolean checkOldPassword(int account_id, String oldPassword) {
       boolean check = false;
        ResultSet rs = null;
        String sql = "SELECT password FROM Account WHERE account_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, account_id);
            rs = stm.executeQuery();
            if (rs.next()) {
                String accPsw = rs.getString("Password");
                if (accPsw.equalsIgnoreCase(oldPassword)) {
                    check = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean updateAccountPassword(int account_id, String newPasword) {
       boolean check = false;
        String sql = "UPDATE Account Set password = ? WHERE account_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newPasword);
            stm.setInt(2, account_id);

            check = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Account> getAccountStaffs() {
       List<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT a.*, s.role as 'staff_role' FROM Account a join Staff s on a.account_id = s.account_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int AccId = rs.getInt("account_id");
                String Email = rs.getString("email");
                String FullName = rs.getString("fullname");
                String Phone = rs.getString("phone");
                int Status = rs.getInt("status_acc");
                int Role = rs.getInt("role");
                String address = rs.getString("address");
                String token = rs.getString("token");
                Date birth = rs.getDate("dob");
                int gender = rs.getInt("gender");
                String password = rs.getString("password");
                int staff_role = rs.getInt("staff_role");
                Account acc = new Account(AccId, Email, password, FullName, Phone, Status, address, birth, gender, token, Role, staff_role);
                list.add(acc);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }



}
