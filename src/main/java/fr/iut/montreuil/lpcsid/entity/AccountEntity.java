package fr.iut.montreuil.lpcsid.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mélina on 07/03/2015.
 */
@Entity
@Table(name = "account")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;
    private String libelle;
    private double balance = 0;
    private double MAX_BALANCE;
    private String type;
    private Date dateCreated;
    private double taxation;

    @ManyToMany()// annotation pour tracer le type de base de l'objet
    private List<TransactionEntity> operations = new ArrayList<TransactionEntity>();

    @OneToOne
    private CustomerEntity customer;

    /* Pour le dozer */
    public AccountEntity() {
    }
    public static AccountEntity newAccountEntity() {
        return newAccountEntity();
    }

    public AccountEntity(Long id, String libelle, String type, CustomerEntity customer) {
        this.id = id;
        this.libelle = libelle;
        this.type = type;
        this.customer = customer;
    }

    public AccountEntity(Long id, String libelle, String type, Date dateCreated, CustomerEntity customer) {


        this.id = id;
        this.libelle = libelle;
        this.balance = balance;
        this.MAX_BALANCE = MAX_BALANCE;
        this.type = type;
        this.dateCreated = new Date();
        this.customer = customer;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMaxBalance() {
        return MAX_BALANCE;
    }

    /* SetMaxBalance : si le montant saisie est <0 et différent de 0
    * et si le montant + le montant de la balance ne dépasse pas le montant du plafond alors met à jour la balance
    */

    public void setMaxBalance() {
        if (this.type == "CURRENT") {
            this.MAX_BALANCE = 25000;
        }
        if (this.type == "PEL") {
            this.MAX_BALANCE = 850000;
        }
      /*  if (maxBalance < 0 && maxBalance + balance <= this.MAX_BALANCE) {
            this.MAX_BALANCE = maxBalance;
        }*/
    }
    public double getTaxation() {

        return taxation;
    }

    public void setTaxation() {
        if (this.type == "CURRENT") {
            this.taxation = 0;
        }
        if (this.type == "PEL") {
            this.taxation = 0.06;
        }
        /*
        this.taxation = taxation;*/
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated() {
        this.dateCreated = new Date();
    }

    public List<TransactionEntity> getOperations() {
        return operations;
    }
    public void setOperations(List<TransactionEntity> operations) {
        this.operations = operations;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public double getMAX_BALANCE() {
        return MAX_BALANCE;
    }
    public void setMAX_BALANCE(double MAX_BALANCE) {
        this.MAX_BALANCE = MAX_BALANCE;
    }

    /* Deposit */
    // Opération Crédit(ajout)
    public void deposit(final int amount, AccountEntity accountCredited) {
        Date date = new Date();
        if (amount >= 0 && amount + balance <= this.getMaxBalance()) {
            balance = balance + amount;
        }
    }

    /* Withdrawal */
    // Opération Débit(retrait)
    public void withDrawal(final int amount, AccountEntity debited) {
        Date date = new Date();
        if (amount > 0 && balance - amount >= 0) {
            balance = balance - amount;
        }
    }

    /* Transfert */
    /*
    * Si les comptes sont des comptes courant, le montant du transfert est <0 et après le transfert le solde du compte débité est <0 alors tranfert
    * Attention : Le transfert sera possible vers un compte même si après transfert le solde dépasse 25000 euros, sinon il faut rajouter une vérification
     */
    @Transactional
    public int transfert(final int amount, AccountEntity accountEntityDebited, AccountEntity accountEntityCredited) {
        Date date = new Date();
        if (accountEntityDebited.type == "CURRENT" && accountEntityCredited.type == "CURRENT" && amount > 0 && accountEntityDebited.balance - amount >= 0) {
            double transactionDebit = accountEntityCredited.balance - amount;
            double transactionCredit = accountEntityCredited.balance + amount;
        }
        return amount;
    }
}



