package entityTest;

import fr.iut.montreuil.lpcsid.config.DozerConfig;
import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.entity.OperationDetailEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import fr.iut.montreuil.lpcsid.web.dto.OperationDetailDto;
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

/**
 * Created by NIIRO on 26/04/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DozerConfig.class})
public class OperationDetailEntityTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationDetailEntity.class);
    @Autowired
    private Mapper mapper;
    OperationDetailEntity operationDetailEntity;

    @Before
    public void setUp() throws Exception {
        // C'est l'instance que l'on va utiliser pour faire nos tests
        operationDetailEntity = new OperationDetailEntity();
    }

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
        AccountEntity accountDebited = new AccountEntity(id,libelle,balance, max_balance, type, created, taxe, customer);
        AccountEntity accountCredited = new AccountEntity(id,libelle,balance, max_balance, type, created, taxe, customer);

        String typetransaction = "Transfert";
        int amount = 100;
        Date date = new Date();
        OperationDetailEntity operationDetail = new OperationDetailEntity();
        TransactionEntity transactionEntity = new TransactionEntity(typetransaction, amount, date, operationDetail);

        OperationDetailEntity operation = new OperationDetailEntity(accountDebited.getId(), accountCredited.getId(), transactionEntity);

        assertThat(operation, notNullValue());
        assertThat(operation.getCreditedAccount(), is(accountCredited.getId()));
        assertThat(operation.getDebitedAccount(), is(accountDebited.getId()));
        assertThat(operation.getOperation(), is(transactionEntity));
    }

    @Test
    public void creation_PojoToDto(){
        Long from = 15L;
        Long to = 10L;
        TransactionEntity operationEntity = new TransactionEntity();

        OperationDetailEntity operation = new OperationDetailEntity(from,to,operationEntity);
        OperationDetailDto OperationDetailDto = mapper.map(operation, OperationDetailDto.class);

        assertThat(OperationDetailDto, notNullValue());
        assertThat(OperationDetailDto.getCreditedAccount(), is(to));
        assertThat(OperationDetailDto.getDebitedAccount(), is(from));
        //assertThat(OperationDetailDto.getOperation(), is(operationEntity));
    }

    @Test
    public void creation_DtoToPojo(){
        Long from = 15L;
        Long to = 10L;
        Long idTransaction = 1684L;
        TransactionEntity operationEntity = new TransactionEntity();

        OperationDetailDto operation = new OperationDetailDto(from,to,idTransaction);
        OperationDetailEntity OperationDetailEntity = mapper.map(operation, OperationDetailEntity.class);

        assertThat(OperationDetailEntity, notNullValue());
        assertThat(OperationDetailEntity.getCreditedAccount(), is(to));
        assertThat(OperationDetailEntity.getDebitedAccount(), is(from));
        //assertThat(OperationDetailEntity.getOperation(), is(operationEntity));
    }
}
