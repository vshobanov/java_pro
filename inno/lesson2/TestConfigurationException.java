package inno.lesson2;

public class TestConfigurationException extends RuntimeException {
    public TestConfigurationException() {
        super("This is a custom exception.");
    }

    public TestConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestConfigurationException(Throwable cause) {
        super(cause);
    }
    public TestConfigurationException(String message) {
        super(message);
    }
}