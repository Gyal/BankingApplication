package fr.iut.montreuil.lpcsid.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mélina on 10/03/2015.
 */

@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable {

    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long idCustomer;

    @Column(nullable = false)
    private String civilities;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private int zipCode;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private int phoneNumber;

    @Column(nullable = false)
    private int connexionLogin;

    @Column(nullable = false)
    private String password;

    @OneToMany
    private List<AccountEntity> accounts = new ArrayList<AccountEntity>();

    public CustomerEntity(Long idCustomer, String civilities, String lastname, String firstName, Date dateOfBirth, String street, String city, String country, int zipCode, String mail, int phoneNumber, int connexionLogin, String password) {
        this.idCustomer = idCustomer;
        this.civilities = civilities;
        this.lastname = lastname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.connexionLogin = connexionLogin;
        this.password = password;
    }

    public CustomerEntity() {
    }
    //getter & setters
    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long id) {
        this.idCustomer = id;
    }

    public String getName() {
        return lastname;
    }
    public void setName(String name) {
        this.lastname = name;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateBirthDay) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String ville) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCivilities() {
        return civilities;
    }
    public void setCivilities(String civilities) {
        civilities = civilities;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int number) {
        this.phoneNumber = number;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public List<AccountEntity> getAccounts() {
        return accounts;
    }
    public int getConnexionLogin() { return connexionLogin; }
    public void setConnexionLogin(int connexionLogin) { this.connexionLogin = connexionLogin; }

    public long inscription(String civilities, String lastname, String firstName, Date dateOfBirth, String street, String city, String country, int zipCode, String mail, int phoneNumber, int connexionLogin, String password){
        /* à récupérer de la base */
        long idCustomer = 123; /* Cette valeur sera à valeur + 1 du l'id du dernière inscrit de la base de données */
        CustomerEntity newCustomer = new CustomerEntity(idCustomer, civilities, lastname, firstName, dateOfBirth, street, city, country, zipCode, mail, phoneNumber, connexionLogin, password);
        return idCustomer; /* Récupération de l'id pour permettre le récapitulatif de l'inscription */
    }

    public boolean connexion(int login, String pwd){

        if (this.connexionLogin == login && this.password == pwd){
            return true;
        }
        else return false;
    }
}

