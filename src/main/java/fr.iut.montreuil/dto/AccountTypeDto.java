package fr.iut.montreuil.dto;
/*
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by youniik-nana on 11/03/15.
 */

public class AccountTypeDto {

    private Long idAccountType;

    private String title;

    private double celling;

    private double percentage;

    public AccountTypeDto() {

    }

    public AccountTypeDto(Long idAccountType, String title, double celling, double percentage) {
        this.idAccountType = idAccountType;
        this.title = title;
        this.celling = celling;
        this.percentage = percentage;
    }

    public long getIdAccountType() {
        return idAccountType;
    }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getCelling() {
        return celling;
    }
    public void setCelling(double celling) {
        this.celling = celling;
    }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}

