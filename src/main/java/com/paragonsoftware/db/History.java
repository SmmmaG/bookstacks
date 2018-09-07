package com.paragonsoftware.db;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author IvIsmakaev
 */
@Data
@Entity
@Table(name = "history")
public class History extends AuditableEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_date")
    private Date eventDate;
    @Column(name = "change_diff")
    private String dataDiff;
    @Column(name = "entity_id")
    private Long entityId;
    @Column(name = "entity_name")
    private String entityName;

    public History() {
        eventDate = new Date();
    }
}
