/*
1) Design a Call taxi booking application
-There are n number of taxi’s. For simplicity, assume 4. But it should work for any number of taxi’s.
-The are 6 points(A,B,C,D,E,F)
-All the points are in a straight line, and each point is 15kms away from the adjacent points.
-It takes 60 mins to travel from one point to another
-Each taxi charges Rs.100 minimum for the first 5 kilometers and Rs.10 for the subsequent kilometers.
-For simplicity, time can be entered as absolute time. Eg: 9hrs, 15hrs etc.
-All taxi’s are initially stationed at A.
-When a customer books a Taxi, a free taxi at that point is allocated
-If no free taxi is available at that point, a free taxi at the nearest point is allocated.
-If two taxi’s are free at the same point, one with lower earning is allocated
-Note that the taxi only charges the customer from the pickup point to the drop point. Not the distance it travels from an adjacent point to pickup the customer.
-If no taxi is free at that time, booking is rejected

Design modules for

1)    Call taxi booking 
Input 1:
Customer ID: 1
Pickup Point: A
Drop Point: B
Pickup Time: 9

Output 1:
Taxi can be allotted.
Taxi-1 is allotted

Input 2:
Customer ID: 2
Pickup Point: B
Drop Point: D
Pickup Time: 9

Output 1:
Taxi can be allotted.
Taxi-2 is allotted 
(Note: Since Taxi-1 would have completed its journey when second booking is done, so Taxi-2 from nearest point A which is free is allocated)


Input 3:
Customer ID: 3
Pickup Point: B
Drop Point: C
Pickup Time: 12

Output 1:
Taxi can be allotted.
Taxi-1 is allotted 
2) Display the Taxi details


Taxi No:    Total Earnings:
BookingID    CustomerID    From    To    PickupTime    DropTime    Amount
   
Output:
Taxi-1    Total Earnings: Rs. 400

1     1     A    B    9    10    200
3    3    B    C    12    13    200

Taxi-2 Total Earnings: Rs. 350
2    2    B    D    9    11    350 
*/

import java.util.*;

class Booking {
    int pickuptime, droptime, fare, taxiId, customerId;
    char pickupLocation;
    char dropLocation;

    Booking(int customerId, char pickupLocation, char dropLocation, int pickuptime, int droptime, int fare) {
        this.customerId = customerId;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.pickuptime = pickuptime;
        this.droptime = droptime;
        this.fare = fare;
    }
}

class Taxi {
    int id;
    char currentLocation;
    int earnings;
    int freeTime;

    List<Booking> bookings;

    Taxi(int id, char currentLocation) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.earnings = 0;
        this.freeTime = 0;
        this.bookings = new ArrayList<>();
    }

    public boolean isFree(int currentTime) {
        return currentTime >= freeTime;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public void updateLocationAndEarnings(char dropLocation, int distance, int fare, int droptime) {
        this.currentLocation = dropLocation;
        this.earnings += fare;
        this.freeTime = droptime;
    }
}

class TaxiBooking {
    static ArrayList<Taxi> taxis = new ArrayList<>();
    static final int DISTANCE_BETWEEN_POINTS = 15; // 15km
    static final int TIME_BETWEEN_POINTS = 15; // 15 minutes
    static final int BASE_FARE = 100; // Rs. 100 charge
    static final int PER_KM_RATE = 10; // Rs. 10 per km
    private static final int MINIMUM_DISTANCE = 5;

    TaxiBooking(int numberOfTaxis) {
        for (int i = 1; i <= numberOfTaxis; i++) {
            taxis.add(new Taxi(i, 'A'));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaxiBooking taxibooking = new TaxiBooking(4);
        while (true) {
            System.out.println("Choose any one:");
            System.out.println("1.Book Taxi");
            System.out.println("2.Display Details");
            System.out.println("3.Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Customer Id :");
                    int customerId = sc.nextInt();
                    System.out.println("Enter Pickup Location(A-F):");
                    char pickup = sc.next().charAt(0);
                    System.out.println("Enter Drop Location(A-F):");
                    char drop = sc.next().charAt(0);
                    System.out.println("Enter Pickup Time (in format HH:MM AM/PM):");
                    String time = sc.next();//9:00AM
                    taxibooking.bookTaxi(customerId, pickup, drop, time);
                    break;
                case 2:
                    taxibooking.displayDetails();
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    public void bookTaxi(int customerId, char pickupLocation, char dropLocation, String pickuptime) {
        Taxi allocatedTaxi = null;
        int minDistance = Integer.MAX_VALUE;
        int minEarnings = Integer.MAX_VALUE;

        for (Taxi taxi : taxis) {
            int distance = Math.abs(taxi.currentLocation - pickupLocation);
            if (taxi.isFree(getTimeInMinutes(pickuptime)) && (distance < minDistance || distance == minDistance && taxi.earnings < minEarnings)) {
                allocatedTaxi = taxi;
                minDistance = distance;
                minEarnings = taxi.earnings;
            }
        }

        if (allocatedTaxi == null) {
            System.out.println("No taxi available at this time.");
            return;
        }

       
        int distance = Math.abs(pickupLocation - dropLocation) * DISTANCE_BETWEEN_POINTS;

        int fare = BASE_FARE + Math.max(0, distance - MINIMUM_DISTANCE) * PER_KM_RATE;
        int droptime = getTimeInMinutes(pickuptime) + (distance * TIME_BETWEEN_POINTS) / DISTANCE_BETWEEN_POINTS; // Update drop time calculation
        System.out.println(droptime);
        Booking booking = new Booking(customerId, pickupLocation, dropLocation, getTimeInMinutes(pickuptime), droptime, fare);
        allocatedTaxi.addBooking(booking);
        allocatedTaxi.updateLocationAndEarnings(dropLocation, distance, fare, droptime);

        System.out.println("Taxi can be allotted.");
        System.out.println("Taxi-" + allocatedTaxi.id + " is allotted");
    }


        // Method to convert time in HH:MM AM/PM format to minutes
        private int getTimeInMinutes(String time) {
            String[] parts = time.split(":");
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1].substring(0, 2));
            String period = parts[1].substring(2,4).toUpperCase(); // Extracting AM/PM part
            // System.out.println(period);
            if (period.equals("PM") && hour != 12) {
                hour += 12;
            } else if (period.equals("AM") && hour == 12) {
                hour = 0; // Handling midnight
            }
            return hour * 60 + minute;
        }

    public void displayDetails() {
        for (Taxi taxi : taxis) {
            System.out.println("Taxi id: " + taxi.id + " Total Earnings: Rs. " + taxi.earnings);
            System.out.println("BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");

            int bookingId = 1;
            for (Booking booking : taxi.bookings) {
                System.out.println(bookingId + "                " + booking.customerId + "        " + booking.pickupLocation + "          " + booking.dropLocation + "         " + getTimeInAMPM(booking.pickuptime) + "          " + getTimeInAMPM(booking.droptime) + "         " + booking.fare);
                bookingId++;
            }
        }
    }

    // Method to convert time in minutes to HH:MM AM/PM format
    private String getTimeInAMPM(int minutes) {
        int hour = minutes / 60;
        int minute = minutes % 60;
        String period = (hour < 12) ? "AM" : "PM";
        hour = (hour > 12) ? hour - 12 : hour;
        if (hour == 0) hour = 12; // Handling midnight
        return String.format("%02d:%02d %s", hour, minute, period);
    }
}
