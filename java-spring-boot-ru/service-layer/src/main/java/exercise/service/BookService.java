package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Author;
import exercise.model.Book;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        List<Book> Books = bookRepository.findAll();
        return Books.stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + "not found"));
        return bookMapper.map(book);
    }

    public BookDTO create(BookCreateDTO bookData) {
        Book book = bookMapper.map(bookData);
        Author author = authorRepository.findById(bookData.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author with id " + bookData.getAuthorId() + "not found"));
        book.setAuthor(author);
//        author.getBooks().add(book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO update(Long id, BookUpdateDTO bookData) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + "not found"));
        bookMapper.update(bookData, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
