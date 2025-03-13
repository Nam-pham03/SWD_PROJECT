/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import enity.Admin;
import enity.Manager;
import enity.Seller;
import enity.Shipper;
import enity.Staff;

/**
 *
 * @author PCASUS
 */
public class StaffDAOImpl extends dao.DBContext implements dao.StaffDAO{
    @Override
    public Staff getStaff(int account_id) {
        Staff acc = null;
        try {
            String sql = "SELECT account_id, staff_id, role FROM Staff WHERE account_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, account_id);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                acc = createStaffByRole(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return acc;
    }

    private Staff createStaffByRole(ResultSet rs) throws SQLException {
        int accountId = rs.getInt("account_id");
        int staffId = rs.getInt("staff_id");
        int role = rs.getInt("role");

        Staff staff;
        switch (role) {
            case 0:
                staff = new Admin();
                break;
            case 1:
                staff = new Manager();
                break;
            case 2:
                staff = new Shipper();
                break;
            default:
                staff = new Seller();
                break;
        }

        staff.setAccount_id(accountId);
        staff.setStaff_id(staffId);
        staff.setRole(role);

        return staff;
    }



    @Override
    public List<Staff> getStaffAdmins() {
        List<Staff> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Staff where role=0";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int AccId = rs.getInt("account_id");
                int staff_id = rs.getInt("staff_id");
                int Role = rs.getInt("role");

                Staff acc;

                acc = new Admin();
             

                acc.setAccount_id(AccId);
                acc.setStaff_id(staff_id);
                acc.setRole(Role);

                list.add(acc);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insertStaff(int account_id, int i) {
        boolean check = false;
        String sql = "INSERT INTO Staff (account_id, role) VALUES (?, ?)";
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
