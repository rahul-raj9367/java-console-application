/*

*/
import java.util.*;
class Ticket{
	String name;
	int age;
	char gender;
	char breath;
	char ticketType;

	Ticket(String name,int age,char gender,char breath,char ticketType){
		this.name=name;
		this.age=age;
		this.gender=gender;
		this.breath=breath;
		this.ticketType=ticketType;
	}

	void displayBooking(){
		System.out.println("Name :"+name);
		System.out.println("Age :"+age);
		System.out.println("Gender :"+gender);
		System.out.println("Breath :"+breath);
		System.out.println("TicketType :"+ticketType);
	}

	char getTicketType(){
		return ticketType;
	}

}
class Railwayticketbooking{
	static int confirmedTickets=2;
	static	int waitingListTickets=1;
	static	int racTickets=1;
	
	static void displayAvailableTickets(List<Ticket> bookedTickets){
		int countconfirmedTickets=0;
		int countwaitingListTickets=0;
		int countracTickets=0;

		for(Ticket t:bookedTickets){
			char ch=t.getTicketType();
			if(ch=='C'){
				countconfirmedTickets++;
			}else if(ch=='R'){
				countracTickets++;
			}else if(ch=='W'){
				countwaitingListTickets++;
			}
		}

		System.out.println("-----------AVAILABLE TICKETS----------------------");
		System.out.println("Confirmed Tickets: " + countconfirmedTickets + "/2");
		System.out.println("RAC Tickets: " + countracTickets + "/1");
		System.out.println("Waiting List Tickets: "+countwaitingListTickets+ "/1");


		if(countconfirmedTickets>=2){
			System.out.println("Confirmed Tickets are full");
		} 
		if(countracTickets>=1){
			 System.out.println("RAC tickets are full.");
		}
		if(countwaitingListTickets>=1){
			 System.out.println("Waiting List are full.");
		}
		System.out.println("----------------------------------------------------------");
	}
	



	public static void main(String[] args) {

		List<Ticket> bookedTickets=new ArrayList<>();
		Scanner sc= new Scanner(System.in);

		while(true){
			System.out.println("-------------------WELCOME RAILWAY TICKET BOOKING---------------------");
			System.out.println("1. Book a Ticket");
			System.out.println("2. Display Booked tickets");
			System.out.println("3. Display available tickets");
			System.out.println("4. Cancel Ticket");
			System.out.println("5. Exit");
			System.out.println("----------------------------------------------------------------------");
			System.out.print("Enter Your Choice :");
			int choice=sc.nextInt();

			switch(choice){
				case 1:
					bookTicket(sc,bookedTickets);
					break;
				case 2:
					displayBookedTickets(bookedTickets);
					break;
				case 3:
					displayAvailableTickets(bookedTickets);
					break;
				case 4:
					cancelTicket(bookedTickets,sc);
					break;
				case 5:
					System.exit(0);
				default:
					System.out.println("Wrong Choice");
			}
		}
	}

	static void bookTicket(Scanner sc,List<Ticket> bookedTickets){
		if(confirmedTickets>0 || racTickets>0 || waitingListTickets>0  ){
			System.out.println("Enter Passengername :");
	        String name = sc.next();
	        System.out.println("Enter Age :");
	        int age = sc.nextInt();
	        sc.nextLine(); // Consume the newline character
	        System.out.println("Enter your Gender (M/F) :");
	        char gender = sc.nextLine().charAt(0);
	        System.out.println("Enter Berth Preference (U for upper, L for lower): ");
	        char breath = sc.nextLine().charAt(0);


			char ticketType='C';
			if(age>60 || (gender=='F' && age>18)){
				breath='L';
			}
			if(age<5){
				System.out.println("Tickets are not allocated for children below age 5.");
				return;
			}

			if(confirmedTickets>0){
				ticketType='C';
				confirmedTickets--;
			}else if(racTickets>0){
				ticketType='R';
				racTickets--;
			}else if(waitingListTickets>0){
				ticketType='W';
				waitingListTickets--;
			}else{
				System.out.println("No Ticket available");
			}
			Ticket ticket=new Ticket(name,age,gender,breath,ticketType);
			bookedTickets.add(ticket);
			System.out.println("Ticket Booked Successfully!");
		}else{
			 System.out.println("No Ticket available");
		}
	}
	static void displayBookedTickets(List<Ticket> bookedTickets){
		System.out.println("-------------------BOOKED TICKETS----------------------");
		for(Ticket t:bookedTickets){
			t.displayBooking();
			System.out.println("----------------------------------------------------------");
		}
	}
	static void cancelTicket(List<Ticket> bookedTickets,Scanner sc){
		  System.out.println("Enter the name of the passenger whose ticket you want to cancel: ");
		  String name=sc.next();
		  boolean hasbol=false;

		  Iterator<Ticket> it =bookedTickets.iterator();
		  while(it.hasNext()){
		  	Ticket ticket=it.next();
		  	if(ticket.name.equals(name)){
		  		it.remove();
		  		if(ticket.ticketType=='C'){
		  			waitingListTickets++;
		  			if(waitingListTickets>0){
		  				Iterator<Ticket> iit =bookedTickets.iterator();
		  				while(iit.hasNext()){
		  					Ticket tickett=iit.next();
		  					if(tickett.ticketType=='R'){
		  						tickett.ticketType='C';
		  					}if(tickett.ticketType=='W'){
		  						tickett.ticketType='R';
		  					}
		  				}
		  			}
		  		} 
		  		if(ticket.ticketType=='R'){
		  			waitingListTickets++;
		  			if(waitingListTickets>0){
		  				Iterator<Ticket> iit =bookedTickets.iterator();
		  				while(iit.hasNext()){
		  					Ticket tickett=iit.next();
		  					if(tickett.ticketType=='W'){
		  						tickett.ticketType='R';
		  					}if(tickett.ticketType=='W'){
		  						tickett.ticketType='R';
		  					}
		  				}
		  			}
		  		}
		  		// if(ticket.ticketType=='W') waitingListTickets++;
		  		hasbol=true;
		  		 System.out.println("Ticket for passenger " + ticket.name + " has been canceled.");
		  		 break;
		  	}
		  }
		  if(!hasbol){
		  	System.out.println("Ticket not found for passenger " + name);
		  }
	}
}