package atmProject;

public class atmMain extends atmUI {
    public static void main(String[] args) throws atmException {
        atmFileOperations.createDirectoryForAtm();
        atmFileOperations.createACustomerFile();
        atmFileOperations.createALogFile();
        atmUIlogin();
    }

}
