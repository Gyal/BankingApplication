package fr.iut.montreuil.entity;

import javax.persistence.*;

/**
 * Created by NIIRO on 18/02/2015.
 */

@Entity
@Table(name = "account_type")
public class AccountTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_type_id")
    private Long idAccountType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double celling;

    @Column(nullable = false)
    private double percentage;

    public AccountTypeEntity() {

    }

    public AccountTypeEntity(Long idAccountType, String title, double celling, double percentage) {
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
