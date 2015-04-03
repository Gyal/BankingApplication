package fr.iut.montreuil.lpcsid.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by NIIRO on 18/02/2015.
 * Modify By melina on 15/03/2015.
 */

@Entity
@Table(name = "operation")
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operation_id")
    private Long idTransaction;

    @Column(nullable = false)
    private String transactionType;

    @OneToOne
    private OperationDetailEntity operationDetail;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private Date transactionDate;

    /**
     * Récupération du compte bancaire associé
     *
     * @return Compte bancaire associé
     */
    @OneToOne
    public AccountEntity account;

    public TransactionEntity() {

    }

    // Créé par l'utilisateur
    public TransactionEntity(String transactionType, int amount, Date transactionDate, AccountEntity account) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }

    /*public TransactionEntity(Long idTransaction, String transactionType, int amount, Date transactionDate) {
        this.idTransaction = idTransaction;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }*/


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
