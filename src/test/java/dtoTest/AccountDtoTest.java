package dtoTest;

import fr.iut.montreuil.lpcsid.config.DozerConfig;
import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.web.dto.AccountDto;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * AccountDto Tester.
 *
 * @author Melina
 * @version 1.0
 * @since mars 14, 2015
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DozerConfig.class})
public class AccountDtoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDtoTest.class);
    @Autowired
    private Mapper mapper;
    AccountDto accountDto;

    @Before
    public void setUp() throws Exception {
        // C'est l'instance que l'on va utiliser pour faire nos tests
        accountDto = new AccountDto();
    }

    /**
     * -----------------------------------------------------------------------------------
     *      Test de la transmission de notre objet vers le DTO correspondant
     * -----------------------------------------------------------------------------------
     **/

    @Test
    public void should_SerializeDTOToPojo_OnAccount() {

        String libelle = "lib";
        double balance = 10.2;
        String type = "CURRENT";
        Date date = new Date();
        String dateS = "Date";
        double max_balance = 300.00;
        double taxation = 6.00;

        CustomerEntity custom = new CustomerEntity("mr", "max", "mer", dateS, "bret", "st michel", "france", "91240", "melin", "055", "test1", "test2");
        AccountEntity account = new AccountEntity(libelle, balance, max_balance, type, date, taxation, custom);
        AccountDto accountDTO = mapper.map(account, AccountDto.class);

        LOGGER.info("ID accountDto is {}", accountDTO.getId());
        LOGGER.info("libelle accountDto is {}", accountDTO.getLibelle());
        LOGGER.info("balance accountDto is {}", accountDTO.getBalance());

        assertThat(accountDTO, notNullValue());
        assertThat(accountDTO.getLibelle(), is(libelle));
        assertThat(accountDTO.getBalance(), is(balance));
        assertThat(accountDTO.getMAX_BALANCE(), is(max_balance));
        assertThat(accountDTO.getType(), is(type));
        assertThat(accountDTO.getDateCreated(), is(date));
        assertThat(accountDTO.getTaxation(), is(taxation));
    }


    @Test
    public void should_SerializeAccountToDTO_OnAccount() {

        String libelle = "lib";
        double balance = 10.2;
        String type = "CURRENT";
        Date date = new Date();
        String dateS = "Date";
        double max_balance = 300.00;
        double taxation = 6.00;

        CustomerEntity custom = new CustomerEntity("mr", "max", "mer", dateS, "bret", "st michel", "france", "91240", "melin", "055", "test1", "test2");
        AccountDto accountdto = new AccountDto(libelle, balance, max_balance, type, date, taxation, custom);
        AccountEntity account = mapper.map(accountdto, AccountEntity.class);

        LOGGER.info("ID account is {}", account.getId());
        LOGGER.info("libelle account is {}", account.getLibelle());
        LOGGER.info("balance account is {}", account.getBalance());

        assertThat(account, notNullValue());
        assertThat(account.getLibelle(), is(libelle));
        assertThat(account.getBalance(), is(balance));
        assertThat(account.getMAX_BALANCE(), is(max_balance));
        assertThat(account.getType(), is(type));
        assertThat(account.getDateCreated(), is(date));
        assertThat(account.getTaxation(), is(taxation));
    }

    /*
     * -----------------------------------------------------------------------------------
     *      Test des méthodes de dépot et de débit
     * -----------------------------------------------------------------------------------
     */

    @Test
    public void testDeposit() {
        LOGGER.info("Deposit");
        Long id = 1L;
        String libelle = "lib";
        double balance = 10.2;
        double max_balance = 3000;
        String type = "CURRENT";
        Date date = new Date();
        String dateS = "Date";
        double taxation = 0.0;
        CustomerEntity custom = new CustomerEntity("mr", "max", "mer", dateS, "bret", "st michel", "france", "91240", "melin", "055", "test1", "test2");
        int amountDeposit = 10;

        AccountDto accountCredited = new AccountDto(libelle, type, custom);
        accountDto.deposit(amountDeposit);
        assertEquals(10, accountDto.getBalance(), 10);
    }

    @Test
    public void testNegativeDeposit() {
        LOGGER.info("Negative deposit");
        Long id = 1L;
        String libelle = "lib";
        double balance = 10.2;
        double max_balance = 3000;
        String type = "CURRENT";
        String dateS = "Date";
        Date date = new Date();
        double taxation = 0.0;
        CustomerEntity custom = new CustomerEntity("mr", "max", "mer", dateS, "bret", "st michel", "france", "91240", "melin", "055", "test1", "test2");
        int amountDeposit = -10;
        AccountDto accountCredited = new AccountDto(libelle, type, custom);

        // initial balance=0
        accountDto.deposit(amountDeposit);
        double bal = accountDto.getBalance();
        assertTrue("Le solde du compte n'a pas changé car Negative withdrawals are not allowed", bal == 0.0);
    }

    @Test
    public void testWithdraw() {
        LOGGER.info("WithDraw");

        LOGGER.info("Negative deposit");
        Long id = 1L;
        String libelle = "lib";
        String type = "CURRENT";
        Date date = new Date();
        String dateS = "Date";
        CustomerEntity custom = new CustomerEntity("mr", "max", "mer", dateS, "bret", "st michel", "france", "91240", "melin", "055", "test1", "test2");

        int amountWithdraw = 10;
        // initial balance =0 , put in to 1000
        accountDto.setBalance(1000);
        accountDto.withDraw(amountWithdraw);
        double bal = accountDto.getBalance();
        assertTrue("Le solde du compte est passé à 990", bal == 990);
    }

    @Test
    public void testNegativeWithDraw() {
        LOGGER.info("Negative WithDraw");
        final int amountWithDraw = -200;
        Long id = 1L;
        String libelle = "lib";
        String type = "CURRENT";
        Date date = new Date();
        String dateS = "Date";
        CustomerEntity custom = new CustomerEntity("mr", "max", "mer", dateS, "bret", "st michel", "france", "91240", "melin", "055", "test1", "test2");

        // initial balance =0
        accountDto.withDraw(amountWithDraw);
        double bal = accountDto.getBalance();
        LOGGER.info("test{}", bal);
        assertTrue("Le solde du compte n'a pas changé car Negative withdrawals are not allowed", bal == 0.0);
    }

    @Test
    public void testTransfert(){
        LOGGER.info("Test Transfert");
        String libelle = "Account";
        double balanceFrom = 1524.78;
        double balanceTo = 2500.43;
        double MAX_BALANCE = 3500.00;
        String type = "CURRENT";
        Date dateCreated = new Date();
        double taxation = 3.00;
        CustomerEntity customer = new CustomerEntity();

        AccountDto from = new AccountDto(libelle, balanceFrom, MAX_BALANCE, type, dateCreated, taxation, customer);
        AccountDto to = new AccountDto(libelle, balanceTo, MAX_BALANCE, type, dateCreated, taxation, customer);
        int transfertAmount = 100;

        from.transfert(from, to, transfertAmount);

        assertThat(from, notNullValue());
        assertThat(to, notNullValue());
        assertThat(transfertAmount, notNullValue());

        double newBalanceFrom = balanceFrom - transfertAmount;
        double newBalanceTo = balanceTo + transfertAmount;

        assertThat(from.getBalance(), is(newBalanceFrom));
        assertThat(to.getBalance(), is(newBalanceTo));
    }

}