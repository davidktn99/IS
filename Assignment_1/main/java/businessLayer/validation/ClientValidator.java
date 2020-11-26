package businessLayer.validation;

import businessLayer.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by David Katona on 25/11/2020
 */
public class ClientValidator {
    private final List<String> errors;
    private final Client client;

    public ClientValidator(Client client) {
        this.client = client;
        errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean validateEdit() {
        validateId(client.getId());
        validateName(client.getName());
        validatePnc(client.getPnc());
        validateAddress(client.getAddress());
        return errors.isEmpty();
    }

    public boolean validateCreate() {
        validateName(client.getName());
        validatePnc(client.getPnc());
        validateAddress(client.getAddress());
        return errors.isEmpty();
    }

    private void validateId(Long id) {
        if (id < 0) {
            errors.add("Invalid ID!");
        }
    }

    private void validatePnc(Long pnc) {
        if (pnc < 0) {
            errors.add("Invalid PNC!");
        }
    }

    private void validateName(String s) {
        if (s == null || s.trim().isEmpty()) {
            errors.add("Name can't be blank!");
            return;
        }
        Pattern p = Pattern.compile("^[a-zA-z ,.'-]+$");
        Matcher m = p.matcher(s);
        if (!m.find())
            errors.add("Name can't contain special characters or numbers!");

    }

    private void validateAddress(String s) {
        if (s == null || s.trim().isEmpty()) {
            errors.add("Address can't be blank!");
            return;
        }
        Pattern p = Pattern.compile("^[a-zA-z0-9 ,.'-]+$");
        Matcher m = p.matcher(s);
        if (!m.find())
            errors.add("Address can't contain special characters!");
    }
}
