package fr.iut.montreuil.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mélina on 07/03/2015.
 */
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long idAccount;

    private String libelleAccount;

    protected double balance;

    @ElementCollection// annotation pour tracer le type de base de l'objet
    private List<TransactionEntity> transactions = new ArrayList<TransactionEntity>();

    public AccountEntity(Long idAccount, String libelleAccount, double balance) {
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



