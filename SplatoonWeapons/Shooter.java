package SplatoonWeapons;

public class Shooter implements Weapon {
    /*
    * Things that still need to be added:
    * Multiple shots
    */
    private final String weaponName;
    private final int baseDamage;

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

    /**
     * Values are initial velocity, initial velocity time (in frames), and slow velocity
     * Represented by values SpawnSpeed, GoStraightToBrakeStateFrame, GoStraightStateEndMaxSpeed
     */
    private final double[] velocityStats;

    public Shooter() {
        this("Splattershot", 36);
    }

    public Shooter(String weaponName, int baseDamage) {
        this(weaponName, baseDamage,
                new int[]{8, 40, baseDamage / 2}, // falloff stats of Splattershot
                new double[]{0.01, 0.25, 0.01, 6.0},  // deviation stats of Splattershot
                new double[]{2.2, 4, 1.4495}); // velocity stats of Splattershot
    }

    public Shooter(String weaponName, int baseDamage, int[] falloffStats, double[] shotDeviationStats,
                   double[] velocityStats) {
        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.falloffStats = falloffStats;
        this.shotDeviationStats = shotDeviationStats;
        this.velocityStats = velocityStats;
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
            // Return a random number between -shotDeviationStats[3] and +shotDeviationStats[3]
            return ((Math.random() - 0.5) * 2) * shotDeviationStats[3];
        } else return 0.0;
    }

    /**
     * Calculate how far a shot should have travelled based on how many frames it has been in the air.
     * @param frames How many frames the shot has been in the air.
     * @return How far the shot has travelled, in distance units.
     */
    public double calculateDistance(int frames) {
        int firstPhaseFrames = Math.min(frames, (int) velocityStats[1]);
        int secondPhaseFrames = Math.max(frames - (int) velocityStats[1], 0);
        double secondPhaseMultiplier = 2 * (1 - (1/Math.pow(2, secondPhaseFrames)));
        return (velocityStats[0] * firstPhaseFrames) + (velocityStats[2] * secondPhaseMultiplier);
    }

    /**
     * Calculates the maximum range of the weapon.
     * @return Maximum weapon range in distance units.
     */
    public double calculateRange() {
        return (velocityStats[0] * velocityStats[1]) + (velocityStats[2] * 2);
    }

    public double calculateHit(double targetDistance, double targetXOffset, int previousShots) {
        double targetSize = 0.7; // size of target in distance units
        if (targetDistance > calculateRange()) {
            return -1;
        }
        // The shot's angle must be between these two to hit
        double targetAngleLeft = Math.toDegrees(Math.atan((targetXOffset - 0.5 * targetSize) / targetDistance));
        double targetAngleRight = Math.toDegrees(Math.atan((targetXOffset + 0.5 * targetSize) / targetDistance));
        double shotDeviationAngle = calculateShotDeviation(previousShots);
        if (shotDeviationAngle <= targetAngleLeft || shotDeviationAngle >= targetAngleRight) {
            return -1;
        }
        for (int frames = 0; frames < 60; frames++) {
            // todo: calculate when the shot should stop based on velocity
            // doesn't really seem to matter, but should be done anyways?
            // 60 frames should be more than enough
            double shotDistance = calculateDistance(frames);
            if (shotDistance >= targetDistance) {
                return calculateFalloff(frames);
            }
        }
        return -1;
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
