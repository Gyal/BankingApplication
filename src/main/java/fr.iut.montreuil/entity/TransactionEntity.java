package fr.iut.montreuil.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by NIIRO on 18/02/2015.
 */

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @JoinTable(name = "transaction_id", joinColumns = {@JoinColumn(name = "account_id")}, inverseJoinColumns = {@JoinColumn(name = "account_id")})


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Long idTransaction;

    @Column(nullable = false)
    private String transactionName;

    @Column(nullable = false)
    private int NumDebitedAccount;

    @Column(nullable = false)
    private int NumCreditedAccount;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private Date transactionDate;

    public TransactionEntity(Long idTransaction, String transactionName, int numDebitedAccount, int numCreditedAccount, double amount, Date transactionDate) {
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

    public String getTransactionName() { return transactionName; }
    public void setTransactionName(String transactionName) { this.transactionName = transactionName; }

    public int getNumDebitedAccount() {
        return NumDebitedAccount;
    }
    public void setNumDebitedAccount(int numDebitedAccount) { NumDebitedAccount = numDebitedAccount; }

    public int getNumCreditedAccount() { return NumCreditedAccount; }
    public void setNumCreditedAccount(int numCreditedAccount) { NumCreditedAccount = numCreditedAccount; }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void virement(int numDebitedAccount, int numCreditedAccount, double amount, Date transactionDate){
        /* à récupérer de la base */
        long idVirement = 123; /* Cette valeur sera à valeur + 1 du dernier virement de la base de données */
        TransactionEntity virement = new TransactionEntity(idVirement,"Virement",numDebitedAccount, numCreditedAccount, amount, transactionDate);
    }

    public void depot(int numCreditedAccount, double amount, Date transactionDate){
        /* à récupérer de la base */
        long idDepot = 123; /* Cette valeur sera à valeur + 1 du dernier dépot de la base de données */
        int numDebitedAccount = 0;
        TransactionEntity virement = new TransactionEntity(idDepot,"Depot",numDebitedAccount, numCreditedAccount, amount, transactionDate);
    }

    public void retrait(int numDebitedAccount, double amount, Date transactionDate){
        /* à récupérer de la base */
        long idRetrait = 123; /* Cette valeur sera à valeur + 1 du dernier dépot de la base de données */
        int numCreditedAccount = 0;
        TransactionEntity virement = new TransactionEntity(idRetrait,"Depot",numDebitedAccount, numCreditedAccount, amount, transactionDate);
    }
}
