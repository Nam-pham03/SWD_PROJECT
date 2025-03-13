/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import static com.sun.activation.registries.LogSupport.log;
import dao.StaffDAO;
import dao.impl.StaffDAOImpl;
import dto.StaffDTO;
import enity.Staff;
import service.*;

/**
 *
 * @author PCASUS
 */
public class StaffServiceImpl implements service.StaffService {

    private final StaffDAO staffDAO;

    public StaffServiceImpl() {
        this.staffDAO = new StaffDAOImpl();
    }

    @Override
 public StaffDTO getStaff(int account_id) {
    StaffDTO acc = null; // Khởi tạo biến acc là null
    try {
        Staff staff = staffDAO.getStaff(account_id);
        if (staff != null) { // Kiểm tra xem staff có khác null không
            acc = new StaffDTO() {}; // Khởi tạo đối tượng StaffDTO
            acc.setAccount_id(staff.getAccount_id());
            acc.setRole(staff.getRole());
            acc.setStaff_id(staff.getStaff_id()); // Sửa đổi để sử dụng staff.getStaff_id()
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return acc; // Trả về đối tượng acc, có thể là null nếu không tìm thấy staff
}

    @Override
    public boolean insertStaff(int account_id, int i) {
        boolean check = false;
        try {
           
            check = staffDAO.insertStaff(account_id, i);
        } catch (Exception e) {
            log("Error at CustomerServiceImpl.insertCustomer: " + e.getMessage());
            System.err.println("Exception in insertCustomer: " + e.getMessage());
        }
        return check;
    }


}
