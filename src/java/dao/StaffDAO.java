/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import enity.Staff;

import java.util.List;


/**
 *
 * @author PCASUS
 */
public interface StaffDAO  {

    public Staff getStaff(int account_id);
    public List<Staff> getStaffAdmins();

    public boolean insertStaff(int account_id, int i);

}
