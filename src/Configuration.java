import java.io.*;
import java.util.Scanner;

class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Getters and setters
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Save configuration to a JSON file
    public void saveConfig(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("{\"totalTickets\":" + totalTickets +
                    ", \"ticketReleaseRate\":" + ticketReleaseRate +
                    ", \"customerRetrievalRate\":" + customerRetrievalRate +
                    ", \"maxTicketCapacity\":" + maxTicketCapacity + "}");
        }
    }

    // Load configuration from a JSON file
    public void loadConfig(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String json = reader.readLine();
            // Simple parsing for illustration
            totalTickets = Integer.parseInt(json.split(":")[1].split(",")[0].trim());
            ticketReleaseRate = Integer.parseInt(json.split(":")[2].split(",")[0].trim());
            customerRetrievalRate = Integer.parseInt(json.split(":")[3].split(",")[0].trim());
            maxTicketCapacity = Integer.parseInt(json.split(":")[4].replace("}", "").trim());
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found: " + filename);
            throw e;
        } catch (Exception e) {
            System.out.println("Error loading configuration: " + e.getMessage());
            throw e;
        }
    }
}

class ConfigCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        // Input and validate parameters
        System.out.print("Enter total number of tickets: ");
        int totalTickets = scanner.nextInt();
        while (totalTickets <= 0) {
            System.out.print("Invalid. Enter total number of tickets: ");
            totalTickets = scanner.nextInt();
        }
        config.setTotalTickets(totalTickets);

        System.out.print("Enter ticket release rate: ");
        int ticketReleaseRate = scanner.nextInt();
        while (ticketReleaseRate <= 0) {
            System.out.print("Invalid. Enter ticket release rate: ");
            ticketReleaseRate = scanner.nextInt();
        }
        config.setTicketReleaseRate(ticketReleaseRate);

        System.out.print("Enter customer retrieval rate: ");
        int customerRetrievalRate = scanner.nextInt();
        while (customerRetrievalRate <= 0) {
            System.out.print("Invalid. Enter customer retrieval rate: ");
            customerRetrievalRate = scanner.nextInt();
        }
        config.setCustomerRetrievalRate(customerRetrievalRate);

        System.out.print("Enter maximum ticket capacity: ");
        int maxTicketCapacity = scanner.nextInt();
        while (maxTicketCapacity <= 0 || maxTicketCapacity < totalTickets) {
            System.out.print("Invalid. Enter maximum ticket capacity (must be >= total tickets): ");
            maxTicketCapacity = scanner.nextInt();
        }
        config.setMaxTicketCapacity(maxTicketCapacity);

        // Save configuration
        try {
            config.saveConfig("config.json");
            System.out.println("Configuration saved.");
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }

        // Load and display configuration
        System.out.println("\nLoading saved configuration...");
        try {
            config.loadConfig("config.json");
            System.out.println("Configuration loaded successfully:");
            System.out.println("Total Tickets: " + config.getTotalTickets());
            System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate());
            System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
            System.out.println("Max Ticket Capacity: " + config.getMaxTicketCapacity());
        } catch (IOException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
        }
    }
}
