package pl.sda.hello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/all",method = RequestMethod.GET, produces = "application/json")
    public Books getAllBooks(){
        return booksService.getAllBooks();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = "application/json")
    public Book getBookById(@PathVariable String id){
        return booksService.getBookById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addBook(@RequestBody Book book){
        booksService.addBook(book);

        URI uri=null;
        try {
            uri = new URI(ServletUriComponentsBuilder.fromCurrentRequestUri().build()+"/" + book.getId());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return booksService.contains(book) ? ResponseEntity.created(uri).build() : ResponseEntity.badRequest().build();
    }




}
