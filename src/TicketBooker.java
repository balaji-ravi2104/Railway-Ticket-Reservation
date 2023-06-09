import java.util.*;

public class TicketBooker {
    static int  availableLowerBerth = 1;
    static int availableMiddleBerth = 1;
    static int availableUpperBerth = 1;
    static int availableRACTickets = 1;
    static int availableWaitingTickets = 1;

    static List<Integer> lowerBerthPositions = new ArrayList<Integer>(Arrays.asList(1));
    static List<Integer> middleBerthPositions = new ArrayList<Integer>(Arrays.asList(1));
    static List<Integer> upperBerthPositions = new ArrayList<Integer>(Arrays.asList(1));
    static List<Integer> racPositions = new ArrayList<Integer>(Arrays.asList(1));
    static List<Integer> waitingPositions = new ArrayList<Integer>(Arrays.asList(1));

    static Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList = new LinkedList<>();
    static List<Integer> bookedList = new ArrayList<>();

    static Map<Integer,Passenger> passengerMap = new HashMap<>();

    public static int bookTicket(Passenger passenger) {
        Booking booking = new Booking();
        if(availableWaitingTickets==0){
            System.out.println("!!! Sorry No Ticket is Available !!!");
            return 0;
        }

        if((passenger.getBerthPreference().equals("L") && availableLowerBerth > 0)  ||
                (passenger.getBerthPreference().equals("M") && availableMiddleBerth >0) ||
                (passenger.getBerthPreference().equals("U") && availableUpperBerth >0))
        {

            System.out.println("Preferred berth Available");

            if(passenger.getBerthPreference().equals("L")){
                System.out.println("Lower Berth Given");
                //call booking function to conformTicket
                booking.conformTicket(passenger,lowerBerthPositions.get(0),"L");
                lowerBerthPositions.remove(0);
                availableLowerBerth--;

            }
            else if(passenger.getBerthPreference().equals("M")){
                System.out.println("Middle Berth Given");
                //call booking function to conformTicket
                booking.conformTicket(passenger,middleBerthPositions.get(0),"M");
                middleBerthPositions.remove(0);
                availableMiddleBerth--;

            }
            else if(passenger.getBerthPreference().equals("U")){
                System.out.println("Upper Berth Given");
                //call booking function to conformTicket
                booking.conformTicket(passenger,upperBerthPositions.get(0),"U");
                upperBerthPositions.remove(0);
                availableUpperBerth--;

            }
        }
        // preference not available -> book the available berth
        else if(availableLowerBerth>0){
            System.out.println("Lower Berth Given");
            //call booking function to conformTicket
            booking.conformTicket(passenger,lowerBerthPositions.get(0),"L");
            lowerBerthPositions.remove(0);
            availableLowerBerth--;
        }
        else if(availableMiddleBerth>0){
            System.out.println("Middle Berth Given");
            //call booking function to conformTicket
            booking.conformTicket(passenger,middleBerthPositions.get(0),"M");
            middleBerthPositions.remove(0);
            availableMiddleBerth--;
        }
        else if(availableUpperBerth>0){
            System.out.println("Upper Berth Given");
            //call booking function to conformTicket
            booking.conformTicket(passenger,upperBerthPositions.get(0),"U");
            upperBerthPositions.remove(0);
            availableUpperBerth--;
        }
        else if(availableRACTickets>0){
            System.out.println("RAC Available");
            booking.addToRAC(passenger,racPositions.get(0),"RAC");
        }
        else if(availableWaitingTickets>0){
            System.out.println("Added to Waiting List");
            booking.addToWaitingList(passenger,waitingPositions.get(0),"WL");
        }
        return 1;
    }

    public static void cancelTicket(int pid){
        if(!passengerMap.containsKey(pid)){
            System.out.println("!!!  Sorry the ID is not valid... !!!");
        }else{
            Booking booking = new Booking();
            booking.conformCancelTicket(pid);
        }
    }

    public void printAvailableTickets(){
        System.out.println("Available Lower Berths "  + availableLowerBerth);
        System.out.println("Available Middle Berths "  + availableMiddleBerth);
        System.out.println("Available Upper Berths "  + availableUpperBerth);
        System.out.println("Available RACs " + availableRACTickets);
        System.out.println("Available Waiting List " + availableWaitingTickets);
        System.out.println("--------------------------");
    }

    public void printPassengerDetails(){
        if(passengerMap.size()==0){
            System.out.println("No Passengers is Available");
            return;
        }
        for(Passenger p:passengerMap.values()){
            System.out.println("Passenger ID "+p.getPassengerId());
            System.out.println("Name "+p.getName());
            System.out.println("Age "+p.getAge());
            System.out.println("Status "+p.getSeatNumber()+p.getAllotted());
            System.out.println("-------------------------------------------------------");
        }
    }

}
