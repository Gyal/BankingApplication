package dto;

import java.util.Date;

/**
 * Created by youniik-nana on 12/03/15.
 */

public class TransactionDto {
    private Long idTransaction;

    private String transactionName;

    private int NumDebitedAccount;

    private int NumCreditedAccount;

    private double amount;

    private Date transactionDate;

    public TransactionDto() {
    }

    public TransactionDto(Long idTransaction, String transactionName, int numDebitedAccount, int numCreditedAccount, double amount, Date transactionDate) {
        this.idTransaction = idTransaction;
        this.transactionName = transactionName;
        this.NumDebitedAccount = numDebitedAccount;
        this.NumCreditedAccount = numCreditedAccount;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public int getNumDebitedAccount() {
        return NumDebitedAccount;
    }

    public void setNumDebitedAccount(int numDebitedAccount) {
        NumDebitedAccount = numDebitedAccount;
    }

    public int getNumCreditedAccount() {
        return NumCreditedAccount;
    }

    public void setNumCreditedAccount(int numCreditedAccount) {
        NumCreditedAccount = numCreditedAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
