import java.util.List;

/**
 * Enforce that the seatbelt must be fastened, regardless of vehicle type.
 */
public class SeatbeltRule implements TrafficRule{
    private final int fee;

    public SeatbeltRule(int fee) {
        this.fee = fee;
    }

    @Override
    public List<Violation> evaluate(RadarObservation observation) {
        if (!observation.isSeatbeltFastened()){
            return List.of(new Violation(getName(), "Seatbelt not fastened", fee));
        }
        return List.of();
    }

    @Override
    public String getName() {
        return "SeatbeltRule";
    }
}
