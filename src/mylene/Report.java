package mylene;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Report {

    public void generateReports() {
        Scanner sc = new Scanner(System.in);

        boolean exit = false;

        do {
            System.out.println("\nREPORTS MENU:");
            System.out.println("1. INDIVIDUAL REPORT");
            System.out.println("2. GENERAL REPORT");
            System.out.println("3. EXIT");

            System.out.print("Enter Action: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                sc.next();
                System.out.print("Enter Action: ");
            }
            int action = sc.nextInt();

            switch (action) {
                case 1:
                    individualReport();
                    break;
                case 2:
                    generalReport();
                    break;
                case 3:
                    System.out.println("Exiting Reports...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (!exit);

        System.out.println("Thank you, come again!");
    }

    public void individualReport() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter Employee ID to generate report: ");
        int employeeId = getValidPositiveIntInput(sc);

        String query = "SELECT e.e_id, e.fname, e.lname, e.position, e.department, " +
                       "p.basic_salary, p.allowances, p.deductions, p.overtime_pay, p.bonuses, p.gross_salary, p.net_salary " +
                       "FROM tbl_employee e " +
                       "INNER JOIN tbl_gpayslip p ON e.e_id = p.e_id " +
                       "WHERE e.e_id = ?";

        ResultSet rs = conf.getData(query, employeeId);
        String[] headers = {"ID", "First Name", "Last Name", "Position", "Department", "Basic Salary", "Allowances", "Deductions", "Overtime", "Bonuses", "Gross Salary", "Net Salary"};

        System.out.println("\n--- Individual Report ---");

        try {
            if (rs.next()) {
                for (String header : headers) {
                    System.out.printf("%-15s", header);
                }
                System.out.println();
                System.out.printf("%-15d%-15s%-15s%-15s%-15s%-15.2f%-15.2f%-15.2f%-15.2f%-15.2f%-15.2f%-15.2f\n",
                        rs.getInt("e_id"), rs.getString("fname"), rs.getString("lname"),
                        rs.getString("position"), rs.getString("department"),
                        rs.getDouble("basic_salary"), rs.getDouble("allowances"), rs.getDouble("deductions"),
                        rs.getDouble("overtime_pay"), rs.getDouble("bonuses"),
                        rs.getDouble("gross_salary"), rs.getDouble("net_salary"));
            } else {
                System.out.println("No employee found with ID: " + employeeId);
            }
        } catch (SQLException e) {
            System.out.println("Error generating individual report: " + e.getMessage());
        }
    }

    public void generalReport() {
        config conf = new config();

        String query = "SELECT e.e_id, e.fname, e.lname, e.position, e.department, " +
                       "p.basic_salary, p.allowances, p.deductions, p.overtime_pay, p.bonuses, p.gross_salary, p.net_salary " +
                       "FROM tbl_employee e " +
                       "INNER JOIN tbl_gpayslip p ON e.e_id = p.e_id";

        ResultSet rs = conf.getResultSet(query);
        String[] headers = {"ID", "First Name", "Last Name", "Position", "Department", "Basic Salary", "Allowances", "Deductions", "Overtime", "Bonuses", "Gross Salary", "Net Salary"};

        System.out.println("\n--- General Report ---");

        for (String header : headers) {
            System.out.printf("%-15s", header);
        }
        System.out.println();

        try {
            while (rs.next()) {
                System.out.printf("%-15d%-15s%-15s%-15s%-15s%-15.2f%-15.2f%-15.2f%-15.2f%-15.2f%-15.2f%-15.2f\n",
                        rs.getInt("e_id"), rs.getString("fname"), rs.getString("lname"),
                        rs.getString("position"), rs.getString("department"),
                        rs.getDouble("basic_salary"), rs.getDouble("allowances"), rs.getDouble("deductions"),
                        rs.getDouble("overtime_pay"), rs.getDouble("bonuses"),
                        rs.getDouble("gross_salary"), rs.getDouble("net_salary"));
            }
        } catch (SQLException e) {
            System.out.println("Error generating general report: " + e.getMessage());
        }

        double totalNetSalary = calculateTotalNetSalary(query, conf);
        System.out.printf("Total Net Salary of all employees: %.2f\n", totalNetSalary);
    }

    private double calculateTotalNetSalary(String query, config conf) {
        double totalNetSalary = 0;

        ResultSet rs = conf.getResultSet(query);

        try {
            while (rs.next()) {
                totalNetSalary += rs.getDouble("net_salary");
            }
        } catch (SQLException e) {
            System.out.println("Error calculating total net salary: " + e.getMessage());
        }

        return totalNetSalary;
    }

    private int getValidPositiveIntInput(Scanner sc) {
        int value;
        while (true) {
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 0) {
                    break;
                }
            } else {
                sc.next();
            }
            System.out.print("Invalid input. Please enter a positive number: ");
        }
        return value;
    }
}