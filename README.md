Idempotent Transaction Processing Service
------------------------------------------
Overview:

This project implements an Idempotent Background Transaction Processing Service using Java 21 and Spring Boot.

The service processes financial transactions while ensuring:

* Idempotent processing
* Duplicate transaction detection
* Sequence validation
* Retry policy support
* Processing summary generation
* Robust validation and exception handling

The implementation focuses on clean architecture, maintainability, and production-oriented design while satisfying the functional requirements of the assessment.

⸻

Technology Stack:

* Java 21
* Spring Boot 3.x
* Maven
* Spring Web
* Spring Validation
* Lombok
* SpringDoc OpenAPI (Swagger)
* JUnit 5
* Mockito

⸻

Project Structure:

src
├── controller
├── dto
│   ├── request
│   └── response
├── enums
├── exception
├── model
├── repository
├── service
│   ├── impl
│   ├── ordering
│   ├── retry
│   └── summary
├── validation
└── constants

The project follows a layered architecture separating API, business logic, persistence, validation, and infrastructure concerns.

⸻

Features:

* Idempotent transaction processing
* Duplicate transaction detection
* Sequence/order validation
* Retry policy
* Processing summary
* Bean validation
* Global exception handling
* OpenAPI/Swagger documentation
* Unit tests

⸻

Transaction Processing Flow:

Client Request
│
▼
Validation
│
▼
Duplicate Detection
│
▼
Sequence Validation
│
▼
Transaction Processing
│
▼
Repository
│
▼
Response

⸻

Transaction Lifecycle:

RECEIVED
│
▼
VALIDATED
│
▼
PROCESSING
│
┌───┴────────┐
▼            ▼
PROCESSED   FAILED

Duplicate requests return the previously processed result without reprocessing.

⸻

Idempotency Strategy

The service uses the Transaction ID as the idempotency key.

Before processing, the repository checks whether the transaction already exists.

If a duplicate request is received:

* Business processing is skipped.
* The previously processed transaction is returned.
* Duplicate processing is prevented.

Current implementation uses an in-memory ConcurrentHashMap.

In production this could be replaced with:

* Redis
* PostgreSQL
* DynamoDB
* Cassandra

without changing business logic.

⸻

Ordering Strategy:

Transactions are validated using account-specific sequence numbers.

The current implementation accepts only the next expected sequence for an account.

Out-of-order transactions are rejected to preserve processing consistency.

Future enhancement:

* Pending transaction queue
* Event-driven sequencing using Kafka partitions

⸻

Retry Strategy:

Retry decisions are encapsulated within RetryService.

This keeps retry policy independent from transaction processing logic.

The maximum retry count is configurable through business constants.

⸻

Processing Summary:

The application provides aggregated processing statistics including:

* Total received
* Successfully processed
* Duplicates
* Failed
* Pending
* Retried

⸻

REST Endpoints:

Process Transaction

POST /api/v1/transactions/process

Processing Summary

GET /api/v1/transactions/summary

Swagger

http://localhost:8080/swagger-ui/index.html

⸻

Running the Application:

Compile:

mvn clean compile

Run:

mvn spring-boot:run

Execute unit tests:

mvn clean test

Package:

mvn clean package

⸻

Assumptions:

* Single application instance
* In-memory storage
* No database persistence
* No asynchronous processing
* No distributed coordination
* Transaction IDs are globally unique

⸻

Production Improvements:

The following enhancements would be recommended for a production deployment:

* Redis-backed idempotency store
* Persistent transaction repository
* Kafka-based ordered event processing
* Dead Letter Queue (DLQ)
* Distributed tracing
* Micrometer metrics
* Spring Boot Actuator
* Circuit Breaker (Resilience4j)
* Horizontal scaling
* Authentication and authorization
* Rate limiting

⸻

Testing:

The project includes unit tests covering:

* Retry policy
* Ordering validation
* Repository operations
* Processing summary generation

The application successfully passes:

mvn clean test

⸻

Design Principles:

The implementation follows:

* SOLID principles
* Separation of concerns
* Constructor dependency injection
* Single responsibility
* Clean architecture
* Production-oriented design

⸻

Conclusion:

This implementation demonstrates a clean, maintainable, and extensible transaction processing service that satisfies the assessment requirements while providing a foundation for production-scale enhancements.

Author:
Rakesh Baitha