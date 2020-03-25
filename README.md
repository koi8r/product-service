# Asellion Backend Assignment
* [Application requirements](#application-requirements)
* [Using product service](#using-product-service)
* [Product Service REST API](#product-service-rest-api)
* [Security of the API](#security-of-the-api)
* [Test coverage](#test-coverage)
* [Api documentation](#api-documentation)
* [Db migration](#db-migration-was-done-with-help-of-flyway-framework)

## Application requirements
We would like you to create a Java-based backend application using REST. Imagine the following requirements in the context of a real-life work scenario and write what you consider production-ready code. Use the frameworks as you see fit to build and test this. Provide demo data.
It should contain the following endpoints:

  * GET /api/products (get a list of products)
  * GET /api/products/1 (get one product from the list)
  * PUT /api/products/1 (update a single product)
  * POST /api/products (create a product)

The list of products should be stored in an SQL database, which should be pre-populated with product data.

The product object needs to contain the fields:

  * id (pick something suitable)
  * name (String)
  * currentPrice (Amount)
  * lastUpdate (Timestamp)

### Nice to have’s

  * We would also like you to create a front-end which shows
the product list.
  * Secure the API and frontend.
  * With an existing database in place (e.g. after you finished the mandatory part), add a description field on the product object and migrate the database.

### Implementation
Make the code production ready. Provide a means to demonstrate the API. Make sure you use git from the start, and all generated code should be clearly in their own commit, so we can see what you modified or added. Feel free to drag in tools like vagrant or docker or ... to let us easily run a working demo ourselves. Favor running software over being complete!

We expect that the assignment will take you around 2-3 hours.
Let us know how accurate this was.

### Submission and questions
Invite dev@asellion.com to your Gitlab repo and grant it “developer” access. Or keep the assignment in your personal Github and send us the link to it. Let me know if you have any questions and when you’re done.

Lastly, enjoy! :) Looking forward to your completed assignment.

## Requirements

  * Product Service requires Java 11 or later for running locally
  * Lombok plugin installed in IDE fot better experience
  * Mysql running locally or a mysql docker container

## Using Product Service
  * Product Service works in command line builds (plain javac, via Maven) and IDEs.
  * For IntelliJ the [Lombok plug-in](https://plugins.jetbrains.com/plugin/6317-lombok) is available within the IntelliJ marketplace.

```
Go to File > Settings > Plugins
Click on Browse repositories...
Search for Lombok Plugin
Click on Install plugin
Restart IntelliJ IDEA
```
Make sure that you have at least IntelliJ 2018.2.x (needed since support for `annotationProcessors` from the `maven-compiler-plugin` is from that version).
Enable annotation processing in IntelliJ `(Build, Execution, Deployment -> Compiler -> Annotation Processors)`
### To start the project please follow the instructions bellow
  * [Install docker](https://docs.docker.com/docker-for-mac/install)
  * Pull the latest docker [mysql container](https://hub.docker.com/_/mysql):

        $docker pull mysql
  * Run mysql docker container: 
  
         $docker run --name mysql -p 3306:3306 \
         -e MYSQL_ROOT_PASSWORD=mGdU@q2xB59mkV \
         -e MYSQL_USER=user \
         -e MYSQL_PASSWORD=1234 \
         -e MYSQL_DATABASE=asellion \
         -d mysql
         
  * From the project main directory create a docker image:
  
         $mvn clean package docker:build 
         
  * Run docker container:

         $docker run -p 4080:4080 product-service
         
### Product Service REST API:       
#### Get list of Product:

##### Request:

`GET /api/products/`

    curl --location --request GET 'http://localhost:4080/api/products' \
    --header 'Content-Type: application/json' \
    --header 'MediaType: application/json' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='

##### Response:
    
    {
        "productDtoList": [
            {
                "id": 2,
                "name": "Apple Macbook Air (2019) MVFK2",
                "currentPrice": 1005.88,
                "lastUpdate": "2020-03-25 14:11:39"
            },
            {
                "id": 3,
                "name": "BenQ TK800M 4K DLP Beamer",
                "currentPrice": 1110.00,
                "lastUpdate": "2020-03-25 14:11:39"
            },
            {
                "id": 4,
                "name": "LG PH550G HD Mini Beamer",
                "currentPrice": 369.00,
                "lastUpdate": "2020-03-25 14:11:39"
            },
            {
                "id": 5,
                "name": "Canon EOS 2000D",
                "currentPrice": 375.99,
                "lastUpdate": "2020-03-25 14:11:39"
            },
            {
                "id": 6,
                "name": "Apple EarPods 6",
                "currentPrice": 16.44,
                "lastUpdate": "2020-03-25 14:19:27"
            }
        ]
    }

#### Get a Product by product id:

##### Request:

`GET /api/products/{id}`

    curl --location --request GET 'http://localhost:4080/api/products/2' \
    --header 'Content-Type: application/json' \
    --header 'MediaType: application/json' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='

##### Response:
    
    {
        "id": 2,
        "name": "Apple Macbook Air (2019) MVFK2",
        "currentPrice": 1005.88,
        "lastUpdate": "2020-03-25 14:11:39"
    }

#### Update a Product by product id:

##### Request:

`PUT /api/products/{id}`

    curl --location --request PUT 'http://localhost:4080/api/products/2' \
    --header 'Content-Type: application/json' \
    --header 'MediaType: application/json' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
    --data-raw '{
        "name": "Apple EarPods 2001"
    }

##### Successful Response:
    
    {
        "id": 2,
        "name": "Apple EarPods 2001",
        "currentPrice": 1005.88,
        "lastUpdate": "2020-03-25 15:03:23"
    }
    
##### Error Response:
    
    {
        "status": 400,
        "timestamp": "2020-03-25 15:05:02",
        "message": "Changes were not applied and product was not updated/created"
    }    
    
#### Create a Product:

##### Request:

`POST /api/products`

    curl --location --request POST 'http://localhost:4080/api/products' \
    --header 'Content-Type: application/json' \
    --header 'MediaType: application/json' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
    --data-raw '{
    
        "name": "Apple EarPods 2000",
        "currentPrice": 16.44
    }

##### Successful Response:
    
    {
        "id": 8,
        "name": "Apple EarPods 2000",
        "currentPrice": 16.44,
        "lastUpdate": "2020-03-25 15:07:34"
    }
    
##### Error Response:
    
    {
        "status": 400,
        "timestamp": "2020-03-25 15:08:35",
        "message": "Product can't be created. The name already exists"
    }          
#### Delete a Product by product id:

##### Request:

`DELETE /api/products/{id}`

    curl --location --request DELETE 'http://localhost:4080/api/products/8' \
    --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ='

##### Successful Response:
    
    HTTP/1.1 200
    
##### Error Response:
    
    {
        "status": 400,
        "timestamp": "2020-03-25 15:10:23",
        "message": "Product was not found in the database"
    }

## Security of the API:
   For accessing API with Postman application you can use Basic Auth with:
   ```
    user:user
    password:password
   ```

## Test Coverage:

   Test coverage is 96%. The results can be seen in [JoCoCo reports](http://localhost:63342/product-service/target/jacoco-ut/index.html)        

## API documentation:
    
   The [documented API](http://localhost:4080/swagger-ui.html) was done by using swagger framework
  
## DB migration:  
   * DB migration was done with help of flyway framework
   * At the first run the product-service application will be created with original database structure
   * At the second time application runs the migration will be triggered
   * Connection to the mysql db could be done by executing following command

    $docker run -it --rm mysql mysql -h172.17.0.2 -uuser -p1234
    
Results of the migration:
````
+----+---------------------------+---------------+---------------------+-------------+
| id | name                      | current_price | last_update         | description |
+----+---------------------------+---------------+---------------------+-------------+
|  1 | Apple EarPods             |         16.44 | 2020-03-25 15:54:22 | description |
|  2 | Apple EarPods 2001        |       1005.88 | 2020-03-25 16:12:29 | description |
|  3 | BenQ TK800M 4K DLP Beamer |       1110.00 | 2020-03-25 15:54:22 | description |
|  4 | LG PH550G HD Mini Beamer  |        369.00 | 2020-03-25 15:54:22 | description |
|  5 | Canon EOS 2000D           |        375.99 | 2020-03-25 15:54:22 | description |
+----+---------------------------+---------------+---------------------+-------------+
````
````
+----------------+---------+-------------+------+-----------------------+------------+--------------+---------------------+----------------+---------+
| installed_rank | version | description | type | script                | checksum   | installed_by | installed_on        | execution_time | success |
+----------------+---------+-------------+------+-----------------------+------------+--------------+---------------------+----------------+---------+
|              1 | 0.0.1   | intialize   | SQL  | V0_0_1__intialize.sql | -100622699 | user         | 2020-03-25 15:54:22 |             98 |       1 |
|              2 | 0.1.0   | migration   | SQL  | V0_1_0__migration.sql | 1782124245 | user         | 2020-03-25 15:54:22 |             44 |       1 |
+----------------+---------+-------------+------+-----------------------+------------+--------------+---------------------+----------------+---------+
````