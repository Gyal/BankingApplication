package fr.iut.montreuil.lpcsid.web.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by youniik-nana on 10/03/15.
 */

public class AccountDto implements Serializable {

    private Long id;
    private String libelle;
    private double balance = 0;
    private double MAX_BALANCE;
    private String type;
    private Date dateCreated;
    private double taxation;
    /* Pour le dozer */
    public AccountDto() {
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

    public double getMAX_BALANCE() {
        return MAX_BALANCE;
    }

    public void setMAX_BALANCE(double MAX_BALANCE) {
        this.MAX_BALANCE = MAX_BALANCE;
    }

    public double getTaxation() {
        return taxation;
    }

    public void setTaxation(double taxation) {
        this.taxation = taxation;
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

    public void setDateCreated(Date date) {
        this.dateCreated = date;
    }

}