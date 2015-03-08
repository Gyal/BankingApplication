package fr.iut.montreuil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Mélina on 07/03/2015.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Inject
    public AccountServiceImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public AccountEntity save(final AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }

    @Override
    public Iterable<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public AccountEntity getAccountById(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public AccountEntity saveAccountEntity(AccountEntity account) {
        return null;
    }

    @Override
    public AccountEntity updateAccount(AccountEntity account) {
        return null;
    }

    @Override
    public void deleteAccount(Long id) {

    }

    @Override
    public AccountEntity getAccountByShortName(String shortName) {
        return null;
    }
}