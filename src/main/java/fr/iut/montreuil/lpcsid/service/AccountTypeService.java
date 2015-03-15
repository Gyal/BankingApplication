package fr.iut.montreuil.lpcsid.service;


import fr.iut.montreuil.lpcsid.entity.AccountTypeEntity;
import fr.iut.montreuil.lpcsid.repository.AccountTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

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

    public List<AccountTypeEntity> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }
    public AccountTypeEntity getAccountTypeById(Long id) {return accountTypeRepository.findOne(id);}
}
