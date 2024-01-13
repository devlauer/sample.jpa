package de.elnarion.sample.jpa.domain.bidirectional;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "review_seq",sequenceName = "review_seq",allocationSize = 1)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "fk_book")
    private Book book;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
