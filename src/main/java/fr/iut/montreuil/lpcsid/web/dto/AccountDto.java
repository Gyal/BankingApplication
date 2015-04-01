package fr.iut.montreuil.lpcsid.web.dto;

import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by youniik-nana on 10/03/15.
 */

public class AccountDto {

    private Long id;
    private String libelle;
    private double balance = 0;
    private double MAX_BALANCE;
    private String type;
    private Date dateCreated;
    private static double taxation;

    @ManyToMany()// annotation pour tracer le type de base de l'objet
    private List<TransactionEntity> operations = new ArrayList<TransactionEntity>();


    @OneToOne
    private CustomerEntity customer;

    /* Pour le dozer */
    public AccountDto() {
    }

    public static AccountDto newAccountDto() {
        return newAccountDto();
    }


    // Création d'un compte par l'utilisateur*/
    public AccountDto(String libelle, String type, CustomerEntity customer) {
        this.libelle = libelle;
        this.type = type;
        this.customer = customer;
    }

    public AccountDto(String libelle, double balance, double MAX_BALANCE, String type, Date dateCreated, double taxation, CustomerEntity customer) {
        this.libelle = libelle;
        this.balance = balance;
        this.MAX_BALANCE = setMaxBalance();
        this.type = type;
        this.dateCreated = new Date();
        this.taxation = setTaxation();
        this.customer = customer;
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
        return MAX_BALANCE;
    }

    /* SetMaxBalance : Si c'est un compte courant alors MAX_BALANCE = 2500, si PEL alors 85000*/
    public double setMaxBalance() {
        if (this.type.equals("CURRENT")) {
            this.MAX_BALANCE = 25000;
        }
        if (this.type.equals("PEL")) {
            this.MAX_BALANCE = 850000;
        }
        return this.MAX_BALANCE;
    }

    public double getTaxation() {

        return taxation;
    }

    public double setTaxation() {
        if (this.type.equals("CURRENT")) {
            this.taxation = 0;
        }
        if (this.type.equals("PEL")) {
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
    public void deposit(final int amount) {
        Date date = new Date();
        if (amount >= 0 && amount + balance <= this.getMaxBalance()) {
            balance = balance + amount;
        }
    }

    /* Withdrawal */
    // Opération Débit(retrait)
    public void withDraw(final int amount) {
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
    public int transfert(final int amount, AccountDto accountDtoDebited, AccountDto accountDtoCredited) {
        Date date = new Date();
        if (accountDtoDebited.type == "CURRENT" && accountDtoCredited.type == "CURRENT" && amount > 0 && accountDtoDebited.balance - amount >= 0) {
            double transactionDebit = accountDtoCredited.balance - amount;
            double transactionCredit = accountDtoCredited.balance + amount;
        }
        return amount;
    }
}