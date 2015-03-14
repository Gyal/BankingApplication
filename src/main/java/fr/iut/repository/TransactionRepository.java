package repository;

import entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Mélina on 18/02/2015.
 */
@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
