package fr.iut.montreuil.lpcsid.service;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Mélina on 07/03/2015.
 * L'application ne s'intéresse pas à l'implémentation des mécanisme qui doivent rester "invisible".
 * Ainsi même si ces mécanismes changent cela n'impactera pas le code de l'application.
 */
@Service
@Transactional
public class AccountService{
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;

    @Inject
    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public AccountEntity saveAccount(final AccountEntity accountEntity) {return accountRepository.save(accountEntity);}
    public void deleteAccount(Long id) {accountRepository.delete(id);}

    public Iterable<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }
    public AccountEntity getAccountById(Long id) {
        return accountRepository.findOne(id);
    }


  /*  @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("The time is now " +new Date());
    }*/




}
