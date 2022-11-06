package atmProject;

public class atmException extends Exception {
    public atmException(String message) {
        super(message);
    }

    protected static boolean isChoiceValid(int choice) {
        return choice < 1 || choice > 6;
    }
}
