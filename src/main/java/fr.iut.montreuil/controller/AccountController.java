package fr.iut.montreuil.controller;

import fr.iut.montreuil.entity.AccountEntity;
import fr.iut.montreuil.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mélina on 07/03/2015.
 */

@RestController

/*Il est annoté avec RestController .
La différence entre cela et Controller annotation est l'ancien implique également ResponseBody sur chaque méthode,
ce qui signifie qu'il ya moins d'écrire puisque depuis un service Web RESTFUL nous retournons objets JSON de toute façon.
*/


@RequestMapping("api")
public class AccountController {


    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

   /* @Inject
    public AccountController(final AccountService accountService){
        this.accountService = accountService;
    }*/

    // GET /account : Récupération de la liste des comptes
    @RequestMapping(value = "/account", method = RequestMethod.GET, produces = "application/json")
    public  @ResponseBody Iterable<AccountEntity> list(Model model){
        final Iterable<AccountEntity> accounts = this.accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        LOGGER.info("List Accounts is {}", accounts);
        return accounts;
    }


    // GET /accountId : Récupération d'un compte par son ID
    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET, produces = "application/json" )
    public @ResponseBody AccountEntity getById(@PathVariable long id){
        AccountEntity accountEntity = accountService.getAccountById(id);
        LOGGER.info("Account id is {}, return.", accountEntity);
       return  accountEntity;
    }

    // PUT /account : enregistrement d'un nouveau compte, renvoi un statut
    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody AccountEntity accountEntity) {
        accountService.save(accountEntity);

        LOGGER.info("Account Creating{}, persisting.", accountEntity.toString());
    }


    @RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id){
        accountService.deleteAccount(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void
    deleteAccount(@PathVariable(value = "id") Long id) {
        this.accountService.deleteAccount(id);

    }
}
