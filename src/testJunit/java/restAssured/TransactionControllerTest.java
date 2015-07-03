package restAssured;

import fr.iut.montreuil.lpcsid.BankingApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by youniik-nana on 15/04/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BankingApplication.class)
public class TransactionControllerTest {

   /* @Test
    public void testGetUserAccount() throws Exception{
        get("/api/account/balance/1/transfer").then().assertThat().statusCode(200);
    }*/
}
