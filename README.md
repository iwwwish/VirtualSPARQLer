VirtualSPARQLer
===============

A Java utility for querying a relational database (e.g. MySQL db) via a SPARQL endpoint exposed by the virtual RDF graph created for the relational database. The virtual RDF graph is loaded into the GUI with the help of a mapping file which serves as the schema for the graph. A screenshot of the interface with different parts labelled:

![alt tag](http://i61.tinypic.com/2pzxjbc.jpg)


###### System Requirements:

1. Linux or Mac OS
2. Java 7 or later

###### Usage: (for screenshots, go to the bottom of this page)

Virtual SPARQLer relies upon the D2RQ tool to emulate the RDF-based querying environment. Therefore, the application is packed along with the D2RQ tool. To start using, download the archive and follow the instructions below.

1. extract the archive and keep the contents of the archive in the same directory,

    `tar -xvzf VirtualSPARQLer.tar.gz`

2. start the program by executing the JAR file in "target" directory, you will see a plain interface unlike the above,

    `chmod +x Virtual-Sparqler-1.0-jar-with-dependencies.jar`
    
    `java -jar Virtual-Sparqler-1.0-jar-with-dependencies.jar`

3. load the HBP database by selecting option "Open Mapping" from the user interface and selecting an existing mapping file (mapping.ttl) for HBP database located in folder "d2rq-0.8.1" (see screenshot 1),

4. once the mapping file is loaded, the left panel of the interface is loaded with resources on top and properties at bottom (see screenshot 2),

5. start querying the HBP database by simple writing simple SPARQL queries,

    `e.g. SELECT * { <hbp_drugs/donepezil> ?p ?o}`

6. if there is an updated version of the database, choose "New Connection" option instead,

7. provide the credentials of the database in the window popped up and test the connection (see screenshot 3),

8. once successful, a new mapping file is created in the user's home directory,

9. load the new mapping file using "Open Mapping" option and start querying the new RDF graph,

10. the query results are displayed in the "Results" section of the interface, to save the results, click on Save and choose one of the five different output formats (TXT, XML, JSON, CSV, TSV).

###### Advantages:

Virtual SPARQLer features the following advantages over the traditional RDF based SPARQL endpoints.

1. Avoids the duplication of data in the database by creating new RDF dataset.

2. Computational cost is really low.

3. Output can be saved in various formats facilitating easy visualiztion.

###### Screenshots:

screenshot 1:

![alt tag](http://i60.tinypic.com/33kre40.jpg)

screenshot 2:

![alt tag](http://i62.tinypic.com/33u90cx.jpg)

screenshot 3:

![alt tag](http://i60.tinypic.com/3097oe8.jpg)

###### Contact:

For questions or collaborations, contact me (srmshhtty@gmail.com).
