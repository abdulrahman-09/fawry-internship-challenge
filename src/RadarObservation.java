import java.time.LocalDateTime;
/**
 * Represents immutable snapshot of a single observation set by the physical radar.
 */

public record RadarObservation(
        String plateNumber,
        LocalDateTime observedAt,
        VehicleType vehicleType,
        int speed,
        boolean isSeatbeltFastened
) {
}
