# Metro POS System 🚀  

**A comprehensive Point-of-Sale (POS) solution for Metro stores.**  

This project was developed as part of the Software Construction and Design course. It is a role-based system that streamlines branch management, billing, vendor and product handling, and dynamic reporting. The project follows the **MVC architecture** and includes **JUnit testing** for all major modules to ensure robust functionality.  

---

## Features ✨  

### User Roles 👥  
- **Super Admin**:  
  - 🛠️ Creates branches and manages branch managers.  
- **Branch Manager**:  
  - 👩‍💼 Manages employees, vendors, and products.  
- **Cashier**:  
  - 💳 Processes sales and generates bills.  
- **Data Entry Operator**:  
  - 📋 Maintains vendor and product information.  

### Key Functionalities 🚀  
1. **Role-Based Access Control** 🔒  
   - Secure login for different roles with custom access rights.  

2. **Offline Database Functionality** 📡  
   - Stores data locally during internet outages and auto-syncs when reconnected.  

3. **Dynamic Reporting** 📊  
   - Generate and save detailed reports (sales, stock, profit) in PDF format.  
   - Visualize data through graphs and charts.  

4. **Billing System** 🧾  
   - Generate complete bills with itemized details.  
   - Adjust stock levels automatically.  

5. **Vendor and Product Management** 🛍️  
   - Add and manage vendor information.  
   - Enter and update product details, including pricing by unit/carton.  

6. **Internet Connectivity Management** 🌐  
   - Handles offline data storage and synchronization.  

7. **JUnit Testing** 🧪  
   - All core modules were tested rigorously to ensure accuracy and reliability.  

---

## Technologies Used 🛠️  
- **Frontend**: Java Swing  
- **Backend**: Java  
- **Database**: SQL Server  
- **Architecture**: Model-View-Controller (MVC)  
- **Testing**: Junit  

---

## Getting Started 🎯  

### Prerequisites 📌  
1. Install **SQL Server** on your machine.  
2. Install **Java Development Kit (JDK)** version 23 or later.  

### Steps to Run 🚀  

1. **Database Setup**:  
   - Open SQL Server Management Studio (SSMS).  
   - Upload the database script file (`MetroPOSDatabase.sql`) to SQL Server and execute it.  

2. **Configure Database Connection**:  
   - Locate the `src/models/DatabaseConnection` file in the project directory.  
   - Replace the placeholders with your database URLs:  
     ```java
     private static final String JDBC_URL = "Enter your global database URL";
     private static final String Local_URL = "Enter your local database URL";
     ```  

3. **Build and Run the Project**:  
   - Open the project in your preferred IDE (e.g., NetBeans, IntelliJ IDEA).  
   - Compile the project.  
   - Run the main application file (`Main.java`).  

### Additional Notes 📝  
- Ensure that the SQL Server instance is running before launching the application.  
- In case of internet outages, data will be saved locally and auto-synced when reconnected.  

---

