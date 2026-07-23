import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
            List<Violation> found = rule.evaluate(observation);
            for (Violation v : found){
                violationCounts.merge(v.ruleName(), 1, Integer::sum);
            }
            collected.addAll(found);
        }

        if (collected.isEmpty()){
            return null;
        }
        Fine fine = new Fine(observation.plateNumber(), collected);
        issuedFines.add(fine);
        return fine;
    }

    public Map<String, Integer> getAllPossibleFines() {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Fine fine : issuedFines) {
            result.put(fine.getPlateNumber(), fine.getTotalFees());
        }
        return result;
    }

    public Map<String, Integer> getViolationCounts() {
        return new LinkedHashMap<>(violationCounts);
    }
}
