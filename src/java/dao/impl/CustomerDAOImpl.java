/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import java.sql.PreparedStatement;


/**
 *
 * @author PCASUS
 */
public class CustomerDAOImpl extends dao.DBContext implements dao.CustomerDAO{
    
    @Override
    public boolean insertCustomer(int account_id, int i) {
       boolean check = false;
        String sql = "INSERT INTO Customer (account_id, point) VALUES (?, ?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
           
            stm.setInt(1, account_id);
            stm.setInt(2, i);
           

            check = stm.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }
}
