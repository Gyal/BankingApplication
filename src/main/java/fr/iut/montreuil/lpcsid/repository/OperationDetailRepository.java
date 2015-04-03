package fr.iut.montreuil.lpcsid.repository;

import fr.iut.montreuil.lpcsid.entity.OperationDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Mélina on 08/03/2015.
 */
@RepositoryRestResource
public interface OperationDetailRepository extends JpaRepository<OperationDetailEntity, Long> {
}
