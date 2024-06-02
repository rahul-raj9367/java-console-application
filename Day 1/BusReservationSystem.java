/*


*/
import java.util.*;
import java.text.SimpleDateFormat;
class Bus{
	int busno;
	boolean ac;
	int capacity; //get and set
	String location;

	Bus(int busno,boolean ac,int capacity,String location){
		this.busno=busno;
		this.ac=ac;
		this.capacity=capacity;
		this.location=location;
	}

	public int getCapacity(){
		return capacity;
	}

	public int getBusno(){
		return busno;
	}

	public void setCapacity(int cap){
		capacity=cap;
	}

	public boolean getAc(){
		return ac;
	}

	public void setAc(boolean a){
		ac=a;
	}

	public void displayBusInfo(){
		System.out.println("| Bus no :"+busno+"| Ac:"+ac+"| Total capacity :"+capacity+" |"+"Location :"+location +" |");
	}

	
}


class Booking{
	String passengername;
	int busno;
	String confirmlocation;
	Date date;
	

	//Booking
	Booking(){

		Scanner sc=new Scanner(System.in);
	    System.out.println("Enter  Passengername:");
	    passengername=sc.nextLine();
	    System.out.println("Enter  Bus no :");
	    busno=sc.nextInt();
	    // Consume the newline character left by the previous nextInt()
	    sc.nextLine();
	    System.out.println("Enter Confirm Location :");
	    confirmlocation=sc.nextLine();
	    System.out.println("Enter Date dd-mm-yyyy :");
	    String dateinput=sc.next();
	    SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyy");
	    try{
	        date=dateformat.parse(dateinput);
	    }catch(Exception e){

	    }        
		
	}

	//check available 
	public boolean isAvaiable(ArrayList<Booking> bookings, ArrayList<Bus> buses){
	    int capacity = 0;
	    for(Bus b : buses){
	        if(b.getBusno() == busno){
	            capacity = b.getCapacity();
	            // Check if the confirmation location matches the bus location
	            if (!b.location.equals(confirmlocation)) {
	            	System.out.println("please enter Proper location");
	            	System.exit(0);
	            	// break;
	            }
	             
	        }
	    }

	    int booked = 0;
	    for(Booking b : bookings){
	        if(b.busno == busno && b.date.equals(date)){
	            booked++;
	        }
	    }

    	return booked < capacity;
	}



	public void displayBooking(){
		System.out.println("-------------------------------------------------------------------------------------------------------------");
		System.out.println("Passenge Name: " +passengername+" Bus no: "+busno +" Date :"+ date+" Location :"+confirmlocation);
		System.out.println("-------------------------------------------------------------------------------------------------------------");

	}
	
}


class BusReservationSystem{
	public static void main(String[] args) {

		//bus informations
		ArrayList<Bus> buses=new ArrayList<Bus>();
		ArrayList<Booking> bookings=new ArrayList<Booking>();

		// total 3 bus
		buses.add(new Bus(1,true,2,"Tirunelveli to Chennai"));
		buses.add(new Bus(2,false,4,"Chennai to Tirunelveli"));
		buses.add(new Bus(3,true,5,"Chennai to coimbatore"));

		//display bus info
		System.out.println("------------------------------------Bus Details------------------------");
		for(Bus b:buses){
			b.displayBusInfo();
		}
		System.out.println("------------------------------------------------------------------------");

		int useropt=1;
		Scanner sc=new Scanner(System.in);
		do{
			System.out.println("Enter 1 to Book your Ticket");
			System.out.println("Enter 2 Show Ticket ");
			System.out.println("Enter 3 to exit");
			useropt=sc.nextInt();

			
			if(useropt==1){
				Booking booking=new Booking();
				if(booking.isAvaiable(bookings,buses)){
					bookings.add(booking);
					System.out.println("Your Booking is confirmed");
				}else{
					System.out.println("Sorry bus is full . Try Another Bus or Date.");
				}
			}
			if(useropt==2){
				for(Booking b:bookings){
					b.displayBooking();
				}
			}
		}while(useropt!=3);
	}
}

/*
import java.util.Scanner;

public class BusReservationSystem {
    private static boolean[] seats = new boolean[10];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Reserve Seat");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View Available Seats");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reserveSeat();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    viewAvailableSeats();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private static void reserveSeat() {
        System.out.println("Enter Seat Number (1-10):");
        int seatNumber = scanner.nextInt();

        if (seatNumber < 1 || seatNumber > 10) {
            System.out.println("Invalid Seat Number");
            return;
        }

        if (seats[seatNumber - 1]) {
            System.out.println("Seat Already Reserved");
            return;
        }

        seats[seatNumber - 1] = true;
        System.out.println("Seat Reserved Successfully");
    }

    private static void cancelReservation() {
        System.out.println("Enter Seat Number (1-10):");
        int seatNumber = scanner.nextInt();

        if (seatNumber < 1 || seatNumber > 10) {
            System.out.println("Invalid Seat Number");
            return;
        }

        if (!seats[seatNumber - 1]) {
            System.out.println("Seat Not Reserved");
            return;
        }

        seats[seatNumber - 1] = false;
        System.out.println("Reservation Cancelled Successfully");
    }

    private static void viewAvailableSeats() {
        System.out.println("Available Seats:");

        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                System.out.print((i + 1) + " ");
            }
        }

        System.out.println();
    }
}
*/
