package fr.iut.montreuil.lpcsid.web.dto;

import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by youniik-nana on 10/03/15.
 */

public class AccountDto {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDto.class);
    private Long id;
    private String libelle;
    private double balance = 0;
    private double MAX_BALANCE;
    private String type;
    private Date dateCreated;
    private double taxation;
    private List<TransactionEntity> operations = new ArrayList<TransactionEntity>();
    private CustomerEntity customer;

    /* Pour le dozer */
    public AccountDto() {
    }

    public static AccountDto newAccountDto() {
        return newAccountDto();
    }


    // Création d'un compte par l'utilisateur*/
    public AccountDto(String libelle, String type, CustomerEntity customer) {
        this.libelle = libelle;
        this.type = type;
        this.customer = customer;
    }

    public AccountDto(String libelle, double balance, double MAX_BALANCE, String type, Date dateCreated, double taxation, CustomerEntity customer) {
        this.libelle = libelle;
        this.balance = balance;
        this.MAX_BALANCE = setMaxBalance();
        this.type = type;
        this.dateCreated = dateCreated;
        this.taxation = setTaxation();
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

    public double getMaxBalance() {
        return MAX_BALANCE;
    }

    /* SetMaxBalance : Si c'est un compte courant alors MAX_BALANCE = 2500, si SAVINGS alors 85000*/
    public double setMaxBalance() {
        if (this.type.equals("CURRENT")) {
            LOGGER.info(" LOG: accountType is {}, so MAX_BALANCE is setted to 25000 ", this.type);
            this.MAX_BALANCE = 25000;
        }
        if (this.type.equals("SAVINGS")) {
            LOGGER.info(" LOG:accountType is {}, so MAX_BALANCE is setted to 850000 ", this.type);

            this.MAX_BALANCE = 850000;
        }
        return MAX_BALANCE;
    }

    public double getTaxation() {
        return taxation;
    }

    public double setTaxation() {
        if (this.type.equals("SAVINGS")) {
            LOGGER.info(" LOG: accountType is {}, so taxation is setted to 0 ", this.type);
            this.taxation = 0;
        }
        if (this.type.equals("SAVINGS")) {
            LOGGER.info(" LOG: accountType is {}, so taxation is setted to 0.06 ", this.type);

            this.taxation = 0.06;
        }
        return taxation;
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

    public Date setDateCreated() {
        this.dateCreated = new Date();
        return dateCreated;
    }

    public List<TransactionEntity> getOperations() {
        return operations;
    }
    public void setOperations(List<TransactionEntity> operations) {
        this.operations = operations;
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
        if (amount >= 0 && amount + balance <= this.getMaxBalance()) {
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
    public int transfert(AccountDto from, AccountDto to, int amount) {
        if (from.getType().equals("CURRENT") && to.getType().equals("CURRENT") && amount > 0 && from.getBalance() - amount >= 0) {
            double balanceFrom = from.getBalance();
            balanceFrom = from.getBalance() - amount;
            double balanceTo = from.getBalance();
            balanceTo = to.getBalance() + amount;
            LOGGER.info(" Transaction ok ");

        } else {
            LOGGER.info(" LOG: L'un des compte à créditer n'est pas un compte courant, type du compte débité:  {}, type du compte crédité {}", from.getType(), to.getType());
        }
        return amount;
    }
}
