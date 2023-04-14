package SplatoonWeapons;

public class Shooter implements Weapon {
    /*
    * Things that still need to be added:
    * Hit tester
    * Range
    * Bullet hitbox?
    */
    public final String weaponName;
    public final int baseDamage;

    /**
     * Values are falloff starting frame, ending frame, and minimum damage.
     * Damage falloff is linear
     * Represented by values ReduceStartFrame, ReduceEndFrame, ValueMin in the original structure
     */
    private final int[] falloffStats;

    /**
     * Values are starting outer chance, maximum chance, change in chance per shot, and deviation angle.
     * Represented by values Stand_DegBiasMin, Stand_DegBiasMax, Stand_DegBiasKf, Stand_DegSwerve
     * When weapons don't have Stand_DegBiasMax specified, the default is 0.25 (?).
     */
    private final double[] shotDeviationStats;

    public Shooter() {
        this("Splattershot", 36);
    }

    public Shooter(String weaponName, int baseDamage) {
        this(weaponName, baseDamage,
                new int[]{8, 40, baseDamage / 2}, // falloff stats
                new double[]{0.01, 0.25, 0.01, 6.0});
    }

    public Shooter(String weaponName, int baseDamage, int[] falloffStats, double[] shotDeviationStats) {
        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.falloffStats = falloffStats;
        this.shotDeviationStats = shotDeviationStats;
    }

    /**
     * Calculate how much damage a shot should do with falloff.
     * Uses the falloffStats array for falloff parameters.
     * @param frames How many frames the shot has been in the air.
     * @return Falloff-adjusted damage.
     */
    public int calculateFalloff(int frames) {
        if (frames <= falloffStats[0]) {
            return getBaseDamage();
        } else if (frames > falloffStats[1]) {
            return falloffStats[2];
        }

        // calculate damage lost per frame
        double lostPerFrame = (getBaseDamage() - falloffStats[2]) / (double) (falloffStats[1] - falloffStats[0]);
        return (int) (getBaseDamage() - ((frames - falloffStats[0]) * lostPerFrame));
    }

    /**
     * Calculate the angle that the current shot should be fired at, due to shot deviation.
     * Uses the shotDeviationStats array for shot deviation parameters.
     * @param shots How many shots have been previously fired by the weapon.
     * @return The angle that the shot should be fired at.
     */
    public double calculateShotDeviation(int shots) {
        double shotDeviationChance = Math.min(shotDeviationStats[0] + (shotDeviationStats[2] * shots),
                shotDeviationStats[1]); // enforce the maximum
        if (Math.random() < shotDeviationChance) {
            // Shot deviation seems to be a random angle with an even distribution, but can't confirm
            return Math.random() * shotDeviationStats[3]; // pretty sure the angle is in one direction?
        } else return 0.0;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public String toString() {
        return String.format("%s shooter, dealing %d damage per shot", getWeaponName(), getBaseDamage());
    }
}
