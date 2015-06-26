package dtoTest;

import fr.iut.montreuil.lpcsid.config.DozerConfig;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by NIIRO on 26/04/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DozerConfig.class})
public class OperationDetailsDtoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDtoTest.class);
    @Autowired
    private Mapper mapper;
    OperationDetailDto operationDetailDto;

    @Before
    public void setUp() throws Exception {
        // C'est l'instance que l'on va utiliser pour faire nos tests
        operationDetailDto = new OperationDetailDto();
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
        Long idTransaction = 165L;

        OperationDetailDto operation = new OperationDetailDto(from,to,idTransaction);
        OperationDetailEntity OperationDetailEntity = mapper.map(operation, OperationDetailEntity.class);

        assertThat(OperationDetailEntity, notNullValue());
        assertThat(OperationDetailEntity.getCreditedAccount(), is(to));
        assertThat(OperationDetailEntity.getDebitedAccount(), is(from));
    }
}
