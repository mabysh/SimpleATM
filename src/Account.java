public class Account {
    private double balance;

    public Account() {
        balance = 0.0;
    }

    public Account(double b) {
        this.balance = b;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double d) {
        balance = balance + d;
    }

    public void withdraw(double d) {
        balance = balance - d;
    }
}
