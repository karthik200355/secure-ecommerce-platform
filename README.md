# Secure E-Commerce Platform

A secure, scalable e-commerce application built using Java, Spring Boot, Microservices, Apache Kafka, React, and MySQL.

## Project Overview

This project demonstrates a production-style e-commerce system using Microservices architecture.

Users can:

* Register and login
* Browse products
* Search and filter products
* Add products to cart
* Place orders
* Make mock payments
* Track orders

The system also handles concurrent purchase requests and ensures that inventory cannot be oversold.

## Technology Stack

### Backend

* Java 21
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* Hibernate
* REST APIs

### Microservices

* API Gateway
* Authentication Service
* Product Service
* Cart Service
* Order Service
* Inventory Service
* Payment Service
* Notification Service

### Messaging

* Apache Kafka

### Database

* MySQL
* MySQL Workbench

### Frontend

* React
* JavaScript
* HTML
* CSS

### Tools

* Git
* GitHub
* Maven
* Postman
* Docker
* Jira

## Key Feature

The main feature of this project is concurrent inventory management.

When multiple users try to purchase the last available product simultaneously, the system ensures that only the valid number of orders succeeds and prevents overselling.

## Development Methodology

The project will be developed using Agile methodology with Jira and Sprint-based development.

## Project Status

Currently under development.
