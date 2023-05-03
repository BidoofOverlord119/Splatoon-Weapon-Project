package SplatoonWeapons;

public class Blaster implements Weapon {
    public final String weaponName;
    public final int baseDamage;
    public final double mainRange;
    public final int shotInterval;
    public final int firstShotDelay;
    public final int blastDamageNear;
    public final int blastDamageFar;
    public final double blastNearRadius;
    public final double blastFarRadius;

    /**
     * Constructor for the Blaster object.
     *
     * @param weaponName      Friendly name of the weapon.
     * @param baseDamage      Base damage of the weapon, from ValueMax.
     * @param mainRange       Range of the weapon. Calculated based on the velocity stats.
     *                        (SpawnSpeed * GoStraightToBrakeStateFrame) + (2 * GoStraightStateEndMaxSpeed)
     * @param shotInterval    Time (in frames) between shots, from RepeatFrame.
     * @param firstShotDelay  Time (in frames) before the first shot fires. From PreDelayFrame_HumanShot.
     * @param blastDamageNear Maximum blast damage when blast is close, from DistanceDamage[0].Damage.
     * @param blastDamageFar  Minimum blast damage when blast is far, from DistanceDamage[1].Damage.
     * @param blastNearRadius Radius in which the maximum blast damage will occur, from DistanceDamage[0].Distance.
     * @param blastFarRadius  Radius in which any blast damage will occur, from DistanceDamage[1].Distance.
     */
    public Blaster(String weaponName, int baseDamage, double mainRange, int shotInterval, int firstShotDelay,
                   int blastDamageNear, int blastDamageFar, double blastNearRadius, double blastFarRadius) {
        if (!(baseDamage >= 0)) {
            throw new IllegalArgumentException("Base damage must be at least 0");
        }
        if (!(shotInterval >= 1)) {
            throw new IllegalArgumentException("Shot interval must be at least 1");
        }

        if (!(mainRange > 0.0)) {
            throw new IllegalArgumentException("Main range must be greater than 0");
        }
        if (!(firstShotDelay >= 0)) {
            throw new IllegalArgumentException("First shot delay must be at least 0");
        }

        if (!(blastDamageFar >= 0)) {
            throw new IllegalArgumentException("Far blast damage must be at least 0");
        }
        if (!(blastDamageNear >= blastDamageFar)) {
            throw new IllegalArgumentException("Near blast damage must be greater than or equal to far blast damage");
        }

        if (!(blastNearRadius > 0)) {
            throw new IllegalArgumentException("Near blast radius must be greater than 0");
        }
        if (!(blastNearRadius < blastFarRadius)) {
            throw new IllegalArgumentException("Near blast radius must be less than far blast radius");
        }

        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.mainRange = mainRange;
        this.shotInterval = shotInterval;
        this.firstShotDelay = firstShotDelay;
        this.blastDamageNear = blastDamageNear;
        this.blastDamageFar = blastDamageFar;
        this.blastNearRadius = blastNearRadius;
        this.blastFarRadius = blastFarRadius;
    }

    /**
     * An interactive method that asks the user for parameters for a new object, then creates and returns it.
     *
     * @return A new Blaster object with the desired attributes.
     */
    public static Blaster createWeapon() {
        System.out.print("Would you like to use advanced mode? This will ask for many more options, but it gives you " +
                "more control over how the weapon will work. ");
        String response = Utils.nextLine().toLowerCase();
        boolean advanced = (response.equals("y") || response.equals("yes"));
        System.out.println(advanced ? "Using advanced mode." : "Using simple mode.");

        String weaponName;
        int baseDamage, shotInterval, firstShotDelay, blastDamageNear, blastDamageFar;
        double mainRange, blastNearRadius, blastFarRadius;

        System.out.print("Name of the weapon? ");
        weaponName = Utils.nextLine();

        System.out.print("Base damage of the weapon? ");
        baseDamage = Utils.getInt(0);
        System.out.print("Time between shots (in frames)? ");
        shotInterval = Utils.getInt(1);

        System.out.print("Main range of the weapon (distance units)? ");
        mainRange = Utils.getDouble(0);

        if (advanced) {
            System.out.print("Delay before firing the first shot? ");
            firstShotDelay = Utils.getInt(0);

            System.out.print("Near indirect hit damage? ");
            blastDamageNear = Utils.getInt(0);
            System.out.print("Far indirect hit damage? ");
            blastDamageFar = Utils.getInt(blastDamageNear);

            System.out.print("Maximum radius for near blast damage? ");
            blastNearRadius = Utils.getDouble(0);
            System.out.print("Maximum radius for far blast damage? ");
            blastFarRadius = Utils.getDouble(blastNearRadius);
        } else {
            firstShotDelay = 10;
            blastDamageNear = (int) (baseDamage * 0.56);
            blastDamageFar = (int) (baseDamage * 0.4);
            blastNearRadius = 0.94;
            blastFarRadius = 3.3;
        }

        return new Blaster(weaponName, baseDamage, mainRange, shotInterval, firstShotDelay, blastDamageNear,
                blastDamageFar, blastNearRadius, blastFarRadius);
    }

