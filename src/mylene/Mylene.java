package mylene;
import java.util.Scanner;

public class Mylene {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
Employee employee = new Employee();
        Payslip payslip = new Payslip();
        Report report = new Report();
        do {
            System.out.println("\n--------------- PAYSLIP MANAGEMENT SYSTEM ---------------");
            System.out.println("1. EMPLOYEE");
            System.out.println("2. GENERATE PAYSLIP");
            System.out.println("3. REPORT");
            System.out.println("4. EXIT");
            System.out.println("---------------------------------------------------------");
            System.out.print("Enter Action: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                sc.next();  
                System.out.print("Enter Action: ");
            }
            int action = sc.nextInt();

            switch (action) {
               case 1:
                    Employee em = new Employee();
                    em.employeeInfo();
                    break;
                case 2:
                    Payslip ps = new Payslip();
                    ps.Payslip();   
                    break;
                case 3:
                    report.generateReports(); 
                    break;
                case 4:
                    System.out.print("Exiting... Are you sure? (yes/no): ");
                    String resp = sc.next();
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = true;
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        } while (!exit);

        System.out.println("Thank you, come again!");
    }

}
 