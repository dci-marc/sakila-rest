# Sakila RESTful API service

Spring RESTful API service demo on the Sakila dataset.

## Intention

This project is developed exclusively for personal use for educational purposes and possible reference implementations.

It should reflect the best and most widely used concepts and principles -
as far as I can tell as a beginner - while also showing several possibilities
for how different solutions can look.

## Architecture, Concepts & Patterns

### Architecture & Concepts

- Microservice
- RESTful API
- Spring
- Layered Architecture
- Technical Separation
- Docker & Docker Compose (App, Redis, PostgreSQL, MySQL, MariaDB, MinIO)
- GNU Make
- Maven
- GraalVM (highly experimental)
- Coolify with development, testing, production (self-hosted CI/CD)
- SonarQube (Static Code Analysis)
- Qodana (Static Code Analysis)
- CodeQL (Static Code Analysis)
- Git, Git Sub-Modules, Git Flow, Git LFS
- GitHub Actions (Releases, Pages, Packages, SonarQube, Qodana, CodeQL, Build, ..)
- GitHub Pages
- GitHub Packages (custom „Himaya” (Protection) library as dependency)
- Unit Tests
- Authy0 (OAuth2, JWT)
- Entity Caching
- Field Validation
- Database Migrations (Flyway: PostgreSQL, MySQL, MariaDB)
- OpenAPI 3.0 (Swagger)
- Bruno (API Client Generation)
- Strictly Not Null
- S3 service with AWS SDK S3
- `Dotenv` loader
- Config Guard
- Path Normalizer
- Custom Handlers
- Custom SQL Logger
- Custom Request Filter
- Custom Serializer
- Custom Application Profile
- RSocket (Client+Service)
- Bugsnag Integration (Error Monitoring)
- Discord Integration (Notifications)
- Slack Integration (Notifications)

#### Layered Architecture & Technical Separation

- Controller
- Service
- Manager
- Repository

#### Patterns & Principles

- DTO Pattern
- Factory Pattern
- Controller Pattern
- Service Layer Pattern
- Manager Pattern
- Repository Pattern
- Hydrator Pattern (planned)
- Builder Pattern
- Fluent Interface Pattern
- Singleton Pattern
- Problem Details (RFC 7807)
- SOLID Principles
- KISS Principle
- DRY Principle
- Code Analysis

### Software & Modules

#### Spring

- Spring Web (MVC)
- Spring Reactive Web (WebFlux) (planned)
- Spring Security
- Spring Data JPA/JDBC (Entity Navigation, DQL, Native SQL)
- Spring Data Redis
- Spring OAuth2 Resource Server
- Spring Test

#### Custom Modules

- [„Himaya”](https://github.com/dci-marc/java-himaya) (Protection)

## Documentation

[![‹› API Reference](https://badges.ws/badge/%3C%3E-API%20Reference-5555ff)](https://dci-marc.github.io/sakila-rest/)

## Statistics

![Repobeats Analyse](https://repobeats.axiom.co/api/embed/2969d79bc381679b6cfb58bb2fa17c8c60235626.svg "Repobeats analytics image")

![License](https://badges.ws/github/l/dci-marc/sakila-rest)
![Language](https://badges.ws/github/lang/dci-marc/sakila-rest)
![Repository Size](https://badges.ws/github/repo-size/dci-marc/sakila-rest)
![Code Size](https://badges.ws/github/lang-size/dci-marc/sakila-rest)
![Contributors](https://badges.ws/github/contributors/dci-marc/sakila-rest)
![Last Commit](https://badges.ws/github/last-commit/dci-marc/sakila-rest)

[![CodeQL](https://github.com/dci-marc/sakila-rest/actions/workflows/github-code-scanning/codeql/badge.svg)](https://github.com/dci-marc/sakila-rest/actions/workflows/github-code-scanning/codeql)
[![SonarQube](https://github.com/dci-marc/sakila-rest/actions/workflows/build.yml/badge.svg)](https://github.com/dci-marc/sakila-rest/actions/workflows/build.yml)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dci-marc_sakila-rest&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=dci-marc_sakila-rest)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dci-marc_sakila-rest&metric=bugs)](https://sonarcloud.io/summary/new_code?id=dci-marc_sakila-rest)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dci-marc_sakila-rest&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=dci-marc_sakila-rest)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dci-marc_sakila-rest&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=dci-marc_sakila-rest)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dci-marc_sakila-rest&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=dci-marc_sakila-rest)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dci-marc_sakila-rest&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=dci-marc_sakila-rest)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dci-marc_sakila-rest&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=dci-marc_sakila-rest)
