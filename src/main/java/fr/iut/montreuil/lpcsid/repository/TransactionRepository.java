package fr.iut.montreuil.lpcsid.repository;

import fr.iut.montreuil.lpcsid.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Mélina on 08/03/2015.
 */
@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
