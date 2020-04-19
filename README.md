# Foreign Exchange Application
This is a foreign exchange demo application. You can get rates between currencies. 
Queries are saved to embedded H2 database so that you can search for previous queries with the date or transaction id.
A service provider is used to access real data. You may use another provider by implementing FxProvider.java interface.

### Tech Stack 
This is a Maven Spring Boot Web Application with an embedded H2 database. Spring Data Jpa is used to access the database. 
The database runs in RAM and gets cleared when the application terminates.
Sample data is initialized in resources\data.sql file. Unit tests written for repository source codes depend on this data to run successfully.
You can connect to local H2 database with a browser on http://localhost:8080/h2-console while the application is running.
JDBC URL should be "jdbc:h2:mem:testdb" to connect.
### How to Install and Run
You need to install maven in your local computer. Then you may run
<pre>mvn clean install      to download dependencies and build project
mvn spring-boot:run    to run the application</pre>

### How to Docker Run
You need to install Docker in your local computer. Then you may run
<pre>mvn clean install                                       to download dependencies and build jar file
docker build -t spring-boot-exchange-app.jar .          to build docker image
docker run -p 8080:8080 spring-boot-exchange-app.jar    to run the image</pre>

### How to Use the Api
Get exchange rates between currencies:
<pre>GET http://localhost:8080/rate?sourceCurrency=usd&targetCurrency=gbp      
Response:
{
    "sourceCurrency": "usd",
    "targetCurrency": "gbp",
    "rate": 0.8009023941
}
</pre>
Convert source currency amount to the target currency:
<pre>POST http://localhost:8080/conversion      
{
	"sourceCurrency": "USD",
	"targetCurrency": "TRY",
	"sourceAmount": 100
}
Response:
{
    "transactionId": 1,
    "transactionDate": "2020-04-18",
    "sourceCurrency": "USD",
    "targetCurrency": "TRY",
    "rate": 6.9043278085,
    "sourceAmount": 100.0,
    "targetAmount": 690.43278085
}
</pre>
Get previous conversions by date
<pre>GET http://localhost:8080/conversion?transactionDate=2020-04-19      
Response:
[
    {
        "transactionId": 1,
        "transactionDate": "2020-04-19",
        "sourceCurrency": "USD",
        "targetCurrency": "TRY",
        "rate": 6.9043278085,
        "sourceAmount": 100.0,
        "targetAmount": 690.43278085
    },
    {
        "transactionId": 2,
        "transactionDate": "2020-04-19",
        "sourceCurrency": "USD",
        "targetCurrency": "GBP",
        "rate": 0.8009023941,
        "sourceAmount": 100.0,
        "targetAmount": 80.09023941
    }
]
</pre>
Get previous conversion by transaction id
<pre>GET http://localhost:8080/conversion?transactionId=1001      </pre>
Maximum 10 items are returned for each query. You can use page numbers to get more results
<pre>GET http://localhost:8080/conversion?transactionDate=2020-04-18&page=2     </pre>

### Error Handling

There are error codes and messages for specific types of errors encountered. Some examples are below:

<pre>GET http://localhost:8080/rate?sourceCurrency=usd&targetCurrency=gb     
Response:
{
    "errorCode": "0002",
    "message": "Currency code length must be 3: targetCurrency"
}
 </pre>

<pre>GET http://localhost:8080/conversion?transactionDate=2020-04    
Response:
{
    "errorCode": "0003",
    "message": "Date format must be yyyy-mm-dd but found: 2020-04"
}
 </pre>



