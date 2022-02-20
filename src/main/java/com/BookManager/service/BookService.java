package com.bookmanager.service;

import java.util.StringJoiner;
import java.util.List;

import javax.transaction.Transactional;

import com.bookmanager.domain.Book;
import com.bookmanager.domain.Author;
import com.bookmanager.repository.AuthorRepository;
import com.bookmanager.repository.BookRepository;
import com.bookmanager.util.AuthorUtil;
import com.bookmanager.util.BookUtil;
import com.bookmanager.util.IntentUtil;
import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.grpc.Internal;


@Service
@Transactional
public class BookService extends DialogflowApp{
    
    private Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @ForIntent (IntentUtil.LIST_BOOKS_BY_AUTHOR)
    public ActionResponse findAllByAuthor(ActionRequest request) {
        logger.info("Executing Intent - " + IntentUtil.LIST_BOOKS_BY_AUTHOR);
        
        StringBuilder response = new StringBuilder();

        // extract given-name and last-name from request
        String firstName = request.getParameter("given-name").toString();
        String lastName = request.getParameter("last-name").toString();

        // extract author from database
        Author author = authorRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
        if (author != null) {
            // get list of books mapped to this author
            List<Book> booksList = bookRepository.findByAuthor(author);

            // build response to user
            response.append(BookUtil.getRandomBookMessages());

            // use StringJoiner to add comma delimited list of books
            StringJoiner sj = new StringJoiner(", ");
            booksList.forEach(book -> sj.add(book.toString()));

            // add list of books to response
            response.append(sj);
            response.append(". ");

            // add a random message to ask user to select book
            response.append(BookUtil.getRandomBooksSelection());
        } else {
            // author not found
            response.append(AuthorUtil.NOT_FOUND_MESSAGE);
        }

        // create response and send it back to use as ActionResponse
        ResponseBuilder responseBuilder = getResponseBuilder(request).add(response.toString());
        ActionResponse actionResponse = responseBuilder.build();

        return actionResponse;
    }

    @ForIntent(IntentUtil.LIST_BOOKS_BY_GENRE)
    public ActionResponse getBooksByGenre(ActionRequest request) {
        logger.info("Executing intent - " + IntentUtil.LIST_BOOKS_BY_GENRE);

        StringBuilder response = new StringBuilder();

        // extract genre parameter from request
        String genre = request.getParameter("genre").toString();

        // retrieve books under the specified genre
        List<Book> bookList = bookRepository.findByGenreContainingIgnoreCase(genre);

        if (bookList != null && bookList.size() > 0) {
            // build the response
            response = new StringBuilder(BookUtil.getRandomBookMessages());

            // join books into list
            StringJoiner sj = new StringJoiner(", ");
            bookList.forEach(book -> sj.add(book.toString()));

            // add list of books to respone
            response.append(sj);
            response.append(". ");

            // ask if user wants to continue
            response.append(BookUtil.getRandomBooksSelection());
        } else {
            // respond with books not found
            response.append(BookUtil.BOOKS_NOT_FOUND_MESSAGE);
        }

        // create response and return it to the user
        ResponseBuilder responseBuilder = getResponseBuilder(request).add(response.toString());
        ActionResponse actionResponse = responseBuilder.build();
        return actionResponse;
    }

    @ForIntent(IntentUtil.GET_BOOK_DETAILS_BY_AUTHOR)
    public ActionResponse getBookDetailsByAuthor(ActionRequest request) {
        logger.info("Executing intent - " + IntentUtil.GET_BOOK_DETAILS_BY_AUTHOR);

        return getBookDetails(request);
    }

    @ForIntent(IntentUtil.GET_BOOK_DETAILS_BY_GENRE)
    public ActionResponse getBookDetailsByGenre (ActionRequest request) {
        logger.info("Executing intent - " + IntentUtil.GET_BOOK_DETAILS_BY_GENRE);

        return getBookDetails(request);
    }

    public ActionResponse getBookDetails(ActionRequest request) {
        StringBuilder response = new StringBuilder();
        ResponseBuilder responseBuilder = null;

        // extract book title from request
        String bookTitle = request.getParameter("bookTitle").toString();

        // find book in database by title
        Book book = bookRepository.findByTitleContainingIgnoreCase(bookTitle);

        if (book != null) {
            // build the response containing book details
            response.append(BookUtil.getRandomBookDetailsMessage());

            response.append(book.getTitle());
            response.append(" was published in ");
            response.append(book.getYear());
            response.append(" and can be categorized as a ");
            response.append(book.getGenre());
            response.append(". ");
            response.append(book.getSummary());

            // create the responseBuilder object with the response
            responseBuilder = getResponseBuilder(request).add(response.toString()).endConversation();
        } else {
            response.append(BookUtil.BOOK_NOT_FOUND_MESSAGE);
            // create the responseBuilder object with response but do not end conversation
            responseBuilder = getResponseBuilder(request).add(response.toString());
        }

        // send back response to user
        ActionResponse actionResponse = responseBuilder.build();
        return actionResponse;
    }
}