    /**
     * Generates a String that explains all the stats of the current Weapon object.
     *
     * @return A String with weapon info.
     */
    public String getFullStats() {
        return String.format("""
                        %s blaster
                        %.1f base damage, %.1f near blast damage, %.1f far blast damage
                        Shoots every %d frames, %d frames delay before first shot
                        %.2f standard range, %.2f near blast radius, %.2f far blast radius""",
                weaponName, baseDamage / 10.0, blastDamageNear / 10.0, blastDamageFar / 10.0,
                shotInterval, firstShotDelay, mainRange, blastNearRadius, blastFarRadius);
    }

    /**
     * Calculates the blast damage done by a non-direct hit.
     *
     * @param distanceToTarget Distance between the center of the explosion and the target, in distance units.
     * @return Splash damage.
     */
    public int calculateBlastDamage(double distanceToTarget) {
        if (!(distanceToTarget >= 0)) {
            throw new IllegalArgumentException("Distance to target cannot be negative");
        }

        if (distanceToTarget <= blastNearRadius) {
            return blastDamageNear;
        } else if (distanceToTarget >= blastFarRadius) {
            return 0;
        } else {
            // damage scales linearly from blastDamageNear to blastDamageFar
            int damageDifference = blastDamageNear - blastDamageFar;
            double rangeDifference = blastFarRadius - blastNearRadius;
            return (int) ((damageDifference * ((distanceToTarget - blastNearRadius) / rangeDifference)) + blastDamageFar);
        }
    }

    /**
     * Calculates damage dealt by a single shot to a target, based on where the target is.
     * Returns 0 if the shot doesn't hit.
     *
     * @param targetDistance Distance from the player to the target, in distance units.
     * @param targetXOffset  Left-right offset of the target, in distance units.
     * @return Damage dealt by the shot. 0 if a miss.
     */
    public int calculateHit(double targetDistance, double targetXOffset) {
        if (!(targetDistance >= 0)) {
            throw new IllegalArgumentException("Target distance must be at least 0");
        }

        // blasters don't have shot deviation, so if the x offset is more than half the target's size
        // in either direction it will cause an indirect hit or a miss
        if (mainRange < targetDistance ||
                (((TARGET_SIZE / 2.0) - Math.abs(targetXOffset)) < 0)) { // shot misses the target
            // shot and target form a right triangle
            double distanceToTarget = Math.sqrt(Math.pow(mainRange - targetDistance, 2) + Math.pow(targetXOffset, 2));
            return calculateBlastDamage(distanceToTarget);
        }

        return getBaseDamage();
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
        int numShots = ((time - firstShotDelay - 1) / shotInterval) + 1; //same as shooter but with delay for first shot
        for (int i = 0; i < numShots; i++) {
            damageDealt += calculateHit(targetDistance, targetXOffset);
        }
        return damageDealt;
    }

    @Override
    public String toString() {
        return String.format("%s, dealing %.1f damage per shot and %.1f shots per second",
                getWeaponName(), getBaseDamage() / 10.0, getBaseFireRate());
    }

    @Override
    public String getWeaponName() {
        return weaponName;
    }

    @Override
    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public double getBaseFireRate() {
        return 60 / (double) shotInterval;
    }
}
