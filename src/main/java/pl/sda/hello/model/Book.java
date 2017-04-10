package pl.sda.hello.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable{

    private long serialVersionUID = 83843247273768l;

    private String id;
    private String title;
    private String author;
    private String isbn;
    private LocalDate releaseDate;

    public Book(long serialVersionUID, String title, String author, String isbn, LocalDate releaseDate) {
        this.serialVersionUID = serialVersionUID;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
