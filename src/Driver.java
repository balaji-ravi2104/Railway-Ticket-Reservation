import java.util.*;
public class Driver {
    static int id=1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            TicketBooker ticketBooker = new TicketBooker();
            System.out.println("1.Book Ticket");
            System.out.println("2.Cancel Ticket");
            System.out.println("3.Available Ticket");
            System.out.println("4.Passenger Ticket");
            System.out.println("5.Exit");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:{
                    System.out.println("Enter the Name");
                    String name = sc.next();
                    System.out.println("Enter the Age");
                    int age = sc.nextInt();
                    System.out.println("Enter the berth preference");
                    System.out.println("U - Upper");
                    System.out.println("M - Middle");
                    System.out.println("L - Lower");
                    String berthPreference = sc.next();
                    //passenger class object
                    Passenger passenger = new Passenger();

                    passenger.setName(name);
                    passenger.setAge(age);
                    passenger.setBerthPreference(berthPreference);
                    passenger.setPassengerId(id);
                    passenger.setSeatNumber(-1);
                    passenger.setAllotted("");
                    // Booking
                    int toIncreaseID = TicketBooker.bookTicket(passenger);
                    if(toIncreaseID!=0) {
                        ++id;
                    }
                    break;
                }
                case 2 :{
                    System.out.println("Enter the ID to Cancel");
                    int cid = sc.nextInt();
                    TicketBooker.cancelTicket(cid);
                    break;
                }
                case 3: {
                    ticketBooker.printAvailableTickets();
                    break;
                }
                case 4 : {
                    ticketBooker.printPassengerDetails();
                    break;
                }
                case 5 : {
                    System.exit(0);
                    break;
                }
                default : {
                    break;
                }
            }
        }
    }
}
