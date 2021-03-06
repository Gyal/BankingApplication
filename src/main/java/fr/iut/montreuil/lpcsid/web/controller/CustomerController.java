package fr.iut.montreuil.lpcsid.web.controller;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.repository.CustomerRepository;
import fr.iut.montreuil.lpcsid.service.CustomerService;
import fr.iut.montreuil.lpcsid.web.dto.CustomerDto;
import fr.iut.montreuil.lpcsid.web.exception.DataIntegrityException;
import fr.iut.montreuil.lpcsid.web.exception.NotFoundException;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static fr.iut.montreuil.lpcsid.web.exception.ErrorCode.NO_ENTITY_FOUND;
import static fr.iut.montreuil.lpcsid.web.exception.ErrorCode.WRONG_ENTITY_INFORMATION;

/**
 * Created by Mélina on 07/03/2015.
 */

@RestController

/*Il est annoté avec RestController .
La différence entre cela et Controller annotation est l'ancien implique également ResponseBody sur chaque méthode,
ce qui signifie qu'il ya moins d'écrire puisque depuis un service Web RESTFUL nous retournons objets JSON de toute façon.
*/


@RequestMapping("api/customer")
public class CustomerController {


    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private Mapper mapper;

    // GET / : Récupération de la liste des  users
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Iterable<CustomerDto> listCustomer() {
        final Iterable<CustomerEntity> customerEntities = this.customerRepository.findAll();
        Iterable<CustomerDto> customerDtos = newArrayList();
        mapper.map(customerEntities, customerDtos);
        LOGGER.info("List Customers is {}", customerDtos);
        return customerDtos;
    }


    // GET / : Récupération d'un user par son ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody CustomerDto getCustomerById(@PathVariable long id) {
        CustomerEntity customer = customerRepository.findOne(id);
        CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
        List<AccountEntity> accountEntities = customer.getAccounts();
        if (null == customer) {
            throw new NotFoundException(NO_ENTITY_FOUND);
        }
        LOGGER.info("Customer is {}, return.", customerDto);
        LOGGER.info("List account is {}", accountEntities);

        customerRepository.save(customer);
        return customerDto;
    }

    // POST/{id}: crée un compte pour le client
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestParam(value = "customerCiv", required = false) String customerCiv, @RequestParam(value = "customerLastName", required = true) String customerLastName, @RequestParam(value = "customerFirstName", required = true) String customerFirstName, @RequestParam(value = "customerLogin", required = true) String customerLogin, @RequestParam(value = "customerPassword", required = true) String customerPassword, @RequestParam(value = "customerDateBirth", required = true) String customerDateBirth, @RequestParam(value = "customerMail", required = true) String customerMail, @RequestParam(value = "customerPhone", required = true) String customerPhone, @RequestParam(value = "customerAdresse", required = true) String customerAdresse, @RequestParam(value = "customerVille", required = true) String customerVille, @RequestParam(value = "customerPays", required = true) String customerPays, @RequestParam(value = "customerCP", required = true) String customerCP) {

        CustomerEntity customerEntity = new CustomerEntity(customerCiv, customerLastName, customerFirstName, customerDateBirth, customerAdresse, customerVille, customerPays, customerCP, customerMail, customerPhone, customerLogin, customerPassword);

        CustomerDto customerDto = mapper.map(customerEntity, CustomerDto.class);

        CustomerEntity savedCustomer;
        try {
            savedCustomer = customerRepository.save(customerEntity);
            LOGGER.info("Customer Creating id is{}, persisting.", customerEntity.getIdCustomer());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(WRONG_ENTITY_INFORMATION);
        }
        return mapper.map(savedCustomer, CustomerDto.class);
    }

    // PUT /update/{id} : modification des information d'un user
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public CustomerDto updateCustomer(@PathVariable long id, @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(id, customerDto);
    }


    // DELETE delete/{id} : suppression d'un user de tel id, renvoi le statut NOCONTENT
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable(value = "id") Long id) {
       customerService.deleteCustomer(id);
    }

}
