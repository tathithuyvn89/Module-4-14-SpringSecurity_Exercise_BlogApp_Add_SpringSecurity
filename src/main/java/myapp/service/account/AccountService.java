package myapp.service.account;

import myapp.model.Account;

public interface AccountService {
    Account findAccountByUsername(String  usename);
    void saveAccount(Account account);
}
