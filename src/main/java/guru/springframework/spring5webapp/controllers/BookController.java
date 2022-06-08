package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 3. register class as a Spring Bean and as a Controller in Spring MVC
public class BookController { // you should name class as: "Name+Controller" - that tells you the purpose in life of that class

    private final BookRepository bookRepository; // 4. repository as a variable

    public BookController(BookRepository bookRepository) { // 5. because we have constructor of Controller class with repository variable,
        this.bookRepository = bookRepository; // this variable is a Spring managed component - when Spring instantiate Controller instance,
    }                                         // it will inject an instance of repository into our Controller

    @RequestMapping("/books")   // 6.2. tels Spring to associate path "/books" with this specific Controller method
    public String getBooks(Model model) {   // 6.1. method take Model as a parameter

        model.addAttribute("books", bookRepository.findAll()); // 6.3. assigns list of books form "BookRpository" repository to Model instance through attributeName ("books")

        return "books"; // 6.4. define name of a View as a return type
    }
}
