package fr.iut.montreuil.lpcsid.repository;

import fr.iut.montreuil.lpcsid.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mélina on 08/03/2015.
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
