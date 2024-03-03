# Mobile-Service
## It is a Java Enterprise Application maven based project using various concepts of OOPs and Java API(Regex API, DateTime API, Collection Framework). 
### MobileService is an online application which a customer can use to send a service request for his mobile phone if there are any issues and get it fixed. In this project, two operations are performed
### 1.) Submit a request to the service center
### 2.) See all the previous requests based on their status

#### The different steps taking place in the application are explained below:- 
##### 1.) User registers the request in Tester Class (Presentation Tier). In this project, the client tier is not used. the inputs will be taken directly in the Tester Class.
##### 2.) The Tester class sends the Model class object to the Service Class (Business Tier).
##### 3.) The Service class sends the object to the Validator class to get the inputs validated. 
##### 4.) If the inputs are in valid format, the estimated cost for the service will be calculated and data is send to the DAO class (Persistence Tier) to register the request. In this project, the database is not being used simply a service request object will be returned from the database.
##### 4.) The Service class also performs operations like estimating cost for the service and getting services report from DAO class (Persistence Tier). In this project, the database is not being used a hard coded data will be returned.
##### 5.) Based on the responses from the Validator and the DAO classes, the Service class formulates  either a successful output or a failure output and return the same to the Tester class.
##### 6.) The Tester class then displays the output to the User.
