package fr.iut.montreuil.lpcsid.web.controller;

import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import fr.iut.montreuil.lpcsid.service.TransactionService;
import fr.iut.montreuil.lpcsid.web.dto.TransactionDto;
import fr.iut.montreuil.lpcsid.web.exception.DataIntegrityException;
import fr.iut.montreuil.lpcsid.web.exception.NotFoundException;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


@RequestMapping("api/transaction")
public class TransactionController {


    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private Mapper mapper;

    // GET /Transaction : Récupération de la liste des transactions
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Iterable<TransactionDto> listTransaction() {
        Iterable<TransactionEntity> transactions = from(transactionService.getAllTransactions()).toList();
        Iterable<TransactionDto> transactionDtos = newArrayList();
        mapper.map(transactions, transactionDtos);
        LOGGER.info("List Transactions is {}", transactionDtos);
        return transactionDtos;
    }

    // GET /{id} donne le détail d'une transaction
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    TransactionDto getTransactionById(@PathVariable long id) {
        TransactionEntity transaction = transactionService.getTransactionById(id);
        TransactionDto transactionDto = mapper.map(transaction, TransactionDto.class);
        if (null == transaction) {
            throw new NotFoundException(NO_ENTITY_FOUND);
        }
        LOGGER.info("Transaction is {}, return.", transactionDto);
        transactionService.saveTransaction(transaction);
        return transactionDto;
    }

    // POST /Transaction : enregistrement d'une nouvelle transaction, renvoi un statut CREATED
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionEntity transactionEntity = mapper.map(transactionDto, TransactionEntity.class);

        TransactionEntity savedTransaction;
        try {
            savedTransaction = transactionService.saveTransaction(transactionEntity);
            LOGGER.info("Transaction Creating id is{}, persisting.", transactionEntity.getIdTransaction());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(WRONG_ENTITY_INFORMATION);
        }
        return mapper.map(savedTransaction, TransactionDto.class);

    }

}

