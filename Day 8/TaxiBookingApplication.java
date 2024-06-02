import java.util.*;
class TaxiBookingApplication{
    static ArrayList<Taxi> taxis=new ArrayList<>(); 
    // static ArrayList<Booking> bookings=new ArrayList<>(); 

    static final int DISTANCE_BETWEEN_POINTS=15;//15km
    static final int TIME_BETWEEN_POINTS=60;//60minutes
    static final int BASE_FARE=100; //100 charge
    static final int PER_KM_RATE=10; //10per km 
     private static final int MINIMUM_DISTANCE = 5;

    TaxiBookingApplication(int numberOfTaxis){
        for(int i=1;i<=numberOfTaxis;i++){
            taxis.add(new Taxi(i,'A'));
        }
    } 


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        TaxiBookingApplication taxibooking=new TaxiBookingApplication(4);
        while(true){
            System.out.println("Choose any one:");
            System.out.println("1.Book Taxi");
            System.out.println("2.Display Details");
            System.out.println("3.Exit");

            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter Customer Id :");
                    int customerId=sc.nextInt();
                    System.out.println("Enter Pickup Location(A-F):");
                    char pickup=sc.next().charAt(0);
                    System.out.println("Enter Drop Location(A-F):");
                    char drop=sc.next().charAt(0);
                    System.out.println("Enter Pickup Time (in hours, 0-23):");
                    int time = sc.nextInt();
                    taxibooking.bookTaxi(customerId,pickup,drop,time);
                    break;
                case 2:
                    taxibooking.displayDetails();
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }


    //initialize Taxis
    

    public void bookTaxi(int customerId,char pickupLocation,char dropLocation,int pickuptime){
    
        Taxi allocatedTaxi = null;
        int minDistance = Integer.MAX_VALUE;
        int minEarnings = Integer.MAX_VALUE;


        for(Taxi taxi:taxis){
            int distance=Math.abs(taxi.currentLocation-pickupLocation);
            if(taxi.isFree(pickuptime) && (distance<minDistance || distance==minDistance && taxi.earnings<minEarnings) ){
                allocatedTaxi=taxi;
                minDistance=distance;
                minEarnings=taxi.earnings;
            }
        }

        if(allocatedTaxi==null){
            System.out.println("No taxi available at this time.");
            return;
        }


        int distance=Math.abs(pickupLocation-dropLocation)*DISTANCE_BETWEEN_POINTS;
        int fare= BASE_FARE+Math.max(0,distance-MINIMUM_DISTANCE)*PER_KM_RATE;
        int droptime=pickuptime+Math.abs(pickupLocation-dropLocation)*TIME_BETWEEN_POINTS/60;


        Booking booking =new Booking(customerId,pickupLocation,dropLocation,pickuptime,droptime,fare);
        allocatedTaxi.addBooking(booking);
        allocatedTaxi.updateLocationAndEarnings(dropLocation,distance,fare,droptime);


        System.out.println("Taxi can be allotted.");
        System.out.println("Taxi-" + allocatedTaxi.id + " is allotted");


    }

    public void displayDetails(){
        for(Taxi taxi:taxis){
            System.out.println("Taxi id: "+taxi.id+" Total Earnings: Rs. " + taxi.earnings);
            System.out.println("BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");

            int bookingId=1;
            for(Booking booking:taxi.bookings){
                 System.out.println(bookingId + "                " + booking.customerId + "        " + booking.pickupLocation + "          " + booking.dropLocation + "         " + booking.pickuptime + "          " + booking.droptime + "         " + booking.fare);
                bookingId++;
            }
        }   
    }
}