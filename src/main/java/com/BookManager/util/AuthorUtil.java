package com.bookmanager.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AuthorUtil {

    // list of messages for incoming requests (lis_authors intent)
    public static final List<String> listOfAuthorsMessages = new ArrayList<>();

    // list of messages sent as response (list_authors intent)
    public static final List<String> listOfAuthorsSelectionMessages = new ArrayList<>();

    static {
		listOfAuthorsMessages.add("Here is the list of authors: ");
		listOfAuthorsMessages.add("Sure, here is the list: ");
		listOfAuthorsMessages.add("I found the following authors: ");

		listOfAuthorsSelectionMessages.add("Which one would you like to select?");
		listOfAuthorsSelectionMessages.add("Please choose one of them to continue.");
		listOfAuthorsSelectionMessages.add("I can provide list of books if you choose an author.");
	}

    // methods for extracting a random message used by Google Assistant
    public static String getRandomListOfAuthorsMessages() {
        Integer listOfAuthorsValue = new Random().nextInt(listOfAuthorsValue.size());

        return listOfAuthorsMessages.get(listOfAuthorsValue);
    }

    public static String getRandomAuthorSelectionMessage() {
        Integer listOfAuthorsSelectionValue = new Random().nextInt(listOfAuthorsSelectionValue.size());

        return listOfAuthorsSelectionMessages.get(listOfAuthorsSelectionValue);
    }
}
