public class BasicAccount implements IAccount {

    private final int accountNumber;
    /** Maximum amount that can be withdrawn in a single Withdraw call. */
    private final double withdrawLimit;

    private double balance = 0.0;

    public BasicAccount(int accountNumber, double withdrawLimit) {
        this.accountNumber = accountNumber;
        // Spec says the withdrawal limit is a positive double. Be defensive.
        this.withdrawLimit = Math.max(0.0, withdrawLimit);
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

        // Basic accounts have creditLimit = 0 (no overdraft).
        double maxByBalance = Math.max(0.0, balance);
        double maxByLimit = withdrawLimit;
        double actual = Math.min(amount, Math.min(maxByBalance, maxByLimit));
        balance -= actual;
        return actual;
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
