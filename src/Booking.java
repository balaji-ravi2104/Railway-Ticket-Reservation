public class Booking extends TicketBooker{
    TicketBooker ticketBooker = new TicketBooker();
    public void conformTicket(Passenger passenger,int berthInfo,String allottedBerth){
        passenger.setSeatNumber(berthInfo);
        passenger.setAllotted(allottedBerth);
//        add passenger id to the map
        passengerMap.put(passenger.getPassengerId(),passenger);
//        add passenger id to the booked List
        bookedList.add(passenger.getPassengerId());

//        System.out.println(bookedList);

        System.out.println("!!!!!  Booked Successfully   !!!!!");
    }

    public void addToRAC(Passenger passenger,int racInfo,String allottedRAC){
        //assign seat number and type(RAC)
        passenger.setSeatNumber(racInfo);
        passenger.setAllotted(allottedRAC);

        // add passenger to the map
        passengerMap.put(passenger.getPassengerId(),passenger);

        //add passenger id to the queue of RAC tickets
        racList.offer(passenger.getPassengerId());

        //decrease available RAC tickets by 1
        availableRACTickets--;

        //remove the position that was allotted to the passenger
        racPositions.remove(0);

        System.out.println("!!!! Added to RAC Successfully !!!!");
    }

    public void addToWaitingList(Passenger passenger,int wtInfo,String allottedWL){
        //assign seat number and type(WL)
        passenger.setSeatNumber(wtInfo);
        passenger.setAllotted(allottedWL);

        // add passenger to the map
        passengerMap.put(passenger.getPassengerId(),passenger);
        //add passenger id to the queue of WL tickets
        waitingList.offer(passenger.getPassengerId());

        availableWaitingTickets--;
        //remove the position that was alloted to the passenger
        waitingPositions.remove(0);

        System.out.println("!!!!  added to Waiting List Successfully   !!!!!");

    }

    public void conformCancelTicket(int passengerId){
        //remove the passenger from the map
        Passenger p = passengerMap.get(passengerId);
        passengerMap.remove(passengerId);

        //remove the booked ticket from the list
        bookedList.remove(Integer.valueOf(passengerId));
        int positionBooked = p.getSeatNumber();

        System.out.println("!!!! Cancelled Successfully !!!!");

        if(p.getAllotted().equals("L")){
            availableLowerBerth++;
            lowerBerthPositions.add(positionBooked);
        }else if(p.getAllotted().equals("M")){
            availableMiddleBerth++;
            middleBerthPositions.add(positionBooked);
        }else if(p.getAllotted().equals("U")){
            availableUpperBerth++;
            upperBerthPositions.add(positionBooked);
        }


        if(racList.size()>0){
            // Remove the Passenger from the PassengerMap
            Passenger passengerFromRAC = passengerMap.get(racList.poll());
            // Get the passenger seatNumber
            int positionRAC = passengerFromRAC.getSeatNumber();
            racPositions.add(positionRAC);
            // remove the passenger id from Queue
            racList.remove(passengerFromRAC.getPassengerId());
            availableRACTickets++;

            if(waitingList.size()>0){
                // Remove the Passenger from the PassengerMap
                Passenger passengerWaitingList = passengerMap.get(waitingList.poll());
                // Get the passenger seatNumber
                int positionWL = passengerWaitingList.getSeatNumber();
                // Add the seatNumber into List
                waitingPositions.add(positionWL);
                // remove the passenger id from Queue
                waitingList.remove(passengerWaitingList.getPassengerId());

                //Update the passenger seatNumber and Berth
                passengerWaitingList.setSeatNumber(racPositions.get(0));
                passengerWaitingList.setAllotted("RAC");
                //remove id from RAC List
                racPositions.remove(0);
                // Add ID to RAC Queue
                racList.offer(passengerWaitingList.getPassengerId());

                availableWaitingTickets++;
                availableRACTickets--;
            }
            TicketBooker.bookTicket(passengerFromRAC);
        }
    }
}
