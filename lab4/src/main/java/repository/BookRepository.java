package repository;

import model.Book;

import java.util.List;

public interface BookRepository {
    /*public BookRepository() {
        Book book = new BookBuilder()
                .setAuthor("Alex")
                .setTitle("Injection")
                .setId(123L)
                .build();

                This is how you use builder

    }
    */

    List<Book> findAll();

    Book findById(Long id);

    boolean save(Book book);

    void removeAll();


}
