import java.util.*;

public class Patterns {

    // --- 1. Repository Pattern ---
    public static class Patient {
        private String id;
        private String name;
        public Patient(String id, String name) { this.id = id; this.name = name; }
        public String getId() { return id; }
        public String getName() { return name; }
    }

    public static class Repository {
        private Map<String, Patient> db = new HashMap<>();
        public void save(Patient p) { db.put(p.getId(), p); }
        public Patient findById(String id) { return db.get(id); }
        public void delete(String id) { db.remove(id); }
    }

    // --- 2. Strategy Pattern ---
    public interface FeeStrategy {
        double calculateFee(double baseFee);
    }

    public static class StandardFee implements FeeStrategy {
        public double calculateFee(double baseFee) { return baseFee; }
    }

    public static class SeniorFee implements FeeStrategy {
        public double calculateFee(double baseFee) { return baseFee * 0.8; } // 20% discount
    }

    // --- 3. Observer Pattern ---
    public interface Observer {
        void update(String event);
    }

    public static class AppointmentSubject {
        private List<Observer> observers = new ArrayList<>();
        public void subscribe(Observer obs) { observers.add(obs); }
        public void notifyObservers(String event) {
            for (Observer obs : observers) { obs.update(event); }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Running Java Pattern Benchmarks ===");
        
        // Test Repository
        Repository repo = new Repository();
        repo.save(new Patient("P101", "John Doe"));
        assert repo.findById("P101").getName().equals("John Doe");
        System.out.println("[Java] Repository Pattern: PASS");

        // Test Strategy
        FeeStrategy standard = new StandardFee();
        FeeStrategy senior = new SeniorFee();
        assert standard.calculateFee(100.0) == 100.0;
        assert senior.calculateFee(100.0) == 80.0;
        System.out.println("[Java] Strategy Pattern: PASS");

        // Test Observer
        AppointmentSubject subject = new AppointmentSubject();
        final boolean[] notified = {false};
        subject.subscribe(event -> notified[0] = event.equals("APPOINTMENT_CREATED"));
        subject.notifyObservers("APPOINTMENT_CREATED");
        assert notified[0];
        System.out.println("[Java] Observer Pattern: PASS");
    }
}
