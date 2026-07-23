import java.util.List;

/**
 * Represents all violations found on one observation.
 * Compute total fees and print the fine.
 */
public final class Fine {
    private final String plateNumber;
    private final List<Violation> violations;
    private final int totalFees;

    public Fine(String plateNumber, List<Violation> violations){
        this.plateNumber = plateNumber;
        this.violations = violations;
        this.totalFees = violations.stream()
                .mapToInt(Violation::fee)
                .sum();
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public int getTotalFees() {
        return totalFees;
    }

    public void print() {
        System.out.println("Traffic for car " + plateNumber);
        System.out.println("Total amount: " + getTotalFees() + " EGP");
        System.out.println("Violations:");
        for (Violation v : violations) {
            System.out.println("- " + v.description() + " : " + v.fee() + " EGP");
        }
    }
}
