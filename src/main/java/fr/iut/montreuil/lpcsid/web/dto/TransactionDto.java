package fr.iut.montreuil.lpcsid.web.dto;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.OperationDetailEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by NIIRO on 18/02/2015.
 * Modify By melina on 15/03/2015.
 */

public class TransactionDto implements Serializable {

    private Long idTransaction;
    private String transactionType;

    private OperationDetailEntity operationDetail;
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
    public TransactionDto(String transactionType, int amount, Date transactionDate, AccountEntity account) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }

    public TransactionDto(Long idTransaction, String transactionType, int amount, Date transactionDate, AccountEntity account) {
        this.idTransaction = idTransaction;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }

    // Créé par l'utilisateur
    public TransactionDto(String transactionType, int amount, Date transactionDate, OperationDetailEntity operationDetail) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.operationDetail = operationDetail;
    }

    public TransactionDto(Long idTransaction, String transactionType, int amount, Date transactionDate, OperationDetailEntity operationDetail) {
        this.idTransaction = idTransaction;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.operationDetail = operationDetail;
    }

    public TransactionDto(String type, int amount, Date date, OperationDetailDto operationDetail) {
    }

    public TransactionDto(String type, int amount, Date date, AccountDto account) {

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

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public OperationDetailEntity getOperationDetail() {
        return operationDetail;
    }

    public void setOperationDetail(OperationDetailEntity operationDetail) {
        this.operationDetail = operationDetail;
    }
}
