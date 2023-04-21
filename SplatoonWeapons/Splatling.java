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
        this.chargeRing1 = chargeRing1;
        this.chargeRing2 = chargeRing2;
    }

    @Override
    public int calculateDamageOverTime(double targetDistance, double targetXOffset, int time) {
        if (time == 0) return 0;
        int damageDealt = 0;
        int numShots = ((time - 1) / getShotInterval()) + 1; // always shoots on the first frame
        for (int i = 0; i < numShots; i++) {
            int currentDamage = calculateHit(targetDistance, targetXOffset, i);
            if (currentDamage != -1) {
                damageDealt += currentDamage;
            }
        }
        return damageDealt;
    }
}