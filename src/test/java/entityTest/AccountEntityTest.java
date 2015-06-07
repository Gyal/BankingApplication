package entityTest;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by NIIRO on 26/04/2015.
 */
public class AccountEntityTest {

    @Test
    public void construteurEntity() {

        Long id = 5L;
        String libelle = "Current account";
        double balance = 1000.00;
        double max_balance = 3000.00;
        String type = "CURRENT";
        Date created = new Date();
        double  taxe = 10.00;
        CustomerEntity customer = new CustomerEntity();

        AccountEntity test = new AccountEntity(id,libelle,balance, max_balance, type, created, taxe, customer);

        assertThat(customer, notNullValue());
        assertThat(test.getId(), is(id));
        assertThat(test.getLibelle(), is(libelle));
        assertThat(test.getBalance(), is(balance));
        assertThat(test.getMAX_BALANCE(), is(max_balance));
        assertThat(test.getType(), is(type));
        assertThat(test.getDateCreated(), is(created));
        assertThat(test.getTaxation(), is(taxe));
        assertThat(test.getCustomer(), is(customer));
        }

    @Test
    public void withdraw() {
        Long id = 5L;
        String libelle = "Current account";
        double balance = 1000.00;
        double max_balance = 3000.00;
        String type = "CURRENT";
        Date created = new Date();
        double  taxe = 10.00;
        CustomerEntity customer = new CustomerEntity();
        int  retrait = 100;

        double newBalance = balance - retrait;

        AccountEntity account = new AccountEntity(id,libelle,balance, max_balance, type, created, taxe, customer);
        account.withDraw(retrait);

        assertThat(account, notNullValue());
        assertThat(account.getBalance(), is(newBalance));
    }

    @Test
    public void deposit() {
        Long id = 5L;
        String libelle = "Current account";
        double balance = 1000.00;
        double max_balance = 3000.00;
        String type = "CURRENT";
        Date created = new Date();
        double  taxe = 10.00;
        CustomerEntity customer = new CustomerEntity();
        int  depot = 100;

        double newBalance = balance + depot;

        AccountEntity account = new AccountEntity(id,libelle,balance, max_balance, type, created, taxe, customer);
        account.deposit(depot);

        assertThat(account, notNullValue());
        assertThat(account.getBalance(), is(newBalance));
    }

    @Test
    public void transfert() {
        Long id = 5L;
        String libelle = "Current account";
        double balance = 1000.00;
        double max_balance = 3000.00;
        String type = "CURRENT";
        Date created = new Date();
        double  taxe = 10.00;
        CustomerEntity customer = new CustomerEntity();

        AccountEntity accountDebited = new AccountEntity(id,libelle,balance, max_balance, type, created, taxe, customer);
        AccountEntity accountCredited = new AccountEntity(id,libelle,balance, max_balance, type, created, taxe, customer);

        int tranfertAmount = 100;
        double newBalanceCrediteur = balance + tranfertAmount;
        double newBalanceDebiteur = balance - tranfertAmount;

        accountDebited.transfert(accountDebited, accountCredited, tranfertAmount);

        assertThat(accountDebited, notNullValue());
        assertThat(accountCredited, notNullValue());
        assertThat(accountDebited.getBalance(), is(newBalanceDebiteur));
        assertThat(accountCredited.getBalance(), is(newBalanceCrediteur));
    }
}
