package fr.iut.montreuil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Created by Mélina on 07/03/2015.
 */

@RestController

/*Il est annoté avec RestController .
La différence entre cela et Controller annotation est l'ancien implique également ResponseBody sur chaque méthode,
ce qui signifie qu'il ya moins d'écrire puisque depuis un service Web RESTFUL nous retournons objets JSON de toute façon.
*/

public class AccountController {


    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

   /* @Inject
    public AccountController(final AccountService accountService){
        this.accountService = accountService;
    }*/


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(final Model model){
        String msg = "Test" ;
        model.addAttribute("message", msg);
        return "index";
    }

    @RequestMapping(value = "/accountTest", method = RequestMethod.GET)
    public String printWelcome(final Model model) {
        String msg = "Page Account" ;
        LOGGER.info("Msg is {}, persisting.", msg);
        model.addAttribute("message", msg);

        return "project";
    }



    @RequestMapping(value = "/account",method = RequestMethod.POST)
    public AccountEntity createAccount(@RequestBody @Valid final AccountEntity account) {

        return accountService.saveAccountEntity(account);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<AccountEntity> getAllPlaces() {

        return this.accountService.getAllAccounts();
    }

    @RequestMapping(value = "/{shortName}", method = RequestMethod.GET)
    public AccountEntity getPlaceForShortName(@PathVariable(value = "shortName") String shortName) {

        //find place by shortname
        return this.accountService.getAccountByShortName(shortName);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void
    deleteAccount(@PathVariable(value = "id") Long id) {
        this.accountService.deleteAccount(id);

    }


}
