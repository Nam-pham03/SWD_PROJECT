/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import enity.Account;

/**
 *
 * @author PCASUS
 */
public interface AccountDAO {

    public Account getAccountInfoByEmail(String email);

    public Account insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newStatus, int newRole, String newAddress, Date newBirth, int newGender);

    public Account getAccount(String email, String password);

    public List<Account> getAccounts();

    public boolean updateAccount(Account account);

    public boolean changeAccount(String email, String name, String phone, String address, Date birth, int gender);

    public boolean checkOldPassword(int account_id, String oldPassword);

    public boolean updateAccountPassword(int account_id, String newPasword);

    public List<Account> getAccountStaffs();

}
