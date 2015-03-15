package fr.iut.montreuil.lpcsid.controller;

import fr.iut.montreuil.lpcsid.dto.AccountDto;
import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.service.AccountService;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static fr.iut.montreuil.lpcsid.dto.AccountDto.newAccountDto;

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

    //
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    // Au lieu de passer directement par le repositoryTest
    @Autowired
    private AccountService accountService;

    @Autowired
    private Mapper mapper;


    // GET /account : Récupération de la liste des comptes
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Iterable<AccountDto> listAccount() {
        final Iterable<AccountEntity> accounts = from(accountService.getAllAccounts()).toList();
        final Iterable<AccountDto> accountDtos = newArrayList();
        LOGGER.info("List Accounts is {}", accounts);
        return accountDtos;
    }

    // GET /{account-id}/{customer-id} Donne les infos du compte client checks droits" et détail true donne les opérations éffectuées sur le compte
    @RequestMapping(value = "/{account-id}/{customer-id}", method = RequestMethod.GET, produces = "application/json" )
    public
    @ResponseBody
    AccountDto getAccountById(@PathVariable long id) {
        AccountEntity account = accountService.getAccountById(id);
        AccountDto accountDto = newAccountDto();
        LOGGER.info("Account id is {}, return.", accountDto);
        return accountDto;

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
