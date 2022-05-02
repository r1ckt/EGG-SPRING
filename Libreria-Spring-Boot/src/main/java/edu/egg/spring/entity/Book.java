package edu.egg.spring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book", indexes = {@Index(name="idx_book_id", columnList="book_id")})
public class Book {

    @Id
    @GeneratedValue(strategy  =IDENTITY)
    @Column(name="book_id")
    private Long id;
    @Column(name = "book_isbn")
    private String isbn;
    @Column(name = "book_title")
    private String name;
    @Column(name = "book_year", columnDefinition = "YEAR")
    private Integer year;
    @Column(name = "book_copies")
    private Integer copies;
    @Column(name = "book_borrowed_copies")
    private Integer borrowedCopies;
    @Column(name = "book_remained_copies")
    private Integer remainedCopies;
    @Column(name = "book_status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id", nullable = false)
    private Publisher publisher;

}
