package SplatoonWeapons;

public class Splatling extends Shooter {
    /* 
     * Time (in frames) that it takes for the Splatling to reach the first ring of its reticle while charging. (default of 50)
     */
    private final int chargeRing1;

    /* 
     * Time (in frames) that it takes for the Splatling to reach the second ring of its reticle while charging. (default of 75)
     */
    private final int chargeRing2;

    public Splatling(String weaponName, int baseDamage, int shotInterval, int[] falloffStats, double[] shotDeviationStats, double[] velocityStats, int chargeRing1, int chargeRing2) {
        super(weaponName,baseDamage,shotInterval,falloffStats,shotDeviationStats,velocityStats);
        if (!(chargeRing1 >= 0)) {
            throw new IllegalArgumentException("Charge Time for Ring 1 must be at least 0");
        }
        if (!(chargeRing2 >= 0)) {
            throw new IllegalArgumentException("Charge Time for Ring 2 must be at least 0");
        }
        if (!(chargeRing2 > chargeRing1)) {
            throw new IllegalArgumentException("Charge Time for Ring 2 must be greater than that of the Charge Time for Ring 1");
        }
        this.chargeRing1 = chargeRing1;
        this.chargeRing2 = chargeRing2;
    }

    
}