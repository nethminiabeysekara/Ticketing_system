class Customer implements Runnable {
    private final TicketPool pool;

    public Customer(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            String ticket = pool.removeTicket();
            if (ticket != null) {
                System.out.println("Customer purchased: " + ticket);
            } else {
                System.out.println("No tickets available.");
            }
            try {
                Thread.sleep(150); // Simulate delay
            } catch (InterruptedException e) {
                System.out.println("Customer interrupted: " + e.getMessage());
            }
        }
    }
}
