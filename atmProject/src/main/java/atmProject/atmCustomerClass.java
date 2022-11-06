package atmProject;

public class atmCustomerClass {
    private String username;
    private String password;
    private String image;
    private double balance;

    public atmCustomerClass(String username, String password, String image, double balance) {
        this.username = username;
        this.password = password;
        this.image = image;
        this.balance = balance;
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
