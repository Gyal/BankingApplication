package fr.iut.montreuil.lpcsid.service;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Mélina on 07/03/2015.
 */
@Service
@Transactional
public class AccountService{

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
    public AccountEntity getAccountByShortName(String shortName) {
        return null;
    }


}
