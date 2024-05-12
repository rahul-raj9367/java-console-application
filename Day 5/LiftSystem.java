

import java.util.*;

class Lift {
    int currentFloor;
    int destinationFloor;

    Lift(int currentFloor, int destinationFloor) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
    }

    int getCurrentFloor() {
        return currentFloor;
    }

    int getDestinationFloor() {
        return destinationFloor;
    }

    
}

class LiftSystem {
    static boolean isValid(int floor) {
        return floor >= 0 && floor <= 10;
    }

	static int assignLift(List<Lift> lifts, int startFloor, int destinationFloor) {
	  	if(!isValid(startFloor) || !isValid(destinationFloor)){
	  		return -1;
	  	}

	  	int minvalue=Integer.MAX_VALUE;
	  	int indexLift=-1;
	  	//main logic
	  	for(int i=0;i<lifts.size();i++){
	  		Lift l=lifts.get(i);
	  		int distance=Math.abs(l.currentFloor-startFloor);
	  		if(distance<minvalue){
	  			minvalue=distance;
	  			indexLift=i;
	  		}
	  	}

	  	return indexLift;


	}


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Lift> lifts = new ArrayList<>();

        //asign default value is 0 for 5 lift
        lifts.add(new Lift(0, 0));
        lifts.add(new Lift(0, 0));
        lifts.add(new Lift(0, 0));
        lifts.add(new Lift(0, 0));
        lifts.add(new Lift(0, 0));

        int choice;

        while (true) {
            System.out.println("---------------------- WELCOME TO LIFT SYSTEM ----------------------");
            System.out.println("1. Request Lift");
            System.out.println("2. Exit");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("Enter Your Choice :");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                	System.out.println("---------------------------------------------------------------------");
                	 System.out.println("Lift   : L1  L2  L3  L4  L5");
                     System.out.print("Floor: ");
                     for (Lift l : lifts) {
                         System.out.print(l.getCurrentFloor() + "   ");
                     }
                     System.out.println();
                     System.out.println("---------------------------------------------------------------------");
                    
                    System.out.println("Enter your Current floor:");
                    int currentFloor = sc.nextInt();
                    System.out.println("Enter your Destination floor:");
                    int destinationFloor = sc.nextInt();
                    int indexLift=assignLift(lifts,currentFloor,destinationFloor);
                    if(indexLift!=-1){
                    	Lift lift=lifts.get(indexLift);
                    	System.out.println("Lift "+(indexLift+1)+" is assigned");
                    	lift.currentFloor=destinationFloor;
                    }else{
                    	System.out.println("Thank you for using the lift system. Exiting...");
                    }
                    break;
                case 2:
                    System.out.println("Thank you for using the lift system. Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Wrong Choice");
            }
        }
    }
}
