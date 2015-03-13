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
    private Long id;

    private String name;

    protected double balance;

    private String type;

    @ElementCollection// annotation pour tracer le type de base de l'objet
    private List<TransactionEntity> operations = new ArrayList<TransactionEntity>();

    public AccountEntity(Long id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance= balance;
    }

    public long getIdAccount() {
        return id;
    }

    public String getNameAccount() {
        return name;
    }
    public void setNameAccount(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(int solde) {
        this.balance = balance;
    }

    public void debit(int amount){ this.balance = this.balance - amount; };

    public void credit(int amount) { this.balance = this.balance + amount; };

    public List<TransactionEntity> getOperations(){
        return operations;
    }

}



