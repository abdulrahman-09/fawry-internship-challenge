import java.util.List;
import java.util.Optional;

/**
 * Enforce that the seatbelt must be fastened, regardless of vehicle type.
 */
public class SeatbeltRule implements TrafficRule{
    private final int fee;

    public SeatbeltRule(int fee) {
        this.fee = fee;
    }

    @Override
    public Optional<Violation> evaluate(RadarObservation observation) {
        if (!observation.isSeatbeltFastened()){
            return Optional.of(new Violation(getName(), "Seatbelt not fastened", fee));
        }
        return Optional.empty();
    }

    @Override
    public String getName() {
        return "SeatbeltRule";
    }
}
