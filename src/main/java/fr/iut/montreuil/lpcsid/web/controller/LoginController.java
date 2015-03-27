package fr.iut.montreuil.lpcsid.web.controller;

import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.service.CustomerService;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by youniik-nana on 26/03/15.
 */
@RestController
@RequestMapping("api/connexion")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Mapper mapper;

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    CustomerEntity userConnexion(@RequestParam(value = "connexionLogin", required = true) String connexionLogin, @RequestParam(value = "password", required = true) String connexionPassword) {

        String login = connexionLogin;
        String password = connexionPassword;

        LOGGER.info("Password {}", password);
        LOGGER.info("Login {}", login);

        Boolean connect = false;
        CustomerEntity userConnexion = null;
        Iterable<CustomerEntity> customers = customerService.getAllCustomers();


        for(CustomerEntity customer : customers) {
            //  LOGGER.info("login {}", customer.getConnexionLogin().toString());
            if (customer.getConnexionLogin().equals(login)) {

                LOGGER.info("un user trouver avec le login {}", login);
                LOGGER.info("son id est {}", customer.getIdCustomer());

                if (customer.getPassword().equals(password)) {
                    LOGGER.info("un user trouver avec le password{}", password);
                    LOGGER.info("son id est {}", customer.getIdCustomer());
                    connect = true;
                    Long idCustomReturn = customer.getIdCustomer();
                    userConnexion = customerService.getCustomerById(idCustomReturn);
                    LOGGER.info("Connecté {}", connect);
                } else {
                    LOGGER.info("Pas connecté {}", connect);
                }
            }
        }
        return userConnexion;
    }


    @RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "index";
    }
}
