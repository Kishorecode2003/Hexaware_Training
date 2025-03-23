-- Task 1: Database Design

-- Create the Database
CREATE DATABASE TechShop;
USE TechShop;

-- Schema
-- Customers Table
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100),
    Phone VARCHAR(15),
    Address VARCHAR(200)
);

-- Products Table
CREATE TABLE Products (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(100),
    Description TEXT,
    Price DECIMAL(10,2)
);

-- Orders Table
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    CustomerID INT,
    OrderDate DATE,
    TotalAmount DECIMAL(10,2),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- OrderDetails Table
CREATE TABLE OrderDetails (
    OrderDetailID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Inventory Table
CREATE TABLE Inventory (
    InventoryID INT PRIMARY KEY AUTO_INCREMENT,
    ProductID INT,
    QuantityInStock INT,
    LastStockUpdate DATE,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

INSERT INTO Customers (FirstName, LastName, Email, Phone, Address) VALUES
('Rahul', 'Sharma', 'rahul.sharma@gmail.com', '9876543210', 'Delhi'),
('Anjali', 'Verma', 'anjali.verma@gmail.com', '9823456781', 'Mumbai'),
('Raj', 'Kapoor', 'raj.kapoor@gmail.com', '9898765432', 'Chennai'),
('Sneha', 'Patel', 'sneha.patel@gmail.com', '9812345678', 'Ahmedabad'),
('Arjun', 'Reddy', 'arjun.reddy@gmail.com', '9807654321', 'Hyderabad'),
('Pooja', 'Mishra', 'pooja.mishra@gmail.com', '9786543210', 'Kolkata'),
('Aman', 'Gupta', 'aman.gupta@gmail.com', '9123456789', 'Bhopal'),
('Neha', 'Singh', 'neha.singh@gmail.com', '9765432109', 'Lucknow'),
('Ravi', 'Joshi', 'ravi.joshi@gmail.com', '9954321789', 'Pune'),
('Divya', 'Nair', 'divya.nair@gmail.com', '9845123789', 'Bangalore');

INSERT INTO Products (ProductName, Description, Price) VALUES
('Wireless Mouse', 'Ergonomic USB mouse', 599.00),
('Keyboard', 'Mechanical Keyboard with RGB', 1499.00),
('Laptop Bag', 'Waterproof laptop backpack', 999.00),
('USB Cable', 'Type-C fast charging cable', 199.00),
('Smartphone Stand', 'Adjustable mobile holder', 299.00),
('Earphones', 'Wired earphones with mic', 499.00),
('Laptop Cooling Pad', 'Portable cooling pad', 699.00),
('HDMI Cable', '1.5m high-speed HDMI', 399.00),
('Power Bank', '10000mAh battery pack', 1299.00),
('Bluetooth Speaker', 'Portable speaker with bass', 1799.00);

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES
(1, '2025-03-10', 1098.00),
(2, '2025-03-11', 1998.00),
(3, '2025-03-12', 599.00),
(4, '2025-03-13', 2898.00),
(5, '2025-03-14', 999.00),
(6, '2025-03-15', 799.00),
(7, '2025-03-16', 2298.00),
(8, '2025-03-17', 699.00),
(9, '2025-03-18', 199.00),
(10, '2025-03-19', 1499.00);

INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES
(1, 1, 2),
(1, 4, 1),
(2, 2, 1),
(2, 10, 1),
(3, 5, 2),
(4, 3, 1),
(4, 7, 2),
(5, 3, 1),
(6, 6, 2),
(7, 9, 1),
(7, 2, 1),
(8, 8, 1),
(9, 4, 1),
(10, 2, 1);

INSERT INTO Inventory (ProductID, QuantityInStock, LastStockUpdate) VALUES
(1, 120, '2025-03-20'),
(2, 80, '2025-03-20'),
(3, 60, '2025-03-20'),
(4, 150, '2025-03-20'),
(5, 75, '2025-03-20'),
(6, 110, '2025-03-20'),
(7, 90, '2025-03-20'),
(8, 100, '2025-03-20'),
(9, 65, '2025-03-20'),
(10, 70, '2025-03-20');

-- Task 2

-- 1. Write an SQL query to retrieve the names and emails of all customers. 
SELECT FirstName, LastName, Email
FROM Customers;

/*2. Write an SQL query to list all orders with their order dates and corresponding customer 
names.*/
SELECT Orders.OrderID, Orders.OrderDate, Customers.FirstName, Customers.LastName
FROM Orders
JOIN Customers ON Orders.CustomerID = Customers.CustomerID;

/*3. Write an SQL query to insert a new customer record into the "Customers" table. Include 
customer information such as name, email, and address.*/
INSERT INTO Customers (FirstName, LastName, Email, Phone, Address)
VALUES ('Karan', 'Mehta', 'karan.mehta@gmail.com', '9871234560', 'Nagpur');

/*4. Write an SQL query to update the prices of all electronic gadgets in the "Products" table by 
increasing them by 10%.*/
UPDATE Products
SET Price = Price * 1.10;

/*5. Write an SQL query to delete a specific order and its associated order details from the 
"Orders" and "OrderDetails" tables. Allow users to input the order ID as a parameter.*/
DELETE FROM OrderDetails
WHERE OrderID = 5;
DELETE FROM Orders
WHERE OrderID = 5;

/*6. Write an SQL query to insert a new order into the "Orders" table. Include the customer ID, 
order date, and any other necessary information.*/
INSERT INTO Orders (CustomerID, OrderDate, TotalAmount)
VALUES (3, '2025-03-23', 2499.00);

/*7. Write an SQL query to update the contact information (e.g., email and address) of a specific 
customer in the "Customers" table. Allow users to input the customer ID and new contact 
information.*/
UPDATE Customers
SET Email = 'aman.gupta.updated@gmail.com',
    Address = 'Indore'
WHERE CustomerID = 7;

/*8. Write an SQL query to recalculate and update the total cost of each order in the "Orders" 
table based on the prices and quantities in the "OrderDetails" table.*/
UPDATE Orders
SET TotalAmount = (
    SELECT SUM(OD.Quantity * P.Price)
    FROM OrderDetails OD
    JOIN Products P ON OD.ProductID = P.ProductID
    WHERE OD.OrderID = Orders.OrderID
);

/*9. Write an SQL query to delete all orders and their associated order details for a specific 
customer from the "Orders" and "OrderDetails" tables. Allow users to input the customer ID 
as a parameter.*/
DELETE FROM OrderDetails
WHERE OrderID IN (SELECT OrderID FROM Orders WHERE CustomerID = 4);
DELETE FROM Orders
WHERE CustomerID = 4;

/*10. Write an SQL query to insert a new electronic gadget product into the "Products" table, 
including product name, category, price, and any other relevant details.*/
INSERT INTO Products (ProductName, Description, Price)
VALUES ('Smartwatch', 'Fitness tracking smartwatch with heart rate monitor', 2999.00);

/*11. Write an SQL query to update the status of a specific order in the "Orders" table (e.g., from 
"Pending" to "Shipped"). Allow users to input the order ID and the new status.*/
ALTER TABLE Orders ADD OrderStatus VARCHAR(20);
UPDATE Orders
SET OrderStatus = 'Shipped'
WHERE OrderID = 6;

/*12. Write an SQL query to calculate and update the number of orders placed by each customer 
in the "Customers" table based on the data in the "Orders" table.*/
ALTER TABLE Customers ADD NumberOfOrders INT DEFAULT 0;
UPDATE Customers
SET NumberOfOrders = (
    SELECT COUNT(*)
    FROM Orders
    WHERE Orders.CustomerID = Customers.CustomerID
);

-- Task 3

/*1. Write an SQL query to retrieve a list of all orders along with customer information (e.g., 
customer name) for each order.*/
ALTER TABLE orders ADD COLUMN Status VARCHAR(50);

SELECT
    orders.OrderID,
    orders.OrderDate,
    orders.TotalAmount,
    orders.Status,
    customers.CustomerID,
    CONCAT(customers.FirstName, ' ', customers.LastName) AS CustomerName,
    customers.Email,
    customers.Phone
FROM orders
LEFT JOIN customers USING (CustomerID);

/*2. Write an SQL query to find the total revenue generated by each electronic gadget product. 
Include the product name and the total revenue.*/
SELECT
    products.ProductID,
    products.ProductName,
    IFNULL(SUM(products.Price * orderDetails.Quantity), 0) AS TotalRevenue
FROM products
LEFT JOIN orderDetails USING (ProductID)
GROUP BY products.ProductID, products.ProductName;

/*3. Write an SQL query to list all customers who have made at least one purchase. Include their 
names and contact information.*/
SELECT 
    customers.CustomerID,
    CONCAT(customers.FirstName, ' ', customers.LastName) AS CustomerName,
    customers.Email,
    customers.Phone,
    COUNT(orders.OrderID) AS NumberOfOrders
FROM customers
LEFT JOIN orders USING (CustomerID)
GROUP BY customers.CustomerID, customers.FirstName, customers.LastName
HAVING NumberOfOrders > 0;

/*4. Write an SQL query to find the most popular electronic gadget, which is the one with the highest 
total quantity ordered. Include the product name and the total quantity ordered.*/
SELECT 
    products.ProductName,
    SUM(orderDetails.Quantity) AS TotalQuantity
FROM products
JOIN orderDetails USING (ProductID)
GROUP BY products.ProductID, products.ProductName
ORDER BY TotalQuantity DESC
LIMIT 1;

/*5. Write an SQL query to retrieve a list of electronic gadgets along with their corresponding 
categories.*/
ALTER TABLE products ADD COLUMN ProductLine VARCHAR(100);

UPDATE products
SET ProductLine = CASE
    WHEN ProductName IN ('Laptop', 'Smartphone', 'Tablet') THEN 'Smart Computing Devices'
    WHEN ProductName IN ('Smartwatch', 'Earphones', 'Headphones') THEN 'Wearable Devices'
    WHEN ProductName IN ('Keyboard', 'Mouse', 'Monitor', 'Printer') THEN 'Computer Accessories'
    WHEN ProductName = 'External Hard Drive' THEN 'Storage Devices'
    ELSE 'Others'
END;

SELECT ProductName, ProductLine FROM products;

/*6. Write an SQL query to calculate the average order value for each customer. Include the 
customer's name and their average order value.*/
SELECT 
    customers.CustomerID,
    CONCAT(customers.FirstName, ' ', customers.LastName) AS CustomerName,
    ROUND(AVG(orders.TotalAmount), 2) AS AverageOrderValue
FROM customers
JOIN orders USING (CustomerID)
GROUP BY customers.CustomerID;

/*7. Write an SQL query to find the order with the highest total revenue. Include the order ID, 
customer information, and the total revenue.*/
SELECT 
    orders.OrderID,
    customers.CustomerID,
    CONCAT(customers.FirstName, ' ', customers.LastName) AS CustomerName,
    orders.TotalAmount AS TotalRevenue
FROM orders
JOIN customers USING (CustomerID)
ORDER BY TotalRevenue DESC
LIMIT 1;

/*8. Write an SQL query to list electronic gadgets and the number of times each product has been 
ordered.*/
SELECT 
    products.ProductID,
    products.ProductName,
    COUNT(orderDetails.OrderDetailID) AS NumberOfTimesOrdered
FROM products
LEFT JOIN orderDetails USING (ProductID)
GROUP BY products.ProductID, products.ProductName;

/*9. Write an SQL query to find customers who have purchased a specific electronic gadget product. 
Allow users to input the product name as a parameter.*/
SET @productInput = 'Smartphone';

SELECT DISTINCT
    customers.CustomerID,
    CONCAT(customers.FirstName, ' ', customers.LastName) AS CustomerName,
    customers.Email,
    customers.Phone
FROM customers
JOIN orders USING (CustomerID)
JOIN orderDetails USING (OrderID)
JOIN products USING (ProductID)
WHERE products.ProductName = @productInput;

/*10. Write an SQL query to calculate the total revenue generated by all orders placed within a 
specific time period. Allow users to input the start and end dates as parameters.*/
SET @startDate = '2025-04-20';
SET @endDate = '2025-05-22';

SELECT 
    SUM(TotalAmount) AS TotalRevenueInPeriod
FROM orders
WHERE OrderDate BETWEEN DATE(@startDate) AND DATE(@endDate);

-- Task 4

-- 1. Write an SQL query to find out which customers have not placed any orders.
SELECT 
    CustomerID,
    CONCAT(FirstName, ' ', LastName) AS CustomerName
FROM Customers
LEFT JOIN Orders USING (CustomerID)
WHERE Orders.OrderID IS NULL;

-- 2. Write an SQL query to find the total number of products available for sale. 
SELECT 
    COUNT(*) AS ProductAvailable 
FROM Products 
WHERE ProductID IN (
    SELECT ProductID FROM Inventory WHERE QuantityInStock > 0
);

-- 3. Write an SQL query to calculate the total revenue generated by TechShop. 
SELECT SUM(TotalAmount) AS RevenueEarned FROM Orders;

/*4. Write an SQL query to calculate the average quantity ordered for products in a specific category. 
Allow users to input the category name as a parameter.*/
SET @category = 'Smart Computing Devices';

SELECT 
    AVG(Quantity) AS AverageQuantity
FROM OrderDetails
WHERE ProductID IN (
    SELECT ProductID FROM Products WHERE ProductLine = @category
);

/*5. Write an SQL query to calculate the total revenue generated by a specific customer. Allow users 
to input the customer ID as a parameter.*/
SET @customerIDinput = 4;

SELECT 
    SUM(TotalAmount) AS TotalRevenue 
FROM Orders 
WHERE CustomerID = @customerIDinput
GROUP BY CustomerID;

/*6. Write an SQL query to find the customers who have placed the most orders. List their names 
and the number of orders they've placed.*/
SELECT 
    c.CustomerID,
    CONCAT(c.FirstName, ' ', c.LastName) AS CustomerName,
    COUNT(o.OrderID) AS MaxOrders
FROM Customers c
JOIN Orders o USING (CustomerID)
GROUP BY c.CustomerID
HAVING MaxOrders = (
    SELECT MAX(OrderCount) FROM (
        SELECT CustomerID, COUNT(OrderID) AS OrderCount
        FROM Orders GROUP BY CustomerID
    ) AS maxCount
);

/*7. Write an SQL query to find the most popular product category, which is the one with the highest 
total quantity ordered across all orders.*/
SELECT ProductLine, OrdersCount FROM (
    SELECT 
        ProductLine,
        SUM(OrderDetails.Quantity) AS OrdersCount
    FROM Products
    JOIN OrderDetails USING (ProductID)
    GROUP BY ProductLine
) AS HighestCategory
ORDER BY OrdersCount DESC
LIMIT 1;

/*8. Write an SQL query to find the customer who has spent the most money (highest total revenue) 
on electronic gadgets. List their name and total spending.*/
SELECT 
    c.CustomerID,
    CONCAT(c.FirstName, ' ', c.LastName) AS CustomerName,
    SUM(p.Price * od.Quantity) AS TotalSpending
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
JOIN OrderDetails od ON o.OrderID = od.OrderID
JOIN Products p ON p.ProductID = od.ProductID
WHERE p.ProductLine = 'Smart Computing Devices'
GROUP BY c.CustomerID
ORDER BY TotalSpending DESC
LIMIT 1;

/*9. Write an SQL query to calculate the average order value (total revenue divided by the number of 
orders) for all customers.*/
SELECT 
    rpo.CustomerID,
    rpo.CustomerName,
    SUM(rpo.TotalAmount) AS TotalRevenue,
    COUNT(rpo.OrderID) AS CountOfOrders,
    ROUND(SUM(rpo.TotalAmount)/COUNT(rpo.OrderID), 2) AS AverageOrderValue
FROM (
    SELECT 
        c.CustomerID,
        CONCAT(c.FirstName, ' ', c.LastName) AS CustomerName,
        o.TotalAmount,
        o.OrderID
    FROM Customers c
    JOIN Orders o USING (CustomerID)
) AS rpo
GROUP BY CustomerID;

/*10. Write an SQL query to find the total number of orders placed by each customer and list their 
names along with the order count.*/
SELECT 
    rpo.CustomerID,
    rpo.CustomerName,
    COUNT(rpo.OrderID) AS CountOfOrders
FROM (
    SELECT 
        c.CustomerID,
        CONCAT(c.FirstName, ' ', c.LastName) AS CustomerName,
        o.OrderID
    FROM Customers c
    JOIN Orders o USING (CustomerID)
) AS rpo
GROUP BY CustomerID;


