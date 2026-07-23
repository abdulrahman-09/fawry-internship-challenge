import java.util.Optional;

/**
 * Enforces a maximum speed for one specific vehicle type.
 * Each vehicle gets its own rule instance.
 */
public class SpeedLimitRule implements TrafficRule{
    private final VehicleType vehicleType;
    private final int speedLimit;
    private final int fee;

    public SpeedLimitRule(VehicleType vehicleType, int speedLimit, int fee){
        this.vehicleType = vehicleType;
        this.speedLimit = speedLimit;
        this.fee = fee;
    }

    @Override
    public Optional<Violation> evaluate(RadarObservation observation) {
        if (observation.vehicleType().equals(vehicleType) && observation.speed() > speedLimit){
            return Optional.of(new Violation(
                    getName(),
                    "Speed of: " + observation.speed() + " exceeded max allowed: " + speedLimit,
                    fee
            ));
        }
        return Optional.empty();
    }

    @Override
    public String getName() {
        return "SpeedLimitRule (" + vehicleType + ")";
    }
}
