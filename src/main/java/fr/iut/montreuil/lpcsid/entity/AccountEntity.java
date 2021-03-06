package fr.iut.montreuil.lpcsid.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Mélina on 07/03/2015.
 */
@Entity
@Table(name = "account")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=AccountEntity.class)

public class AccountEntity{
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountEntity.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private double balance = 0;

    @Column(nullable = false)
    private double MAX_BALANCE;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Date dateCreated;

    @Column(nullable = false)
    private double taxation;

    // To do : à supprimer et à configurer dans le repository
    /*@OneToMany(cascade=CascadeType.ALL)// annotation pour tracer le type de base de l'objet
    private List<TransactionEntity> operations = new ArrayList<TransactionEntity>();*/

    @OneToOne
    private CustomerEntity customer;

    /* Pour le dozer */
    public AccountEntity() {
    }

    public static AccountEntity newAccountEntity() {
        return newAccountEntity();
    }

    // Création d'un compte par l'utilisateur*/
    public AccountEntity(String libelle, String type, CustomerEntity customer) {
        this.libelle = libelle;
        this.type = type;
        this.customer = customer;
    }

    public AccountEntity(Long id, String libelle, double balance, double MAX_BALANCE, String type, Date dateCreated, double taxation, CustomerEntity customer) {
        this.id = id;
        this.libelle = libelle;
        this.balance = balance;
        this.MAX_BALANCE = MAX_BALANCE;
        this.type = type;
        this.dateCreated = dateCreated;
        this.taxation = taxation;
        this.customer = customer;
    }

    public AccountEntity(String libelle, double balance, double MAX_BALANCE, String type, Date dateCreated, double taxation, CustomerEntity customer) {
        this.libelle = libelle;
        this.balance = balance;
        this.MAX_BALANCE = MAX_BALANCE;
        this.type = type;
        this.dateCreated = dateCreated;
        this.taxation = taxation;
        this.customer = customer;
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

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    /* Deposit */
    // Opération Crédit(ajout)
    public void deposit(final int amount) {
        if (amount >= 0 && amount + balance <= this.getMAX_BALANCE()) {
            balance = balance + amount;
        }
    }

    /* Withdraw */
    // Opération Débit(retrait)
    public void withDraw(int amount) {
        if (amount > 0 && balance - amount >= 0) {
            balance = balance - amount;
        }
    }

    /* Transfert */
    /*
    * Si les comptes sont des comptes courant, le montant du transfert est <0 et après le transfert le solde du compte débité est <0 alors tranfert
    * Attention : Le transfert sera possible vers un compte même si après transfert le solde dépasse 25000 euros, sinon il faut rajouter une vérification
    */
    @Transactional
    public boolean transfert(AccountEntity from, AccountEntity to, int amount) {
        String current = "CURRENT";
        Boolean currentIsTrue = current.equals(from.getType()) && current.equals(to.getType());
        Boolean amountIsTrue = amount > 0;
        Boolean soldePositive = from.getBalance() - amount >= 0;
        Boolean maxBalanceAtteign = to.getBalance() + amount < 25000;

        if (!currentIsTrue.equals(true)) {
            LOGGER.info(" LOG: L'un des compte à créditer n'est pas un compte courant, type du compte débité:  {}, type du compte crédité {}", from.getType(), to.getType());
            return false;
        }
        if (!amountIsTrue.equals(true)) {
            LOGGER.error(" LOG: le montant est inférieur à 0 {} ", amountIsTrue);
            return false;
        }
        if (!soldePositive.equals(true)) {
            LOGGER.error(" LOG: le solde du compte à débiter n'est pas suffisant");
            return false;
        }
        if (!maxBalanceAtteign.equals(true)) {
            LOGGER.error(" LOG: Le compte à créditer atteint le montant maximum autorié", to.getMAX_BALANCE());
            return false;
        }

        if (amountIsTrue.equals(true) & currentIsTrue.equals(true) & soldePositive.equals(true) && maxBalanceAtteign.equals(true)) {
            double balanceFrom = from.getBalance() - amount;
            double balanceTo = to.getBalance() + amount;
            from.setBalance(balanceFrom);
            LOGGER.info("Le compte a bien été débité : {} ", from.getBalance());
            LOGGER.info("Nouveau solde du débiteur : {} ", to.getBalance());
            to.setBalance(balanceTo);
            LOGGER.info("Le compte a bien été crédité : {} ", to.getId());
            LOGGER.info("Nouveau solde du créditeur : {}", from.getId());
            return true;
        } else {
            LOGGER.info(" Le traitement à échouer");
        }
        return false;
    }
}







