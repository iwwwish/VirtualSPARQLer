VirtualSPARQLer
===============

A Java desktop utility for querying a relational database (for e.g. SQL) via a virtual RDF graph based SPARQL endpoint.

Please find below the screenshot of the application. The Resources section contains all the table names contained within the relational database. The leaf nodes (with icons) under each table name represent the rows in the same table. Properties section contains all the prefix forms of the predicates used in the database to connect different entities. They are quivalent to URIs. The querying section is an editor area where a user write a query of interest looking at the structure of the database. The button "Add Prefixes" displays all the prefixes for the namespaces used in the schema of the virtual RDF graph generated from the database using the mapping file.

![alt tag](http://i61.tinypic.com/2pzxjbc.jpg)
