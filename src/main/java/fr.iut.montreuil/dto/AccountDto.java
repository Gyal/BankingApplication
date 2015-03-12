package fr.iut.montreuil.dto;


import fr.iut.montreuil.entity.TransactionEntity;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by youniik-nana on 10/03/15.
 */

public class AccountDto {

    private Long idAccount;

    private String libelleAccount;

    protected double balance;

    private List<TransactionEntity> transactions = new ArrayList<TransactionEntity>();

    public AccountDto(Long idAccount, String libelleAccount, double balance) {
        this.idAccount = idAccount;
        this.libelleAccount = libelleAccount;
        this.balance= balance;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public String getLibelleAccount() {
        return libelleAccount;
    }
    public void setLibelleAccount(String libelleAccount) {
        this.libelleAccount = libelleAccount;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(int solde) {
        this.balance = balance;
    }

    public void debit(int amount){ this.balance = this.balance - amount; };

    public void credit(int amount) { this.balance = this.balance + amount; };

}
