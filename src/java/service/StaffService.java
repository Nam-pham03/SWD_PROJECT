/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dto.StaffDTO;

/**
 *
 * @author PCASUS
 */
public interface StaffService {

    public StaffDTO getStaff(int account_id);

    public boolean insertStaff(int account_id, int i);
    
}
