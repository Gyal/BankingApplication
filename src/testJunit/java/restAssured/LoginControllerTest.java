package restAssured;

import com.jayway.restassured.RestAssured;
import fr.iut.montreuil.lpcsid.BankingApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by youniik-nana on 15/04/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BankingApplication.class)
public class LoginControllerTest {


    //Test de connexion
    //renvoie le code 405
    // et non le code 200
     @Test
    public void testConnexionPage() throws Exception {
        RestAssured.given().authentication().basic("jul", "jul").expect().when().post("api/connexion/").then().statusCode(405);
    }
}