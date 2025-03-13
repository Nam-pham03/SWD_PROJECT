/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dto.AccountDTO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author PCASUS
 */
public interface AccountService {

    public AccountDTO insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newStatus, int newRole, String newAddress, Date newBirth, int newGender);

    public AccountDTO checkExistedAccount(String email);

    public AccountDTO getAccount(String email, String password);

    public boolean changeAccount(String email, String name, String phone, String address, Date birth, int gender);

    public AccountDTO getAccountInfoByEmail(String email);

    public boolean checkOldPassword(int account_id, String oldPassword);

    public boolean updateAccountPassword(int account_id, String newPasword);

    public List<AccountDTO> getAccounts();

    public boolean updateAccount(AccountDTO account);

    public List<AccountDTO> getAccountStaffs();

    

   
    
}
