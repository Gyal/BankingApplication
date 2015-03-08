package fr.iut.montreuil;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mélina on 07/03/2015.
 */

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByShortName(String shortName);
}
