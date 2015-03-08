package fr.iut.montreuil;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mélina on 07/03/2015.
 */
public interface AccountService {

    @Transactional
    AccountEntity save(AccountEntity accountEntity);

    Iterable<AccountEntity> getAllAccounts();
    AccountEntity getAccountById(Long id);
    AccountEntity saveAccountEntity(AccountEntity account);
    AccountEntity updateAccount(AccountEntity account);
    void deleteAccount(Long id);
    AccountEntity getAccountByShortName(String shortName);
}
