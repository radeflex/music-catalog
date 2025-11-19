package by.radeflex.musiccatalog.exception;

import by.radeflex.musiccatalog.lib.MyMap;

public class ValidationException extends RuntimeException {
    private final MyMap<String, String> errors;

    public ValidationException(MyMap<String, String> errors) {
      super();
      this.errors = errors;
    }

    public MyMap<String, String> getErrors() {
      return errors;
    }
}
