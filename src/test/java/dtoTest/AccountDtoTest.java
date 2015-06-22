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
        Long id = 5L;

        CustomerEntity custom = new CustomerEntity("mr", "max", "mer", dateS, "bret", "st michel", "france", "91240", "melin", "055", "test1", "test2");
        AccountDto accountdto = new AccountDto(id,libelle, balance, max_balance, type, date, taxation);
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
}