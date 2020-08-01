## Dive 2
	- Watch SQL & JDBC videos
	- Set up Your DataBase
	- Code Data Access Layer

## Dive 3

### JDBC

Some Definitions
JAR: Java Archive. Is like a zip, used for aggregating many files into one.


Java DataBase Connectivity API is used to connet to and exchange data with databases. JDBC works
with all databases. You can: 
	- Use the static forName() method from the Class class to load a driver.
	- w/ a username & password create a Connection to the database using the Connection class.
	

### Types of JDBC statements
- Statement
- Prepared Statement: These can help protect your application against a malicious attack called
SQL injection. 
- Callable Statement

Statements have not innate protection against SQL injection

### DAO
DAO is Data Access Object, a common design patttern, and it's useful in applications that access
a database. 
- It decouples the business logic from the code that accesses the database, makes it able to adapt. 
You will need 3 types of files:
- an interface that defines teh data access methods
- a concrete subclass that implements those methods based on chosed DBMS
- a model class(es) to represent entities.
- (optional) utitlity class to handle repetetive operations such as obtaining a connection.

package
imports

public class Product {
	long productId;
	String productName;
	String productDescription;
}

### POJO
Plain Old Java Objects
