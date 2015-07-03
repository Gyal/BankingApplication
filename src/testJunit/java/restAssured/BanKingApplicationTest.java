package restAssured;

import com.jayway.restassured.RestAssured;
import fr.iut.montreuil.lpcsid.BankingApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by juliana on 15/04/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BankingApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class BanKingApplicationTest {


    //Vérifie que l'application se lance sur le bon port
    //test avec un mauvais port -- echec
    //test avec le bon port ok
    @Test
    public void testPortApplication() throws Exception {
        RestAssured.given().param("title", "Banking Application").
                get("http://localhost:8080/").then().assertThat().statusCode(200);
    }
}
