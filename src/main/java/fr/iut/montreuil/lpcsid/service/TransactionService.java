package fr.iut.montreuil.lpcsid.service;

import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import fr.iut.montreuil.lpcsid.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Mélina on 07/03/2015.
 */
@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Inject
    public TransactionService(final TransactionRepository transactionRepository) {this.transactionRepository = transactionRepository;}


    public TransactionEntity saveTransaction(final TransactionEntity transactionEntity) {return transactionRepository.save(transactionEntity);}
    public void deleteTransaction(Long id){transactionRepository.delete(id);}
    public Iterable<TransactionEntity> getAllTransactions() {return transactionRepository.findAll();}
    public TransactionEntity getTransactionById(Long id) {return transactionRepository.findOne(id);}

}
