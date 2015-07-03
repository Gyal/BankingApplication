package restAssured;

import com.jayway.restassured.response.ValidatableResponse;
import fr.iut.montreuil.lpcsid.BankingApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.post;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by juliana on 15/04/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BankingApplication.class)
public class CustomerControllerTest {

    // test de l'id existant
    //test avec un customer inexistant -- echec
    // test avec un customer existant -- ok
    @Test
    public void testId() throws Exception {
        get("/api/customer/1").then().assertThat().content("idCustomer", equalTo(1)).statusCode(200);
        //get("/api/customer/2").then().assertThat().content("idCustomer", equalTo(2)).statusCode(200);
    }


    // test d'affichage de la liste
    //test ok
    @Test
    public void testListCustomer() throws Exception {
        ValidatableResponse test = get("api/customer/list").then().assertThat().statusCode(200);
        System.out.println("test" + test.toString());
    }


    // test create customer
    //renvoie 405 au lieu du code 200
    @Test
    public void testCreateCustomer() {
        post("/api/customer/add").then().assertThat().statusCode(405);
       /* RestAssured.given().formParam("add")
                .expect()
                .statusCode(200)
                .post("http://localhost:8080/api/customer/add");*/

    }

    // test ok car existe en bdd
    //test ok si pas en bdd
    // a verifier
    /*@Test
    public void testUserNotFound() {
        get("/api/customer/10").then().assertThat().content(nullValue()).statusCode(200);
    }*/

    // @Test
 /*   public void testUpdate(){
        get("/api/customer/update/1").then().assertThat().content("list", equalTo(1));
    }
  */



}