package fr.iut.montreuil.entity;

import javax.persistence.*;

/**
 * Created by Mélina on 07/03/2015.
 */
@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String shortName;



}
