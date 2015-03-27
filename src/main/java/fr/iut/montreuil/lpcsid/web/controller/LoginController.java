package fr.iut.montreuil.lpcsid.web.controller;

import fr.iut.montreuil.lpcsid.web.dto.CustomerDto;
import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.service.CustomerService;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public CustomerEntity connexion(@RequestBody CustomerEntity connexion, @RequestParam(value = "connexionLogin" ,required = true) String connexionLogin, @RequestParam(value = "password",required = true) String connexionPassword) {

        String login = connexionLogin;
        String password = connexionPassword;
        Boolean connect = false;
        Iterable<CustomerEntity> customers = customerService.getAllCustomers();


        for(CustomerEntity customer : customers) {
            if (customer.getConnexionLogin() == login) {
                if(customer.getPassword() == password){
                    connect = true;
                    LOGGER.info("Connecté {}", connect);
                }
            }
        }
        return connexion;
    }


    @RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "index";
    }
}
