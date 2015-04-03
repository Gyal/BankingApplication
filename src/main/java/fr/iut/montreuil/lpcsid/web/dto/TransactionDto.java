package fr.iut.montreuil.lpcsid.web.dto;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;

import java.util.Date;

/**
 * Created by youniik-nana on 12/03/15.
 */

public class TransactionDto {

    private Long idTransaction;

    private String transactionType;

    private AccountEntity NumDebitedAccount;


    private AccountEntity NumCreditedAccount;


    private int amount;

    private Date transactionDate;

    /**
     * Récupération du compte bancaire associé
     *
     * @return Compte bancaire associé
     */

    public AccountEntity account;

    public TransactionDto() {

    }

    // Créé par l'utilisateur
    public TransactionDto(String transactionType, int amount, Date transactionDate, AccountEntity NumDebitedAccount, AccountEntity NumCreditedAccount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.NumCreditedAccount = NumCreditedAccount;
        this.NumDebitedAccount = NumDebitedAccount;
    }

    public TransactionDto(Long idTransaction, String transactionType, int amount, Date transactionDate, AccountEntity NumDebitedAccount, AccountEntity NumCreditedAccount) {
        this.idTransaction = idTransaction;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.NumCreditedAccount = NumCreditedAccount;
        this.NumDebitedAccount = NumDebitedAccount;
    }


    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long id) {
        this.idTransaction = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public AccountEntity getNumDebitedAccount() {
        return NumDebitedAccount;
    }

    public void setNumDebitedAccount(AccountEntity numDebitedAccount) {
        NumDebitedAccount = numDebitedAccount;
    }

    public AccountEntity getNumCreditedAccount() {
        return NumCreditedAccount;
    }

    public void setNumCreditedAccount(AccountEntity numCreditedAccount) {
        NumCreditedAccount = numCreditedAccount;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}