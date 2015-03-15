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

    //  @JoinTable(name = "transaction_id", joinColumns = {@JoinColumn(name = "account_id")}, inverseJoinColumns = {@JoinColumn(name = "account_id")})


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Long idTransaction;

    @Column(nullable = false)
    private String transactionName;

   /* @Column(nullable = false)
    private int NumDebitedAccount;

    @Column(nullable = false)
    private int NumCreditedAccount;
    */

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private Date transactionDate;

    @Column(nullable = false)
    private String type;

    public TransactionEntity() {

    }

    public TransactionEntity(Long idTransaction, String transactionName, int amount, Date transactionDate, String type) {
        this.idTransaction = idTransaction;
        this.transactionName = transactionName;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.type = type;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long id) {
        this.idTransaction = id;
    }

    public String getTransactionName() { return transactionName; }
    public void setTransactionName(String transactionName) { this.transactionName = transactionName; }

    /*public int getNumDebitedAccount() {
        return NumDebitedAccount;
    }
    public void setNumDebitedAccount(int numDebitedAccount) { NumDebitedAccount = numDebitedAccount; }

    public int getNumCreditedAccount() { return NumCreditedAccount; }
    public void setNumCreditedAccount(int numCreditedAccount) { NumCreditedAccount = numCreditedAccount; }
*/
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    //public void transfer(int numDebitedAccount, int numCreditedAccount, int amount, Date transactionDate){
        /* à récupérer de la base */
    //   long idTransfert = 123; /* Cette valeur sera à valeur + 1 du dernier virement de la base de données */
    // TransactionEntity virement = new TransactionEntity(idTransfert,"Virement", amount, transactionDate,"DEPOSIT" );
    // }


    // public void depot(int numCreditedAccount, int amount, Date transactionDate){
        /* à récupérer de la base */
    //  long idDepot = 123; /* Cette valeur sera à valeur + 1 du dernier dépot de la base de données */
    // int numDebitedAccount = 0;
    // TransactionEntity virement = new TransactionEntity(idDepot,"Depot",numDebitedAccount, numCreditedAccount, amount, transactionDate);
    //}

    //public void retrait(int numDebitedAccount, int amount, Date transactionDate){
        /* à récupérer de la base */
    //    long idRetrait = 123; /* Cette valeur sera à valeur + 1 du dernier dépot de la base de données */
    //   int numCreditedAccount = 0;
    //   TransactionEntity virement = new TransactionEntity(idRetrait,"Depot",numDebitedAccount, numCreditedAccount, amount, transactionDate);
    // }
}
