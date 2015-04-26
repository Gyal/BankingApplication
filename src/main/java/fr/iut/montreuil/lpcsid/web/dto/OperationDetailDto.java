package fr.iut.montreuil.lpcsid.web.dto;

import fr.iut.montreuil.lpcsid.entity.TransactionEntity;

/**
 * Created by Mélina on 03/04/2015.
 */
public class OperationDetailDto {
    private Long id;

    private Long debitedAccount;

    private Long creditedAccount;

    private TransactionEntity operation;

    /* Pour le dozer */
    public OperationDetailDto() {
    }

    public static OperationDetailDto newOperationDetailDto() {
        return newOperationDetailDto();
    }

    public OperationDetailDto(Long from, Long to, TransactionEntity operation) {
        this.debitedAccount = from;
        this.creditedAccount = to;
        this.operation = operation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDebitedAccount(Long from) {
        this.debitedAccount = from;
    }

    public void setCreditedAccount(Long to) {
        this.creditedAccount = to;
    }

    public void setOperation(TransactionEntity operation) {
        this.operation = operation;
    }

    public Long getId() {
        return id;
    }

    public Long getDebitedAccount() {
        return debitedAccount;
    }

    public Long getCreditedAccount() {
        return creditedAccount;
    }

    public TransactionEntity getOperation() {
        return operation;
    }

}

