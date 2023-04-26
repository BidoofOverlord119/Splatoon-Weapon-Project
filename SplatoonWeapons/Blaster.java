package SplatoonWeapons;
import java.util.Dictionary;
import java.util.Hashtable;

public class Blaster implements Weapon {
    // TODO: COMMENTS!

    private final String weaponName;
    private final int baseDamage;
    //private final int blastDamage1;
    //private final int blastDamage2;

    private final double mainRange;
    private final Dictionary<Double, Integer> damageRanges;
    
    //private final double blastRange1;
    //private final double blastRange2;
    private final int shotInterval;

    public Blaster(String weaponName, int baseDamage, int blastDamage1, int blastDamage2,
                   double mainRange, double blastRange1, double blastRange2, int shotInterval) {
        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        //this.blastDamage1 = blastDamage1;
        //this.blastDamage2 = blastDamage2;
        this.mainRange = mainRange;
        //this.blastRange1 = blastRange1;
        //this.blastRange2 = blastRange2;
        this.shotInterval = shotInterval;
        this.damageRanges =  new Hashtable<>();
        //damageHelper = blastDamage1;
        double rangeHelper = blastRange1;
        this.damageRanges.put(rangeHelper, blastDamage1);
        //damageHelper = blastDamage2;
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



    @Override
    public int calculateDamageOverTime(double targetDistance, double targetXOffset, int time) {
        if (time == 0) return 0;
        int damageDealt = 0;
        int numShots = ((time - 1) / shotInterval) + 1; // always shoots on the first frame
        for (int i = 0; i<numShots;i++){
            damageDealt+= i; // change i to Calculate hit when implemented. 
        }
        return 1250;
    }

    @Override
    public String toString() {
        return String.format("%s blaster, dealing %.1f damage per shot and %.1f shots per second",
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
