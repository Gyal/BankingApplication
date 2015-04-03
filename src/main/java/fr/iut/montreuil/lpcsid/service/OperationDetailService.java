package fr.iut.montreuil.lpcsid.service;

import fr.iut.montreuil.lpcsid.entity.OperationDetailEntity;
import fr.iut.montreuil.lpcsid.repository.OperationDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Mélina on 07/03/2015.
 * L'application ne s'intéresse pas à l'implémentation des mécanisme qui doivent rester "invisible".
 * Ainsi même si ces mécanismes changent cela n'impactera pas le code de l'application.
 */
@Service
@Transactional
public class OperationDetailService {

    private final OperationDetailRepository operationDetailRepository;

    @Inject
    public OperationDetailService(final OperationDetailRepository operationDetailRepository) {
        this.operationDetailRepository = operationDetailRepository;
    }

    public OperationDetailEntity saveOperationDetail(final OperationDetailEntity operationDetailEntity) {
        return operationDetailRepository.save(operationDetailEntity);
    }

    public void deleteOperationDetail(Long id) {
        operationDetailRepository.delete(id);
    }

}
