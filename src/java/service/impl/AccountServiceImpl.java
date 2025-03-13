package service.impl;

import dao.AccountDAO;
import dao.impl.AccountDAOImpl; // Thêm import cho DAO cụ thể
import dto.AccountDTO;
import enity.Account;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements service.AccountService {

    private final AccountDAO accountDAO;

    public AccountServiceImpl() {
        this.accountDAO = new AccountDAOImpl(); // Khởi tạo AccountDAO
    }

    @Override
    public AccountDTO insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newStatus, int newRole, String newAddress, Date newBirth, int newGender) {
        AccountDTO acc = new AccountDTO();
        try {
            Account account = accountDAO.insertAccount(newEmail, newPassword, newFullname, newPhone, newStatus, newRole, newAddress, newBirth, newGender);
            acc.setAccount_id(account.getAccount_id());
        } catch (Exception e) {
            System.err.println("Error at AccountServiceImpl.insertAccount: " + e.getMessage());
        }
        return acc;
    }

    @Override
    public AccountDTO checkExistedAccount(String email) {
        AccountDTO acc = new AccountDTO();
        try {
            Account account = accountDAO.getAccountInfoByEmail(email);
            if (account != null) {
                acc.setAccount_id(account.getAccount_id());
                acc.setEmail(account.getEmail());
                acc.setFullname(account.getFullname());
            } else {
                acc = null;
            }
        } catch (Exception e) {
            System.err.println("Error at AccountServiceImpl.checkExistedAccount: " + e.getMessage());
        }
        return acc;
    }

    @Override

    public AccountDTO getAccount(String email, String password) {

        AccountDTO acc = null;
        try {
            Account account = accountDAO.getAccount(email, password);
            if (account != null) {
                acc = new AccountDTO();
                acc.setAccount_id(account.getAccount_id());
                acc.setAddress(account.getAddress());
                acc.setDob(account.getDob());
                acc.setEmail(account.getEmail());
                acc.setFullname(account.getFullname());
                acc.setGender(account.getGender());
                acc.setPhone(account.getPhone());
                acc.setRole(account.getRole());
                acc.setStatus(account.getStatus());
                acc.setToken(account.getToken());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return acc;
    }

    @Override
    public boolean changeAccount(String email, String name, String phone, String address, Date birth, int gender) {
        boolean check = false;
        try {
            check = accountDAO.changeAccount(email, name, phone, address, birth, gender);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return check;
    }

    @Override
    public AccountDTO getAccountInfoByEmail(String email) {
        AccountDTO acc = null;
        try {
            Account account = accountDAO.getAccountInfoByEmail(email);
            if (account != null) {
                acc = new AccountDTO();
                acc.setAccount_id(account.getAccount_id());
                acc.setAddress(account.getAddress());
                acc.setDob(account.getDob());
                acc.setEmail(account.getEmail());
                acc.setFullname(account.getFullname());
                acc.setGender(account.getGender());
                acc.setPhone(account.getPhone());
                acc.setRole(account.getRole());
                acc.setStatus(account.getStatus());
                acc.setToken(account.getToken());
                acc.setPassword(account.getPassword());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return acc;
    }

    @Override
    public boolean checkOldPassword(int account_id, String oldPassword) {
        boolean check = false;
        try {
            check = accountDAO.checkOldPassword(account_id, oldPassword);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean updateAccountPassword(int account_id, String newPasword) {
        boolean check = false;
        try {
            check = accountDAO.updateAccountPassword(account_id, newPasword);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return check;
    }

    @Override
    public List<AccountDTO> getAccounts() {
        List<AccountDTO> list = new ArrayList<>();
        try {
            List<Account> acc = accountDAO.getAccounts();

            for (Account product : acc) {
                AccountDTO dto = new AccountDTO();
                dto.setAccount_id(product.getAccount_id());
                dto.setAddress(product.getAddress());
                dto.setDob(product.getDob());
                dto.setEmail(product.getEmail());
                dto.setFullname(product.getFullname());
                dto.setGender(product.getGender());
                dto.setPhone(product.getPhone());
                dto.setRole(product.getRole());
                dto.setStatus(product.getStatus());
                dto.setToken(product.getToken());

                list.add(dto);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateAccount(AccountDTO account) {
        boolean check = false;
        try {
            Account acc = new Account();
            acc.setAccount_id(account.getAccount_id());
            acc.setAddress(account.getAddress());
            acc.setDob(account.getDob());
            acc.setEmail(account.getEmail());
            acc.setFullname(account.getFullname());
            acc.setGender(account.getGender());
            acc.setPhone(account.getPhone());
            acc.setRole(account.getRole());
            acc.setStatus(account.getStatus());
            acc.setToken(account.getToken());
            acc.setPassword(account.getPassword());
            check = accountDAO.updateAccount(acc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return check;
    }

    @Override
    public List<AccountDTO> getAccountStaffs() {
       List<AccountDTO> list = new ArrayList<>();
        try {
            List<Account> acc = accountDAO.getAccountStaffs();

            for (Account product : acc) {
                AccountDTO dto = new AccountDTO();
                dto.setAccount_id(product.getAccount_id());
                dto.setAddress(product.getAddress());
                dto.setDob(product.getDob());
                dto.setEmail(product.getEmail());
                dto.setFullname(product.getFullname());
                dto.setGender(product.getGender());
                dto.setPhone(product.getPhone());
                dto.setRole(product.getRole());
                dto.setStatus(product.getStatus());
                dto.setToken(product.getToken());
                dto.setStaff_role(product.getStaff_role());
                list.add(dto);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
