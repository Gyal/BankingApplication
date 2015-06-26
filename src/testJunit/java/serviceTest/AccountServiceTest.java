package serviceTest;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.service.AccountService;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by NIIRO on 22/05/2015.
 */
public class AccountServiceTest {

    @Test
    public void AccountTaxation() {
        Long id = 15L;
        String libelle = "COURANT";
        double balance = 12000.00;
        double MAX_BALANCE = 30000.00;
        String type = "CURRENT";
        Date dateCreated = new Date();
        double taxation = 10.00;
        CustomerEntity customer = new CustomerEntity();

        double deductTest = taxation * balance;
        double result = balance - deductTest / 100;

        AccountEntity accountEntity = new AccountEntity(id, libelle,balance,MAX_BALANCE, type, dateCreated, taxation, customer);

        assertThat(accountEntity, notNullValue());
        assertThat(accountEntity.getBalance(), is(balance));
        assertThat(accountEntity.getTaxation(), is(taxation));

        double deduct = accountEntity.getTaxation() * accountEntity.getBalance();
        accountEntity.setBalance(accountEntity.getBalance() - deduct / 100);

        assertThat(deduct, is(deductTest));
        assertThat(accountEntity.getBalance(), is(result));
    }

    @Test
    public void AccountTaxationService(){

        AccountService accountService = new AccountService();

    }

}
