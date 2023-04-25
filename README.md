<h2> Professional Management API </h2>

<p>
This API is the first part of a larger project. This project was designed to put into practice the acquired knowledge, being a project focused on learning. It consists of three parts, the first is an API that consults professionals in a database, but in this case we don’t use a database, but a JSON file, with information from some fictitious employees. The second part is an API that consumes this API, and the third part is a microservice that listens to the second API, the exact functioning of the remaining two parts will be better explained in their repositories.
</p>
### Test

To test this API, you can download the project's .jar file.
### Methods

You can see the project's documentation and try it out with Swagger:
http://localhost:9090/swagger-ui/index.html

This API contains three Get methods:
   Finds a Professional:
   http://localhost:9090/api/professionals/v1/search/:id
      
   Ex.:
   
        {
              "id": 1,
              "first_name": "José",
              "last_name": "Pereira de Silva",
              "registration_code": "H0976rF0",
              "registration_date": "2022-12-23",
              "_links": {
                  "self": {
                      "href": "http://localhost:9090/api/professionals/v1/search/1",
                      "type": "GET",
                      "name": "Find By ID"
                  },
                  "Find a Professionals": {
                      "href": "http://localhost:9090/api/professionals/v1/search-registrationCode/H0976rF0",
                      "type": "GET",
                      "name": "Find By Registration Code"
                  },
                  "All Professionals": {
                      "href": "http://localhost:9090/api/professionals/v1/search",
                      "type": "GET",
                      "name": "Find All"
                  }
              }
          }
    
   Finds a Professional by his registration code:
   http://localhost:9090/api/professionals/v1/search-registrationCode/:registrationCode
   
   Ex.:
   
         {
              "id": 10,
              "first_name": "Israel",
              "last_name": "Pato Bezerra",
              "registration_code": "H0976rF9",
              "registration_date": "2022-12-23",
              "_links": {
                  "self": {
                      "href": "http://localhost:9090/api/professionals/v1/search-registrationCode/H0976rF9",
                      "type": "GET",
                      "name": "Find By Registration Code"
                  },
                  "Find a Professionals": {
                      "href": "http://localhost:9090/api/professionals/v1/search/10",
                      "type": "GET",
                      "name": "Find By ID"
                  },
                  "All Professionals": {
                      "href": "http://localhost:9090/api/professionals/v1/search",
                      "type": "GET",
                      "name": "Find All"
                  }
              }
          }
          
   Finds all Professionals:
   http://localhost:9090/api/professionals/v1/search
   
   Ex.:
    
        [
         {
            "id": 1,
            "first_name": "José",
            "last_name": "Pereira de Silva",
            "registration_code": "H0976rF0",
            "registration_date": "2022-12-23",
            "links": [
                {
                    "rel": "Find a Professionals by Id",
                    "href": "http://localhost:9090/api/professionals/v1/search/1",
                    "type": "GET",
                    "name": "Find By ID"
                },
                {
                    "rel": "Find a Professionals by Registration Code",
                    "href": "http://localhost:9090/api/professionals/v1/search-registrationCode/H0976rF0",
                    "type": "GET",
                    "name": "Find By Registration Code"
                }
            ]
         },
         {
            "id": 2,
            "first_name": "Afonso",
            "last_name": "Albuquerque",
            "registration_code": "H0976rF1",
            "registration_date": "2022-12-23",
            "links": [
                {
                    "rel": "Find a Professionals by Id",
                    "href": "http://localhost:9090/api/professionals/v1/search/2",
                    "type": "GET",
                    "name": "Find By ID"
                },
                {
                    "rel": "Find a Professionals by Registration Code",
                    "href": "http://localhost:9090/api/professionals/v1/search-registrationCode/H0976rF1",
                    "type": "GET",
                    "name": "Find By Registration Code"
                }
            ]
         }
        ]
