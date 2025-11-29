import java.util.Scanner;

class Candidate {
    String name;
    boolean offerAccepted;

    Candidate(String name) {
        this.name = name;
        this.offerAccepted = false;
    }

    void receiveOffer() {
        System.out.println(name + " received job offer.");
        Scanner sc = new Scanner(System.in);
        System.out.print("Accept offer? (yes/no): ");
        String answer = sc.nextLine();
        offerAccepted = answer.equalsIgnoreCase("yes");
    }

    void receiveRejection() {
        System.out.println(name + " received rejection notice.");
    }
}

class Manager {
    void createJobRequest() {
        System.out.println("Manager creates job request.");
    }

    void conductTechnicalInterview(Candidate candidate, boolean passed) {
        System.out.println("Manager conducts technical interview.");
    }
}

class HR {
    boolean checkApplication(boolean valid) {
        if (valid) {
            System.out.println("HR approves the application.");
            return true;
        } else {
            System.out.println("HR rejects the application.");
            return false;
        }
    }

    boolean reviewCandidate(boolean suitable) {
        if (suitable) {
            System.out.println("HR invites candidate for interview.");
            return true;
        } else {
            System.out.println("HR rejects candidate.");
            return false;
        }
    }

    void conductInitialInterview() {
        System.out.println("HR conducts initial interview.");
    }

    void notifyIT() {
        System.out.println("HR notifies IT department.");
    }
}

class IT {
    void setupWorkspace() {
        System.out.println("IT sets up workspace for new employee.");
    }
}

class SystemModule {
    void addEmployeeToDatabase(Candidate candidate) {
        System.out.println("System adds " + candidate.name + " to database.");
    }

    void publishVacancy() {
        System.out.println("System publishes vacancy.");
    }

    void receiveApplications() {
        System.out.println("System receives candidate applications.");
    }
}

public class ActivityDiagramHR {
    public static void main(String[] args) {
        Manager manager = new Manager();
        HR hr = new HR();
        IT it = new IT();
        SystemModule system = new SystemModule();
        Candidate candidate = new Candidate("John Doe");

        manager.createJobRequest();

        boolean applicationValid = hr.checkApplication(true);
        if (!applicationValid) return;

        system.publishVacancy();
        system.receiveApplications();

        boolean candidateSuitable = hr.reviewCandidate(true);
        if (!candidateSuitable) return;

        hr.conductInitialInterview();
        manager.conductTechnicalInterview(candidate, true);

        candidate.receiveOffer();
        if (candidate.offerAccepted) {
            system.addEmployeeToDatabase(candidate);
            hr.notifyIT();
            it.setupWorkspace();
        } else {
            System.out.println(candidate.name + " declined the offer. Process ends.");
        }
    }
}
