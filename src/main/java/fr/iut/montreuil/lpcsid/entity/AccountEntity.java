package fr.iut.montreuil.lpcsid.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    private double balance = 0;// la balance du compte est initialisé à 0
    private String type;

    @ElementCollection// annotation pour tracer le type de base de l'objet
    private List<TransactionEntity> operations = new ArrayList<TransactionEntity>();


    public AccountEntity() {
    }

    public AccountEntity(Long id, String libelle, double balance) {
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

    public int withDraw(final int amount) {
        if (amount >= 0 &&
                balance - amount >= 0) {
            balance = balance - amount;
            return amount;
        } else {
            return 0;
        }
    }

    public void deposit(final int amount) {
        if (amount >= 0) {
            balance = balance + amount;
        }
    }

    public List<TransactionEntity> getOperations() {
        return operations;
    }

}



