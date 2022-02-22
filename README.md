<br>
<h2 align="center">BookManager</h2>

<p align="center">
  Google Assistant Application to Verbally Manage a Collection of Books
  <br><br><br><br><br>
</p>
  
  
  
  

## About The Project

BookManager is an application designed to be paired with Google Assistant to allow users to make verbal requests for information about a collection of books. It serves as a processor for incoming dialogue requests (intents). Based on the incoming intent, the application will query a database containing a list of authors, a list of books written by those authors, and detailed information about those books. It will then join the query results with some additional dialog in order to preserve conversational flow and build a response that is then sent back to the user.


### Project Based On

[Getting Started with Google Assistant Development](https://www.udemy.com/course/build-a-google-assistant-app-using-java-and-spring-boot/)  
Udemy Course


### Built With

-   [Spring](https://spring.io/)
-   [Maven](https://maven.apache.org/)
-   [MySql](https://www.mysql.com/)
-   [Actions on Google](https://console.actions.google.com/)
-	[DialogFlow](https://cloud.google.com/dialogflow)
-	[ngrok](https://ngrok.com/) 


## Usage

When the application is running, it will initialize by loading a set list of Authors and Books into a database. By default the spring web server runs on localhost port 8080, however, DialogFlow's WebHook requirements specify that the URL must use https, so we must use ngrok to introspect the traffic to localhost. The resulting URL can be used in DialogFlow's Webhook configuration.

An actions command can be used at any time to inform the user on what the application can do. This command would be simply "What can you do" or anything similar. Google assistant will respond with a list of actions that the user can choose from.

Users give a basic command such as "Name authors in the collection" and follow up with commands such as "Name books written by Shakespeare." If a user wishes to follow up further they can say "Tell me more about Hamlet". 

As this is a new project, more Authors and Books will be added as time goes on.

## Contact

Feel free to reach out for anything regarding this project or others!

Billy Cuttrell -  [wcuttrell@gmail.com](mailto:wcuttrell@gmail.com)

Project Link:  [https://github.com/CuttrellW/BookManager](https://github.com/CuttrellW/BookManager)

([back to top](https://github.com/CuttrellW/BookManager/tree/CuttrellW-patch-1#top))
