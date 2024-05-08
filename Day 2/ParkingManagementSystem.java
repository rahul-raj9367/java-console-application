/*

*/
import java.util.*;
class ParkingLot{
	int capacity;
	int avaiableSpot;
	boolean parkingspots[][];

	ParkingLot(int capacity){
		this.capacity=capacity;
		this.avaiableSpot=capacity;
		this.parkingspots=new boolean[3][3];
	}

	void displayParkingSlot(){
		System.out.println("->_>_>_>_>_>Parking Lot<_<_<_<_<_<_<_-");
		System.out.println("--------------------------");
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(parkingspots[i][j]){
					System.out.print(" [x] ");
				}else{
					System.out.print(" [ ] ");
				}
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

	boolean isParkingLotFull(){
		return avaiableSpot==0;
	}

	void parkVechile(int row,int col){
		parkingspots[row][col]=true;
		avaiableSpot--;
	}

	boolean isSpotOccupied(int row, int column) {
        return parkingspots[row][column];
    }





}
class Vehicle {  
	String numberPlate;
    String type;
   
    Vehicle(){
    	System.out.println("-------------------Enter your Vehicle Details------------------");
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter your Number Plate");
    	numberPlate=sc.nextLine();
    	System.out.println("Enter your Vehicle type");
    	type=sc.nextLine();

    	System.out.println("--------Successfully Booked------");

    }

    void ShowVehicleDetails(){
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------------- ");
		System.out.println("NumberPlate : " +numberPlate+" Vehicle type : "+type);
		// System.out.println("------------------------------------------------------------------------------------------------------------------------------------ ");
    }

}
class Tickets{
	String ticketnumber;
	String entrytime;
	String exittime;
	int row;
	int column;
	// Vehicle vehicle;

	Tickets(ParkingLot lot){
		Scanner sc=new Scanner(System.in);
    	System.out.print("Enter your ticketnumber : ");
    	ticketnumber=sc.nextLine();
    	System.out.print("Enter your Entry Time :");
    	entrytime=sc.nextLine();
    	System.out.print("Enter your Exit Time : ");
    	exittime=sc.nextLine();

    	boolean isValidSpot = false;
    	do{
    		System.out.print("Enter the row number of the parking spot :");
	        row = sc.nextInt();
	        System.out.print("Enter the column number of the parking spot :");
	        column = sc.nextInt();

 			if (lot.isSpotOccupied(row, column)) {
                System.out.println("Parking spot at row " + row + " and column " + column + " is already occupied. Please try another spot.");
            } else {
                isValidSpot = true;
            }
        } while (!isValidSpot);
    	
        
    	
    	lot.parkingspots[row][column]=true;
    	lot.parkVechile(row,column);
	}


	void ShowTicketsDetails(){
    	// System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Ticketnumber : " +ticketnumber+" Entrytime : "+entrytime +" Exittime : "+ exittime +" parking spot row : "+row +" parking spot column : "+column  );
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------ ");
    }



}

class ParkingManagementSystem{
	public static void main(String[] args) {
		
		//set parking lot space
		ParkingLot lot=new ParkingLot(9);

		//like database
		ArrayList<Vehicle> vehicles=new ArrayList<Vehicle>();
		ArrayList<Tickets> tickets=new ArrayList<Tickets>();

		Scanner sc=new Scanner(System.in);
		int park;
		do{
			System.out.println("-------------------Parking Management System Menu:------------------");
            System.out.println("1. Purchase Ticket");
            System.out.println("2. Available Parking Spots");
            System.out.println("3. Show Vehicle And Tickets Details");
            System.out.println("4. Exit");
            System.out.println("----------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
			park=sc.nextInt();
			if(park==1){
				if (lot.isParkingLotFull()) {
                    System.out.println("->->->->->Parking lot is full. Cannot purchase ticket<-<-<-<-<-");
                } else {
                    Tickets ticket = new Tickets(lot);
                    tickets.add(ticket);
                    Vehicle vehicle=new Vehicle();
                    vehicles.add(vehicle);
                }
			}if(park==2){
				lot.displayParkingSlot();
			}
			if(park==3){
				// System.out.println("")
				for (int i = 0; i < vehicles.size() && i < tickets.size(); i++) {
				    vehicles.get(i).ShowVehicleDetails();
				    tickets.get(i).ShowTicketsDetails();
				}

				
			}

		}while(park!=4);
	}
}