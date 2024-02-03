# e-commerce platform

![Project microservice architecture"](https://github.com/anderson92zolis/ecommerceChallenge/blob/develop/ArchitectureMicro2.png)

**Challenge: Microservices Architecture Design Challenge**

    Background:
    You are tasked with designing a microservices architecture for an e-commerce platform. The platform should handle product listings, user accounts, order processing, and payment processing. Each of these functionalities should be implemented as separate microservices.
    
    Challenge Description:
    Design the microservices architecture for the e-commerce platform based on the following requirements and constraints:

**Products Service (Stock) (Microservice 1):**

Manages product listings, including details like name, description, price, and availability.
Allows for product creation, retrieval, update, and deletion.
Supports searching for products by name or category.

**User Service (Microservice 2):**

Manages user accounts, including user registration and authentication.
Stores user information such as username, email, and hashed passwords.
Handles user authentication and authorization.

**Order Service (Microservice 3):**

Manages customer orders, including order creation, status tracking, and order history.
Links orders to users, so each user can have multiple orders.
Supports order placement, cancellation, and retrieval.

**Inventory Service (Microservice 4):**

A microservice for inventory management, often referred to as an "Inventory Service," is a component of a microservices architecture that is responsible for handling all aspects related to tracking, managing, and controlling an organization's inventory. Here's a high-level definition of a typical Inventory Microservice:

**Payment Service (Microservice 5):**

Handles payment processing for orders.
Integrates with external payment gateways for payment authorization and capture.
Ensures payment security and handles payment failures gracefully.

**Constraints**:

-  Use a RESTful API design for communication between microservices.
-  Each microservice should be independently deployable and scalable.
-  Ensure data consistency and consider eventual consistency where needed.
-  Implement proper error handling and validation in each microservice.
-  You'll choose Java & Spring Boot for implementing microservices.

**Deliverables**:

-  Provide a high-level architectural diagram that illustrates how these microservices interact with each other. Additionally, describe the communication patterns between microservices and how they handle data storage and retrieval.

**Evolving ideas**:

-  Parent Project 
-  SonarCloud

**Bonus Challenge:**

-  Bonus Challenge 1: Container Orchestration (Advanced)
Consider containerizing your microservices using Docker and orchestrate them using Kubernetes or Docker Compose. Design your architecture to be easily deployable and scalable within a containerized environment.

-  Bonus Challenge 2: API Gateway (Intermediate)
Implement an API Gateway as a separate microservice responsible for routing and load balancing requests to the appropriate microservices. Consider using tools like Netflix Zuul or Spring Cloud Gateway.

-  Bonus Challenge 3: Event-Driven Architecture (Advanced)
Introduce an event-driven architecture using a message broker like Apache Kafka or RabbitMQ. Microservices can publish events when certain actions occur (e.g., new orders) and subscribe to events they're interested in. This allows for asynchronous communication and decoupling of services.

-  Bonus Challenge 4: Centralized Logging and Monitoring (Intermediate)
Set up centralized logging and monitoring for your microservices. Use tools like ELK Stack (Elasticsearch, Logstash, Kibana) or Prometheus and Grafana to gain insights into the health and performance of your services.

-  Bonus Challenge 5: Security (Advanced)
Implement robust security measures in your microservices architecture. This includes securing API endpoints with OAuth2 or JWT, ensuring data encryption, and setting up role-based access control (RBAC).

-  Bonus Challenge 6: Service Resilience (Intermediate)
Design your microservices to be resilient to failures. Implement circuit breakers (e.g., Hystrix), retries, and timeouts to prevent cascading failures in case one microservice becomes unresponsive.

-  Bonus Challenge 7: Continuous Integration and Deployment (CI/CD) Pipeline (Advanced)
Create a CI/CD pipeline to automate the testing, building, and deployment of your microservices. Use tools like Jenkins, Travis CI, or GitLab CI to achieve this.

-  Bonus Challenge 8: Micro Frontends (Intermediate)
Explore the concept of micro frontends to complement your microservices architecture. Implement separate front-end modules that can be independently developed and deployed.

-  Bonus Challenge 9: Load Testing and Performance Tuning (Advanced)
Conduct load testing to assess the performance of your microservices under high loads. Identify bottlenecks and optimize your services accordingly.

-  Bonus Challenge 10: Cross-Service Testing (Intermediate)
Set up automated integration tests that span multiple microservices to ensure they work together seamlessly.

These bonus challenges will further elevate your microservices architecture design skills and provide valuable insights into real-world scenarios and best practices. Choose the ones that align with your current level of expertise and desired learning objectives. Happy designing!

# Ecommerce Challenge Project

This project showcases the use of ecommerce shop, Postman, swagger to document and visualize APIs in a Spring Boot project.

## Prerequisites

Make sure you have the following installed:

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)

## Project Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/anderson92zolis/ecommerceChallenge.git
    ```

2. Navigate to the project directory:

    ```bash
    cd ecommerceChallenge
    ```

3. Build the project:

    ```bash
    mvn clean install
    ```

## Running the Application

1. Run the Spring Boot application:
   ```bash
    cd docker
    ```   
    ```bash
    docker compose up -d 
    ```
   * Create the database for ecommerce in PostgreSQL http://localhost:5050/browser/
   * Create the tables for **products** **stocks** 

2. Access the Postman to check the endpoints:

3. Access the Swagger documentation:

   Open your browser and go to [http://localhost:8080/webjars/swagger-ui/4.15.5/index.html](http://localhost:8080/webjars/swagger-ui/4.15.5/index.html).

   Here, you will find the Swagger interface to explore and test your APIs.

## Docker
   
   you can start and stop the container in the docker program directly (**some microservices are interconnect**)

## Project Modules

- `customersMicroservices`
- `ordersMicroservices`
- `productsMicroservice`
- `stockMicroservice`
- `notification-server`   - Kafka
- `ApiGateway` - Manager of Microservices.
- `discovery-server` - Eureka Service

## Resource

- [Configuring Swagger in a Spring Cloud Gateway Project](https://medium.com/@pubuduc.14/swagger-openapi-specification-3-integration-with-spring-cloud-gateway-part-2-1d670d4ab69a)

## How to Contribute

If you wish to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your contribution: `git checkout -b feature/new-feature`.
3. Make your changes and commit: `git commit -m "Add new feature"`.
4. Push your changes to the remote repository: `git push origin feature/new-feature`.
5. Open a pull request on GitHub.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


