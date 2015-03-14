package dto;

import config.DozerConfig;
import entity.AccountEntity;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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
     * Test de la transmission de notre objet vers le DTO correspondant
     * -----------------------------------------------------------------------------------
     */
    @Test
    public void should_SerializeDTOToPojo_OnAccount() {
        Long id = 1L;
        String libelle = "lib";
        double balance = 10.2;

        AccountEntity account = new AccountEntity(id, libelle, balance);
        AccountDto accountDTO = mapper.map(account, AccountDto.class);

        LOGGER.info("ID accountDto is {}", accountDTO.getId());
        LOGGER.info("libelle accountDto is {}", accountDTO.getLibelle());
        LOGGER.info("balance accountDto is {}", accountDTO.getBalance());

        assertThat(accountDTO, notNullValue());
        assertThat(accountDTO.getId(), is(id));
        assertThat(accountDTO.getLibelle(), is(libelle));
        assertThat(accountDTO.getBalance(), is(balance));
    }


    @Test
    public void should_SerializePojoToDTO_OnAccount() {

        Long id = 10L;
        String libelle = "test";
        double balance = 10.0;

        AccountDto accountDTO = new AccountDto(id, libelle, balance);
        AccountEntity account = mapper.map(accountDTO, AccountEntity.class);

        LOGGER.info("ID account is {}", account.getId());
        LOGGER.info("libelle account is {}", account.getLibelle());
        LOGGER.info("balance account is {}", account.getBalance());

        assertThat(account, notNullValue());
        assertThat(account.getId(), is(id));
        assertThat(account.getLibelle(), is(libelle));
        assertThat(account.getBalance(), is(balance));

    }

    /**
     * ----------------------------------------------------------------------------------
     * <p/>
     * <p/>
     * /*-----------------------------------------------------------------------------------
     * Test des méthodes de dépot et de débit
     * -----------------------------------------------------------------------------------
     */

    @Test
    public void testDeposit() {
        LOGGER.info("Deposit");
        int amountDeposit = 10;
        // initial balance = 0
        accountDto.deposit(amountDeposit);
        assertEquals(10, accountDto.getBalance(), 10);
    }

    @Test
    public void testNegativeDeposit() {
        LOGGER.info("Negative deposit");
        int amountDeposit = -10;
        // initial balance=0
        accountDto.deposit(amountDeposit);
        double bal = accountDto.getBalance();
        assertTrue("Le solde du compte n'a pas changé car Negative withdrawals are not allowed", bal == 0.0);
    }

    @Test
    public void testWithdraw() {
        LOGGER.info("WithDraw");
        final int amountWithDraw = 200;
        // initial balance =0 , put in to 1000
        accountDto.setBalance(1000);
        accountDto.withDraw(amountWithDraw);
        double bal = accountDto.getBalance();
        assertTrue("Le solde du compte est passé à 800", bal == 800);
    }

    @Test
    public void testNegativeWithDraw() {
        LOGGER.info("Negative WithDraw");
        final int amountWithDraw = -200;
        // initial balance =0
        accountDto.withDraw(amountWithDraw);
        double bal = accountDto.getBalance();
        LOGGER.info("test{}", bal);
        assertTrue("Le solde du compte n'a pas changé car Negative withdrawals are not allowed", bal == 0.0);
    }

/**----------------------------------------------------------------------------------*/
}