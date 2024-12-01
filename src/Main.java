public class Main {
    public static void main(String[] args) {
        TicketPool pool = new TicketPool();

        Thread vendor1 = new Thread(new Vendor(pool, "Vendor1"));
        Thread vendor2 = new Thread(new Vendor(pool, "Vendor2"));
        Thread customer1 = new Thread(new Customer(pool));
        Thread customer2 = new Thread(new Customer(pool));

        vendor1.start();
        vendor2.start();
        customer1.start();
        customer2.start();
    }
}
