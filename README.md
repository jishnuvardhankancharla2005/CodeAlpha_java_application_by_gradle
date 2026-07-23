# ⚡ CodeAlpha Task 3: Java Application using Gradle

<div align="center">

![Java](https://img.shields.io/badge/Java-21_LTS-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.1-brightgreen?style=for-the-badge&logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-8.13-blue?style=for-the-badge&logo=gradle)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker)
![Prometheus](https://img.shields.io/badge/Prometheus-Monitoring-E6522C?style=for-the-badge&logo=prometheus)
![Grafana](https://img.shields.io/badge/Grafana-Dashboards-F46800?style=for-the-badge&logo=grafana)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD_Pipeline-D24939?style=for-the-badge&logo=jenkins)

<br/>

### 🚀 Production-Grade Enterprise Java Microservice with Automated Gradle Builds, Real-Time Observability & Declarative CI/CD Pipelines

[⚡ Quick Start](#-one-click-quick-start) • [📊 Features](#-key-features) • [⚙️ CI/CD Pipeline](#-jenkins-cicd-pipeline) • [📈 Monitoring](#-prometheus--grafana-monitoring)

</div>

---

## 🎯 Task Objectives Achieved

This project fulfills all requirements for **Task 3: Java Application using Gradle** under CodeAlpha DevOps Engineering:

- ✅ **Automate Java project builds using Gradle**: Complete Gradle build lifecycle management, automated task execution (`checkstyle`, `jacoco`, `test`, `integrationTest`, `bootJar`).
- ✅ **Manage dependencies efficiently in the Java app**: Modern Spring Boot 3.4.1 dependency management, Actuator metric registry, and Micrometer Prometheus exporter.
- ✅ **Integrate CI/CD pipelines for continuous delivery**: Cross-platform 8-Stage Declarative Jenkins Pipeline ([Jenkinsfile](file:///c:/Users/jishn/OneDrive/%E3%83%89%E3%82%AD%E3%83%A5%E3%83%A1%E3%83%B3%E3%83%88/CodeAlpha_Java%20Application%20using%20Gradle/Jenkinsfile)) supporting both Windows native execution (`bat`) and Linux/Docker execution (`sh`).
- ✅ **Streamline build and deployment processes**: Single command orchestration script (`start.bat` / `start.sh`) deploying 5 containerized microservices in isolated networks.
- ✅ **Understand core DevOps principles in Java development**: End-to-end telemetry (Counters, Gauges, Timers), time-series metrics scraping (Prometheus), automated visual dashboard provisioning (Grafana), and host OS monitoring (Node Exporter).

---

## 🏗️ Architecture & Monitoring Stack

```
                                +-----------------------------------+
                                |     Browser Control Hub           |
                                |     http://localhost:5173         |
                                +-----------------+-----------------+
                                                  |
       +------------------------------------------+------------------------------------------+
       |                                          |                                          |
       v                                          v                                          v
+--------------+                       +-------------------+                      +--------------------+
|  Spring Boot | -- Actuator Metrics ->| Prometheus Engine | -- Scrapes Data ---> | Grafana Dashboards |
|  REST App    |    (/prometheus)      |   (Port 9090)     |                      |    (Port 3000)     |
| (Port 5173)  |                       +---------+---------+                      +--------------------+
+--------------+                                 ^
                                                 | Scrapes System Telemetry
                                       +---------+---------+
                                       |   Node Exporter   |
                                       |    (Port 9100)    |
                                       +-------------------+
```

---

## ⚡ One-Click Quick Start

Launch the entire Java application, Prometheus metrics database, Grafana dashboards, Node Exporter, and Jenkins pipeline with a single command:

```cmd
.\start.bat
```

*(On Linux / macOS / Bash: `./start.sh`)*

### 🌐 Instant Access Links

| Service | Port | Direct Link | Credentials |
| :--- | :--- | :--- | :--- |
| 🚀 **Java Application Hub** | `5173` | [http://localhost:5173](http://localhost:5173) | N/A |
| 🔥 **Prometheus Metrics** | `9090` | [http://localhost:9090/graph?g0.expr=app_tasks_created_total](http://localhost:9090/graph?g0.expr=app_tasks_created_total) | Public |
| 📈 **Grafana Dashboards** | `3000` | [http://localhost:3000/d/java-app-metrics/java-spring-boot-application-dashboard](http://localhost:3000/d/java-app-metrics/java-spring-boot-application-dashboard) | `admin` / `admin` |
| ⚙️ **Jenkins Pipeline** | `8080` / `8085` | [http://localhost:8080/job/java-app-pipeline/](http://localhost:8080/job/java-app-pipeline/) | Local Admin |

---

## 🔄 Jenkins CI/CD Pipeline

The project features a **Declarative 8-Stage CI/CD Pipeline** defined in [Jenkinsfile](file:///c:/Users/jishn/OneDrive/%E3%83%89%E3%82%AD%E3%83%A5%E3%83%A1%E3%83%B3%E3%83%88/CodeAlpha_Java%20Application%20using%20Gradle/Jenkinsfile):

```
[Stage 1: SCM Checkout] ➔ [Stage 2: Checkstyle Quality] ➔ [Stage 3: Unit Tests] ➔ [Stage 4: Integration Tests] 
           │
           ▼
[Stage 5: Security Scan] ➔ [Stage 6: Package Jar] ➔ [Stage 7: Docker Build] ➔ [Stage 8: Deployment Smoke Test]
```

### Key Highlights:
- **Cross-Platform Compatibility**: Automatically detects OS (`isUnix()`) and executes `sh` on Linux/Docker agents and `gradlew.bat` on Windows native Jenkins nodes.
- **Zero Configuration**: Does not require manual Global Tool entries in Jenkins. Uses the workspace Gradle wrapper (`gradlew`) out of the box.

---

## 🛠️ Technology Stack

- **Core Application**: Java 21 LTS, Spring Boot 3.4.1 (Web, Actuator, Micrometer Prometheus)
- **Build & Automation**: Gradle 8.13, Checkstyle 10.12, JaCoCo Coverage
- **Containerization**: Multi-stage Dockerfile (`eclipse-temurin:21-jdk-alpine`), Docker Compose
- **Observability**: Prometheus v2.45.0, Grafana v10.0.3, Node Exporter v1.6.1
- **CI/CD**: Jenkins LTS (Declarative Pipeline)

---

## 💻 What Was Done in This Project

1. **REST Microservice Implementation**: Built a full Task Management API with CRUD endpoints, custom Micrometer metrics (`app_tasks_created_total`, `app_tasks_active_count`, `app_task_processing_duration_seconds`), and simulation endpoints (`/api/tasks/simulate/work`, `/api/tasks/simulate/error`).
2. **Interactive Landing Dashboard**: Built a responsive glassmorphism web interface at `@GetMapping("/")` providing status checks, direct tool navigation, and interactive metric simulation buttons.
3. **Automated Grafana Provisioning**: Configured Grafana to auto-load datasources and pre-built JSON dashboards for JVM memory, HTTP throughput, and OS server metrics without manual setup.
4. **Resilient Jenkins Pipeline**: Created a 100% fail-safe declarative pipeline resolving JDK/Gradle toolchain version mismatches and cross-platform shell execution issues.

---

<div align="center">

Made with ❤️ for **CodeAlpha DevOps Task 3**

</div>