package SplatoonWeapons;

public class Shooter extends BaseShooter implements Weapon {
    public final String weaponName;
    public final int baseDamage;
    public final int shotInterval;
    public final int falloffStartingFrame;
    public final int falloffEndingFrame;
    public final int minimumDamage;
    public final double deviationMinOuterChance;
    public final double deviationMaxOuterChance;
    public final double deviationChangePerShot;
    public final double deviationAngle;
    public final double initialVelocity;
    public final int initialVelocityTime;
    public final double slowVelocity;

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
     * An interactive method that asks the user for parameters for a new object, then creates and returns it.
     *
     * @return A new Shooter object with the desired attributes.
     */
    public static Shooter createWeapon() {
        System.out.print("Would you like to use advanced mode? This will ask for many more options, but it gives you " +
                "more control over how the weapon will work. ");
        String response = Utils.nextLine().toLowerCase();
        boolean advanced = (response.equals("y") || response.equals("yes"));
        System.out.println(advanced ? "Using advanced mode." : "Using simple mode.");

        String weaponName;
        int baseDamage, shotInterval, falloffStartingFrame, falloffEndingFrame, minimumDamage, initialVelocityTime;
        double deviationMinOuterChance, deviationMaxOuterChance, deviationChangePerShot, deviationAngle,
                initialVelocity, slowVelocity;

        System.out.print("Name of the weapon? ");
        weaponName = Utils.nextLine();

        System.out.print("Base damage of the weapon? ");
        baseDamage = Utils.getInt(0);
        System.out.print("Time between shots (in frames)? ");
        shotInterval = Utils.getInt(1);

        if (advanced) {
            System.out.print("Damage falloff starting frame? ");
            falloffStartingFrame = Utils.getInt(0);
            System.out.print("Damage falloff ending frame? ");
            falloffEndingFrame = Utils.getInt(falloffStartingFrame);
            System.out.print("Minimum damage after falloff? ");
            minimumDamage = Utils.getInt(0, baseDamage);

            System.out.print("Minimum chance for shot to deviate? ");
            deviationMinOuterChance = Utils.getDouble(0, 1);
            System.out.print("Maximum chance for shot to deviate? ");
            deviationMaxOuterChance = Utils.getDouble(deviationMinOuterChance, 1);
            System.out.print("Increase in deviation chance per shot? ");
            deviationChangePerShot = Utils.getDouble(0, 1);
            System.out.print("Maximum angle for shot deviation? ");
            deviationAngle = Utils.getDouble(0);

            System.out.print("Initial shot velocity (units/frame)? ");
            initialVelocity = Utils.getDouble(0);
            System.out.print("Frames spent at initial velocity? ");
            initialVelocityTime = Utils.getInt(0);
            System.out.print("Secondary (slow) velocity? ");
            slowVelocity = Utils.getDouble(0, initialVelocity);
        } else {
            falloffStartingFrame = 8;
            falloffEndingFrame = 24;
            minimumDamage = baseDamage / 2;

            deviationMinOuterChance = 0.01;
            deviationMaxOuterChance = 0.25;
            deviationChangePerShot = 0.01;
            deviationAngle = 6;

            System.out.print("Maximum range (distance units)? ");
            double desiredRange = Utils.getDouble(0);

            initialVelocity = (desiredRange * 0.75) / 4.0;
            initialVelocityTime = 4;
            slowVelocity = desiredRange * 0.25;
        }

        return new Shooter(weaponName, baseDamage, shotInterval, falloffStartingFrame, falloffEndingFrame,
                minimumDamage, deviationMinOuterChance, deviationMaxOuterChance, deviationChangePerShot, deviationAngle,
                initialVelocity, initialVelocityTime, slowVelocity);
    }

    /**
     * Generates a String that explains all the stats of the current Weapon object.
     *
     * @return A String with weapon info.
     */
    public String getFullStats() {
        return String.format("""
                        %s shooter
                        %.1f base damage, %.1f minimum damage, shoots every %d frames
                        Falloff starts on frame %d and ends on frame %d
                        %.1f%% minimum shot deviation chance, %.1f%% maximum chance
                        %.1f%% increase in chance per shot, %.1f degree shot deviation angle
                        Initial velocity of %.2f for %d frames, %.2f slow velocity afterwards""",
                weaponName, baseDamage / 10.0, minimumDamage / 10.0, shotInterval, falloffStartingFrame,
                falloffEndingFrame, deviationMinOuterChance * 100, deviationMaxOuterChance * 100,
                deviationChangePerShot * 100, deviationAngle, initialVelocity, initialVelocityTime, slowVelocity
        );
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
        if (!(time >= 0)) {
            throw new IllegalArgumentException("Time cannot be negative");
        }

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
