package com.alexander.springlibrary.controller;

import com.alexander.springlibrary.dto.BookDTO;
import com.alexander.springlibrary.dto.AuthorDTO;
import com.alexander.springlibrary.dto.PublisherDTO;  // Импортируем PublisherDTO
import com.alexander.springlibrary.entity.Book;
import com.alexander.springlibrary.entity.Author;
import com.alexander.springlibrary.entity.Publisher;  // Импортируем Publisher
import com.alexander.springlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Получение списка всех книг
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Получение книги по ID
    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        Optional<Book> optionalBook = bookService.getBookById(id);
        if (optionalBook.isPresent()) {
            return convertToDTO(optionalBook.get());
        } else {
            return null; // Или ResponseEntity.notFound().build() для ответа с кодом 404
        }
    }

    // Создание новой книги
    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book createdBook = bookService.createBook(book);
        return convertToDTO(createdBook);
    }

    // Обновление книги
    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book updatedBook = bookService.updateBook(id, book);
        return convertToDTO(updatedBook);
    }

    // Удаление книги
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // Преобразование сущности Book в DTO
    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(convertToAuthorDTO(book.getAuthor()));  // Преобразуем Author в AuthorDTO
        bookDTO.setPublisher(convertToPublisherDTO(book.getPublisher())); // Преобразуем Publisher в PublisherDTO
        return bookDTO;
    }

    // Преобразование сущности Author в AuthorDTO
    private AuthorDTO convertToAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setBiography(author.getBiography());
        return authorDTO;
    }

    // Преобразование сущности Publisher в PublisherDTO
    private PublisherDTO convertToPublisherDTO(Publisher publisher) {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        // Если есть другие поля, добавьте их здесь
        return publisherDTO;
    }

    // Преобразование BookDTO в сущность Book
    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        // Преобразование AuthorDTO обратно в Author, если нужно
        return book;
    }
}
