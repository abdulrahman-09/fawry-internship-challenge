/**
 * Represents a single rule breach.
 * Produced by one TrafficRule for one RadarObservation.
 * */
public record Violation(
        String ruleName,
        String description,
        int fee
) {
}
