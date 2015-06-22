package fr.iut.montreuil.lpcsid.web.dto;

import java.io.Serializable;

/**
 * Created by Mélina on 03/04/2015.
 */

public class OperationDetailDto implements Serializable {

    private Long id;
    private Long debitedAccount;
    private Long creditedAccount;
    private Long idTransaction;

    /* Pour le dozer */
    public OperationDetailDto() {
    }

    public OperationDetailDto(Long id,Long from, Long to, Long idTransaction) {
        this.id =id;
        this.debitedAccount = from;
        this.creditedAccount = to;
        this.idTransaction = idTransaction;
    }
    public OperationDetailDto(Long from, Long to, Long idTransaction) {
        this.debitedAccount = from;
        this.creditedAccount = to;
        this.idTransaction = idTransaction;
    }

    public static OperationDetailDto newOperationDetail() {
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

    public Long getIdTransaction() {
        return idTransaction;
    }
    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }
}