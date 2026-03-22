# 🛒 Order Management System (Java + JDBC)

A console-based Order Management System built using **Core Java**, **JDBC**, and **MySQL**.  
This project simulates basic e-commerce functionalities such as product management, cart handling, and order processing.

---

## 🚀 Features

- 👤 User role selection (Customer / Admin)
- 📦 View all available products
- 🛒 Add items to cart
- ❌ Remove items from cart
- 📋 View cart / order history
- 💳 Checkout system
- 📉 Automatic stock update on order placement
- 🔍 Product availability check before ordering

---

## 🏗️ Tech Stack

- **Language:** Java (Core Java)
- **Database:** MySQL
- **Database Connectivity:** JDBC
- **Architecture:** DAO (Data Access Object) pattern
- **Interface:** Console-based (CLI)

---

## 📁 Project Structure


src/
├── controller/
├── console/
├── repository/
│ ├── implementation/
│ └── interfaces/
├── model/
├── database/
└── Main.java


---

## ⚙️ Database Schema

### Products Table
- productId (PK)
- productName
- price
- availableQty

### Orders Table
- id (PK)
- productId (FK → products.productId)
- productName
- totalItems
- totalPrice
- orderDate

---

## 🧾 How It Works

1. User selects a role (Customer/Admin)
2. Customer can:
   - View products
   - Add items to cart
   - System checks availability before adding
3. On order placement:
   - Stock is reduced automatically
   - Order is saved in the database
4. Cart history can be viewed anytime
5. Admin can manage products (if implemented)

---

## 🧑‍💻 Setup Instructions

### 1. Clone the Repository
```bash
git clone <your-repo-link>
cd order-management-system
2. Setup Database
Install MySQL
Create database:
CREATE DATABASE order_management_system;
Import or create required tables (products, orders)
3. Configure Database Connection

Update your DB configuration:

DBconnection db = new DBconnection();

Make sure it contains:

URL
Username
Password

Example:

String url = "jdbc:mysql://localhost:3306/order_management_system";
String user = "root";
String password = "your_password";
4. Run the Application
Compile and run Main.java
Use console menu to interact with the system
⚠️ Known Considerations
Uses JDBC without ORM frameworks
No GUI (console-based only)
Foreign key constraints must be handled carefully
Input validation is basic
🔐 Key Concepts Used
JDBC connection handling
PreparedStatement vs Statement
SQL queries (SELECT, UPDATE, DELETE)
Exception handling
DAO design pattern
Basic inventory management logic
📌 Future Improvements
Add GUI (JavaFX / Swing / Web UI)
Implement authentication system
Add REST API (Spring Boot)
Improve transaction management
Add pagination for product listing
Add order status tracking
👨‍💻 Author
Developed as part of learning Java backend development and JDBC
Focus on understanding database interaction and system design
📜 License

This project is for educational purposes.


---

If you want, I can next help you:
✅ Add **screenshots section** to README  
✅ Convert this into a **GitHub-ready professional README with badges**  
✅ Or tailor it specifically to impress recruiters 🔥
