package fr.iut.montreuil.lpcsid.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by NIIRO on 18/02/2015.
 * Modify By melina on 15/03/2015.
 */

@Entity
@Table(name = "transaction")
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Long idTransaction;

    @Column(nullable = false)
    private String transactionType;

    @OneToOne
    private AccountEntity NumDebitedAccount;

    @OneToOne
    private AccountEntity NumCreditedAccount;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private Date transactionDate;

    /**
     * Récupération du compte bancaire associé
     *
     * @return Compte bancaire associé
     */
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    public AccountEntity account;

    public TransactionEntity() {

    }

    public TransactionEntity(Long idTransaction, String transactionType, int amount, Date transactionDate, AccountEntity NumDebitedAccount, AccountEntity NumCreditedAccount) {
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