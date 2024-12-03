package mylene;

import java.util.Scanner;
class Employee {
   
public void employeeInfo() {
Scanner sc = new Scanner(System.in);
String response;
boolean exit = true;
do {
System.out.println("\n----EMPLOYEE INFORMATION----");
System.out.println("1. ADD EMPLOYEE ");
System.out.println("2. VIEW EMPLOYEES");
System.out.println("3. UPDATE EMPLOYEE");
System.out.println("4. DELETE EMPLOYEE");
System.out.println("5. EXIT");
System.out.println("\n----------------------------");

System.out.print("Enter Action: ");
int action = getValidAction(sc);

switch (action) {
case 1:
addEmployee();
break;
case 2:
viewEmployees();
break;
case 3:
viewEmployees();
updateEmployee();
viewEmployees();
break;
case 4:
viewEmployees();
deleteEmployee();
viewEmployees();
break;
case 5:
System.out.println("Exiting...");
return;
default:
System.out.println("Invalid choice. Try again.");
}
System.out.print("Do you want to continue? (yes/no): ");
response = sc.next();
} while (response.equalsIgnoreCase("yes"));

System.out.println("Thank you, See you soon!");
}

public int getValidAction(Scanner sc) {
int action = 0;
boolean validInput = false;

while (!validInput) {
while (!sc.hasNextInt()) {
System.out.println("Invalid input. Please enter a number between 1 and 5.");
sc.next();
}
action = sc.nextInt();

if (action >= 1 && action <= 5) {
validInput = true;
} else {
System.out.println("Invalid choice. Please try again.");
}
}
return action;
}

public void addEmployee() {
Scanner sc = new Scanner(System.in);
config conf = new config();

int id = 0;
while (true) {
System.out.print("Enter Employee ID: ");
while (!sc.hasNextInt()) {
System.out.println("Invalid input. Please enter a valid Employee ID.");
sc.next();
}
id = sc.nextInt();
if (id > 0) {
break;
} else {
System.out.println("ID must be a positive integer.");
}
}
sc.nextLine();
String fname;
while (true) {
System.out.print("Enter First Name: ");
fname = sc.nextLine().trim();
if (!fname.isEmpty()) {
break;
} else {
System.out.println("First Name cannot be empty.");
}
}
String lname;
while (true) {
System.out.print("Enter Last Name: ");
lname = sc.nextLine().trim();
if (!lname.isEmpty()) {
break;
} else {
System.out.println("Last Name cannot be empty.");
}
}
String position;
while (true) {
System.out.print("Enter Position: ");
position = sc.nextLine().trim();
if (!position.isEmpty()) {
break;
} else {
System.out.println("Position cannot be empty.");
}
}
String department;
while (true) {
System.out.print("Enter Department: ");
department = sc.nextLine().trim();
if (!department.isEmpty()) {
break;
} else {
System.out.println("Department cannot be empty.");
}
}
String sql = "INSERT INTO tbl_employee (e_id, fname, lname, position, department) VALUES ( ?, ?, ?, ?, ?)";
conf.addRecord(sql, id, fname, lname, position, department);

System.out.println("Employee added successfully!");
}

public void viewEmployees() {
config conf = new config();

String query = "SELECT e_id, fname, lname, position, department FROM tbl_employee";
String[] headers = {"ID", "First Name", "Last Name", "Position", "Department"};
String[] columns = {"e_id", "fname", "lname", "position", "department"};

conf.viewRecords(query, headers, columns);
}

public void updateEmployee() {
Scanner sc = new Scanner(System.in);
config conf = new config();

// Validate Employee ID
int id = 0;
while (true) {
System.out.print("Enter the ID to update: ");
while (!sc.hasNextInt()) {
System.out.println("Invalid input. Please enter a valid Employee ID.");
sc.next();
}
id = sc.nextInt();
if (id > 0) {
break;
} else {
System.out.println("ID must be a positive integer.");
}
}
sc.nextLine();

String fname;
while (true) {
System.out.print("New Employee First Name: ");
fname = sc.nextLine().trim();
if (!fname.isEmpty()) {
break;
} else {
System.out.println("First Name cannot be empty.");
}
}

String lname;
while (true) {
System.out.print("New Employee Last Name: ");
lname = sc.nextLine().trim();
if (!lname.isEmpty()) {
break;
} else {
System.out.println("Last Name cannot be empty.");
}
}

String position;
while (true) {
System.out.print("New Employee Position: ");
position = sc.nextLine().trim();
if (!position.isEmpty()) {
break;
} else {
System.out.println("Position cannot be empty.");
}
}

String department;
while (true) {
System.out.print("New Employee Department: ");
department = sc.nextLine().trim();
if (!department.isEmpty()) {
break;
} else {
System.out.println("Department cannot be empty.");
}
}

String sql = "UPDATE tbl_employee SET fname = ?, lname = ?, position = ?, department = ? WHERE e_id = ?";
conf.updateRecord(sql, fname, lname, position, department, id);

System.out.println("Employee updated successfully!");
}

public void deleteEmployee() {
Scanner sc = new Scanner(System.in);
config conf = new config();

int id = 0;
while (true) {
System.out.print("Enter Employee ID to delete: ");
while (!sc.hasNextInt()) {
System.out.println("Invalid input. Please enter a valid Employee ID.");
sc.next();
}
id = sc.nextInt();
if (id > 0) {
break;
} else {
System.out.println("ID must be a positive integer.");
}
}

String sql = "DELETE FROM tbl_employee WHERE e_id = ?";
conf.deleteRecord(sql, id);

System.out.println("Employee deleted successfully.");
}
   
}