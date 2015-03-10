package fr.iut.montreuil.entity;

import javax.persistence.*;

/**
 * Created by Mélina on 07/03/2015.
 */
@Entity
@Table(name = "account")
public class AccountEntity {
    @JoinTable(name = "account_customer", joinColumns = {@JoinColumn(name = "account_id")}, inverseJoinColumns = {@JoinColumn(name = "customer_id")})

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String shortName;



}
