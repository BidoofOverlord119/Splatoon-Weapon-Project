package SplatoonWeapons;
import java.util.Dictionary;
import java.util.Hashtable;

public class Blaster implements Weapon {
    /* 
     * The name given to the weapon.
    */
    private final String weaponName;

    /* 
     * The damage dealt by the projectile on a direct impact.
    */
    private final int baseDamage;
    
    /* 
     * The distance the projectile travels.
    */
    private final double mainRange;

    /* 
     * A Dictionary for storing the radius and damage dealt in that radius by the blast caused by the projectile.
    */
    private final Dictionary<Double, Integer> damageRanges;
    
    /* 
     * Fire Rate variable
    */
    private final int shotInterval;

    public Blaster(String weaponName, int baseDamage, int blastDamage1, int blastDamage2,
                   double mainRange, double blastRange1, double blastRange2, int shotInterval) {
        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.mainRange = mainRange;
        this.shotInterval = shotInterval;
        this.damageRanges =  new Hashtable<>();
        double rangeHelper = blastRange1;
        this.damageRanges.put(rangeHelper, blastDamage1);
        rangeHelper = blastRange2;
        this.damageRanges.put(rangeHelper, blastDamage2);
    }

    /* public int calculateHit(double targetDistance, double targetXOffset, int previousShots, int baseDamage,
                            int falloffStartingFrame, int falloffEndingFrame, int minimumDamage,
                            double deviationMinOuterChance, double deviationMaxOuterChance,
                            double deviationChangePerShot, double deviationAngle, double initialVelocity,
                            int initialVelocityTime, double slowVelocity) {
        if (!(targetDistance >= 0)) {
            throw new IllegalArgumentException("Target distance must be at least 0");
        }
        if (!(previousShots >= 0)) {
            throw new IllegalArgumentException("Previous shots must be at least 0");
        }

        double targetSize = 0.7; // (horizontal) size of target in distance units
        if (targetDistance > calculateRange(initialVelocity, initialVelocityTime, slowVelocity)) {
            return 0;
        }
        // The shot's angle must be between these two to hit
        // Math.atan is in radians, needs to be converted to degrees
        double targetAngleLeft = Math.toDegrees(Math.atan((targetXOffset - (0.5 * targetSize)) / targetDistance));
        double targetAngleRight = Math.toDegrees(Math.atan((targetXOffset + (0.5 * targetSize)) / targetDistance));
        double shotDeviationAngle = calculateShotDeviation(previousShots, deviationMinOuterChance,
                deviationMaxOuterChance, deviationChangePerShot, deviationAngle);
        if (shotDeviationAngle <= targetAngleLeft || shotDeviationAngle >= targetAngleRight) {
            return 0;
        }
        for (int frames = 0; frames < 60; frames++) {
            // todo: calculate when the shot should stop based on velocity
            // doesn't really seem to matter as this loop should always return at some value
            // 60 frames should be more than enough
            double shotDistance = calculateDistance(frames, initialVelocity, initialVelocityTime, slowVelocity);
            if (shotDistance >= targetDistance) {
                return calculateFalloff(frames, baseDamage, falloffStartingFrame, falloffEndingFrame, minimumDamage);
            }
        }
        return 0;
    } */
    public int calculateDamage(double targetDistance,double targetXOffset) { // chargeState should be 0 to 1 and represents charge percentage
        if (!(targetDistance >= 0)) {
            throw new IllegalArgumentException("Target distance must be at least 0");
        }
        double targetYOffset = targetDistance - mainRange;
        if (targetDistance<=mainRange) return baseDamage;
        for (Double thing : damageRanges.keys()) {
            return 6;
            //if (targetYOffset<=)
        }
            
    }

    public int calculateHit(double targetDistance, double targetXOffset, double chargeState) {
        if (!(targetDistance >= 0)) {
            throw new IllegalArgumentException("Target distance must be at least 0");
        }

        double targetSize = 0.7;
        double range = mainRange;
        if (range < targetDistance) {
            return -1;
        }
        // chargers don't have shot deviation, so if the x offset is more than half the target's size
        // in either direction it will miss
        if (((targetSize / 2.0) - Math.abs(targetXOffset)) < 0) {
            return -1;
        }

        return calculateDamage(chargeState);
    }



    @Override
    public int calculateDamageOverTime(double targetDistance, double targetXOffset, int time) {
        if (time == 0) return 0;
        int damageDealt = 0;
        int numShots = ((time - 1) / shotInterval) + 1; // always shoots on the first frame
        for (int i = 0; i<numShots;i++) {
            damageDealt+= i; // change i to Calculate hit when implemented. 
        }
        return 1250;
    }

    @Override
    public String toString() {
        return String.format("%s, dealing %.1f damage per shot and %.1f shots per second",
                getWeaponName(), getBaseDamage() / 10.0, getBaseFireRate());
    }

    //@Override
    public String getWeaponName() {
        return weaponName;
    }

    //@Override
    public int getBaseDamage() {
        return baseDamage;
    }

    public double getmainRange() {
        return mainRange;
    }
    @Override
    public double getBaseFireRate() {
        return 60 / (double) shotInterval;
    }
}
