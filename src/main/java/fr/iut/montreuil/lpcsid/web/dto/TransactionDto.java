package fr.iut.montreuil.lpcsid.web.dto;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;

import java.util.Date;

/**
 * Created by youniik-nana on 12/03/15.
 */

public class TransactionDto {

    private Long idTransaction;

    private String transactionType;

    private AccountDto NumDebitedAccount;

    private AccountDto NumCreditedAccount;

    private int amount;

    private Date transactionDate;

    private OperationDetailDto operationDetail;
    /**
     * Récupération du compte bancaire associé
     *
     * @return Compte bancaire associé
     */

    public AccountDto account;

    public TransactionDto() {

    }

    // Créé par l'utilisateur
    public TransactionDto(String transactionType, int amount, Date transactionDate, AccountDto account) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }

    public TransactionDto(Long idTransaction, String transactionType, int amount, Date transactionDate, AccountDto account) {
        this.idTransaction = idTransaction;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }

    // Créé par l'utilisateur
    public TransactionDto(String transactionType, int amount, Date transactionDate, AccountDto NumDebitedAccount, AccountDto NumCreditedAccount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.NumCreditedAccount = NumCreditedAccount;
        this.NumDebitedAccount = NumDebitedAccount;
    }

    public TransactionDto(Long idTransaction, String transactionType, int amount, Date transactionDate, AccountDto NumDebitedAccount, AccountDto NumCreditedAccount) {
        this.idTransaction = idTransaction;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.NumCreditedAccount = NumCreditedAccount;
        this.NumDebitedAccount = NumDebitedAccount;
    }

    // Créé par l'utilisateur
    public TransactionDto(String transactionType, int amount, Date transactionDate, OperationDetailDto operationDetail) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.operationDetail = operationDetail;
    }

    public TransactionDto(Long idTransaction, String transactionType, int amount, Date transactionDate, OperationDetailDto operationDetail) {
        this.idTransaction = idTransaction;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.operationDetail = operationDetail;
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

    public AccountDto getNumDebitedAccount() {
        return NumDebitedAccount;
    }

    public void setNumDebitedAccount(AccountDto numDebitedAccount) {
        NumDebitedAccount = numDebitedAccount;
    }

    public AccountDto getNumCreditedAccount() {
        return NumCreditedAccount;
    }

    public void setNumCreditedAccount(AccountDto numCreditedAccount) {
        NumCreditedAccount = numCreditedAccount;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public OperationDetailDto getOperationDetail() {
        return operationDetail;
    }

    public void setOperationDetail(OperationDetailDto operationDetail) {
        this.operationDetail = operationDetail;
    }
}