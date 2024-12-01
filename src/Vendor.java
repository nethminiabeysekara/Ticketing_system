class Vendor implements Runnable {
    private final TicketPool pool;
    private final String name;

    public Vendor(TicketPool pool, String name) {
        this.pool = pool;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            pool.addTickets(name + "-Ticket-" + i);
            try {
                Thread.sleep(100); // Simulate delay
            } catch (InterruptedException e) {
                System.out.println("Vendor interrupted: " + e.getMessage());
            }
        }
    }
}

