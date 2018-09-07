package com.paragonsoftware.db;

import com.paragonsoftware.annatation.IgnoreInDiff;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author IvIsmakaev
 */
@Data
@Entity
@Table(name = "author")
public class Author extends AuditableEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_birth")

    private Date dateOfBirth;
    @Column(name = "date_of_death")
    private Date dateOfDeath;
    @IgnoreInDiff
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
