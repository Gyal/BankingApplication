package fr.iut.montreuil.service;


import fr.iut.montreuil.entity.AccountTypeEntity;
import fr.iut.montreuil.repository.AccountTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Mélina on 07/03/2015.
 */

@Service
@Transactional
public class AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;

    @Inject
    public AccountTypeService(final AccountTypeRepository accountTypeRepository) {this.accountTypeRepository = accountTypeRepository;}

    public AccountTypeEntity saveTypeAccount(final AccountTypeEntity accountTypeEntity) {return accountTypeRepository.save(accountTypeEntity);}
    public void deleteTypeAccount(Long id){accountTypeRepository.delete(id);}
    public Iterable<AccountTypeEntity> getAllAccountTypes() {return accountTypeRepository.findAll();}
    public AccountTypeEntity getAccountTypeById(Long id) {return accountTypeRepository.findOne(id);}
}
