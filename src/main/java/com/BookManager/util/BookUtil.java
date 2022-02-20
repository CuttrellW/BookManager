package com.bookmanager.util;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class BookUtil {
    public static final List<String> listOfBooksMessages = new ArrayList<>();
    public static final List<String> listOfBooksSelectionMessages = new ArrayList<>();

    static {
        listOfBooksMessages.add("Sure, I got this books in my list: ");
		listOfBooksMessages.add("Ok, I found some books for you: ");
		listOfBooksMessages.add("I found the following books: ");

		listOfBooksSelectionMessages.add("Please select the book you want to know more details about.");
		listOfBooksSelectionMessages.add("Choose one book to continue.");
		listOfBooksSelectionMessages.add("I can provide book details if you select one of them.");

    }

    public static String getRandomListOfBookMessages() {
        Integer listOfBooksValue = new Random().nextInt(listOfBooksMessages.size());

        return listOfBooksMessages.get(listOfBooksValue);
    }

    public static String getRandomListOfBooksSelection() {
        Integer listOfBooksSelectionValue = new Random().nextInt(listOfBooksSelectionMessages.size());

        return listOfBooksSelectionMessages.get(listOfBooksSelectionValue);
    }
}
