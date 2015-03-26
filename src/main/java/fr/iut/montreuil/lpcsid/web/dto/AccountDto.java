package fr.iut.montreuil.lpcsid.web.dto;

import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;

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

    public AccountDto() {
    }


    public AccountDto(Long id, String libelle, double balance, double MAX_BALANCE, String type, Date dateCreated, CustomerEntity customer) {
        this.id = id;
        this.libelle = libelle;
        this.balance = balance;
        this.MAX_BALANCE = MAX_BALANCE;
        this.type = type;
        this.dateCreated = dateCreated;
        this.customer = customer;
    }

    public static AccountDto newAccountDto() {
        return newAccountDto();
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

    public List<TransactionEntity> getOperations() {
        return operations;
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

    public String getType() {
        return type;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public static double getTaxation() {
        return taxation;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public static void setTaxation(double taxation) {
        AccountDto.taxation = taxation;
    }

    public void setOperations(List<TransactionEntity> operations) {
        this.operations = operations;
    }

    // Méthode withDraw(amount) : débit d'un montant
    public int withDraw(final int amount) {
        if (amount >= 0 &&
                balance - amount >= 0) {
            balance = balance - amount;
            return amount;
        } else {
            return 0;
        }
    }

    // Méthode deposit(ammount) : depot d'un montant
    public void deposit(final int amount) {
        if (amount >= 0) {
            balance = balance + amount;
        }
    }
}

