package fr.iut.montreuil.lpcsid.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountEntity.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private double balance = 0;

    @Column(nullable = false)
    private double MAX_BALANCE;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Date dateCreated;

    @Column(nullable = false)
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

    // Création d'un compte par l'utilisateur*/
    public AccountEntity(String libelle, String type, CustomerEntity customer) {
        this.libelle = libelle;
        this.type = type;
        this.customer = customer;
    }

    public AccountEntity(String libelle, double balance, double MAX_BALANCE, String type, Date dateCreated, double taxation, CustomerEntity customer) {
        this.libelle = libelle;
        this.balance = balance;
        this.MAX_BALANCE = setMaxBalance();
        this.type = type;
        this.dateCreated = new Date();
        this.taxation = setTaxation();
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

    /* SetMaxBalance : Si c'est un compte courant alors MAX_BALANCE = 2500, si PEL alors 85000*/
    public double setMaxBalance() {
        if (this.type.equals("CURRENT")) {
            LOGGER.info(" LOG: accountType is {}, so MAX_BALANCE is setted to 25000 ", this.type);
            this.MAX_BALANCE = 25000;
        }
        if (this.type.equals("PEL")) {
            LOGGER.info(" LOG:accountType is {}, so MAX_BALANCE is setted to 850000 ", this.type);

            this.MAX_BALANCE = 850000;
        }
        return this.MAX_BALANCE;
    }

    public double getTaxation() {

        return taxation;
    }

    public double setTaxation() {
        if (this.type.equals("CURRENT")) {
            LOGGER.info(" LOG: accountType is {}, so taxation is setted to 0 ", this.type);
            this.taxation = 0;
        }
        if (this.type.equals("PEL")) {
            LOGGER.info(" LOG: accountType is {}, so taxation is setted to 0.06 ", this.type);

            this.taxation = 0.06;
        }
        /*
        this.taxation = taxation;*/
        return this.taxation;
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
    public void withDraw(final int amount, AccountEntity debited) {
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
        if (accountEntityDebited.type.equals("CURRENT") && accountEntityCredited.type.equals("CURRENT") && amount > 0 && accountEntityDebited.balance - amount >= 0) {
            double transactionDebit = accountEntityCredited.balance - amount;
            double transactionCredit = accountEntityCredited.balance + amount;
        }
        return amount;
    }
}



