package com.paragonsoftware.db;

import lombok.Data;

import javax.persistence.*;

/**
 * @author IvIsmakaev
 */
@Data
@Entity
@Table(name = "stack")
public class Stack extends AuditableEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "number")
    private Integer number;
}
