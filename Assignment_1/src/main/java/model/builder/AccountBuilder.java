package model.builder;

import model.Account;

import java.util.Date;

/**
 * Created by David Katona on 18/11/2020
 */
public class AccountBuilder {

    private Account account;

    public AccountBuilder() {
        account = new Account();
    }

    public AccountBuilder setId(Long id) {
        account.setId(id);
        return this;
    }

    public AccountBuilder setType(String type) {
        account.setType(type);
        return this;
    }

    public AccountBuilder setMoney(float money) {
        account.setMoney(money);
        return this;
    }

    public AccountBuilder setDate(Date date) {
        account.setDate(date);
        return this;
    }

    public Account build() {
        return account;
    }

}
