# RevShop – Console Based E-Commerce Application

## Project Overview

**RevShop** is a secure, console-based e-commerce application developed using **Java**, **JDBC**, and **MySQL**.
The system supports two types of users:

* **Buyers** – can browse products, manage cart, place orders, and give reviews
* **Sellers** – can add products, manage inventory, and view orders

The application follows **OOP principles** and is structured using a layered architecture (UI, Service, DAO, Model).

---

## Features

### Buyer Module

* Register and Login
* Browse available products
* Add products to cart
* Manage cart items
* Place orders
* Add product reviews
* Manage wishlist

### Seller Module

* Seller Registration and Login
* Add new products
* Update product details
* Delete products
* View orders
* Manage inventory stock

### Common Features

* Secure authentication
* Password reset (Forgot Password)
* Input validations using Regex
* Structured database design
* Exception handling

---

## Technology Stack

**Frontend (Interface)**

* Java Console (Scanner-based UI)

**Backend**

* Java
* JDBC

**Database**

* MySQL

**Tools Used**

* Eclipse / IntelliJ IDEA
* MySQL Workbench
* Git & GitHub

---

## Project Architecture

The project follows a **layered architecture**:

* **UI Layer**

  * Handles user interaction through console menus
* **Service Layer**

  * Contains business logic
* **DAO Layer**

  * Handles database operations using JDBC
* **Model Layer**

  * Contains entity classes like User, Product, Cart, Orders, Reviews

---

## Database Tables

Main tables used in the project:

* Users
* Products
* Cart
* Orders
* Order Items
* Reviews
* Wishlist

---

## Validations Implemented

* Email validation using Regex
* Password strength validation
* Mobile number validation
* Input checks for empty values

---

## How to Run the Project

1. Clone the repository
2. Open the project in Eclipse/IntelliJ
3. Create the database in MySQL

```sql
CREATE DATABASE revshop_db;
USE revshop_db;
```

4. Execute all table creation scripts
5. Update DB credentials in the connection class
6. Run the **Main Class** (UI Menu)

---

## Future Enhancements

* GUI version using JavaFX / Web
* Online payment integration
* Admin dashboard
* Order tracking
* Email notifications
* Product search with filters

---

## Learning Outcomes

* Implemented OOP concepts (Encapsulation, Abstraction, Inheritance, Polymorphism)
* Used JDBC for database connectivity
* Designed normalized database schema
* Applied layered architecture
* Implemented validations and exception handling

---

## Author

**Adi Jahnavi**
B.Tech Computer Science (2024 Graduate)

---
