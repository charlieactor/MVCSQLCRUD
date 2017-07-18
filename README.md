## Colorado's Top Hikes CRUD Project

Charlie Actor created this WebApp that allows the user to see Colorado's top hikes near Denver. The user can also update or delete hikes currently in the system, or add a new hike of their own. 

Upon looking at individual hikes, the user is presented with all of the hike's information, as well as a google maps interface that displays the hike's location. Assuming the user enters a real hike, the google maps interface functions for user entered hikes as well. 

This was coded using the Spring framework and JSPs to minimize repetitive coding. I've added session data so every user has their own list of hikes! Given enough time, I'd like to add JUnit tests to ensure session data is working properly, as well as that the printwriter is overwriting the Server's copy of the CSV. 

The biggest stumbling blocks I encountered were with learning the Spring framework - something I now feel I have a better handle of. There are a few little things that need to be included in the .pom file as well as the servlet.xml file that I was missing initially. Fortunately, once I included them, all worked as intended!  