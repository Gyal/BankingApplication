package fr.iut.montreuil.lpcsid.service;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import fr.iut.montreuil.lpcsid.entity.OperationDetailEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import fr.iut.montreuil.lpcsid.repository.AccountRepository;
import fr.iut.montreuil.lpcsid.repository.CustomerRepository;
import fr.iut.montreuil.lpcsid.repository.TransactionRepository;
import fr.iut.montreuil.lpcsid.web.dto.AccountDto;
import fr.iut.montreuil.lpcsid.web.dto.CustomerDto;
import fr.iut.montreuil.lpcsid.web.exception.DataIntegrityException;
import fr.iut.montreuil.lpcsid.web.exception.NotFoundException;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static fr.iut.montreuil.lpcsid.web.exception.ErrorCode.*;

/**
 * Created by Mélina on 07/03/2015.
 */

@Component
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    //Utilisation des services au lieu des repository
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private OperationDetailService operationDetailService;
    @Autowired
    private Mapper mapper;


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
        Iterable<AccountEntity> accountList = accountRepository.findAll();
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
                accountRepository.save(account);

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

    public AccountDto getUserAccount(Long accountId, Long customerId) {
        AccountEntity account = accountRepository.getOne(accountId);
        CustomerEntity user = customerRepository.getOne(customerId);
        if (null == account || null == user) {

            LOGGER.info("Aucun utilisateur n'est trouvé avec l'ID mappé : {}", user);
            throw new NotFoundException(NO_ENTITY_FOUND);
        }

        // Vérification de la concordance entre le compte et l'utilisateur*/
        if (! account.getCustomer().getIdCustomer().equals(user.getIdCustomer())) {
            LOGGER.warn("Le compte n'appartient pas ) l'utilisateur demandé{}", account.getCustomer().getIdCustomer().equals(user.getIdCustomer()));
            throw new DataIntegrityException(UNAUTHORIZED);
        }

        AccountDto accountDto = mapper.map(account, AccountDto.class);
        LOGGER.info("AccountGetted {}", accountDto);
        return accountDto;
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

    public void createAccount(long id, String accountName, String accountType) {

        // Vérification du champ Type sinon lève une exception WRONG_ENTITY_INFORMATION
        if (("CURRENT").equals(accountType) || ("SAVINGS").equals(accountType)) {
            LOGGER.info("LOG: accountType is OK : equals CURRENT OR SAVINGS, continue");
            CustomerEntity customer =customerRepository.getOne(id);
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
                    accountEntity = accountRepository.save(accountEntity);
                    customer.getAccounts().add(accountEntity);
                    customerRepository.save(customer);

                    LOGGER.info(" LOG: Account id {}, as bean added to the customer id {}.", accountEntity.getId(), customer.getIdCustomer());

                }
            } else {
                if (compteurSAV == 0) {
                    accountEntity = accountRepository.save(accountEntity);
                    customer.getAccounts().add(accountEntity);
                    customerRepository.save(customer);
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

    public void deposit(Long customerId, final int amountDeposit, Long idAccount) {

        AccountEntity accountCredited = accountRepository.getOne(idAccount);

        CustomerEntity customer = customerRepository.findOne(customerId);
        Boolean accountCustomer = customer.getIdCustomer().equals(accountCredited.getCustomer().getIdCustomer());
        if (accountCustomer.equals(true)) {

            int sommeOperationDeposit = 0;
            List<TransactionEntity> currentOperations = transactionRepository.findAllByAccountAndTransactionType(accountCredited, "DEPOSIT");
            LOGGER.info("For account {}", accountCredited.getId());
            LOGGER.info("Operations is:{}", currentOperations);
            Date today = new Date();

            for (TransactionEntity operation : currentOperations) {
                if (operation.getTransactionDate().getMonth() == today.getMonth()) {
                    sommeOperationDeposit += operation.getAmount();
                }
            }
            LOGGER.info("operation {}", sommeOperationDeposit);

            if (amountDeposit + sommeOperationDeposit < 3000) {
                LOGGER.info("Le montant maximum de dépot n'est pas encore atteint {}", amountDeposit + sommeOperationDeposit);
            } else {
                LOGGER.error("Le montant maximum de dépot est atteint {}", amountDeposit + sommeOperationDeposit);
            }
            if (amountDeposit > 0) {
                LOGGER.info("Le montant déposé est supérieur à 0 :{}", amountDeposit);
            } else {
                LOGGER.error("Le montant déposé n'être pas supérieur à 0 :{}", amountDeposit);
            }
            if (amountDeposit + accountCredited.getBalance() < accountCredited.getMAX_BALANCE()) {
                LOGGER.info("Le plafond n'est pas encore atteint :{}", amountDeposit + accountCredited.getBalance());
            } else {
                LOGGER.error("Le plafond est atteint :{}", amountDeposit + accountCredited.getBalance());
            }

                if (amountDeposit + sommeOperationDeposit <= 3000 && amountDeposit > 0 && amountDeposit+ amountDeposit + accountCredited.getBalance() <= accountCredited.getMAX_BALANCE()) {

                    LOGGER.info("Verification {} {} {}", amountDeposit + sommeOperationDeposit < 3000, amountDeposit > 0, amountDeposit + accountCredited.getBalance() <= accountCredited.getMAX_BALANCE());

                   accountCredited.deposit(amountDeposit);
                    accountRepository.save(accountCredited);


                    TransactionEntity operation = new TransactionEntity("DEPOSIT", amountDeposit, today, accountCredited);
                    transactionRepository.save(operation);
                    accountRepository.save(accountCredited);
                }else{  throw new DataIntegrityException(BAD_REQUEST);}


        } else {
            LOGGER.error("pas de concordance{}", accountCustomer);
            throw new DataIntegrityException(UNAUTHORIZED);

        }
    }


    public void withdraw(Long customerId, final int amountWithDraw, Long idAccount) {
        AccountEntity accountDebited = accountRepository.getOne(idAccount);
        CustomerEntity customer = customerRepository.findOne(customerId);
        List<TransactionEntity> withdrawOperations = transactionRepository.findAllByAccountAndTransactionType(accountDebited, "WITHDRAW");
        LOGGER.info("WithDrawOperations is:{}", withdrawOperations.size());
        Boolean accountCustomer = customer.getIdCustomer().equals(accountDebited.getCustomer().getIdCustomer());
        if (accountCustomer.equals(true) && ("CURRENT").equals(accountDebited.getType())) {

            int sommeOperationDebit = 0;
            LOGGER.info("For account {}", accountDebited.getId());
            Date today = new Date();

            for (TransactionEntity operation : withdrawOperations) {
                if (operation.getTransactionDate().getMonth() == today.getMonth()) {
                    sommeOperationDebit += operation.getAmount();
                }
            }
            LOGGER.info("operation {}", sommeOperationDebit);

            if (amountWithDraw + sommeOperationDebit <=2500) {
                LOGGER.info("Le montant maximum de retrait n'est pas encore atteint {}", amountWithDraw + sommeOperationDebit);
            } else {
                LOGGER.error("Le montant maximum de retrait est atteint {} pour le mois en cours {}", amountWithDraw + sommeOperationDebit,today.getMonth());
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
            if (amountWithDraw + sommeOperationDebit <=2500 && amountWithDraw > 0 && accountDebited.getBalance() - amountWithDraw >= 0 && amountWithDraw > 0 && amountWithDraw + accountDebited.getBalance() <= accountDebited.getMAX_BALANCE()) {
                accountDebited.withDraw(amountWithDraw);
                accountRepository.save(accountDebited);

                TransactionEntity operation = new TransactionEntity("WITHDRAW", amountWithDraw, today, accountDebited);
                transactionRepository.save(operation);

            } else{  throw new DataIntegrityException(BAD_REQUEST);}
        } else {
            LOGGER.info("pas de concordance{}", accountCustomer);
            throw new DataIntegrityException(UNAUTHORIZED);
        }
    }


    public void transfert(Long customerId, final int amountTransfer, Long from, Long to) {
        AccountEntity accountDebited = accountRepository.getOne(from);

        LOGGER.info("accountDebited  exist {}", accountDebited);

        AccountEntity accountCredited = accountRepository.getOne(to);
        LOGGER.info("accountCredited exist {}", accountCredited);

        CustomerEntity customer = customerRepository.findOne(customerId);
        LOGGER.info("customer {}", customer);

        Boolean accountCustomer = customer.getIdCustomer().equals(accountDebited.getCustomer().getIdCustomer());
        LOGGER.info("accountCustomer exist{}", customer);

        Date today = new Date();

        if (accountCustomer.equals(true)) {
            LOGGER.info(" Le compte appartiend bien à l'utilisateur qui initie la demande TRUE{}", accountCustomer);
            accountDebited.transfert(accountDebited, accountCredited, amountTransfer);

            TransactionEntity operation = new TransactionEntity("WITHDRAW", amountTransfer, today, accountDebited);
            transactionRepository.save(operation);

            OperationDetailEntity operationDetailEntity = new OperationDetailEntity(from, to, operation);
            operationDetailService.saveOperationDetail(operationDetailEntity);
        } else {
            LOGGER.info("pas de concordance{}", accountCustomer);
            throw new DataIntegrityException(UNAUTHORIZED);
        }
    }

    // GET /account : Récupération de la liste des comptes
    public List<AccountDto> listAccount() {
        List<AccountEntity> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = newArrayList();
        mapper.map(accounts, accountDtos);
        LOGGER.info("List Accounts is {}", accountDtos);
        return accountDtos;
    }


    public CustomerDto getUserAccounts(Long customerId) {

        // Récupération de l'utilisateur en BDD avec l'ID fournis
        CustomerEntity userGetted = customerRepository.findOne(customerId);
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

