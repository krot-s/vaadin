vaadin proof of concept
======

Vaadin framework proof of concept.  
The purpose of this POC is to estimate memory/cpu load on the server under heavy load. It contains only a couple of views with grids and forms.  
Another purpose is to get familiar with the framework and see how convenient to use it is.
 
The application uses Google Guava EventBus and Google Guice for messaging and dependency injection.  
Guice integration is based on tutorial - https://vaadin.com/wiki/-/wiki/Main/Integrating%20Vaadin%20with%20Guice%202.0    
It also uses Hibernate Validator for bean validation purposes.  
Real application should also adopt MVP pattern and store UI layout in XML.

The test configuration is located under src/main/resources/jmeter forlder.

To run the application invoke: 
mvn jetty:run
and open url http://localhost:8080/vaadin/

If you don't want to run it, just see the screenshots:  


Test environment setup
------
Application runs on dedicated server under Tomcat7.     
Server hardware: Core i7 720Q 1.60Ghz, 6GB DDR3, Linux x64, Java 1.6.0_29.    
Java options: -Xmx4096m  
Servlet container: Tomcat7
No other specific VM options were set. Tomcat configuration is default.    

JMeter instances run at two dedicated Windows7 32bit stations, Core i5-2300 2.80 GHz.
Each instance runs 20 threads executing tests without delays.
Connection between instances and server is wired ethernet.

Test results
-----
So, there are two jmeter instances, each running 20 threads without deplay.  
Total throughput: ~3000 operations/sec  
Aggregated report - http://clip2net.com/s/2bxsP  
This is the report from one instance, second has same numbers.

Server load:  
Network - 22MBit/s inbound, 158MBit/s outbound  
Java memory usage - between 1Gb and 2Gb  
CPU results (from top utility): 5.7/4.09/3.92  
In average Java consumes 80% of CPU  

See [screenshot of jvisualvm metrics](http://krot.od.ua/visualvm.png)

Conclusion: Vaadin works quite well under heavy load. It do consumes memory, but its amount is quite moderate.  
The sensitive resource is network - Vaadin posts a lot of internal data.  

Memory leaks: no leaks. The test were running for hours, as soon as tests were stopped and all session expired, memory usage fell down to standard 20MB.

JMeter caveats
------
If you run jmeter tests, don't forget to increase OS connection sockets count. For Win7 invoke:
netsh int ipv4 set dynamicport tcp start=10000 num=40000  


