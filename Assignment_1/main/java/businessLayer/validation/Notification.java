package businessLayer.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 18/03/2017.
 */
public class Notification<T> {

    private T result;
    private final List<String> errors;

    public Notification() {
        this.errors = new ArrayList<>();
    }

    public void addError(String message) {
        errors.add(message);
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }

    public T getResult() throws ResultFetchException {
        if (hasErrors()) {
            throw new ResultFetchException(errors);
        }
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getFormattedErrors() {
        return errors.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

}
