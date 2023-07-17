import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Account {

    double balance;
    // Instance initialiser block to set the balance's taint.
    {
        balance = Tainting.taint(balance, IFSPEC);
    }
    
    ErrorLog errorLog = new ErrorLog();

    TransactionLog transactionLog = new TransactionLog();

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            this.logTransaction(true);
        } else {
            this.logError("Cannot deposit a non-positive amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0) {
            double newAmount = this.balance - amount;
            if (newAmount > 0) {
                this.balance = newAmount;
                this.logTransaction(false);
                return true;
            } else {
                this.logError("Account has insufficient funds to withdraw ");
                return false;
            }
        }
        this.logError("Cannot withdraw a non-positive amount.");
        return false;
    }

    private void logTransaction(boolean isDeposit) {
        String transaction = isDeposit ? "Deposit" : "Withdrawal";
        this.transactionLog.logTransaction(transaction + " completed");
    }

    public void logError(String message) {
        Tainting.check(message, IFSPEC);
        Tainting.stopAnalysis();
        this.errorLog.logError(message);
    }

}
