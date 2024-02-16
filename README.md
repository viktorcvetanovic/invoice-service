his repository contains a Spring Boot application for managing invoices and items, with features implemented to enhance functionality and user experience. Below are the key features and endpoints implemented:

Features Implemented:
Find Invoice Items with Discounts:

Service enables finding all invoice items purchased with a discount, including the discount amount for each item.
Create Invoices Endpoint:

Endpoint for creating invoices with validation to ensure:
Items can't be purchased if the quantity is 0.
Users can't purchase more items than available in stock.
Provides appropriate messages for validation failures.
Returns the entire invoice with all items and the total price as a JSON response.
Find Sale Items in Stock Endpoint:

Endpoint allows finding all items currently on sale and in stock.
Endpoints:
Find Invoice Items with Discounts:

GET /api/invoices/discounted-items
Create Invoices:

POST /api/invoices
Find Sale Items in Stock:

GET /api/items/sale-and-in-stock
Technologies Used:
Java
Spring Boot
Maven (for dependency management)
JSON (for data exchange)
Getting Started:
Clone this repository to your local machine.
Ensure you have Java and Maven installed.
Build the project using Maven.
Run the Spring Boot application.
Access the endpoints using the provided paths and methods.
