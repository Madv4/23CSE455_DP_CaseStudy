# Pattern Architecture Diagram & System Topology
**System:** Hospital Management System using Spring Cloud Microservices

## Distributed Architecture & Design Pattern Mapping

```mermaid
graph TD
    Client[Client Apps / Postman] -->|HTTP Requests| Gateway[API Gateway - Port 9191
<<Pattern: API Gateway & Filter Chain>>]
    
    subgraph Security & Filter Pipeline
        Gateway --> AuthFilter[JwtAuthenticationFilter
<<Pattern: Chain of Responsibility>>]
    end
    
    subgraph Service Discovery
        Eureka[Eureka Server - Port 8761
<<Pattern: Service Discovery>>] <--->|Register & Lookup| Gateway
        Eureka <---> Auth[Auth Service - Port 8081]
        Eureka <---> Patient[Patient Service - Port 8082]
        Eureka <---> Appointment[Appointment Service - Port 8083]
    end
    
    AuthFilter -->|/auth/**| Auth
    AuthFilter -->|/api/patients/**| Patient
    AuthFilter -->|/api/appointments/**| Appointment
    
    subgraph Inter-Service Communication
        Appointment -->|Validate Patient ID
<<Pattern: Strategy - RestTemplate vs WebClient>>| Patient
    end
    
    subgraph Data Access Layer
        Auth -->|Spring Data JPA| DB1[(PostgreSQL: users table
<<Pattern: Repository>>)]
        Patient -->|Spring Data JPA| DB2[(PostgreSQL: patients table
<<Pattern: Repository>>)]
        Appointment -->|Spring Data JPA| DB3[(PostgreSQL: appointments table
<<Pattern: Repository>>)]
    end
```

## Discovered Pattern Highlights
1. **API Gateway & Filter Chain:** `hospital-gateway` serves as single entry point with `JwtAuthenticationFilter`.
2. **Service Discovery:** `EurekaServer` provides dynamic registration and client load balancing.
3. **Repository Pattern:** `PatientRepo`, `AppointmentRepo`, and `UserRepo` abstract database persistence via Spring Data JPA.
4. **Strategy Pattern (Inter-Service Validation):** Interchangeable `RestTemplateConfig` and `WebClientConfig` client execution strategies.
