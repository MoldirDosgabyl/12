import java.util.*;

public class EventBookingSystemSequenceDiagram {
    public static void main(String[] args) {
        Clientt client = new Clientt("Alice");
        SystemSystem system = new SystemSystem();
        Manager manager = new Manager();
        List<Contractor> contractors = List.of(new Contractor("Contractor1"), new Contractor("Contractor2"));

        System.out.println("=== Event Booking Process Started ===");

        if (client.requestVenue(system)) {
            System.out.println("Clientt: Venue is available");

            if (client.confirmBooking(system) && system.requestPayment(1000)) {
                System.out.println("Clientt: Booking confirmed and payment successful");

                system.notifyBooking(client);
                system.sendReport(contractors);

                client.provideFeedback(system, "Great service!");
                for (String fb : system.getFeedbacks()) {
                    manager.receiveFeedback(fb);
                }

            } else {
                System.out.println("Clientt: Payment failed or booking not confirmed");
            }

        } else {
            System.out.println("Clientt: Venue not available, please choose another date or venue");
        }

        System.out.println("=== Event Booking Process Completed ===");
    }
}

class Clientt {
    String name;
    public Clientt(String name) { this.name = name; }

    public boolean requestVenue(SystemSystem system) {
        System.out.println(name + " requests venue availability");
        return system.checkAvailability();
    }

    public boolean confirmBooking(SystemSystem system) {
        System.out.println(name + " confirms booking");
        return true;
    }

    public void provideFeedback(SystemSystem system, String feedback) {
        System.out.println(name + " provides feedback: " + feedback);
        system.collectFeedback(feedback);
    }
}

class PaymentGateway {
    public boolean processPayment(double amount) {
        System.out.println("PaymentGateway: Processing payment of $" + amount);
        return true;
    }
}

class VenueAdmin {
    public void assignTasks(List<Contractor> contractors) {
        System.out.println("VenueAdmin: Assigning tasks to contractors");
        for (Contractor c : contractors) c.confirmTask();
    }
}

class Contractor {
    String name;
    public Contractor(String name) { this.name = name; }
    public void confirmTask() {
        System.out.println(name + " has completed the assigned task");
    }
}

class Manager {
    public void receiveFeedback(String feedback) {
        System.out.println("Manager receives feedback: " + feedback);
    }
}

class SystemSystem {
    private List<String> feedbacks = new ArrayList<>();
    private PaymentGateway paymentGateway = new PaymentGateway();
    private VenueAdmin venueAdmin = new VenueAdmin();

    public boolean checkAvailability() {
        System.out.println("System: Checking venue availability...");
        return true;
    }

    public boolean requestPayment(double amount) {
        System.out.println("System: Requesting payment...");
        return paymentGateway.processPayment(amount);
    }

    public void notifyBooking(Clientt client) {
        System.out.println("System: Booking confirmed for " + client.name);
    }

    public void sendReport(List<Contractor> contractors) {
        System.out.println("System: Sending report to VenueAdmin");
        venueAdmin.assignTasks(contractors);
    }

    public void collectFeedback(String feedback) {
        feedbacks.add(feedback);
        System.out.println("System: Feedback collected: " + feedback);
    }

    public List<String> getFeedbacks() {
        return feedbacks;
    }
}
