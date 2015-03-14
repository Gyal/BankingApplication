package controller;

import entity.AccountTypeEntity;
import service.AccountTypeService;
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
public class AccountTypeController {


    private static final Logger LOGGER = LoggerFactory.getLogger(AccountTypeController.class);
    @Autowired
    private AccountTypeService accountTypeService;

    // GET /accountType : Récupération de la liste des comptes
    @RequestMapping(value = "/accountTypes", method = RequestMethod.GET, produces = "application/json")
    public  @ResponseBody Iterable<AccountTypeEntity> list(Model model){
        final Iterable<AccountTypeEntity> accountTypes = this.accountTypeService.getAllAccountTypes();
        model.addAttribute("accountTypes", accountTypes);
        LOGGER.info("List AccountTypes is {}", accountTypes);
        return accountTypes;
    }


    // GET /accountId : Récupération d'un compte par son ID
    @RequestMapping(value = "/accountType/{id}", method = RequestMethod.GET, produces = "application/json" )
    public @ResponseBody AccountTypeEntity getById(@PathVariable long id){
        AccountTypeEntity accounTypetEntity = accountTypeService.getAccountTypeById(id);
        LOGGER.info("AccountType id is {}, return.", accounTypetEntity);
       return  accounTypetEntity;
    }

    // PUT /account : enregistrement d'un nouveau compte, renvoi un statut
    @RequestMapping(value = "/accountType", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody AccountTypeEntity accountTypeEntity) {
        accountTypeService.saveTypeAccount(accountTypeEntity);

        LOGGER.info("Account Type Creating{}, persisting.", accountTypeEntity.toString());
    }

    @RequestMapping(value = "/acountType/{id}", method = RequestMethod.DELETE)
    public void
    deleteAccount(@PathVariable(value = "id") Long id) {
        this.accountTypeService.deleteTypeAccount(id);

    }
}
