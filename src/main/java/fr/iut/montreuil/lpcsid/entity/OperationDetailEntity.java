package fr.iut.montreuil.lpcsid.entity;

import javax.persistence.*;

/**
 * Created by Mélina on 03/04/2015.
 */
@Entity
@Table(name = "operationDetail")
public class OperationDetailEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "operationDetail_id")
    private Long id;

    @Column(nullable = true)
    private Long debitedAccount;

    @Column(nullable = true)
    private Long creditedAccount;

    @OneToOne
    private TransactionEntity operation;

    /* Pour le dozer */
    public OperationDetailEntity() {
    }
    public OperationDetailEntity(Long id,Long from, Long to, TransactionEntity operation) {
        this.id =id;
        this.debitedAccount = from;
        this.creditedAccount = to;
        this.operation = operation;
    }

    public OperationDetailEntity(Long from, Long to, TransactionEntity operation) {
        this.debitedAccount = from;
        this.creditedAccount = to;
        this.operation = operation;
    }

    public static OperationDetailEntity newOperationDetail() {
        return newOperationDetail();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public Long getDebitedAccount() {
        return debitedAccount;
    }
    public void setDebitedAccount(Long debitedAccount) {
        this.debitedAccount = debitedAccount;
    }

    public Long getCreditedAccount() {
        return creditedAccount;
    }
    public void setCreditedAccount(Long creditedAccount) {
        this.creditedAccount = creditedAccount;
    }

    public TransactionEntity getOperation() {
        return operation;
    }
    public void setOperation(TransactionEntity operation) {
        this.operation = operation;
    }

}