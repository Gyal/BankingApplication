package dto;


import entity.TransactionEntity;

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


    public AccountDto() {
    }

    public AccountDto(Long id, String libelle, double balance) {
        this.id = id;
        this.libelle = libelle;
        this.balance = balance;
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

