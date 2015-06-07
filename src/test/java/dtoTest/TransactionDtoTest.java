package dtoTest;

import fr.iut.montreuil.lpcsid.config.DozerConfig;
import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.OperationDetailEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import fr.iut.montreuil.lpcsid.web.dto.AccountDto;
import fr.iut.montreuil.lpcsid.web.dto.OperationDetailDto;
import fr.iut.montreuil.lpcsid.web.dto.TransactionDto;
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

/**
 * Created by NIIRO on 03/04/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DozerConfig.class})
public class TransactionDtoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDtoTest.class);
    @Autowired
    private Mapper mapper;

    TransactionDto transactionDto;

    @Before
    public void setUp() throws Exception {
        // C'est l'instance que l'on va utiliser pour faire nos tests
        transactionDto = new TransactionDto();
    }

    @Test
         public void creationTransaction_DtoToPojo_Account(){

        String type = "Transfert";
        int amount = 100;
        Date date = new Date();
        AccountDto account = new AccountDto();

        TransactionDto transactionDto = new TransactionDto(type, amount, date, account);
        TransactionEntity transactionEntity = mapper.map(transactionDto, TransactionEntity.class);

        assertThat(transactionEntity.getTransactionType(),is(type));
        assertThat(transactionEntity.getAmount(),is(amount));
        assertThat(transactionEntity.getTransactionDate(),is(date));
        assertThat(transactionEntity.getAccount().getBalance(),is(account.getBalance()));
        assertThat(transactionEntity.getAccount().getTaxation(),is(account.getTaxation()));
        assertThat(transactionEntity.getAccount().getMAX_BALANCE(),is(account.getMAX_BALANCE()));
        assertThat(transactionEntity.getAccount().getDateCreated(),is(account.getDateCreated()));
        assertThat(transactionEntity.getAccount().getId(),is(account.getId()));
        assertThat(transactionEntity.getAccount().getLibelle(),is(account.getLibelle()));
        assertThat(transactionEntity.getAccount().getType(),is(account.getType()));
        assertThat(transactionEntity.getAccount().getCustomer(),is(account.getCustomer()));
    }

    @Test
    public void creationTransaction_PojoToDto_Account(){

        String type = "Transfert";
        int amount = 100;
        Date date = new Date();
        AccountEntity account = new AccountEntity();

        TransactionEntity transactionEntity = new TransactionEntity(type, amount, date, account);
        TransactionDto transactionDto = mapper.map(transactionEntity, TransactionDto.class);

        assertThat(transactionDto.getTransactionType(),is(type));
        assertThat(transactionDto.getAmount(),is(amount));
        assertThat(transactionDto.getTransactionDate(),is(date));
        assertThat(transactionDto.getAccount().getBalance(),is(account.getBalance()));
        assertThat(transactionDto.getAccount().getTaxation(),is(account.getTaxation()));
        assertThat(transactionDto.getAccount().getMAX_BALANCE(),is(account.getMAX_BALANCE()));
        assertThat(transactionDto.getAccount().getDateCreated(),is(account.getDateCreated()));
        assertThat(transactionDto.getAccount().getId(),is(account.getId()));
        assertThat(transactionDto.getAccount().getLibelle(),is(account.getLibelle()));
        assertThat(transactionDto.getAccount().getType(),is(account.getType()));
        assertThat(transactionDto.getAccount().getCustomer(),is(account.getCustomer()));
    }

    @Test
    public void creationTransaction_DtoToPojo_Operation(){

        String type = "Transfert";
        int amount = 100;
        Date date = new Date();
        OperationDetailDto operationDetail = new OperationDetailDto();

        TransactionDto transactionDto = new TransactionDto(type, amount, date, operationDetail);
        TransactionEntity transactionEntity = mapper.map(transactionDto, TransactionEntity.class);

        assertThat(transactionEntity.getTransactionType(),is(type));
        assertThat(transactionEntity.getAmount(),is(amount));
        assertThat(transactionEntity.getTransactionDate(),is(date));
        assertThat(transactionEntity.getOperationDetail().getId(),is(operationDetail.getId()));
        assertThat(transactionEntity.getOperationDetail().getCreditedAccount(),is(operationDetail.getCreditedAccount()));
        assertThat(transactionEntity.getOperationDetail().getDebitedAccount(),is(operationDetail.getDebitedAccount()));
    }

    @Test
    public void creationTransaction_PojoToDto_Operation(){

        String type = "Transfert";
        int amount = 100;
        Date date = new Date();
        OperationDetailEntity operationDetail = new OperationDetailEntity();

        TransactionEntity transactionEntity = new TransactionEntity(type, amount, date, operationDetail);
        TransactionDto transactionDto = mapper.map(transactionEntity, TransactionDto.class);

        assertThat(transactionDto.getTransactionType(),is(type));
        assertThat(transactionDto.getAmount(),is(amount));
        assertThat(transactionDto.getTransactionDate(),is(date));
        assertThat(transactionDto.getOperationDetail().getId(),is(operationDetail.getId()));
        assertThat(transactionDto.getOperationDetail().getCreditedAccount(),is(operationDetail.getCreditedAccount()));
        assertThat(transactionDto.getOperationDetail().getDebitedAccount(),is(operationDetail.getDebitedAccount()));
    }
}
