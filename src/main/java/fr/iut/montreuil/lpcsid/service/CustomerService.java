package fr.iut.montreuil.lpcsid.service;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.repository.CustomerRepository;
import fr.iut.montreuil.lpcsid.web.dto.CustomerDto;
import fr.iut.montreuil.lpcsid.web.exception.DataIntegrityException;
import fr.iut.montreuil.lpcsid.web.exception.NotFoundException;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static fr.iut.montreuil.lpcsid.web.exception.ErrorCode.NO_ENTITY_FOUND;
import static fr.iut.montreuil.lpcsid.web.exception.ErrorCode.WRONG_ENTITY_INFORMATION;

/**
 * Created by Mélina on 07/03/2015.
 */

/*Il est annoté avec RestController .
La différence entre cela et Controller annotation est l'ancien implique également ResponseBody sur chaque méthode,
ce qui signifie qu'il ya moins d'écrire puisque depuis un service Web RESTFUL nous retournons objets JSON de toute façon.
*/

@Component
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private Mapper mapper;


    // GET / : Récupération de la liste des  users
    public Iterable<CustomerDto> listCustomer() {
        final Iterable<CustomerEntity> customerEntities = customerRepository.findAll();
        Iterable<CustomerDto> customerDtos = newArrayList();
        mapper.map(customerEntities, customerDtos);
        LOGGER.info("List Customers is {}", customerDtos);
        return customerDtos;
    }


    // GET / : Récupération d'un user par son ID
    public CustomerDto getCustomerById(long id) {
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
    public CustomerDto createCustomer(String customerCiv, String customerLastName, String customerFirstName, String customerLogin, String customerPassword, String customerDateBirth, String customerMail, String customerPhone, String customerAdresse, String customerVille, String customerPays, String customerCP) {
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
    public CustomerDto updateCustomer(long id, CustomerDto customerDto) {
        CustomerEntity customer = mapper.map(customerDto, CustomerEntity.class);
        CustomerEntity customerToUpdate = customerRepository.getOne(id);

        if (null == customerToUpdate) {
            throw new NotFoundException(NO_ENTITY_FOUND);
        }

        customer.setIdCustomer(customerToUpdate.getIdCustomer());
        CustomerEntity updatedCustomer = customerRepository.save(customer);

        return mapper.map(updatedCustomer, CustomerDto.class);
    }

    // DELETE delete/{id} : suppression d'un user de tel id, renvoi le statut NOCONTENT
    public void deleteCustomer(Long id) {
        try {
            customerRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(NO_ENTITY_FOUND);
        }
    }

}
