package fr.iut.montreuil.controller;

import fr.iut.montreuil.entity.TransactionEntity;
import fr.iut.montreuil.service.TransactionService;
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
public class TransactionController {


    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    private TransactionService transactionService;

   /* @Inject
    public AccountController(final AccountService accountService){
        this.accountService = accountService;
    }*/

    // GET /account : Récupération de la liste des comptes
    @RequestMapping(value = "/transactions", method = RequestMethod.GET, produces = "application/json")
    public  @ResponseBody Iterable<TransactionEntity> list(Model model){
        final Iterable<TransactionEntity> transactions = this.transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        LOGGER.info("List Transaction is {}", transactions);
        return transactions;
    }


    // GET /accountId : Récupération d'un compte par son ID
    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.GET, produces = "application/json" )
    public @ResponseBody TransactionEntity getById(@PathVariable long id){
        TransactionEntity transactionEntity = transactionService.getTransactionById(id);
        LOGGER.info("Transaction id is {}, return.", transactionEntity);
       return  transactionEntity;
    }

    // PUT /account : enregistrement d'un nouveau compte, renvoi un statut
    @RequestMapping(value = "/transaction", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTransaction(@RequestBody TransactionEntity transactionEntity) {
        transactionService.saveTransaction(transactionEntity);

        LOGGER.info("Transaction Creating{}, persisting.", transactionEntity.toString());
    }

    @RequestMapping(value = "/transaction/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable long id){
        transactionService.deleteTransaction(id);
    }
    }

