package pl.sda.hello.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Books implements Serializable {

    private long serialVersionUID = 83877944648906l;

    private Map<String,Book> bookMap = new HashMap();

    public  Map<String,Book> getBookMap() {
        return bookMap;
    }

    public void setBookMap(Map<String,Book> bookMap) {
        this.bookMap = bookMap;
    }

    public String addBook(Book book){
        book.setId(UUID.randomUUID().toString());
        bookMap.put(book.getId(),book);
        return book.getId();
    }

    public void deleteBook(Book book){
        bookMap.remove(book);
    }

    public Book get(String id){
        return bookMap.get(id);
    }

    public boolean contains(Book book){
        System.out.println(bookMap.containsValue(book));
        return bookMap.containsValue(book);
    }

    public boolean contains(String id){
        return bookMap.containsKey(id);
    }


}
