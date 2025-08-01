```markdown
# Spring Boot + Cassandra + Docker Setup

This project demonstrates a Spring Boot 2.7.13 application using Java 8 and Apache Cassandra as the database, running via Docker. The app provides a simple REST API to manage users.

---

## 🚀 Tech Stack

- Java 8
- Spring Boot 2.7.13
- Spring Data Cassandra
- Apache Cassandra 4.1 (Docker)
- Maven
- Docker & Docker Compose

---

## 📁 Project Structure

├── docker-compose.yml
├── Dockerfile
├── pom.xml
├── src
│   ├── main
│   │   ├── java/com/examplecasandra/demo/
│   │   │   ├── DemoApplication.java
│   │   │   ├── controller/UserController.java
│   │   │   ├── entity/User.java
│   │   │   ├── repository/UserRepository.java
│   │   │   └── config/CassandraKeyspaceInitializer.java
│   │   └── resources/
│   │       ├── application.yml
│   │     └── schema.cql

````

---

## 🛠️ Prerequisites

- [Java 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Docker Desktop](https://www.docker.com/products/docker-desktop)
- Optional: Postman, curl

---

## 🔧 Setup Instructions

### 1. Start Cassandra in Docker

```bash
docker-compose up -d
````

Wait for Cassandra to fully start (takes \~30 seconds).

---

### 2. Load Schema (Keyspace + Table)

```bash
docker exec -it cassandra-db cqlsh -e "SOURCE '/docker-entrypoint-initdb.d/schema.cql';"
```

This creates:

* `testkeyspace`
* `users` table

---

### 3. Build the Spring Boot App

```bash
mvn clean package
```

---

### 4. Run the App

**Option A: From Terminal (local machine)**

```bash
mvn spring-boot:run
```

**Option B: With Docker (requires Dockerfile)**

```bash
docker build -t my-spring-app .
docker run -p 8081:8081 --network=host my-spring-app
```

---

## 🧪 How to Use the API

### ✅ Add a New User

```bash
curl -X POST http://localhost:8081/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Alice", "email": "alice@example.com"}'
```

### 📥 Get All Users

```bash
curl http://localhost:8081/users
```

---

## 🗄️ Access Cassandra from Terminal

### 1. Enter the Cassandra container:

```bash
docker exec -it cassandra-db cqlsh
```

### 2. Switch to the keyspace:

```sql
USE testkeyspace;
```

### 3. View all users:

```sql
SELECT * FROM users;
```

---

## 🧹 Tear Down

To stop and remove all containers:

```bash
docker-compose down
```

---

## 📚 Notes

* Cassandra does **not automatically run** `.cql` files — schema must be loaded manually or programmatically.
* Spring Boot will fail if the keyspace does not exist before the app starts.

---

## ✅ Tested With

* macOS with Docker Desktop
* Java 8
* Cassandra 4.1
* Spring Boot 2.7.13

---

## 👨‍💻 Author

**Harshit Srivastava**
Senior Software Engineer

---

```
