package fr.iut.montreuil.lpcsid.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Mélina on 10/03/2015.
 */

@Entity
@Table(name = "customer")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idCustomer", scope=CustomerEntity.class)
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
    private String dateOfBirth;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String connexionLogin;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade=CascadeType.ALL)
    private List<AccountEntity> accounts;


    // Par l'utilisateur
    public CustomerEntity(Long id){
        this.idCustomer = id;
    }
    public static CustomerEntity newCustomerEntity() {
        return newCustomerEntity();
    }

    public CustomerEntity(String civilities, String lastname, String firstName, String dateOfBirth, String street, String city, String country, String zipCode, String mail, String phoneNumber, String connexionLogin, String password) {
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

    public CustomerEntity(Long idCustomer, String civilities, String lastname, String firstName, String dateOfBirth, String street, String city, String country, String zipCode, String mail, String phoneNumber, String connexionLogin, String password) {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateBirthDay) {
        this.dateOfBirth = dateBirthDay;
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
        this.city = ville;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
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
        this.civilities = civilities;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String number) {
        this.phoneNumber = number;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {return lastname;}
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
  public void setAccounts(List<AccountEntity> accounts) {
        this.accounts = accounts;
    }

    public String getConnexionLogin() {
        return connexionLogin;
    }
    public void setConnexionLogin(String connexionLogin) {
        this.connexionLogin = connexionLogin;
    }
}
