package SplatoonWeapons;

public class Shooter implements Weapon {
    private final String weaponName;

    /**
     * Base damage of the weapon, from ValueMax
     */
    private final int baseDamage;

    /**
     * Time (in frames) between shots, from RepeatFrame (default of 6)
     */
    private final int shotInterval;

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

    public Shooter(String weaponName, int baseDamage, int shotInterval, int[] falloffStats,
                   double[] shotDeviationStats, double[] velocityStats) {
        // 13 different parameters results in a multitude of different possible errors
        if (!(baseDamage >= 0)) {
            throw new IllegalArgumentException("Base damage must be at least 0");
        }
        if (!(shotInterval >= 1)) {
            throw new IllegalArgumentException("Shot interval must be at least 1");
        }

        if (falloffStats.length != 3) {
            throw new IllegalArgumentException("Falloff stats array length must be exactly 3");
        }
        if (!(falloffStats[0] >= 0)) {
            throw new IllegalArgumentException("Falloff starting frame must be at least 0");
        }
        if (!(falloffStats[1] >= falloffStats[0])) {
            throw new IllegalArgumentException("Falloff ending frame must be at least equal to ending frame");
        }
        if (!(falloffStats[2] >= 0)) {
            throw new IllegalArgumentException("Falloff minimum damage must be at least 0");
        }

        if (shotDeviationStats.length != 4) {
            throw new IllegalArgumentException("Shot deviation stats array length must be exactly 4");
        }
        if (!(shotDeviationStats[0] >= 0.0)) {
            throw new IllegalArgumentException("Shot deviation minimum chance must be at least 0");
        }
        if (!(shotDeviationStats[0] <= 1.0)) {
            throw new IllegalArgumentException("Shot deviation minimum chance must be at most 1.0");
        }
        if (!(shotDeviationStats[1] >= shotDeviationStats[0])) {
            throw new IllegalArgumentException("Shot deviation maximum chance must be at least equal to starting chance");
        }
        if (!(shotDeviationStats[1] <= 1.0)) {
            throw new IllegalArgumentException("Shot deviation maximum chance must be at most 1.0");
        }
        if (!(shotDeviationStats[2] >= 0)) {
            throw new IllegalArgumentException("Shot deviation chance change per shot must be at least 0");
        }
        if (!(shotDeviationStats[3] >= 0)) {
            throw new IllegalArgumentException("Shot deviation angle must be at least 0");
        }

        if (velocityStats.length != 3) {
            throw new IllegalArgumentException("Velocity stats array length must be exactly 4");
        }
        if (!(velocityStats[0] > 0)) {
            throw new IllegalArgumentException("Velocity starting value must be greater than 0");
        }
        if (!((velocityStats[1] % 1) == 0)) {
            throw new IllegalArgumentException("Velocity starting value time must be a whole number");
        }
        if (!(velocityStats[2] > 0)) {
            throw new IllegalArgumentException("Velocity slow speed must be greater than 0");
        }

        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.shotInterval = shotInterval;
        this.falloffStats = falloffStats;
        this.shotDeviationStats = shotDeviationStats;
        this.velocityStats = velocityStats;
    }

