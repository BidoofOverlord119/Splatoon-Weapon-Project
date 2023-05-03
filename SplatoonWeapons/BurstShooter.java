package SplatoonWeapons;

public class BurstShooter extends Shooter implements Weapon {
    public int burstSize;
    public int burstCooldown;

    /**
     * Constructor for the BurstShooter object.
     * All parameters other than burstSize and burstInterval are identical to Shooter.
     *
     * @param burstSize Amount of shots in a single burst. Always 3 shots with the default weapons.
     * @param burstCooldown Cooldown period between shot bursts, from TripleShotSpanFrame.
     */
    public BurstShooter(String weaponName, int baseDamage, int shotInterval, int falloffStartingFrame,
                        int falloffEndingFrame, int minimumDamage, double deviationMinOuterChance,
                        double deviationMaxOuterChance, double deviationChangePerShot, double deviationAngle,
                        double initialVelocity, int initialVelocityTime, double slowVelocity,
                        int burstSize, int burstCooldown) {
        super(weaponName, baseDamage, shotInterval, falloffStartingFrame, falloffEndingFrame, minimumDamage,
                deviationMinOuterChance, deviationMaxOuterChance, deviationChangePerShot, deviationAngle,
                initialVelocity, initialVelocityTime, slowVelocity);

        if (!(burstSize >= 1)) {
            throw new IndexOutOfBoundsException("Burst size must be at least 1");
        }
        if (!(burstCooldown >= 1)) {
            throw new IndexOutOfBoundsException("Burst interval must be at least 1");
        }

        this.burstSize = burstSize;
        this.burstCooldown = burstCooldown;
    }

    /**
     * An interactive method that asks the user for parameters for a new object, then creates and returns it.
     *
     * @return A new BurstShooter object with the desired attributes.
     */
    public static BurstShooter createWeapon() {
        System.out.print("Would you like to use advanced mode? This will ask for many more options, but it gives you " +
                "more control over how the weapon will work. ");
        String response = Utils.nextLine().toLowerCase();
        boolean advanced = (response.equals("y") || response.equals("yes"));
        System.out.println(advanced ? "Using advanced mode." : "Using simple mode.");

        String weaponName;
        int baseDamage, shotInterval, falloffStartingFrame, falloffEndingFrame, minimumDamage, initialVelocityTime,
                burstSize, burstCooldown;
        double deviationMinOuterChance, deviationMaxOuterChance, deviationChangePerShot, deviationAngle,
                initialVelocity, slowVelocity;

        System.out.print("Name of the weapon? ");
        weaponName = Utils.nextLine();

        System.out.print("Base damage of the weapon? ");
        baseDamage = Utils.getInt(0);
        System.out.print("Time between shots (in frames)? ");
        shotInterval = Utils.getInt(1);

        System.out.print("Amount of shots in each burst? ");
        burstSize = Utils.getInt(1);
        System.out.print("Amount of time in between each burst? ");
        burstCooldown = Utils.getInt(1);

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

        return new BurstShooter(weaponName, baseDamage, shotInterval, falloffStartingFrame, falloffEndingFrame,
                minimumDamage, deviationMinOuterChance, deviationMaxOuterChance, deviationChangePerShot, deviationAngle,
                initialVelocity, initialVelocityTime, slowVelocity, burstSize, burstCooldown);
    }

    /**
     * Generates a String that explains all the stats of the current Weapon object.
     *
     * @return A String with weapon info.
     */
    @Override
    public String getFullStats() {
        return String.format("""
                %s
                %d shots per burst, %d frames in between bursts""",
                super.getFullStats(), burstSize, burstCooldown);
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
        int numBursts = ((time - 1) / ((shotInterval * burstSize) + burstCooldown)) + 1;

        int shotNumber = 0;
        int damageDealt = 0;
        for (int burst = 0; burst < numBursts; burst++) {
            for (int i = 0; i < burstSize; i++) {
                damageDealt += calculateHit(targetDistance, targetXOffset, shotNumber, baseDamage, falloffStartingFrame,
                        falloffEndingFrame, minimumDamage, deviationMinOuterChance, deviationMaxOuterChance,
                        deviationChangePerShot, deviationAngle, initialVelocity, initialVelocityTime, slowVelocity);
                shotNumber++;
            }
        }

        return damageDealt;
    }

    @Override
    public double getBaseFireRate() {
        return (60 / (double) ((shotInterval * burstSize) + burstCooldown)) * burstSize;
    }

    @Override
    public String toString() {
        return String.format("%s burst shooter, dealing %.1f damage per shot and %.1f shots per second",
                getWeaponName(), getBaseDamage() / 10.0, getBaseFireRate());
    }
}
