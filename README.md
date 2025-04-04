# Game Rental Club

## Overview
The Game Rental Club is a desktop-based application developed in Java and JavaFX with MySQL as the database backend. It allows users to manage game rentals with three roles: Administrator, Staff, and Customer. Key features include user authentication, game catalog browsing, rental processing, overdue notifications, and reporting.

## Features
- *User Authentication*: Secure login for Admin, Staff, and Customer roles.
- *Game Catalog*: Browse games with search by genre.
- *Rental Processing*: Rent games with stock management.
- *Overdue Notifications*: Alerts for overdue rentals on the Customer Panel.
- *Reporting*: Rental reports for administrators.
- *Navigation*: Basic navigation bar for quick access to pages.

## Setup Instructions
1. *Install Java*: Ensure JDK 22 is installed.
2. *Install MySQL*: Set up MySQL Server 8.0 and create a database named gamerentalclub.
3. *Database Setup*:
   - Create tables:
     ```sql
     CREATE TABLE Users (UserID INT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(100), Email VARCHAR(100) UNIQUE, Role VARCHAR(20), EncryptedPassword VARCHAR(255));
     CREATE TABLE Games (GameID INT AUTO_INCREMENT PRIMARY KEY, Title VARCHAR(100), Genre VARCHAR(50), StockAvailability INT, RentalPrice DECIMAL(5,2));
     CREATE TABLE Rentals (RentalID INT AUTO_INCREMENT PRIMARY KEY, GameID INT, UserID INT, RentDate DATE, ExpectedReturnDate DATE, Status VARCHAR(20), FOREIGN KEY (GameID) REFERENCES Games(GameID), FOREIGN KEY (UserID) REFERENCES Users(UserID));


INSERT INTO Users (Name, Email, Role, EncryptedPassword) VALUES ('Alice', 'alice@example.com', 'Customer', 'hashedpass123');
INSERT INTO Users (Name, Email, Role, EncryptedPassword) VALUES ('Parul Saini', 'parul@gamerentalclub.com', 'Admin', 'admin123');
INSERT INTO Users (Name, Email, Role, EncryptedPassword) VALUES ('Dhruv', 'dhruv@gamerentalclub.com', 'Staff', 'staff123');
INSERT INTO Users (Name, Email, Role, EncryptedPassword) VALUES ('Abhinav', 'abhinav@gamerentalclub.com', 'Customer', 'customer123');
INSERT INTO Users (Name, Email, Role, EncryptedPassword) VALUES ('Aniket', 'aniket@gamerentalclub.com', 'Customer', 'customer456');
INSERT INTO Games (Title, Genre, StockAvailability, RentalPrice) VALUES ('Super Adventure', 'Action', 5, 3.99);
