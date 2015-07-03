package restAssured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import fr.iut.montreuil.lpcsid.BankingApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.get;

/** 
* AccountController Tester. 
* 
* @author <Authors name> 
* @since <pre>mars 12, 2015</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BankingApplication.class)
public class AccountControllerTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: list(Model model) 
* 
*/ 
@Test
public void testList() throws Exception {
    ValidatableResponse test = RestAssured.given().get("/api/account/list").then().assertThat().statusCode(200);
    System.out.println("test" + test.toString());
} 

/** 
* 
* Method: getById(@PathVariable long id) 
* 
*/ 
@Test
public void testGetAccountById() throws Exception {
        get("/api/account/1").then().assertThat().statusCode(200);
    }


/** 
* 
* Method: balance(@PathVariable long id) 
* 
*/ 
@Test
public void testBalance() throws Exception { 

}

} 
