package businessLayer.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeValidator {

    public static boolean validateLong(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static boolean validateFloat(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("^[0-9.]+$");
        Matcher m = p.matcher(s);
        return m.find();
    }
}
