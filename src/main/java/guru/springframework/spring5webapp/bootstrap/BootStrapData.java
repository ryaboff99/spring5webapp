package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans"); // define "Author" entity
        Book ddd = new Book("Domain Driven Design", "123123"); // define "Book" entity
        eric.getBooks().add(ddd); // add "ddd" to "eric" HashSet
        ddd.getAuthors().add(eric); // add "eric" to "ddd" HashSet

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric); // save "eric" - "Author" entity instance to "Author" repository
        bookRepository.save(ddd); // save "ddd" - "Book" entity instance to "Book" repository
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson"); // define "Author" entity
        Book noEJB = new Book("J2EE Development without EJB", "2548623"); // define "Book" entity
        rod.getBooks().add(noEJB); // add "noEJB" to "rod" HashSet
        noEJB.getAuthors().add(rod); // add "rod" to "noEJB" HashSet

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod); // save "rod" - "Author" entity instance to "Author" repository
        bookRepository.save(noEJB); // save "noEJB" - "Book" entity instance to "Book" repository
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count()); // output the number of books loaded in database with count() method implemented from CrudRepository interface
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());

    }
}
