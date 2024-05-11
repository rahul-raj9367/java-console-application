
import java.util.*;
class Employee{
	int id;
	String name;
	String email;
	String number;
	String dateofbirth;
	String dateOfJoining;
	double salary;

	public Employee(int id, String name, String email, String number, String dateofbirth, String dateOfJoining, double salary) {
	    this.id = id;
	    this.name = name;
	    this.email = email;
	    this.number = number;
	    this.dateofbirth = dateofbirth;
	    this.dateOfJoining = dateOfJoining;
	    this.salary = salary;


	    System.out.println("-------Successfully Added---------");
	}

}
class EmployeeManagement{
	Map<Integer,Employee> employee;

	EmployeeManagement(){
		employee= new HashMap<>();
	}

	void addEmployee(int id,Employee employeee){
		employee.put(id,employeee);
	}

	void displayEmoloyee(int id){
		 for (Map.Entry<Integer, Employee> entry : employee.entrySet()) {
	        if (entry.getKey() == id) {
	            Employee emp = entry.getValue();
	            System.out.println("Employee Id: " + entry.getKey());
	            System.out.println("Name: " + emp.name);
	            System.out.println("Email: " + emp.email);
	            System.out.println("Number: " + emp.number);
	            System.out.println("Date of Birth: " + emp.dateofbirth);
	            System.out.println("Date of Joining: " + emp.dateOfJoining);
	            System.out.println("Salary: " + emp.salary);
	            return;
	        }
    	}
    	System.out.println("Employee with ID " + id + " not found.");
	}

	void removeEmoloyee(int id){
		if(employee.containsKey(id)){
			employee.remove(id);
        	System.out.println("Employee with ID " + id + " removed successfully.");
		}
		else{
			System.out.println("Employee with ID " + id + " not found.");
		}
		
	}

	void updateEmoloyee(int id,Employee employe){
		if(employee.containsKey(id)){
			employee.put(id,employe);
			System.out.println("Employee with ID " + id + " updated successfully.");
		}else{
			 System.out.println("Employee with ID " + id + " not found.");
		}
	}


	void displayAllEmoloyee(){
		if(employee.size()==0){
			System.out.println(">>>>>>>>>>>>>>>-Not Available Emoloyees Details-<<<<<<<<<<<<<<");
			System.out.println("Please Click 1 to Add Emoloyees Details :");
		 }else{
		 	for (Map.Entry<Integer, Employee> entry : employee.entrySet()) {
	            Employee emp = entry.getValue();
	            System.out.println("--------------------------------------");
	            System.out.println("Employee Id: " + entry.getKey());
	            System.out.println("Name: " + emp.name);
	            System.out.println("Email: " + emp.email);
	            System.out.println("Number: " + emp.number);
	            System.out.println("Date of Birth: " + emp.dateofbirth);
	            System.out.println("Date of Joining: " + emp.dateOfJoining);
	            System.out.println("Salary: " + emp.salary);
	            System.out.println("--------------------------------------");
			}
    	}
	}

	void checupdate(int id){
		if(!employee.containsKey(id)){
			System.out.println("Employee with ID " + id + " not found.");
		}else{		Scanner sc= new Scanner(System.in);
					System.out.println("Update your Name:");
    				String namee = sc.nextLine();
					System.out.println("Update your Email:");
					String emaill=sc.nextLine();
					System.out.println("Update your Phone Number:");
					String numberr=sc.nextLine();
					System.out.println("Update your DD-MM-YYYY:");
					String dateofbirthh=sc.nextLine();
					System.out.println("Enter your Date Of Joining (DD-MM-YYYY):");
					String dateOfJoiningg=sc.nextLine();
					System.out.println("Update your salary :");
					double salaryy=sc.nextDouble();
					updateEmoloyee(id,new Employee(id, namee, emaill, numberr, dateofbirthh,dateOfJoiningg, salaryy));
		}
	}
}
class EmployeeManagementSystem {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		EmployeeManagement em=new EmployeeManagement();


		while(true){
			System.out.println("---------------------WELCOME EMPLOYEE MANAGEMENT SYSTEM------------------------");
			System.out.println("1. Add Employee");
			System.out.println("2. View Emoloyee");
			System.out.println("3. Remove Emoloyee");
			System.out.println("4. Update Employee");
			System.out.println("5. View All Employees");
			System.out.println("6. Exit");
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("Enter Your Choice : ");

			int choice=sc.nextInt();

			switch(choice){
				case 1:
					System.out.println("Create New Employee Id:");
    				int employeeId = sc.nextInt();
    				sc.nextLine();
    				System.out.println("Enter your Name:");
    				String name = sc.nextLine();
					System.out.println("Enter your Email:");
					String email=sc.nextLine();
					System.out.println("Enter your Phone Number:");
					String number=sc.nextLine();
					System.out.println("Enter your DD-MM-YYYY:");
					String dateofbirth=sc.nextLine();
					System.out.println("Enter your Date Of Joining (DD-MM-YYYY):");
					String dateOfJoining=sc.nextLine();
					System.out.println("Enter your salary :");
					double salary=sc.nextDouble();
					em.addEmployee(employeeId,new Employee(employeeId, name, email, number, dateofbirth, dateOfJoining, salary));
					break;
				case 2:
					System.out.println("Enter your Emoloyee Id :");
					int id=sc.nextInt();
					em.displayEmoloyee(id);
					break;
				case 3:
					System.out.println("Enter your Emoloyee Id :");
					int did=sc.nextInt();
					em.removeEmoloyee(did);
					break;
				case 4:
					System.out.println("Enter your Emoloyee Id :");
					int d=sc.nextInt();
					// System.out.println("Update  Employee Id:");
    				// int employeeIdd = sc.nextInt();
    				// sc.nextLine();
    				em.checupdate(d);
					break;
				case 5:
					em.displayAllEmoloyee();
					break;
				case 6:
					System.exit(0);
				default:
					System.out.println("Wrong Choice");
			}
		}
	}
}