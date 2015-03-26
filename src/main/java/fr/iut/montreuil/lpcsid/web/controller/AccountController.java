package fr.iut.montreuil.lpcsid.web.controller;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import fr.iut.montreuil.lpcsid.service.AccountService;
import fr.iut.montreuil.lpcsid.service.CustomerService;
import fr.iut.montreuil.lpcsid.service.TransactionService;
import fr.iut.montreuil.lpcsid.web.dto.AccountDto;
import fr.iut.montreuil.lpcsid.web.exception.DataIntegrityException;
import fr.iut.montreuil.lpcsid.web.exception.ErrorNotFoundException;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static fr.iut.montreuil.lpcsid.web.exception.ErrorCode.NO_ENTITY_FOUND;
import static fr.iut.montreuil.lpcsid.web.exception.ErrorCode.WRONG_ENTITY_INFORMATION;

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

    // Au lieu de passer directement par le repositoryTest
    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private Mapper mapper;


    /**
     * *************************************Methode HTTP basic ****************************************
     */

    // GET /account : Récupération de la liste des comptes
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Iterable<AccountDto> listAccount() {
        Iterable<AccountEntity> accounts = from(accountService.getAllAccounts()).toList();
        Iterable<AccountDto> accountDtos = newArrayList();
        mapper.map(accounts, accountDtos);
        LOGGER.info("List Accounts is {}", accountDtos);

       /* CustomerEntity customer = new CustomerEntity(1l, "eee", "mel", "tat", new Date(), "rue", "vii", "france", 91240, "mmm@", 0651, 111, "melin");

        customerService.saveCustomer(customer);
        AccountEntity accountEntity = new AccountEntity(22L, "test", 10.00, 3000, "CURRENT", new Date(), customer);
        accountService.saveAccount(accountEntity);
        */
        return accountDtos;
    }

    // GET /{account-id}/{customer-id} Donne les infos du compte client checks droits" et détail true donne les opérations éffectuées sur le compte
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    AccountDto getAccountById(@PathVariable long id) {
        AccountEntity account = accountService.getAccountById(id);
        AccountDto accountDto = mapper.map(account, AccountDto.class);
        List<TransactionEntity> operations = accountDto.getOperations();
        if (null == account) {
            throw new ErrorNotFoundException(NO_ENTITY_FOUND);
        }
        LOGGER.info("Account is {}, return.", accountDto);
        LOGGER.info("List operation is {}", operations);

        accountService.saveAccount(account);
        return accountDto;
    }

    // POST /account : enregistrement d'un nouveau compte, renvoi un statut CREATED
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto createAccount(@RequestBody AccountDto accountDto) {
        AccountEntity accountEntity = mapper.map(accountDto, AccountEntity.class);

        AccountEntity savedAccount;
        try {
            savedAccount = accountService.saveAccount(accountEntity);
            LOGGER.info("Account Creating id is{}, persisting.", accountEntity.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(WRONG_ENTITY_INFORMATION);
        }
        return mapper.map(savedAccount, AccountDto.class);
    }

    // PUT /update/{id} : modification des information d'un compte
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public AccountDto updateAccount(@PathVariable long id, @RequestBody AccountDto accountDto) {
        AccountEntity account = mapper.map(accountDto, AccountEntity.class);
        AccountEntity AccountToUpdate = accountService.getAccountById(id);

        if (null == AccountToUpdate) {
            throw new ErrorNotFoundException(NO_ENTITY_FOUND);
        }

        account.setId(AccountToUpdate.getId());
        AccountEntity updatedAccount = accountService.saveAccount(account);

        return mapper.map(updatedAccount, AccountDto.class);
    }


    // DELETE /account/delete/{id} : suprpession d'un compte de tel id, renvoi le statut NOCONTENT
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable(value = "id") Long id) {
        try {
            accountService.deleteAccount(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ErrorNotFoundException(NO_ENTITY_FOUND);
        }
    }


    /* PUT/balance/{customer-id}:  crédit ou débit d’argent sur le compte client (conditions comptes, alimenter historique opération client)
     transfère de l’argent du compte de customer-id vers le compte customer-id-crediteur (check conditions compte alimenter historique opérations sur les comptes)
    */

    @RequestMapping(value = "/balance/{customer-id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void balance(@PathVariable long id) {

        // a faire
    }

/*****************************************************************************************************************************/

    /**
     * ************************** Functional knowledge **************************************************************************
     */

    @RequestMapping(value = "/{id}/transfert", method = RequestMethod.POST)
    public void deposit(@PathVariable long id, @RequestBody AccountDto accountDto, @RequestParam(value = "amount", required = true) final int amountTransfer, @RequestParam(value = "idAccountCredited", required = true) final Long idAccountCredited) {

        AccountEntity account = mapper.map(accountDto, AccountEntity.class);
        AccountEntity accountDebited = accountService.getAccountById(id);
        AccountEntity accountCredited = accountService.getAccountById(idAccountCredited);

        if (null == accountDebited) {
            throw new ErrorNotFoundException(NO_ENTITY_FOUND);
        }

        if (accountDebited.getType() != "CURRENT") {
            LOGGER.info("le transfert n'est pas autorisé car le compte débité est de type{}", accountDebited.getType());
        }
        if (accountCredited.getType() != "CURRENT") {
            LOGGER.info("le transfert n'est pas autorisé car le compte crédité est de type{}", accountCredited.getType());

        }
        if (amountTransfer <= 0) {
            LOGGER.info("le montant du transfert est inférieur à 0 : {}", amountTransfer);

        }
        if (accountDebited.getBalance() - amountTransfer < 0) {
            LOGGER.info("Le retrait ne peut pas être éffectuer car le compte serait à découvert : solde-montant = {}", accountDebited.getBalance() - amountTransfer);

        } else {

            accountDebited.tranfert(amountTransfer, accountDebited, accountCredited);
            accountService.saveAccount(accountCredited);
            accountService.saveAccount(accountCredited);
        }

    }

    @RequestMapping(value = "/{id}/deposit", method = RequestMethod.POST)
    public void deposit(@PathVariable long id, @RequestBody AccountDto accountDto, @RequestParam(value = "amount", required = true) final int amountDeposit) {

        AccountEntity account = mapper.map(accountDto, AccountEntity.class);
        AccountEntity accountToDeposit = accountService.getAccountById(id);

        int sommeOperationDeposit = 0;
        List<TransactionEntity> operations = account.getOperations();
        LOGGER.info("For account {}", account.getId());
        LOGGER.info("Operations is:{}", operations);

        for (TransactionEntity operation : operations) {
            if (operation.getType() == "DEPOSIT") {
                sommeOperationDeposit = sommeOperationDeposit + operation.getAmount();
            }
        }
        if (amountDeposit + sommeOperationDeposit > 3000) {
            LOGGER.info("le montant maximum de dépot est atteint {}", amountDeposit + sommeOperationDeposit);
        }

        if (amountDeposit >= 0) {
            LOGGER.info("Le montant déposé doit être supérieur à 0 :{}", amountDeposit);
        }
        if (amountDeposit + accountToDeposit.getBalance() > accountToDeposit.getMaxBalance()) {
            LOGGER.info("Le montant déposé est supérieur au plafond indiqué par le compte :{}", amountDeposit + accountToDeposit.getBalance());
        } else {
            accountToDeposit.deposit(amountDeposit);
            accountService.saveAccount(accountToDeposit);
        }
    }

    @RequestMapping(value = "/{id}/withdraw", method = RequestMethod.POST)
    public void withDraw(@PathVariable long id, @RequestBody AccountDto accountDto, @RequestParam(value = "amount", required = true) final int amountDebit) {
        AccountEntity account = mapper.map(accountDto, AccountEntity.class);
        AccountEntity accountToDebit = accountService.getAccountById(id);

        int sommeOperationDebit = 0;
        List<TransactionEntity> operations = account.getOperations();
        LOGGER.info("For account {}", account.getId());
        LOGGER.info("Operations is:{}", operations);

        for (TransactionEntity operation : operations) {
            if (operation.getType() == "WITHDRAW") {
                sommeOperationDebit = sommeOperationDebit + operation.getAmount();
            }
            if (sommeOperationDebit + amountDebit > 2500) {
                LOGGER.info("Le montant maximum de retrait est atteind {}", sommeOperationDebit);

            }
        }
        if (amountDebit <= 0) {
            LOGGER.info("La somme débité doit être suppérieur à 0 {}", amountDebit);
        }
        if (amountDebit + accountToDebit.getBalance() > accountToDebit.getMaxBalance()) {
            LOGGER.info("Le solde dépasse le plafond {}", amountDebit + accountToDebit.getBalance());

        }


    }

}
