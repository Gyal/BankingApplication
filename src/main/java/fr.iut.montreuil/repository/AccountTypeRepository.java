package fr.iut.montreuil.repository;

import fr.iut.montreuil.entity.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, Long> {

}
