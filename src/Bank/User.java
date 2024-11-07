package Bank;

public class User {

    private String username;
    private String password;
    private BankAccount account;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public void displayAccountDetails() {
        System.out.println("Account Number:" + account.getAccountNumber());
        System.out.println("Current Balance:" + account.getBalance());
        System.out.println("Transaction History:" );
        account.displayTransactionHistory();

    }

    public void updateUsername(String newUsername) {
        username = newUsername;
    }

    public void updatePassword(String newPassword ) {
        password = newPassword;
    }
}