/*
			System.out.println("\n-------------------");
			System.out.println("BANK    OF     JAVA");
			System.out.println("-------------------\n");
			System.out.println("1. Register account.");
			System.out.println("2. Login.");
			System.out.println("3. Update accounts.");
			System.out.println("4. Exit.");
			System.out.print("\nEnter your choice : ");
*/
import java.util.*;
class Customer{
	String username,password,name,address,number;
	double balance;
	Date date;


	Customer(String username,String password,String name,String address,String number,double balance,Date date ){
		this.username=username;
		this.password=password;
		this.name=name;
		this.address=address;
		this.number=number;
		this.balance=balance;
		this.date=date;

		System.out.println("|------------------Register account Successfully--------------------|");
	}

	void deposit(double amount){
		balance+=amount;
	}

	void withdrawl(double amount){
		if(amount>(balance-200)){
			System.out.println("Insufficient balance.");
			return;
		}
		balance-=amount;
	}

	void checkBalance(){
		System.out.println("Your Balance :" + balance);
	}
}
class BankManagementSystem{
	//<Key,Value>
	Map<String,Customer> customermap;
	BankManagementSystem(){
		customermap=new HashMap<String,Customer>();
	}

	void displayAllCustomer(){
        if(customermap.size()==0){
        	System.out.println("|----------------------------------|");
        	System.out.println("NO Registered Customers:");
        	System.out.println("Please Click 1 Register your Account :");
        	System.out.println("|----------------------------------|");
        }else{
        	System.out.println("Registered Customers:");
        	for(Map.Entry<String,Customer> entry: customermap.entrySet()){
				String username=entry.getKey();
				Customer customer=entry.getValue();
				System.out.println("|-----------------------------------------------|");
	            System.out.println("Username: " + username);
	            System.out.println("Customer Details:");
	            System.out.println("Name: " + customer.name);
	            System.out.println("Address: " + customer.address);
	             System.out.println("Contact: " + customer.number);
			 	System.out.println("|-----------------------------------------------|");
	             System.out.println(); 
			}
        }

		

	}
	public static void main(String[] args) {

		BankManagementSystem bank=new BankManagementSystem();
		Customer customer;
		String username,password;
		double amount;

		int choice;
outer : while(true){
			System.out.println("\n------------------------------------------");
			System.out.println("BANK    OF     JAVA");
			System.out.println("--------------------------------------------\n");
			System.out.println("1. Register account.");
			System.out.println("2. Login.");
			System.out.println("3. Display Register Customer Details.");
			System.out.println("4. Exit.");
			System.out.print("\nEnter your choice : ");
			
			Scanner sc=new Scanner(System.in);
			choice=sc.nextInt();
			sc.nextLine();
			switch(choice){
				case 1:
					System.out.println("Enter Your Name :");
					String name=sc.nextLine();
					System.out.println("Enter Your Address :");
					String address=sc.nextLine();
					System.out.println("Enter Your Contact Number");
					String number=sc.nextLine();
					System.out.println("Set username :");
					username=sc.nextLine();
					while(bank.customermap.containsKey(username)){
						System.out.println("Username already exists. Set again : ");
						username = sc.next();
					}
					System.out.println("Set password (minimum 8 char, minimum 1 digit, 1 lowercase , 1 uppercase,1 special char[!@#$%^&*_]) :");
					password=sc.next();
					sc.nextLine();
					while(!password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}")))){
						System.out.println("Invalid Conditions password : set again :");
						password=sc.next();
					}
					System.out.println("Enter initial Deposit :");
					amount=sc.nextDouble();
					sc.nextLine();
					customer = new Customer(username,password,name,address,number,amount,new Date());
					bank.customermap.put(username,customer);
					break;

				case 2:
					System.out.println("Enter Your username :");
					username=sc.nextLine();
					System.out.println("Enter your  Password :");
					password=sc.nextLine();

					if(bank.customermap.containsKey(username)){

						customer=bank.customermap.get(username);

						if(customer.password.equals(password)){

							while(true){
								System.out.println("--------WELCOME JAVA BANK---------");
								System.out.println("1.Deposit :");
								System.out.println("2.Withdrawl :");
								System.out.println("3.Ckeck Balance :");
								System.out.println("4.Use Information :");
								System.out.println("5.Log Out :");
								System.out.println("Enter your choice :");
								choice=sc.nextInt();

								switch(choice){
									case 1:
										System.out.println("Enter your Deposit Amount :");
										amount=sc.nextDouble();
										customer.deposit(amount);
										break;
									case 2:
										System.out.println("Enter your Withdrawl Amount :");
										amount=sc.nextDouble();
										customer.withdrawl(amount);
										break; 
									case 3:
										customer.checkBalance();
										break;
									case 4:
									    System.out.println("Accountholder name : "+customer.name);
									    System.out.println("Accountholder address : "+customer.address);
									    System.out.println("Accountholder contact : "+customer.number);
									    break;
									case 5:
									 	continue outer;
									default:
									 	System.out.println("Wrong Choice");
								}

							}
						}else{
							System.out.println("wrong Password ");
							continue outer;
						}
					}else{
						System.out.println("wrong username ");
						continue outer;
					}
				case 3:
					bank.displayAllCustomer();
					break;
				case 4:
					System.exit(0);
				default:
					System.out.println("Wrong Choice");
			}
		}
	}
}