# onlineRegistro
## Synopsis
* Management for a user (CRUD methods), all used json format


## validations 
*Email format
*Password format (at least 1 digit, 1 uppercase, 1 lowercase, no space)
	


## Code Specs
* Tomcat 8
* Java 8
* Intellij idea
* Gradle
* H2 
	*BD url :http://localhost:8080/h2-console/login.jsp
	*Db script is not needed, entities will create the scheme.
* Springboot 2
* Hibernate
* UUID for ID entities



## Deployment
Use gradle and the comand : gradle bootRun


## Use api rest
	CRUD User :
		
		GET: 
			http://localhost:8080/v1/api/client
		
		POST:
			http://localhost:8080/v1/api/client
			{
				"name": "Juan Rodriguez",
				"email": "juan@rodriguez.org",
				"password": "hunter2",
				"phones": [
					{
					"number": "1234567",
					"citycode": "1",
					"contrycode": "57"
					}
				]
			}
			
		PUT: se requiere ids para actualizar
		
			http://localhost:8080/v1/api/client
			
			{
				"id": "f8a1bae4-1567-4523-827c-d5293166f849",
				"name": "Juan Perez",
				"email": "juan2@rodriguez.org",
				"password": "hunter2A",
				"phones": [
					{
					"id": "bafa97f1-7b2b-4dbf-9148-5e5ee43b0581",
					"number": "1234567",
					"citycode": "1",
					"contrycode": "57"
					}
				]
			}
			
		DELETE:
			http://localhost:8080/v1/api/client
			
			{
				"id": "f106fc3a-c248-45d9-abd1-57e27435c770"
			}

	
	Obtiene csrf token
		GET:
			http://localhost:8080/v1/api/client/csrf
			
