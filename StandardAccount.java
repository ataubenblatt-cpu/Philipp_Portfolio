public class StandardAccount implements IAccount {

    private final int accountNumber;
    /**
     * Negative (or zero) overdraft floor. The account balance may not go below this value.
     * Per assignment: if a positive creditLimit is provided, treat it as zero.
     */
    private final double creditLimit;

    private double balance = 0.0;

    public StandardAccount(int accountNumber, double creditLimit) {
        this.accountNumber = accountNumber;
        this.creditLimit = (creditLimit < 0) ? creditLimit : 0.0;
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

        // We cannot go below creditLimit.
        double maxAllowed = balance - creditLimit;
        if (maxAllowed <= 0) {
            return 0.0;
        }

        double actual = Math.min(amount, maxAllowed);
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
