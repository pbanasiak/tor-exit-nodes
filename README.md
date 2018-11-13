# Tor Exit Nodes
Design and implement service allowing to check whether provided IPv4 address is a Tor exit node or not. Hint: the list of Tor exit nodes can be found at https://check.torproject.org/exit-addresses. Create a pull request to the `master` branch.

## Functional requirements
- the web API should respond with `HTTP 200 OK` and empty body on `HEAD /A.B.C.D` request when the provided IP is a Tor exit node
- the web API should respond with `HTTP 404 Not Found` and empty body on `HEAD /A.B.C.D` request when the provided IP is not a Tor exit node
- (optional) the web API should respond with `HTTP 200 OK` and JSON body (design the response) on `GET /A.B.C.D` request when the provided IP is a Tor exit node
- (optional) the web API should respond with `HTTP 404 Not Found` and empty body on `GET /A.B.C.D` request when the provided IP is not a Tor exit node

## Non-functional requirements
- set of Tor exit nodes should be cached in memory
- set of Tor exit nodes should be refreshed every 30 minutes
- the web API should respond with `HTTP 200 OK` and empty body on `HEAD /status` request
- the web API should respond with `HTTP 200 OK` and `{"tor_exit_nodes_count":<<TOR_EXIT_NODES_COUNT>>}` on `GET /status` request
- use Java 8/11 and Spring Boot
- provide documentation on:
  - installation steps
  - how to deploy and run the service
  - how to run automatic tests
  - how to use API endpoints

## Evaulation criteria
- code quality and readability
- presence and quality of (or lack of) automatic tests
- commits history (thought process, commit messages)

## Bonus points
- deploy the application on a free cloud service (i.e. Heroku)

## Installation Steps ##
To build packaged application as jar:
```
mvnw clean install 
```
## How to run the service ##
### Running packaged application
To run application that was built in previous step:
```
java -jar target/tor-exit-nodes-0.0.0.jar
```
### Using maven plugin
Alternatively if you don't want to build application jar:
```
mvnw spring-boot:run
```
## How to run tests ##
To run automatic tests:
```
mvnw test
```
## How to use API ##
Application default port is **3080**.
Api model and operations are well described using swagger.
I recommend using provider swagger-ui
```
http://localhost:3080/swagger-ui.html
```
Alternatively you can check out swagger api docs
```
http://localhost:3080/v2/api-docs
```


## Heroku ##
To deploy:
```
heroku deploy:jar target\tor-exit-nodes-0.0.0.jar -o --server.port=3080 --app
```

To use application on heroku:
```
http://tor-exit-nodes.herokuapp.com/swagger-ui.html
```
