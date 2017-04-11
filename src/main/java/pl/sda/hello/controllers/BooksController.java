package pl.sda.hello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.sda.hello.model.Book;
import pl.sda.hello.model.Books;
import pl.sda.hello.services.BooksService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Books> getAllBooks() {
        return new ResponseEntity<Books>(booksService.getAllBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        if (booksService.getBookById(id) == null) {
            return new ResponseEntity<Book>(booksService.createWithError("Book with id:" + id + " has not been found!"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Book>(booksService.getBookById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{author}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Books> getBookByAuthor(@PathVariable String author) {
        if (booksService.getBookByAuthor(author) == null) {
          //  return new ResponseEntity<Books>(booksService.createWithError("Book with id:" + author + " has not been found!"), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Books>(booksService.getBookByAuthor(author), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        booksService.addBook(book);

        URI uri = null;
        try {
            uri = new URI(ServletUriComponentsBuilder.fromCurrentRequestUri().build() + "/" + book.getId());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return  booksService.contains(book.getId()) ? ResponseEntity.created(uri).build() : ResponseEntity.badRequest().build();
    }


}
