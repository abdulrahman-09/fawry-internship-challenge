import java.util.*;

/**
 * Represents the core traffic radar processing engine.
 * Receives observations reported by the physical radar,
 * and runs every registered traffic rule against each one.
 */
public class QuantumRadar {
    private final List<TrafficRule> rules;
    private final List<Fine> issuedFines = new ArrayList<>();
    private final Map<String, Integer> violationCounts = new LinkedHashMap<>();

    public QuantumRadar(List<TrafficRule> rules) {
        this.rules = new ArrayList<>(rules);
    }

    public void addRule(TrafficRule rule) {
        rules.add(rule);
    }

    public Fine process(RadarObservation observation){
        List<Violation> collected = new ArrayList<>();
        for (TrafficRule rule : rules){
            Optional<Violation> found = rule.evaluate(observation);
            found.ifPresent(v -> {
                violationCounts.merge(v.ruleName(), 1, Integer::sum);
                collected.add(v);
            });
        }

        if (collected.isEmpty()){
            return null;
        }
        Fine fine = new Fine(observation.plateNumber(), collected);
        issuedFines.add(fine);
        return fine;
    }

    public Map<String, Integer> getTotalFinesByPlate() {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Fine fine : issuedFines) {
            result.merge(fine.getPlateNumber(), fine.getTotalFees(), Integer::sum);
        }
        return result;
    }

    public Map<String, Integer> getViolationCounts() {
        return new LinkedHashMap<>(violationCounts);
    }
}
