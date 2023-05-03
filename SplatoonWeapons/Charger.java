package SplatoonWeapons;

public class Charger implements Weapon {
    public final String weaponName;
    public final int baseDamage;
    public final int minDamage;
    public final int maxDamage;
    public final double minRange;
    public final double maxRange;
    public final double fullRange;
    public final int framesToCharge;

    /**
     * Constructor for the Charger object.
     *
     * @param weaponName     Friendly name of the weapon.
     * @param baseDamage     Full charge damage of the weapon, from ValueFullCharge.
     * @param minDamage      Damage done by a minimum charge, from ValueMinCharge.
     * @param maxDamage      Maximum damage dealt by a non-full charge, from ValueMaxCharge.
     * @param fullRange      Range of a full-charge shot, from DistanceFullCharge.
     * @param minRange       Range of a minimum-charge shot, from DistanceMinCharge.
     * @param maxRange       Maximum range of a non-full charge, from DistanceMaxCharge.
     * @param framesToCharge Time (in frames) to reach full charge, from ChargeFrameFullCharge. Has a default value of
     *                       60 if not specified by the weapon data.
     */
    public Charger(String weaponName, int baseDamage, int minDamage, int maxDamage,
                   double fullRange, double minRange, double maxRange, int framesToCharge) {
        if (!(baseDamage >= 0)) {
            throw new IllegalArgumentException("Base damage must be at least 0");
        }
        if (!(minDamage >= 0)) {
            throw new IllegalArgumentException("Minimum damage must be at least 0");
        }
        if (!(maxDamage >= minDamage)) {
            throw new IllegalArgumentException("Maximum damage must be at least equal to minimum damage");
        }

        if (!(fullRange > 0)) {
            throw new IllegalArgumentException("Full charge range must be greater than 0");
        }
        if (!(minRange > 0)) {
            throw new IllegalArgumentException("Minimum range must be greater than 0");
        }
        if (!(maxRange >= minRange)) {
            throw new IllegalArgumentException("Maximum range must be at least equal to minimum range");
        }

        if (!(framesToCharge > 0)) {
            throw new IllegalArgumentException("Charging time must be greater than 0");
        }

        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.fullRange = fullRange;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.framesToCharge = framesToCharge;
    }

    /**
     * An interactive method that asks the user for parameters for a new object, then creates and returns it.
     *
     * @return A new Charger object with the desired attributes.
     */
    public static Charger createWeapon() {
        System.out.print("Would you like to use advanced mode? This will ask for many more options, but it gives you " +
                "more control over how the weapon will work. ");
        String response = Utils.nextLine().toLowerCase();
        boolean advanced = (response.equals("y") || response.equals("yes"));
        System.out.println(advanced ? "Using advanced mode." : "Using simple mode.");

        String weaponName;
        int baseDamage, minDamage, maxDamage, framesToCharge;
        double fullRange, minRange, maxRange;

        System.out.print("Name of the weapon? ");
        weaponName = Utils.nextLine();

        System.out.print("Full charge shot damage? ");
        baseDamage = Utils.getInt(0);
        System.out.print("Time to do a full charge (in frames)? ");
        framesToCharge = Utils.getInt(1);

        if (advanced) {
            System.out.print("Minimum charge damage? ");
            minDamage = Utils.getInt(0);
            System.out.print("Maximum (not full!) charge damage? ");
            maxDamage = Utils.getInt(minDamage);

            System.out.print("Range of a full charge? ");
            fullRange = Utils.getDouble(0);
            System.out.print("Range of a minimum charge? ");
            minRange = Utils.getDouble(0);
            System.out.print("Range of a maximum (not full!) charge? ");
            maxRange = Utils.getDouble(minRange);
        } else {
            minDamage = baseDamage / 4;
            maxDamage = baseDamage / 2;

            System.out.print("Range of a full charge (distance units)? ");
            fullRange = Utils.getDouble(0);
            maxRange = fullRange;
            minRange = fullRange / 2.666;
        }

        return new Charger(weaponName, baseDamage, minDamage, maxDamage, fullRange, minRange, maxRange, framesToCharge);
    }

    /**
     * Generates a String that explains all the stats of the current Weapon object.
     *
     * @return A String with weapon info.
     */
    public String getFullStats() {
        return String.format("""
                        %s charger
                        %.1f base damage, %.1f minimum charge damage, %.1f max charge damage
                        %.2f full range, %.2f minimum range, %.2f maximum range
                        %d frames to do a full charge""", weaponName, baseDamage / 10.0, minDamage / 10.0, maxDamage / 10.0,
                fullRange, minRange, maxRange, framesToCharge);
    }

    /**
     * Calculates the damage done by a single shot, based on the charge.
     *
     * @param chargeState The charge state of the shot, between 0 and 1 (inclusive).
     * @return Charge-adjusted damage.
     */
    public int calculateDamage(double chargeState) { // chargeState should be 0 to 1 and represents charge percentage
        if (!(chargeState < 1.005 && chargeState >= 0.0)) {
            throw new IllegalArgumentException("Charge state must be 0.0-1.0");
        }

        if (Math.abs(chargeState - 1.0) < 0.005) { // has to be done due to floating point precision
            return getBaseDamage();
        } else {
            int damageDifference = maxDamage - minDamage;
            return (int) (minDamage + (damageDifference * chargeState));
        }
    }

    /**
     * Calculates the range that a shot should, based on the charge.
     *
     * @param chargeState The charge state of the shot, between 0 and 1 (inclusive).
     * @return Charge-adjusted range.
     */
    public double calculateRange(double chargeState) {
        if (!(chargeState < 1.005 && chargeState >= 0.0)) {
            throw new IllegalArgumentException("Charge state must be 0.0-1.0");
        }

        if (Math.abs(chargeState - 1.0) < 0.005) {
            return fullRange;
        } else {
            double rangeDifference = maxRange - minRange;
            return (minRange + (rangeDifference * chargeState));
        }
    }

    /**
     * Calculate the damage dealt by a single shot to a target, based on where the target is.
     * Returns 0 if the shot doesn't hit.
     *
     * @param targetDistance Distance from the player to the target, in distance units.
     * @param targetXOffset  Left-right offset of the target, in distance units.
     * @param chargeState    The charge state of the shot, between 0 and 1 (inclusive).
     * @return Damage dealt by the shot. 0 if a miss.
     */
    public int calculateHit(double targetDistance, double targetXOffset, double chargeState) {
        if (!(targetDistance >= 0)) {
            throw new IllegalArgumentException("Target distance must be at least 0");
        }

        double targetSize = 0.7;
        double range = calculateRange(chargeState);
        if (range < targetDistance) {
            return 0;
        }
        // chargers don't have shot deviation, so if the x offset is more than half the target's size
        // in either direction it will miss
        if (((targetSize / 2.0) - Math.abs(targetXOffset)) < 0) {
            return 0;
        }

        return calculateDamage(chargeState);
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

        int fullCharges = time / framesToCharge;
        int totalDamage = 0;

        for (int i = 0; i < fullCharges; i++) {
            totalDamage += calculateHit(targetDistance, targetXOffset, 1.0);
        }

        if (time % framesToCharge != 0) { // there will be a partial charge
            double partialCharge = (time % framesToCharge) / (double) framesToCharge;
            totalDamage += calculateHit(targetDistance, targetXOffset, partialCharge);
        }

        return totalDamage;
    }

    @Override
    public double getBaseFireRate() {
        return framesToCharge / 60.0;
    }

    @Override
    public String toString() {
        return String.format("%s charger, dealing %.1f damage on a full charge and %.2f seconds to charge",
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
}
