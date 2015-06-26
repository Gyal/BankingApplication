package fr.iut.montreuil.lpcsid.service;

import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.repository.CustomerRepository;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by youniik-nana on 26/03/15.
 */
@Component
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Mapper mapper;

    public Long userConnexion(@RequestParam(value = "connexionLogin", required = false) String connexionLogin, @RequestParam(value = "password", required = false) String connexionPassword) {

        String login = connexionLogin;
        String password = connexionPassword;

        LOGGER.info("Password {}", password);
        LOGGER.info("Login {}", login);

        Boolean connect = false;
        CustomerEntity userConnexion = null;
        CustomerEntity customer = customerRepository.findByconnexionLogin(login);
        //  LOGGER.info("login {}", customer.getConnexionLogin().toString());
        if (customer != null) {
            LOGGER.info("un user trouver avec le login {}", login);
            LOGGER.info("son id est {}", customer.getIdCustomer());

            if (customer.getPassword().equals(password)) {
                LOGGER.info("un user trouver avec le password{}", password);
                LOGGER.info("son id est {}", customer.getIdCustomer());
                connect = true;
                Long idCustomReturn = customer.getIdCustomer();
                userConnexion = customerRepository.findOne(idCustomReturn);
                LOGGER.info("Connecté {}", connect);
            } else {
                LOGGER.info("Pas connecté {}", connect);
            }
        }else {
            LOGGER.info("aucun user avec ce login {}", login);
        }

            return userConnexion.getIdCustomer();
    }

}
