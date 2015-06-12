package fr.iut.montreuil.lpcsid.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mélina on 07/03/2015.
 * L'application ne s'intéresse pas à l'implémentation des mécanisme qui doivent rester "invisible".
 * Ainsi même si ces mécanismes changent cela n'impactera pas le code de l'application.
 */

@Service
@Transactional
public class TransactionService {

    //private final TransactionRepository transactionRepository;


}
