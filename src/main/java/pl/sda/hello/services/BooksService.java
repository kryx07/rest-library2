package pl.sda.hello.services;

import org.springframework.stereotype.Service;
import pl.sda.hello.logic.BooksFileHandler;
import pl.sda.hello.model.Book;
import pl.sda.hello.model.Books;

import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class BooksService {

    private BooksFileHandler booksFileHandler = new BooksFileHandler();

    public Book getBookById(String id){
        return booksFileHandler.readAll().get(id);
    }

    public Books getAllBooks(){
        return booksFileHandler.readAll();
    }

    public void deleteBookById(String id){
        Books books = booksFileHandler.readAll();
        books.deleteBook(books.get(id));
        booksFileHandler.save(books);
    }

    public boolean addBook(Book book){

        Books books = booksFileHandler.readAll();
        if (books==null){
            books = new Books(new HashMap<String,Book>());
        }

        books.addBook(book);
        booksFileHandler.save(books);

        return booksFileHandler.readAll().contains(book.getId());
    }

    public boolean contains(Book book){
        return booksFileHandler.readAll().contains(book);
    }
    public boolean contains(String id){
        return booksFileHandler.readAll().contains(id);
    }

    public Book createWithError(String errorMessage){
        Book  book = new Book();
        book.setErrorMessage(errorMessage);
        return book;
    }
    /*public Book createBooksWithError(String errorMessage){
        Books  books = new Books();
        books.setErrorMessage(errorMessage);
        return books;
    }*/


    public Books getBookByAuthor(String author) {
        return new Books(booksFileHandler.readAll()
                .getBookMap()
                .values()
                .stream()
                .filter(b->b.getAuthor().equals(author))
                .collect(Collectors.toMap(book->book.getId(),x->x)));
    }
}
