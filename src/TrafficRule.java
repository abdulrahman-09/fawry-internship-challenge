import java.util.List;
/**
 * Represents a contract that every traffic rule must satisfy
 * New Rules can be created by implementing this interface
 * */
interface TrafficRule {
    List<Violation> evaluate(RadarObservation observation);

    String getName();
}
