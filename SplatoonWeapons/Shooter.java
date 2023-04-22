package SplatoonWeapons;

public class Shooter extends BaseShooter implements Weapon {
    private final String weaponName;
    private final int baseDamage;
    private final int shotInterval;
    private final int falloffStartingFrame;
    private final int falloffEndingFrame;
    private final int minimumDamage;
    private final double deviationMinOuterChance;
    private final double deviationMaxOuterChance;
    private final double deviationChangePerShot;
    private final double deviationAngle;
    private final double initialVelocity;
    private final int initialVelocityTime;
    private final double slowVelocity;

    /**
     * Constructor for the Shooter object.
     *
     * @param weaponName              Friendly name of the weapon.
     * @param baseDamage              Base damage of the weapon, from ValueMax.
     * @param shotInterval            Time (in frames) between shots, from RepeatFrame. Has a default value of 6 if
     *                                not specified by the weapon data.
     * @param falloffStartingFrame    Frame at which falloff starts taking effect, from ReduceStartFrame.
     * @param falloffEndingFrame      Frame at which falloff stops taking effect, from ReduceEndFrame.
     * @param minimumDamage           Minimum damage due to falloff, from ValueMin.
     * @param deviationMinOuterChance Minimum chance that the shot goes towards the outer reticle,
     *                                from Stand_DegBiasMin.
     * @param deviationMaxOuterChance Maximum chance that the shot goes towards the outer reticle,
     *                                from Stand_DegBiasMax. Has a default value of 0.25 if not specified by the
     *                                weapon data.
     * @param deviationChangePerShot  The amount that the deviation chance goes up by per shot, from Stand_DegBiasKf.
     * @param deviationAngle          The maximum angle that the shot can deviate. Dictates the size of the
     *                                outer reticle. From Stand_DegSwerve.
     * @param initialVelocity         Initial velocity of the shot, in distance units per frame. From SpawnSpeed.
     * @param initialVelocityTime     Amount of frames that the shot will travel at the initial velocity,
     *                                from GoStraightToBrakeStateFrame.
     * @param slowVelocity            The slower velocity that the shot will slow down to after initialVelocityTime.
     *                                From GoStraightStateEndMaxSpeed.
     */
    public Shooter(String weaponName, int baseDamage, int shotInterval, int falloffStartingFrame,
                   int falloffEndingFrame, int minimumDamage, double deviationMinOuterChance,
                   double deviationMaxOuterChance, double deviationChangePerShot, double deviationAngle,
                   double initialVelocity, int initialVelocityTime, double slowVelocity) {
        // 13 different parameters results in a multitude of different possible errors
        if (!(baseDamage >= 0)) {
            throw new IllegalArgumentException("Base damage must be at least 0");
        }
        if (!(shotInterval >= 1)) {
            throw new IllegalArgumentException("Shot interval must be at least 1");
        }

        if (!(falloffStartingFrame >= 0)) {
            throw new IllegalArgumentException("Falloff starting frame must be at least 0");
        }
        if (!(falloffEndingFrame >= falloffStartingFrame)) {
            throw new IllegalArgumentException("Falloff ending frame must be at least equal to ending frame");
        }
        if (!(minimumDamage >= 0)) {
            throw new IllegalArgumentException("Falloff minimum damage must be at least 0");
        }

        if (!(deviationMinOuterChance >= 0.0)) {
            throw new IllegalArgumentException("Shot deviation minimum chance must be at least 0");
        }
        if (!(deviationMinOuterChance <= 1.0)) {
            throw new IllegalArgumentException("Shot deviation minimum chance must be at most 1.0");
        }
        if (!(deviationMaxOuterChance >= deviationMinOuterChance)) {
            throw new IllegalArgumentException("Shot deviation maximum chance must be at least equal to starting chance");
        }
        if (!(deviationMaxOuterChance <= 1.0)) {
            throw new IllegalArgumentException("Shot deviation maximum chance must be at most 1.0");
        }
        if (!(deviationChangePerShot >= 0)) {
            throw new IllegalArgumentException("Shot deviation chance change per shot must be at least 0");
        }
        if (!(deviationAngle >= 0)) {
            throw new IllegalArgumentException("Shot deviation angle must be at least 0");
        }

        if (!(initialVelocity > 0)) {
            throw new IllegalArgumentException("Velocity starting value must be greater than 0");
        }
        if (!(initialVelocityTime > 0)) {
            throw new IllegalArgumentException("Velocity starting value time must be greater than 0");
        }
        if (!(slowVelocity > 0)) {
            throw new IllegalArgumentException("Velocity slow speed must be greater than 0");
        }

        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.shotInterval = shotInterval;
        this.falloffStartingFrame = falloffStartingFrame;
        this.falloffEndingFrame = falloffEndingFrame;
        this.minimumDamage = minimumDamage;
        this.deviationMinOuterChance = deviationMinOuterChance;
        this.deviationMaxOuterChance = deviationMaxOuterChance;
        this.deviationChangePerShot = deviationChangePerShot;
        this.deviationAngle = deviationAngle;
        this.initialVelocity = initialVelocity;
        this.initialVelocityTime = initialVelocityTime;
        this.slowVelocity = slowVelocity;
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
        int numShots = ((time - 1) / shotInterval) + 1; // always shoots on the first frame
        for (int i = 0; i < numShots; i++) {
            damageDealt += calculateHit(targetDistance, targetXOffset, i, baseDamage, falloffStartingFrame,
                    falloffEndingFrame, minimumDamage, deviationMinOuterChance, deviationMaxOuterChance,
                    deviationChangePerShot, deviationAngle, initialVelocity, initialVelocityTime, slowVelocity);
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


    @Override
    public double getBaseFireRate() {
        return 60 / (double) shotInterval;
    }

    @Override
    public String toString() {
        return String.format("%s shooter, dealing %.1f damage per shot and %.1f shots per second",
                getWeaponName(), getBaseDamage() / 10.0, getBaseFireRate());
    }
}
