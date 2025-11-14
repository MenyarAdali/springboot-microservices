# 🧩 Spring Boot Microservices Project

This repository contains a complete **Microservices Architecture** built with **Spring Boot** and **Spring Cloud**.  
The project demonstrates how independent services communicate securely through a centralized **API Gateway**, with configuration and discovery handled by **Spring Cloud Config** and **Eureka**.

---

## 🚀 Project Overview

The project is composed of several independent microservices, each responsible for a specific business domain.

### 🏗️ Architecture Components

| Service Name | Description |
|---------------|-------------|
| **authentification-service** | Manages user authentication and authorization using Spring Security and JWT tokens. |
| **client-service** | Handles client data and CRUD operations. |
| **produit-service** | Manages products and related business logic. |
| **facture-service** | Handles invoices and billing operations. |
| **reglement-service** | Manages payments and settlements associated with invoices. |
| **devise-service** | Provides currency exchange and conversion features. |
| **config-service** | Central configuration service for all microservices using Spring Cloud Config. |
| **eureka-discoveryservice** | Service registry that enables service discovery and load balancing. |
| **gateway-service** | API Gateway that routes requests to appropriate services and applies authentication filters. |

---

## ⚙️ Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Cloud Netflix (Eureka, Config, Gateway)**
- **Spring Security + JWT**
- **Maven**
- **MySQL / PostgreSQL**
- **Docker (optional)**
- **Postman / Swagger** for testing APIs

---

## 🧠 System Architecture
                         +----------------------+
                         |   Config Service     |
                         +----------+-----------+
                                    |
                 +------------------+------------------+
                 |                                     |
      +----------v-----------+             +-----------v-----------+
      |  Eureka Discovery    |             |    Gateway Service    |
      +----------+-----------+             +-----------+-----------+
                 |                                     |
   +-------------+---------------------------+---------+--------------------+
   |             |                           |                              |
   +------v------+ +-----v-------+ +--------v-------+ +---------v-------+
   | Auth Service| | Client Svc | | Facture Svc | | Reglement Svc |
   +-------------+ +-------------+ +----------------+ +----------------+

communicate via REST APIs through Gateway---


---

## 🧰 Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/MenyarAdali/springboot-microservices.git
cd springboot-microservices

2️⃣ Start Core Services

Run these services first:

config-service

eureka-discoveryservice

3️⃣ Start the Gateway and Business Services

Then run:

gateway-service

authentification-service

client-service

produit-service

facture-service

reglement-service

devise-service

Each service uses its own port defined in application.yml.

🔒 Security

The authentification-service issues JWT tokens.
To access protected endpoints, first authenticate through the login endpoint and use the token in your headers:Authorization: Bearer <your_token>

🧪 Testing

You can use Postman or Swagger UI to test endpoints.
Make sure all services are running and registered in Eureka.

🐳 Optional: Docker Support

To containerize each service: mvn clean package -DskipTests
docker build -t <service-name> .

💡 Running via IDE

If using Eclipse or IntelliJ IDEA:

Import each microservice as a Maven Project.

Run config-service and eureka-discoveryservice first.

Then start other services individually.

Check http://localhost:8761 to ensure all services are registered.


🤝 Contributing

Feel free to fork this repository, open issues, or submit pull requests to improve the architecture or add new services.

👨‍💻 Author

Menyar Adali
📧 [minyaradali@gmail.com]

