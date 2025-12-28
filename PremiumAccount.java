public class PremiumAccount implements IAccount {

    private final int accountNumber;
    private double balance = 0.0;

    public PremiumAccount(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void Deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    @Override
    public double Withdraw(double amount) {
        if (amount <= 0) {
            return 0.0;
        }

        // Premium accounts have no credit limit.
        balance -= amount;
        return amount;
    }

    @Override
    public double GetCurrentBalance() {
        return balance;
    }

    @Override
    public int GetAccountNumber() {
        return accountNumber;
    }
}
