package com.bookmanager.component;

import com.bookmanager.domain.Author;
import com.bookmanager.domain.Book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadDataIntoDB implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // 3 authors, 2 books each

		Author author1 = new Author("Mark", "Twain");

		Book book1 = new Book("Life on the Mississippi", "Biography", 1883);
		book1.setSummary("Life on the Mississippi is a memoir by Mark Twain detailing his days as a"
				+ " steamboat pilot on the Mississippi River before and after the American Civil War.");

		Book book2 = new Book("The Adventures of Tom Sawyer", "Biography", 1876);
		book2.setSummary("The Adventures of Tom Sawyer by Mark Twain is an 1876 novel about a young boy "
				+ "growing up along the Mississippi River. The story is set in the fictional town of"
				+ " St. Petersburg, inspired by Hannibal, Missouri, where Twain lived.");

		// save data to DB
		author1.addBook(book1);
		author1.addBook(book2);

    }
    
}
