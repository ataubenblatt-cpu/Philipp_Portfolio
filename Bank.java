import java.util.ArrayList;
import java.util.List;

public class Bank implements IBank {

    private final List<IAccount> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    @Override
    public void OpenAccount(IAccount account) {
        if (account == null) {
            return;
        }
        accounts.add(account);
    }

    @Override
    public void CloseAccount(int accountNumber) {
        IAccount target = null;
        for (IAccount account : accounts) {
            if (account.GetAccountNumber() == accountNumber) {
                target = account;
                break;
            }
        }

        if (target == null) {
            // Not found: ignore request.
            return;
        }

        // The assignment text says "positive"; the demo/intent is "not in debt".
        if (target.GetCurrentBalance() < 0) {
            System.out.println("Account " + accountNumber + " is not closed due to debt");
            return;
        }

        accounts.remove(target);
    }

    @Override
    public List<IAccount> GetAllAccounts() {
        // Return a copy to avoid exposing the internal list.
        List<IAccount> copy = new ArrayList<>();
        for (IAccount account : accounts) {
            copy.add(account);
        }
        return copy;
    }

    @Override
    public List<IAccount> GetAllAccountsInDebt() {
        List<IAccount> result = new ArrayList<>();
        for (IAccount account : accounts) {
            if (account.GetCurrentBalance() < 0) {
                result.add(account);
            }
        }
        return result;
    }

    @Override
    public List<IAccount> GetAllAccountsWithBalance(double balanceAbove) {
        List<IAccount> result = new ArrayList<>();
        for (IAccount account : accounts) {
            if (account.GetCurrentBalance() > balanceAbove) {
                result.add(account);
            }
        }
        return result;
    }
}
