package fr.iut.montreuil.lpcsid.web.dto;

import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youniik-nana on 10/03/15.
 */

public class AccountDto {

    private Long id;
    private String libelle;
    protected double balance = 0;// la balance du compte est initialisé à 0
    private String type;
    private List<TransactionEntity> operations = new ArrayList<TransactionEntity>();
    private CustomerEntity customer;

    public AccountDto() {
    }


    public AccountDto(Long id, String libelle, double balance, CustomerEntity customer) {
        this.id = id;
        this.libelle = libelle;
        this.balance = balance;
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

