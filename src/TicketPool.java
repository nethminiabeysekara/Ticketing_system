import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class TicketPool {
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());

    public synchronized void addTickets(String ticket) {
        tickets.add(ticket);
        System.out.println("Ticket added: " + ticket);
    }

    public synchronized String removeTicket() {
        if (!tickets.isEmpty()) {
            String ticket = tickets.remove(0);
            System.out.println("Ticket removed: " + ticket);
            return ticket;
        }
        return null;
    }
}
