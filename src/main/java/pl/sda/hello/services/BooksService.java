package pl.sda.hello.services;

import org.springframework.stereotype.Service;
import pl.sda.hello.logic.BooksFileHandler;
import pl.sda.hello.model.Book;
import pl.sda.hello.model.Books;

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

    public void addBook(Book book){

        Books books = booksFileHandler.readAll();
        if (books==null){
            books = new Books();
        }

        books.addBook(book);
        booksFileHandler.save(books);
    }

    public boolean contains(Book book){
        return booksFileHandler.readAll().contains(book);
    }











}
