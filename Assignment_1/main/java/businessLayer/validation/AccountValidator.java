package businessLayer.validation;

import businessLayer.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by David Katona on 25/11/2020
 */
public class AccountValidator {
    private final List<String> errors;
    private final Account account;

    public AccountValidator(Account account) {
        this.account = account;
        errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validateCreate() {
        validateType(account.getType());
        validateMoney(account.getMoney());
        validateClientId(account.getClientId());
        return errors.isEmpty();
    }

    public boolean validateEdit() {
        validateId(account.getId());
        validateType(account.getType());
        validateMoney(account.getMoney());
        validateClientId(account.getClientId());
        return errors.isEmpty();
    }

    public boolean validateDelete() {
        validateId(account.getId());
        return errors.isEmpty();
    }

    private void validateId(Long id) {
        if (id < 0) {
            errors.add("Invalid ID!");
        }
    }

    private void validateMoney(Float money) {
        if (money < 0 || money > Float.MAX_VALUE) {
            errors.add("Invalid amount of money!");
        }
    }

    private void validateType(String s) {
        if (s == null || s.trim().isEmpty()) {
            errors.add("Type can't be blank!");
            return;
        }
        Pattern p = Pattern.compile("^[a-zA-z -]+$");
        Matcher m = p.matcher(s);
        if (!m.find())
            errors.add("Type can't contain special characters or numbers!");

    }

    private void validateClientId(Long id) {
        if (id < 0) {
            errors.add("Invalid client ID!");
        }
    }
}
