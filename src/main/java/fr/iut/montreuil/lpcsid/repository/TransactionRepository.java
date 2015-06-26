package fr.iut.montreuil.lpcsid.repository;

import fr.iut.montreuil.lpcsid.entity.AccountEntity;
import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

/**
 * Created by Mélina on 08/03/2015.
 */
@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    public List<TransactionEntity> findAllByAccountAndTransactionDateGreaterThan(AccountEntity account, Date date);
    public List<TransactionEntity> findAllByAccount(AccountEntity account);
    public List<TransactionEntity> findAllByAccountAndTransactionType(AccountEntity account, String type);

}
