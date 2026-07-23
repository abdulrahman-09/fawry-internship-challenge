import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        QuantumRadar radar = new QuantumRadar(List.of(
                new SeatbeltRule(100),
                new SpeedLimitRule(VehicleType.TRUCK, 60, 300),
                new SpeedLimitRule(VehicleType.PRIVATE, 80, 200)
        ));

        radar.addRule(new SpeedLimitRule(VehicleType.BUS, 70, 250));

        List<RadarObservation> observations = List.of(
                new RadarObservation("ABC1234", LocalDateTime.of(2026, 7, 19, 0, 0), VehicleType.PRIVATE, 94, false),
                new RadarObservation("XYZ7788", LocalDateTime.of(2026, 7, 23, 0, 0), VehicleType.TRUCK, 55, true),
                new RadarObservation("TRK9091", LocalDateTime.of(2026, 7, 24, 1, 7), VehicleType.TRUCK, 70, false),
                new RadarObservation("BUS2244", LocalDateTime.of(2026, 7, 19, 0, 0), VehicleType.BUS, 85, true)
        );

        System.out.println("--- Processing observations ---\n");
        for (RadarObservation observation : observations) {
            Fine fine = radar.process(observation);
            if (fine != null) {
                fine.print();
                System.out.println();
            } else {
                System.out.println("No violations for car " + observation.plateNumber() + "\n");
            }
        }

        System.out.println("--- Fine totals by plate ---");
        for (Map.Entry<String, Integer> entry : radar.getTotalFinesByPlate().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " EGP");
        }

        System.out.println("\n--- Violation counts (rule -> times violated) ---");
        for (Map.Entry<String, Integer> entry : radar.getViolationCounts().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}