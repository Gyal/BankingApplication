package dtoTest;

import fr.iut.montreuil.lpcsid.config.DozerConfig;
import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.web.dto.CustomerDto;
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
 * Created by NIIRO on 02/04/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DozerConfig.class})
public class CustomerDtoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDtoTest.class);
    @Autowired
    private Mapper mapper;
    CustomerDto customerDto;

    @Before
    public void setUp() throws Exception {
        // C'est l'instance que l'on va utiliser pour faire nos tests
        customerDto = new CustomerDto();
    }
    @Test
    public void creationCustomer_PojoToDto(){
        String civilities = "Mlle";
        String lastname = "Joinville";
        String firstName = "Melina";
        String dateOfBirth = "14/10/1991 13:55:00";
        String street = "5 allée des acacias";
        String city = "Paris";
        String country = "France";
        String zipCode = "93000";
        String mail = "Banking@app.fr";
        String phoneNumber = "0215478963";
        String connexionLogin = "Niiro";
        String password = "68541684";

        CustomerEntity customer = new CustomerEntity(civilities,lastname,firstName,dateOfBirth,street,city,country,zipCode,mail,phoneNumber,connexionLogin,password);
        CustomerDto customerDTO = mapper.map(customer, CustomerDto.class);

        assertThat(customerDTO.getCivilities(),is(civilities));
        assertThat(customerDTO.getLastname(),is(lastname));
        assertThat(customerDTO.getFirstName(),is(firstName));
        assertThat(customerDTO.getDateOfBirth(),is(dateOfBirth));
        assertThat(customerDTO.getStreet(),is(street));
        assertThat(customerDTO.getCity(),is(city));
        assertThat(customerDTO.getCountry(),is(country));
        assertThat(customerDTO.getZipCode(),is(zipCode));
        assertThat(customerDTO.getMail(),is(mail));
        assertThat(customerDTO.getPhoneNumber(),is(phoneNumber));
        assertThat(customerDTO.getConnexionLogin(),is(connexionLogin));
        assertThat(customerDTO.getPassword(),is(password));
    }

    @Test
    public void creationCustomer_DtoToPojo(){
        String civilities = "Mlle";
        String lastname = "Joinville";
        String firstName = "Melina";
        String dateOfBirth = "14/10/1991 13:55:00";
        String street = "5 allée des acacias";
        String city = "Paris";
        String country = "France";
        String zipCode = "93000";
        String mail = "Banking@app.fr";
        String phoneNumber = "0215478963";
        String connexionLogin = "Niiro";
        String password = "68541684";

        CustomerDto customerDTO = new CustomerDto(civilities,lastname,firstName,dateOfBirth,street,city,country,zipCode,mail,phoneNumber,connexionLogin,password);
        CustomerEntity customer = mapper.map(customerDTO, CustomerEntity.class);

        assertThat(customer.getCivilities(),is(civilities));
        assertThat(customer.getLastname(),is(lastname));
        assertThat(customer.getFirstName(),is(firstName));
        assertThat(customer.getDateOfBirth(),is(dateOfBirth));
        assertThat(customer.getStreet(),is(street));
        assertThat(customer.getCity(),is(city));
        assertThat(customer.getCountry(),is(country));
        assertThat(customer.getZipCode(),is(zipCode));
        assertThat(customer.getMail(),is(mail));
        assertThat(customer.getPhoneNumber(),is(phoneNumber));
        assertThat(customer.getConnexionLogin(),is(connexionLogin));
        assertThat(customer.getPassword(),is(password));
    }
}
