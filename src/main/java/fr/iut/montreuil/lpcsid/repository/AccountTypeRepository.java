package fr.iut.montreuil.lpcsid.repository;

import fr.iut.montreuil.lpcsid.entity.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mélina on 08/03/2015.
 */
public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, Long> {
}
