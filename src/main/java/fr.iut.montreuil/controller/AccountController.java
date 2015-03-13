package fr.iut.montreuil.controller;

import fr.iut.montreuil.entity.AccountEntity;
import fr.iut.montreuil.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mélina on 07/03/2015.
 */

@RestController

/*Il est annoté avec RestController .
La différence entre cela et Controller annotation est l'ancien implique également ResponseBody sur chaque méthode,
ce qui signifie qu'il ya moins d'écrire puisque depuis un service Web RESTFUL nous retournons objets JSON de toute façon.
*/


@RequestMapping("api/account")
public class AccountController {


    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

   // Au lieu de passer directement par le repository
    @Autowired
    private AccountService accountService;

    // GET /account : Récupération de la liste des comptes
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public  @ResponseBody Iterable<AccountEntity> listAccount(){
        final Iterable<AccountEntity> accounts = this.accountService.getAllAccounts();
        LOGGER.info("List Accounts is {}", accounts);
        return accounts;
    }

     // GET /{account-id}/{customer-id} Donne les infos du compte client checks droits" et détail true donne les opérations éffectuées sur le compte
    @RequestMapping(value = "/{account-id}/{customer-id}", method = RequestMethod.GET, produces = "application/json" )
    public @ResponseBody AccountEntity getAccountById(@PathVariable long id){
        AccountEntity accountEntity = accountService.getAccountById(id);
        LOGGER.info("Account id is {}, return.", accountEntity);
       return  accountEntity;

    }


    /* PUT/balance/{customer-id}:  crédit ou débit d’argent sur le compte client (conditions comptes, alimenter historique opération client)
     transfère de l’argent du compte de customer-id vers le compte customer-id-crediteur (check conditions compte alimenter historique opérations sur les comptes)
    */

    @RequestMapping(value = "/balance/{customer-id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void balance(@PathVariable long id){

      // a faire
    }


    }
