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
    //@JoinTable(name = "customer_account", joinColumns = {@JoinColumn(name = "account_id")}, inverseJoinColumns = {@JoinColumn(name = "customer_id")})


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;
    private String libelle;
    private double balance = 0;
    private double MAX_BALANCE;
    private String type;
    private Date dateCreated;
    private static double taxation;

    @ElementCollection// annotation pour tracer le type de base de l'objet
    private List<TransactionEntity> operations = new ArrayList<TransactionEntity>();


    public AccountEntity() {
    }

    public AccountEntity(Long id, String libelle, double balance, String type) {
        this.id = id;
        this.libelle = libelle;
        this.balance = balance;
    }

    public static AccountEntity newAccountEntity() {
        return newAccountEntity();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }
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
        if (type == "CURRENT") {
            MAX_BALANCE = 25000;
        }
        if (type == "PEL") {
            MAX_BALANCE = 850000;
        }
        return MAX_BALANCE;
    }

    /* SetMaxBalance : si le montant saisie est <0 et différent de 0
    * et si le montant + le montant de la balance ne dépasse pas le montant du plafond alors met à jour la balance
   */

    public void setMaxBalance(double maxBalance) {
        if (maxBalance < 0 && maxBalance + balance <= this.getMaxBalance()) {
            MAX_BALANCE = maxBalance;
        }
    }

    public double getTaxation() {
        if (type == "CURRENT") {
            taxation = 0;
        }
        if (type == "PEL") {
            taxation = 0.06;
        }
        return taxation;
    }

    public static void setTaxation(double taxation) {
        AccountEntity.taxation = taxation;
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

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<TransactionEntity> getOperations() {
        return operations;
    }

    public int withDraw(final int amount) {
        if (amount > 0 && balance - amount >= 0) {
            balance = balance - amount;
            return amount;
        } else {
            return 0;
        }
    }


    // Opération Crédit(ajout)
    public void deposit(final int amount) {
        if (amount >= 0 && amount + balance >= this.getMaxBalance()) {
            balance = balance + amount;
        }
    }

    /*
    * Si les comptes sont des comptes courant, le montant du transfert est <0 et après le transfert le solde du compte débité est <0 alors tranfert
    * Attention : Le transfert sera possible vers un compte même si après transfert le solde dépasse 25000 euros, sinon il faut rajouter une vérification
     */
    @Transactional
    public int tranfert(final int amount, AccountEntity accountEntityDebited, AccountEntity accountEntityCredited) {

        if (accountEntityDebited.type == "CURRENT" && accountEntityCredited.type == "CURRENT" && amount < 0 && accountEntityDebited.balance - amount >= 0) {
            double transactionDebit = accountEntityCredited.balance - amount;
            double transactionCredit = accountEntityCredited.balance + amount;
        }
        return amount;
    }


}