    /**
     * Calculate how much damage a shot should do with falloff.
     * Uses the falloffStats array for falloff parameters.
     *
     * @param frames How many frames the shot has been in the air.
     * @return Falloff-adjusted damage.
     */
    public int calculateFalloff(int frames) {
        if (!(frames >= 0)) {
            throw new IllegalArgumentException("Frames must be at least 0");
        }

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
     *
     * @param previousShots How many shots have been previously fired by the weapon.
     * @return The angle that the shot should be fired at.
     */
    public double calculateShotDeviation(int previousShots) {
        if (!(previousShots >= 0)) {
            throw new IllegalArgumentException("Previous shots must be at least 0");
        }

        double shotDeviationChance = Math.min(shotDeviationStats[0] + (shotDeviationStats[2] * previousShots),
                shotDeviationStats[1]); // enforce the maximum
        if (Math.random() < shotDeviationChance) {
            // Shot deviation seems to be a random angle with an even distribution, but can't confirm
            // Return a random number between -shotDeviationStats[3] and +shotDeviationStats[3]
            return ((Math.random() - 0.5) * 2) * shotDeviationStats[3];
        } else return 0.0;
    }

    /**
     * Calculate how far a shot should have travelled based on how many frames it has been in the air.
     *
     * @param frames How many frames the shot has been in the air.
     * @return How far the shot has travelled, in distance units.
     */
    public double calculateDistance(int frames) {
        if (!(frames >= 0)) {
            throw new IllegalArgumentException("Frames must be at least 0");
        }

        int firstPhaseFrames = Math.min(frames, (int) velocityStats[1]);
        int secondPhaseFrames = Math.max(frames - (int) velocityStats[1], 0);
        // second phase distance seems to max out at 2*velocityStats[2] units
        // We approximated that it follows the infinite series 1 + 1/2 + 1/4 + 1/8 ....
        double secondPhaseMultiplier = 2 * (1 - (1 / Math.pow(2, secondPhaseFrames)));
        return (velocityStats[0] * firstPhaseFrames) + (velocityStats[2] * secondPhaseMultiplier);
    }

    /**
     * Calculates the maximum range of the weapon.
     *
     * @return Maximum weapon range in distance units.
     */
    public double calculateRange() {
        return (velocityStats[0] * velocityStats[1]) + (velocityStats[2] * 2);
    }

    /**
     * Calculates damage dealt by a single shot to a target, based on where the target is.
     * Returns -1 if the shot doesn't hit.
     *
     * @param targetDistance Distance from the player to the target, in distance units.
     * @param targetXOffset  Left-right offset of the target, in distance units.
     * @param previousShots  How many shots have been fired by the weapon already, for shot deviation.
     * @return Damage dealt by the shot. -1 if a miss.
     */
    public int calculateHit(double targetDistance, double targetXOffset, int previousShots) {
        if (!(targetDistance >= 0)) {
            throw new IllegalArgumentException("Target distance must be at least 0");
        }
        if (!(previousShots >= 0)) {
            throw new IllegalArgumentException("Previous shots must be at least 0");
        }

        double targetSize = 0.7; // (horizontal) size of target in distance units
        if (targetDistance > calculateRange()) {
            return -1;
        }
        // The shot's angle must be between these two to hit
        // Math.atan is in radians, needs to be converted to degrees
        double targetAngleLeft = Math.toDegrees(Math.atan((targetXOffset - (0.5 * targetSize)) / targetDistance));
        double targetAngleRight = Math.toDegrees(Math.atan((targetXOffset + (0.5 * targetSize)) / targetDistance));
        double shotDeviationAngle = calculateShotDeviation(previousShots);
        if (shotDeviationAngle <= targetAngleLeft || shotDeviationAngle >= targetAngleRight) {
            return -1;
        }
        for (int frames = 0; frames < 60; frames++) {
            // todo: calculate when the shot should stop based on velocity
            // doesn't really seem to matter as this loop should always return at some value
            // 60 frames should be more than enough
            double shotDistance = calculateDistance(frames);
            if (shotDistance >= targetDistance) {
                return calculateFalloff(frames);
            }
        }
        return -1;
    }

    /**
     * Calculates damage dealt by the weapon to a target over a certain amount of time.
     *
     * @param targetDistance Distance from the player to the target, in distance units.
     * @param targetXOffset  Left-right offset of the target, in distance units.
     * @param time           How long to shoot for, in frames.
     * @return Total damage dealt.
     */
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

    @Override
    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public String getWeaponName() {
        return weaponName;
    }

    public int getShotInterval() {
        return shotInterval;
    }

    @Override
    public double getBaseFireRate() {
        return 60 / (double) getShotInterval();
    }

    @Override
    public String toString() {
        return String.format("%s shooter, dealing %.1f damage per shot and %.1f shots per second",
                getWeaponName(), getBaseDamage() / 10.0, getBaseFireRate());
    }
}
