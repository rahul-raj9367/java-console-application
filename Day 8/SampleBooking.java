import java.util.*;
class Taxi{
	int id;
	char currentLocation;
	int freeTime;
	int earnings;

	List<Booking> booking;
	
	Taxi(int id,char currentLocation){
		this.id=id;
		this.currentLocation=currentLocation;
		this.earnings=0;
		this.freeTime=0;
		this.booking=new ArrayList<>();
	}

	public void updateLocationAndEarnings(char dropLocation,int fare,int droptime){
		this.currentLocation=dropLocation;
		this.earnings+=fare;
		this.freeTime=droptime;
	}

	public boolean isFree(int currentTime){
		return currentTime>=freeTime;
	}

	public void booking(Booking booking){
		this.booking.add(booking);
	}
}

class Booking{
	int customerId,pickuptime,Booking,droptime,fare;
	char pickupLocation,dropLocation;

	Booking(int customerId,char pickupLocation,char dropLocation,int pickuptime,int droptime,int fare){
		this.customerId=customerId;
		this.pickupLocation=pickupLocation;
		this.dropLocation=dropLocation;
		this.pickuptime=pickuptime;
		this.droptime=droptime;
		this.fare=fare;
	}
}
class SampleBooking{
	//like database
	static ArrayList<Taxi> taxis=new ArrayList<>();


	SampleBooking(int numberOfTaxis){
		for(int i=1;i<=numberOfTaxis;i++){
			taxis.add(new Taxi(i,'A'));
		}
	}

	static final int DISTANCE_BETWEEN_POINTS=15; //15km
	static final int TIME_BETWEEN_POINTS=15; //15min
	static final int BASE_FARE=100;
	static final int PER_KM_RATE=10;
	static final int MINIMUM_DISTANCE=5;



	public static void main(String[] args) {
		Scanner  sc=new Scanner(System.in);

		SampleBooking booking=new SampleBooking(4);

		while(true){
			System.out.println("Choose Any one :");
			System.out.println("1.Book Taxi");
			System.out.println("2.Display Details");
			System.out.println("3.Exit");

			int choice=sc.nextInt();
			switch(choice){
				case 1:
					System.out.println("Enter Customer Id");
					int customerId=sc.nextInt();
					System.out.println("Enter Pickup Point:");
					char pickup=sc.next().charAt(0);
					System.out.println("Enter Drop Point:");
					char drop=sc.next().charAt(0);
					System.out.println("Enter PickUp Time");
					String time=sc.next();
					booking.bookTaxi(customerId,pickup,drop,time);
					break;
				case 2:
					booking.displayBooking();
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Wrong Choice");
					break;
			}
		}
	}



	public void bookTaxi(int customerId,char pickupLocation,char dropLocation,String time){
		Taxi allocatedTaxi=null;
		int minDistance=Integer.MAX_VALUE;
		int minEarnings=Integer.MAX_VALUE;


		for(Taxi taxi:taxis){
			int distance=Math.abs(taxi.currentLocation-pickupLocation);
			if(taxi.isFree(getTimeInMinute(time)) && (distance<minDistance || distance==minDistance && taxi.earnings<minEarnings)){
				allocatedTaxi=taxi;
				minDistance=distance;
				minEarnings=minDistance;
			}
		}

		if(allocatedTaxi==null){
			System.out.println("No taxi is free at that time, booking is rejected");
			return;
		}

		int distance=Math.abs(pickupLocation-dropLocation)*DISTANCE_BETWEEN_POINTS;
		int fare=BASE_FARE+Math.max(0,distance-MINIMUM_DISTANCE)*PER_KM_RATE;
		//
		int droptime=getTimeInMinute(time)+(distance*TIME_BETWEEN_POINTS)/DISTANCE_BETWEEN_POINTS;

		Booking booking=new Booking(customerId,pickupLocation,dropLocation,getTimeInMinute(time),droptime,fare);
		allocatedTaxi.booking(booking);
		allocatedTaxi.updateLocationAndEarnings(dropLocation,fare,droptime);

		System.out.println("Taxi can be allotted.");
		System.out.println("Taxi-"+allocatedTaxi.id+"is allotted ");
	}

	public void displayBooking(){
		for(Taxi taxi:taxis){
			System.out.println("Taxi-"+ taxi.id+"  Total Earnings :"+ taxi.earnings);
			int bookid=1;
			for(Booking booking:taxi.booking){
				System.out.println("BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
				System.out.println(bookid+"      "+booking.customerId+"    "+booking.pickupLocation+"    "+booking.dropLocation+"        "
					+getTimeInAMPM(booking.pickuptime)+"     "+getTimeInAMPM(booking.droptime)+"      "+booking.fare);
				bookid++;
			}
		}
	}


	static int getTimeInMinute(String time){
		String part[]=time.split(":");
		int hour=Integer.parseInt(part[0]);
		int minute=Integer.parseInt(part[1].substring(0,2));
		String peroid=part[1].substring(2,4).toUpperCase();

		if(peroid.equals("PM") && hour!=2){
			hour+=12;
		}else if(peroid.equals("AM") && hour==12){
			hour=0;
		}

		return hour*60+minute;
	}

	static String getTimeInAMPM(int minutes){
		StringBuilder sb=new StringBuilder();
		int hour=minutes/60;
		int minute=minutes%60;
		String period=(hour<12)?"AM":"PM";
		hour=(hour>12)?hour-12:hour;
		if(hour==0) hour=12;
			
	    sb.append(hour).append(":");

	    // Ensure minutes are two digits
	    if (minute < 10) {
	        sb.append("0");
	    }
	    sb.append(minute).append(" ").append(period);

	    return sb.toString();

	}
}