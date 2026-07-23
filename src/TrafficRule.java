import java.util.Optional;

/**
 * Represents a contract that every traffic rule must satisfy
 * New Rules can be created by implementing this interface
 * */
interface TrafficRule {
    Optional<Violation> evaluate(RadarObservation observation);

    String getName();
}
