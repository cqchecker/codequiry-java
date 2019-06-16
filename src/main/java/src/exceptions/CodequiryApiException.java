package src.exceptions;

public class CodequiryApiException extends RuntimeException {

    public CodequiryApiException(String msg) {
        super(msg);
    }

    public CodequiryApiException(Throwable t) {
        super(t);
    }

    public CodequiryApiException(String msg, Throwable t) {
        super(msg, t);
    }
}
