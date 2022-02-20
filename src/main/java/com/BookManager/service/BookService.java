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
        Author author = authorRepository.findByFirstNameContainingIgnoreCaseandLastNameContainingIgnoreCase(firstName, lastName);
        if (author != null) {
            // get list of books mapped to this author
            List<Book> booksList = bookRepository.findAll();

            // build response to user
            response.append(BookUtil.getRandomListOfBookMessages());

            // use StringJoiner to add comma delimited list of books
            StringJoiner sj = new StringJoiner(", ");
            booksList.forEach(book -> sj.add(book.toString()));

            // add list of books to response
            response.append(sj);
            response.append(". ");

            // add a random message to ask user to select book
            response.append(BookUtil.getRandomListOfBooksSelection());
        } else {
            // author not found
            response.append(AuthorUtil.NOT_FOUND_MESSAGE);
        }

        // create response and send it back to use as ActionResponse
        ResponseBuilder responseBuilder = getResponseBuilder(request).add(response.toString());
        ActionResponse actionResponse = responseBuilder.build();

        return actionResponse;
    }
}
