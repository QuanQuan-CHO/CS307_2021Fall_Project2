package cn.edu.sustech.cs307.exception;

/**
 * In all <b>add</b> method, if some parameters are invalid, throw IntegrityViolationException
 */
public class IntegrityViolationException extends RuntimeException {
    public IntegrityViolationException() {
    }

    public IntegrityViolationException(String message) {
        super(message);
    }

    public IntegrityViolationException(Throwable cause) {
        super(cause);
    }
}
