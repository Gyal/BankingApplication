package dto;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by youniik-nana on 12/03/15.
 */

public class CustomerDto {

    //Variables
    private Long idCustomer;

    private String civilities;

    private String lastname;

    private String firstName;

    private Date dateOfBirth;

    private String street;

    private String city;

    private String country;

    private int zipCode;

    private String mail;

    private int phoneNumber;

    private int connexionLogin;

    private String password;

    private List<AccountEntity> accountEntities;

    public CustomerDto() {
    }

    public CustomerDto(Long idCustomer, String civilities, String lastname, String firstName, Date dateOfBirth, String street, String city, String country, int zipCode, String mail, int phoneNumber, int connexionLogin, String password) {
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

    //getter & setters
    public Long getIdCustomer() {
        return idCustomer;
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

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<AccountEntity> getAccountEntities() {
        return accountEntities;
    }

    public int getConnexionLogin() {
        return connexionLogin;
    }

    public void setConnexionLogin(int connexionLogin) {
        this.connexionLogin = connexionLogin;
    }

    public long inscription(String civilities, String lastname, String firstName, Date dateOfBirth, String street, String city, String country, int zipCode, String mail, int phoneNumber, int connexionLogin, String password) {
        /* à récupérer de la base */
        long idCustomer = 123; /* Cette valeur sera à valeur + 1 du l'id du dernière inscrit de la base de données */
        CustomerDto newCustomer = new CustomerDto(idCustomer, civilities, lastname, firstName, dateOfBirth, street, city, country, zipCode, mail, phoneNumber, connexionLogin, password);
        return idCustomer; /* Récupération de l'id pour permettre le récapitulatif de l'inscription */
    }

    public boolean connexion(int login, String pwd) {

        if (this.connexionLogin == login && this.password == pwd) {
            return true;
        } else return false;
    }
}
