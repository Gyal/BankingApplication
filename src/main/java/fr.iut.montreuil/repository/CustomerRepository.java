package fr.iut.montreuil.repository;

import fr.iut.montreuil.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Mélina on 13/03/2015.
 */
    @RepositoryRestResource
    public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    }

