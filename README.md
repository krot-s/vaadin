vaadin proof of concept
======

Vaadin framework proof of concept.
The purpose of this POC is to estimate memory/cpu load on the server under heavy load.
The application uses Google Guava EventBus and Google Guice for messaging and dependency injection.
Guice integration is based on tutorial - https://vaadin.com/wiki/-/wiki/Main/Integrating%20Vaadin%20with%20Guice%202.0
Real application should also adopt MVP pattern and store UI layout in XML.  