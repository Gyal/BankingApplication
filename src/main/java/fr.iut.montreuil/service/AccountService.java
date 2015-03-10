package fr.iut.montreuil.service;


import fr.iut.montreuil.entity.AccountEntity;
import fr.iut.montreuil.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Mélina on 07/03/2015.
 */
@Service
public class AccountService{

    private final AccountRepository accountRepository;

    @Inject
    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Transactional
    public AccountEntity save(final AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }


    public Iterable<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }


    public AccountEntity getAccountById(Long id) {
        return accountRepository.findOne(id);
    }


    public AccountEntity saveAccountEntity(AccountEntity account) {
        return account;
    }


    public AccountEntity updateAccount(AccountEntity account) {
        return account;
    }


    public void deleteAccount(Long id) {
            accountRepository.delete(id);

    }

    public AccountEntity getAccountByShortName(String shortName) {
        return null;
    }

}
