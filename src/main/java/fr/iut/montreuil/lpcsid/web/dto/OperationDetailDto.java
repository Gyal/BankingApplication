package fr.iut.montreuil.lpcsid.web.dto;

import fr.iut.montreuil.lpcsid.entity.TransactionEntity;

/**
 * Created by Mélina on 03/04/2015.
 */
public class OperationDetailDto {
    private Long id;


    private Long from;


    private Long to;


    private TransactionEntity operation;

    /* Pour le dozer */
    public OperationDetailDto() {
    }

    public static OperationDetailDto newOperationDetailDto() {
        return newOperationDetailDto();
    }

    public OperationDetailDto(Long from, Long to, TransactionEntity operation) {
        this.from = from;
        this.to = to;
        this.operation = operation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public void setOperation(TransactionEntity operation) {
        this.operation = operation;
    }


    public Long getId() {
        return id;
    }

    public Long getFrom() {
        return from;
    }

    public Long getTo() {
        return to;
    }

    public TransactionEntity getOperation() {
        return operation;
    }
}

