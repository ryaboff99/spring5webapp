package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity // tells Hibernate that this is an entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // automatic ID generation by Database
    private Long id; // Primary Key

    private String title;
    private String isbn;

    @ManyToOne
    private Publisher publisher;

    // Author's Set will have such "Many To Many" implementation: @ManyToMany(mappedBy = "authors") private Set<Book> books; - mapped by variable "authors"
    @ManyToMany // Database will have "author" table and "book" table and they will be joined in table "author_book" that will hold relationship between "author" and "book" tables
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"), // @JoinColumn setting up the properties within the JoinTable
    inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();


    public Book() {
    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Long getId() {   // getter for Primary Key
        return id;
    }

    public void setId(Long id) {    // setter for Primary Key
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id != null ? id.equals(book.id) : book.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
