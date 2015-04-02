package fr.iut.montreuil.lpcsid.web.controller;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import fr.iut.montreuil.lpcsid.service.AccountService;
import fr.iut.montreuil.lpcsid.service.CustomerService;
import fr.iut.montreuil.lpcsid.service.TransactionService;
import fr.iut.montreuil.lpcsid.web.dto.AccountDto;
import fr.iut.montreuil.lpcsid.web.dto.CustomerDto;
import fr.iut.montreuil.lpcsid.web.exception.DataIntegrityException;
import fr.iut.montreuil.lpcsid.web.exception.ErrorNotFoundException;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    //Utilisation des services au lieu des repository
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private Mapper mapper;


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
    public
    @ResponseBody
    AccountDto getUserAccount(@PathVariable(value = "account-id") Long accountId, @PathVariable(value = "customer-id") Long customerId) {

        // Récupération du compte en BDD avec l'ID fournis
        AccountEntity accountGetted = accountService.getAccountById(accountId);
        AccountDto accountDto = mapper.map(accountGetted, AccountDto.class);
        if (null == accountDto) {
            LOGGER.info("Aucun compte n'est trouvé avec l'ID mappé : {}", accountDto);
            throw new ErrorNotFoundException(NO_ENTITY_FOUND);
        }
        LOGGER.info("AccountGetted {}", accountDto);


        // Récupération de l'utilisateur en BDD avec l'ID fournis
        CustomerEntity userGetted = customerService.getCustomerById(customerId);
        CustomerDto userDto = mapper.map(userGetted, CustomerDto.class);
        if (null == userDto) {
            LOGGER.info("Aucun utilisateur n'est trouvé avec l'ID mappé : {}", userDto);
            throw new ErrorNotFoundException(NO_ENTITY_FOUND);
        }
        LOGGER.info("UserGetted {}", userDto);

        // Vérification de la concordance entre le compte et l'utilisateur*/
        if (accountDto.getCustomer().getIdCustomer().equals(userDto.getIdCustomer())) {
            LOGGER.info("AccountGetted is mapped with the userGetted {}", accountDto + "" + userDto);
            return accountDto;

        }
        return accountDto;
    }
    /**************************************************************************************************************/


    /**
     * ***********************************************************************************************************
     * <p/>
     * Method: api/account/{id}
     * Type : POST
     * Enregistrement d'un nouveau compte, renvoi un statut CREATED
     *
     * @Param : accountName
     * @Param : accoutType
     * *************************************************************************************************************
     */

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto createAccount(@PathVariable("id") long id, @RequestParam(value = "accountName", required = true) String accountName, @RequestParam(value = "accountType", required = true) String accountType) {

        // Vérification du champ Type
        if (accountType.equals("CURRENT") || accountType.equals("SAVINGS")) {
            LOGGER.info("LOG: accountType is OK : equals CURRENT OR SAVINGS{}");
            CustomerEntity customer = customerService.getCustomerById(id);
            CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
            AccountEntity accountEntity = new AccountEntity(accountName, accountType, customer);
            accountEntity.setDateCreated();
            accountEntity.setMaxBalance();
            accountEntity.setTaxation();
            AccountEntity accountSaved;

            accountSaved = accountService.saveAccount(accountEntity);
            // Ajout du compte crée à l'utilisateur
            customer.getAccounts().add(accountEntity);
            customerService.saveCustomer(customer);
            LOGGER.info(" LOG: Account id {}, as bean added to the customer id {}.", accountEntity.getId(), customer.getIdCustomer());

            return mapper.map(accountSaved, AccountDto.class);
        } else {
            LOGGER.info(" LOG: AccountType is NOK : not equals CURRENT OR SAVINGS {}", accountType);

            throw new DataIntegrityException(WRONG_ENTITY_INFORMATION);

        }
    }

    /****************************************************************************************************************/



    /* PUT/balance/{customer-id}:  crédit ou débit d’argent sur le compte client (conditions comptes, alimenter historique opération client)
     transfère de l’argent du compte de customer-id vers le compte customer-id-crediteur (check conditions compte alimenter historique opérations sur les comptes)
    */

   /* @RequestMapping(value = "/balance/{customer-id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    //public void balance(@PathVariable long id) {
        // a faire
    }*/

    /**
     * *************************************************************************************************************************
     */

    @RequestMapping(value = "/balance/{customer-id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void transfert(@PathVariable("customer-id") final Long customerId, @RequestBody AccountDto accountDto, @RequestParam(value = "Amount", required = true) final int amountTransfer, @RequestParam(value = "TransfertType", required = true) String transfertType) {

        CustomerEntity customer = customerService.getCustomerById(customerId);
        List<AccountEntity> customerAccounts = customer.getAccounts();

        if (transfertType.equals("DEPOSIT")) {
            for (AccountEntity customerAccount : customerAccounts) {
                AccountEntity compteToDeposit = accountService.getAccountById(1L);
                if (customerAccount.getId().equals(customerId)) {
                    customerAccount.getId().equals(compteToDeposit.getId());
                    {
                        //apel de la méthode deposit
                    }
                }
            }
        }
    }

    //@RequestMapping(value = "/{id}/deposit", method = RequestMethod.POST)
    public void DEPOSIT(@PathVariable long id, @RequestBody AccountDto accountDto,
                        @RequestParam(value = "amount", required = true) final int amountDeposit) {

        AccountEntity account = mapper.map(accountDto, AccountEntity.class);
        AccountEntity accountToDeposit = accountService.getAccountById(id);

        int sommeOperationDeposit = 0;
        List<TransactionEntity> operations = account.getOperations();
        LOGGER.info("For account {}", account.getId());
        LOGGER.info("Operations is:{}", operations);
        Date today = new Date();

        for (TransactionEntity operation : operations) {
            if (operation.getTransactionType() == "DEPOSIT" && operation.getTransactionDate().getMonth() == today.getMonth()) {
                sommeOperationDeposit += operation.getAmount();
            }
            }
        /* Informations logger */
        if (amountDeposit + sommeOperationDeposit < 3000) {
            LOGGER.info("Le montant maximum de dépot n'est pas encore atteint {}", amountDeposit + sommeOperationDeposit);
        } else {
            LOGGER.info("Le montant maximum de dépot est atteint {}", amountDeposit + sommeOperationDeposit);
        }
        if (amountDeposit > 0) {
            LOGGER.info("Le montant déposé est supérieur à 0 :{}", amountDeposit);
        } else {
            LOGGER.info("Le montant déposé n'être pas supérieur à 0 :{}", amountDeposit);
        }
        if (amountDeposit + accountToDeposit.getBalance() < accountToDeposit.getMaxBalance()) {
            LOGGER.info("Le plafond n'est pas encore atteint :{}", amountDeposit + accountToDeposit.getBalance());
        } else {
            LOGGER.info("Le plafond est atteint :{}", amountDeposit + accountToDeposit.getBalance());
        }

        /* Action si tout ce passe bien */
        if (amountDeposit + sommeOperationDeposit > 3000 && amountDeposit > 0 && amountDeposit + accountToDeposit.getBalance() < accountToDeposit.getMaxBalance()) {
            accountToDeposit.deposit(amountDeposit);
            accountService.saveAccount(accountToDeposit);

            TransactionEntity transfertEntity = new TransactionEntity((long) account.getOperations().size(), "Transfert", amountDeposit, today, accountToDeposit, null);
            account.getOperations().add(transfertEntity);
        }
        }

    // @RequestMapping(value = "/{id}/withdraw", method = RequestMethod.POST)
    public void WITHDRAW(@PathVariable long id, @RequestBody AccountDto accountDto,
                         @RequestParam(value = "amount", required = true) final int amountDebit) {
        AccountEntity account = mapper.map(accountDto, AccountEntity.class);
        AccountEntity accountToDebit = accountService.getAccountById(id);

        int sommeOperationDebit = 0;
        List<TransactionEntity> operations = account.getOperations();
        LOGGER.info("For account {}", account.getId());
        LOGGER.info("Operations is:{}", operations);
        Date today = new Date();

        for (TransactionEntity operation : operations) {
            if (operation.getTransactionType() == "WITHDRAWAL") {
                sommeOperationDebit = sommeOperationDebit + operation.getAmount();
            }
            if (sommeOperationDebit + amountDebit > 2500) {
                LOGGER.info("Le montant maximum de retrait est atteind {}", sommeOperationDebit);
            }
            }
        /* Informations logger */
        if (amountDebit > 0) {
            LOGGER.info("La somme débité est supérieur à 0 {}", amountDebit);
        } else {
            LOGGER.info("La somme débité n'est pas supérieur à 0 {}", amountDebit);
        }

        if (amountDebit + accountToDebit.getBalance() > accountToDebit.getMaxBalance()) {
            LOGGER.info("Le solde dépasse le plafond {}", amountDebit + accountToDebit.getBalance());
        } else {
            LOGGER.info("Le solde ne dépasse pas encore le plafond {}", amountDebit + accountToDebit.getBalance());
        }
        /* Action */
        if (amountDebit > 0 && amountDebit + accountToDebit.getBalance() < accountToDebit.getMaxBalance()) {
            accountToDebit.withDraw(amountDebit);
            accountService.saveAccount(accountToDebit);

            TransactionEntity transfertEntity = new TransactionEntity((long) account.getOperations().size(), "Transfert", amountDebit, today, null, accountToDebit);
            account.getOperations().add(transfertEntity);
        }
        }


    // GET /account : Récupération de la liste des comptes
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Iterable<AccountDto> listAccount() {
        Iterable<AccountEntity> accounts = from(accountService.getAllAccounts()).toList();
        Iterable<AccountDto> accountDtos = newArrayList();
        mapper.map(accounts, accountDtos);
        LOGGER.info("List Accounts is {}", accountDtos);

        return accountDtos;
    }
}

