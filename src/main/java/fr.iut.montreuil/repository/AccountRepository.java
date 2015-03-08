package fr.iut.montreuil.repository;

import fr.iut.montreuil.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mélina on 08/03/2015.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, Long>{
    AccountEntity findByShortName(String shortName);
}
