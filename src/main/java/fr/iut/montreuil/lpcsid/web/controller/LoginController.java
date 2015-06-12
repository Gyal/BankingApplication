package fr.iut.montreuil.lpcsid.web.controller;

import fr.iut.montreuil.lpcsid.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by youniik-nana on 26/03/15.
 */
@RestController
@RequestMapping("api/connexion")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    Long userConnexion(@RequestParam(value = "connexionLogin", required = false) String connexionLogin, @RequestParam(value = "password", required = false) String connexionPassword) {
    return loginService.userConnexion(connexionLogin,connexionPassword);
    }

}
