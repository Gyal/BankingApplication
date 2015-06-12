package fr.iut.montreuil.lpcsid.web.controller;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import fr.iut.montreuil.lpcsid.repository.AccountRepository;
import fr.iut.montreuil.lpcsid.repository.TransactionRepository;
import fr.iut.montreuil.lpcsid.service.AccountService;
import fr.iut.montreuil.lpcsid.web.dto.AccountDto;
import fr.iut.montreuil.lpcsid.web.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    /**
     * ***********************************************************************************************************
     * <p/>
     * Method: {account-id}/{customer-id}
     * Type : GET
     * Donne les infos du compte client checks droits" et détail true donne les opérations éffectuées sur le compte
     * <p/>
     * *************************************************************************************************************
     */

    @RequestMapping(value = "/{account-id}/{customer-id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    AccountDto getUserAccount(@PathVariable(value = "account-id") Long accountId, @PathVariable(value = "customer-id") Long customerId) {
        return accountService.getUserAccount(accountId, customerId);
    }
    /**************************************************************************************************************/


    /**
     * ***********************************************************************************************************
     * <p/>
     * Method: api/account/{customer-id}
     * Type : POST
     * Enregistrement d'un nouveau compte, renvoi un statut CREATED
     *
     * @Param : accountName
     * @Param : accoutType
     * *************************************************************************************************************
     */

    @RequestMapping(value = "/{customer-id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@PathVariable("customer-id") long id, @RequestParam(value = "accountName", required = true) String accountName, @RequestParam(value = "accountType", required = true) String accountType) {
        accountService.createAccount(id, accountName, accountType);

    }

    /****************************************************************************************************************/


    /**
     * *************************************************************************************************************************
     */

    @RequestMapping(value = "/balance/{customer-id}/deposit", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void deposit(@PathVariable(value = "customer-id") Long customerId,
                        @RequestParam(value = "amount", required = true) final int amountDeposit,
                        @RequestParam(value = "accountCredited", required = true) Long account) {
        accountService.deposit(customerId, amountDeposit, account);

    }

    @RequestMapping(value = "/balance/{customer-id}/withdraw", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@PathVariable(value = "customer-id") Long customerId,
                         @RequestParam(value = "amount", required = true) final int amountWithDraw,
                         @RequestParam(value = "accountDebited", required = true) Long account) {
        accountService.withdraw(customerId,amountWithDraw,account);


    }


    @RequestMapping(value = "/balance/{customer-id}/transfer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void transfert(@PathVariable(value = "customer-id") Long customerId,
                          @RequestParam(value = "amount", required = true) final int amountTransfer,
                          @RequestParam(value = "from", required = true) Long from,
                          @RequestParam(value = "toTest", required = true) Long to) {
        accountService.transfert(customerId,amountTransfer,from,to);
    }

    // GET /account : Récupération de la liste des comptes
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> listAccount() {
        return accountService.listAccount();
    }


    @RequestMapping(value = "/{customer-id}", method = RequestMethod.GET, produces = "application/json")
       public CustomerDto getUserAccounts(@PathVariable(value = "customer-id") Long customerId) {

        return accountService.getUserAccounts(customerId);
    }
    @RequestMapping(value = "/transaction/{id-account}", method = RequestMethod.GET, produces = "application/json")
    public List<TransactionEntity> getAccountTransaction(@PathVariable(value = "id-account") Long accountId) {
        AccountEntity account = accountRepository.findOne(accountId);

        return transactionRepository.findAllByAccount(account);

    }

}

