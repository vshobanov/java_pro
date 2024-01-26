package inno.lesson2;

public class AnnotationException extends Exception {
    public AnnotationException() {
        super("This is a custom exception.");
    }

    public AnnotationException(String message) {
        super(message);
    }
}