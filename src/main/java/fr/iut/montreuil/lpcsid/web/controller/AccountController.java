package fr.iut.montreuil.lpcsid.web.controller;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.entity.OperationDetailEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import fr.iut.montreuil.lpcsid.service.AccountService;
import fr.iut.montreuil.lpcsid.service.CustomerService;
import fr.iut.montreuil.lpcsid.service.OperationDetailService;
import fr.iut.montreuil.lpcsid.service.TransactionService;
import fr.iut.montreuil.lpcsid.web.dto.AccountDto;
import fr.iut.montreuil.lpcsid.web.dto.CustomerDto;
import fr.iut.montreuil.lpcsid.web.exception.DataIntegrityException;
import fr.iut.montreuil.lpcsid.web.exception.NotFoundException;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static fr.iut.montreuil.lpcsid.web.exception.ErrorCode.*;

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
    private OperationDetailService operationDetailService;
    @Autowired
    private Mapper mapper;


    CustomerEntity customerTest = new CustomerEntity();


    /**
     * ***********************************************************************************************************
     * <p/>
     * Method: RecurringTaxSAVINGS
     * Type : Scheduled pour les comptes EPARGNE UNIQUEMENT
     * Prélèvement du montant de la taxe du compte tous les ans (0.06% du solde)
     * <p/>
     * *************************************************************************************************************
     */


    //Prélévement de l'impot une fois par ans ( 31557600000 milliseconde) pour le compte SAVINGS pour faire le test : mettre 60000 qui correspond à une minute
    //@Scheduled(fixedRateString = "60000")
    /*String day=;
    String month =;
    */
    @Scheduled(cron = " 0 0 1 1 1 *")
    public void RecurringTaxSAVINGS() {
        LOGGER.info("Scheduler launched at {}", new Date());
        Iterable<AccountEntity> accountList = accountService.getAllAccounts();
        LOGGER.info("List : {}", accountList);

        for (AccountEntity account : accountList) {
            if ("SAVINGS".equals(account.getType())) {
                LOGGER.info("ID account will be taxed : {}", account.getId());
                double balance = account.getBalance();
                LOGGER.info("Initial balance {}", account.getBalance());

                double deduct = account.getTaxation() * balance;
                LOGGER.info("Deduct Amount {}", deduct / 100);
                account.setBalance(balance - deduct / 100);
                LOGGER.info("Balance after taxation{}", balance);
                accountService.saveAccount(account);

                // ici il faudra utiliser la méthode withdraw pour plus de simplicité
            }

        }
    }


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

        // Récupération du compte en BDD avec l'ID fournis
        AccountEntity accountGetted = accountService.getAccountById(accountId);
        if (null == accountGetted) {
            throw new NotFoundException(NO_ENTITY_FOUND);
        }
        AccountDto accountDto = mapper.map(accountGetted, AccountDto.class);
        LOGGER.info("AccountGetted {}", accountDto);


        // Récupération de l'utilisateur en BDD avec l'ID fournis
        CustomerEntity userGetted = customerService.getCustomerById(customerId);
        if (null == userGetted) {
            LOGGER.info("Aucun utilisateur n'est trouvé avec l'ID mappé : {}", userGetted);
            throw new NotFoundException(NO_ENTITY_FOUND);
        }
        CustomerDto userDto = mapper.map(userGetted, CustomerDto.class);
        LOGGER.info("UserGetted {}", userDto);

        // Vérification de la concordance entre le compte et l'utilisateur*/
        if (accountDto.getCustomer().getIdCustomer().equals(userDto.getIdCustomer())) {
            LOGGER.info("AccountGetted is mapped with the userGetted {}", accountDto + "" + userDto);
            return accountDto;
        } else {
            LOGGER.info("Le compte n'appartient pas ) l'utilisateur demandé{}", accountDto.getCustomer().getIdCustomer().equals(userDto.getIdCustomer()));
            LOGGER.info("AccountId {}", accountDto.getCustomer().getIdCustomer());
            LOGGER.info("USER {}", userDto.getIdCustomer());
            throw new DataIntegrityException(UNAUTHORIZED);
        }
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

        // Vérification du champ Type sinon lève une exception WRONG_ENTITY_INFORMATION
        if (("CURRENT").equals(accountType) || ("SAVINGS").equals(accountType)) {
            LOGGER.info("LOG: accountType is OK : equals CURRENT OR SAVINGS, continue");
            CustomerEntity customer = customerService.getCustomerById(id);
            CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
            AccountEntity accountEntity = new AccountEntity(accountName, accountType, customer);
            double MAX_BALANCE = 0;
            double taxation = 0;
            // Mise à jour du montant de l'impot+de la balanceMax en fonction du type de oompte
            //  Si c'est un compte courant alors MAX_BALANCE = 2500 et taxation = 0, si SAVINGS alors 85000 et taxation = 0.06*/
            if (("CURRENT").equals(accountEntity.getType())) {
                LOGGER.info(" LOG: accountType is {}, so MAX_BALANCE is setted to 25000 ", accountEntity.getType());
                MAX_BALANCE = 25000;
                LOGGER.info(" LOG: accountType is {}, so taxation is setted to 0 ", accountEntity.getType());
                taxation = 0;
            }
            if (("SAVINGS").equals(accountEntity.getType())) {
                LOGGER.info(" LOG:accountType is {}, so MAX_BALANCE is setted to 850000 ", accountEntity.getType());
                MAX_BALANCE = 850000;
                LOGGER.info(" LOG: accountType is {}, so taxation is setted to 0.06 ", accountEntity.getType());
                taxation = 0.06;
            }

            accountEntity.setMAX_BALANCE(MAX_BALANCE);
            accountEntity.setTaxation(taxation);
            accountEntity.setDateCreated(new Date());
            accountEntity.setCustomer(customer);

            int compteurSAV = 0;
            int compteurCUR = 0;

            //Récupération de la liste des comptes
            List<AccountEntity> accounts = customer.getAccounts();

            // Parcours de la liste des comptes
            for (AccountEntity account : accounts) {
                if (("CURRENT").equals(account.getType())) {
                    compteurCUR = 1;
                    LOGGER.info(" l'utilisateur d'id {}", customer.getIdCustomer() + " a au moins un compte courrant");
                } else {
                    compteurSAV = 1;
                    LOGGER.info(" l'utilisateur d'id {}", customer.getIdCustomer() + " a au moins un compte PEL");
                }
            }
            if (("CURRENT").equals(accountEntity.getType())) {
                if (compteurCUR == 0) {
                    accountEntity = accountService.saveAccount(accountEntity);
                    customer.getAccounts().add(accountEntity);
                    customerService.saveCustomer(customer);

                    LOGGER.info(" LOG: Account id {}, as bean added to the customer id {}.", accountEntity.getId(), customer.getIdCustomer());

                }
            } else {
                if (compteurSAV == 0) {
                    accountEntity = accountService.saveAccount(accountEntity);
                    customer.getAccounts().add(accountEntity);
                    customerService.saveCustomer(customer);
                    LOGGER.info(" LOG: Account id {}, as bean added to the customer id {}.", accountEntity.getId(), customer.getIdCustomer());

                    //  return mapper.map(accountSaved, AccountDto.class);
                }
            }

        } else {
            LOGGER.info(" LOG: AccountType is NOK : not equals CURRENT OR SAVINGS or already existing {}", accountType);

            throw new DataIntegrityException(WRONG_ENTITY_INFORMATION);

        }
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
        CustomerEntity customer = customerService.getCustomerById(customerId);
        AccountEntity accountCredited = accountService.getAccountById(account);

        Boolean accountCustomer = customer.getIdCustomer().equals(accountCredited.getCustomer().getIdCustomer());
        if (accountCustomer.equals(true)) {


            // AccountEntity account = mapper.map(accountCredited, AccountEntity.class);
            // AccountEntity accountToDeposit = accountService.getAccountById(id);

            int sommeOperationDeposit = 0;
            List<TransactionEntity> operations = accountCredited.getOperations();
            LOGGER.info("For account {}", accountCredited.getId());
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
            if (amountDeposit + accountCredited.getBalance() < accountCredited.getMAX_BALANCE()) {
                LOGGER.info("Le plafond n'est pas encore atteint :{}", amountDeposit + accountCredited.getBalance());
            } else {
                LOGGER.info("Le plafond est atteint :{}", amountDeposit + accountCredited.getBalance());
            }

        /* Action si tout ce passe bien */
            try {
            if (amountDeposit + sommeOperationDeposit < 3000 && amountDeposit > 0 && amountDeposit + accountCredited.getBalance() < accountCredited.getMAX_BALANCE()) {
                LOGGER.info("Verification {} {} {}", amountDeposit + sommeOperationDeposit < 3000, amountDeposit > 0, amountDeposit + accountCredited.getBalance() < accountCredited.getMAX_BALANCE());

                accountCredited.deposit(amountDeposit);
                accountService.saveAccount(accountCredited);


                TransactionEntity operation = new TransactionEntity("DEPOSIT", amountDeposit, today, accountCredited);
                transactionService.saveTransaction(operation);
                accountCredited.getOperations().add(operation);
            }
                } catch (DataIntegrityException e) {
                    throw new DataIntegrityException(BAD_REQUEST);
                }

        } else {
            LOGGER.info("pas de concordance{}", accountCustomer);
            throw new DataIntegrityException(UNAUTHORIZED);

        }
    }


    @RequestMapping(value = "/balance/{customer-id}/withdraw", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@PathVariable(value = "customer-id") Long customerId,
                         @RequestParam(value = "amount", required = true) final int amountWithDraw,
                         @RequestParam(value = "accountDebited", required = true) Long account) {
        AccountEntity accountDebited = accountService.getAccountById(account);

        CustomerEntity customer = customerService.getCustomerById(customerId);
        LOGGER.info("type{}", accountDebited.getType());

        Boolean accountCustomer = customer.getIdCustomer().equals(accountDebited.getCustomer().getIdCustomer());
        if (accountCustomer.equals(true) && ("CURRENT").equals(accountDebited.getType())) {

            int sommeOperationDebit = 0;
            List<TransactionEntity> operations = accountDebited.getOperations();
            LOGGER.info("For account {}", accountDebited.getId());
            LOGGER.info("Operations is:{}", operations);
            Date today = new Date();

            for (TransactionEntity operation : operations) {
                if (("WITHDRAW").equals(operation.getTransactionType())) {
                    sommeOperationDebit = sommeOperationDebit + operation.getAmount();
                }
                if (sommeOperationDebit + amountWithDraw > 2500) {
                    LOGGER.info("Le montant maximum de retrait est atteind {}", sommeOperationDebit);
                }
            }
        /* Informations logger */
            if (amountWithDraw > 0) {
                LOGGER.info("La somme débité est supérieur à 0 : {}", amountWithDraw);
            } else {
                LOGGER.info("La somme débité n'est pas supérieur à 0 {}", amountWithDraw);
            }

            if (amountWithDraw + accountDebited.getBalance() > accountDebited.getMAX_BALANCE()) {
                LOGGER.info("Le solde dépasse le plafond {}", amountWithDraw + accountDebited.getBalance());
            } else {
                LOGGER.info("Le solde ne dépasse pas encore le plafond {}", amountWithDraw + accountDebited.getBalance());
            }
        /* Action */
            if (accountDebited.getBalance() - amountWithDraw >= 0 && amountWithDraw > 0 && amountWithDraw + accountDebited.getBalance() <= accountDebited.getMAX_BALANCE()) {
                accountDebited.withDraw(amountWithDraw);
                accountService.saveAccount(accountDebited);

                TransactionEntity operation = new TransactionEntity("WITHDRAW", amountWithDraw, today, accountDebited);
                transactionService.saveTransaction(operation);
                accountDebited.getOperations().add(operation);
            }
        } else {
            LOGGER.info("pas de concordance{}", accountCustomer);
            throw new DataIntegrityException(UNAUTHORIZED);
        }
    }


    @RequestMapping(value = "/balance/{customer-id}/transfer", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void transfert(@PathVariable(value = "customer-id") Long customerId,
                          @RequestParam(value = "amount", required = true) final int amountTransfer,
                          @RequestParam(value = "from", required = true) Long from,
                          @RequestParam(value = "toTest", required = true) Long to) {
        AccountEntity accountDebited = accountService.getAccountById(from);

        LOGGER.info("accountDebited  exist {}", accountDebited);

        AccountEntity accountCredited = accountService.getAccountById(to);
        LOGGER.info("accountCredited exist {}", accountCredited);

        CustomerEntity customer = customerService.getCustomerById(customerId);
        LOGGER.info("customer {}", customer);

        Boolean accountCustomer = customer.getIdCustomer().equals(accountDebited.getCustomer().getIdCustomer());
        LOGGER.info("accountCustomer exist{}", customer);

        Date today = new Date();

        if (accountCustomer.equals(true)) {
            LOGGER.info(" Le compte appartiend bien à l'utilisateur qui initie la demande TRUE{}", accountCustomer);
            accountDebited.transfert(accountDebited, accountCredited, amountTransfer);

            TransactionEntity operation = new TransactionEntity("WITHDRAW", amountTransfer, today, accountDebited);
            transactionService.saveTransaction(operation);

            OperationDetailEntity operationDetailEntity = new OperationDetailEntity(from, to, operation);
            operationDetailService.saveOperationDetail(operationDetailEntity);
            accountDebited.getOperations().add(operation);

        } else {
            LOGGER.info("pas de concordance{}", accountCustomer);
            throw new DataIntegrityException(UNAUTHORIZED);
        }
    }

    // GET /account : Récupération de la liste des comptes
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<AccountDto> listAccount() {
        List<AccountEntity> accounts = from(accountService.getAllAccounts()).toList();
        //Iterable<AccountEntity> accounts = accountService.getAllAccounts();
        List<AccountDto> accountDtos = newArrayList();
        mapper.map(accounts, accountDtos);
        LOGGER.info("List Accounts is {}", accountDtos);
        return accountDtos;
    }


    @RequestMapping(value = "/{customer-id}", method = RequestMethod.GET, produces = "application/json")
    public CustomerDto getUserAccounts(@PathVariable(value = "customer-id") Long customerId) {

        // Récupération de l'utilisateur en BDD avec l'ID fournis
        CustomerEntity userGetted = customerService.getCustomerById(customerId);
        CustomerDto userDto = mapper.map(userGetted, CustomerDto.class);
        if (userDto.equals(null)) {
            LOGGER.info("in exception");

            LOGGER.info("Aucun utilisateur n'est trouvé avec l'ID mappé : {}", userDto);
            throw new NotFoundException(NO_ENTITY_FOUND);
        } else {
            LOGGER.info("User {}", userDto.getLastname());

            LOGGER.info("Comptes:{}", userGetted.getAccounts());
            LOGGER.info("Comptes:{}", userDto.getAccounts());

        }
        return userDto;
    }
}

