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
     * Damage Dealt in the close radius of the blast caused at the end of the projectile's lifespan
    */
    private final int blastDamageNear;

    /* 
     * Damage Dealt in the far radius of the blast caused at the end of the projectile's lifespan
    */
    private final int blastDamageFar;

    /* 
     * The near radius of the blast caused at the end of the projectile's lifespan
    */
    private final double blastRangeNear;

    /* 
     * The far radius of the blast caused at the end of the projectile's lifespan
    */
    private final double blastRangeFar;

    
    
    /* 
     * Fire Rate variable
    */
    private final int shotInterval;

    public Blaster(String weaponName, int baseDamage, int blastDamageNear, int blastDamageFar,
                   double mainRange, double blastRangeNear, double blastRangeFar, int shotInterval) {
        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.mainRange = mainRange;
        this.shotInterval = shotInterval;
        this.blastDamageNear = blastDamageNear;
        this.blastDamageFar = blastDamageFar;
        this.blastRangeNear = blastRangeNear;
        this.blastRangeFar = blastRangeFar;
    }

    public int calculateDamage(double targetDistance,double targetXOffset) { // chargeState should be 0 to 1 and represents charge percentage
        if (!(targetDistance >= 0)) {
            throw new IllegalArgumentException("Target distance must be at least 0");
        }
        if (targetDistance<=mainRange) return baseDamage;
        double targetYOffset = targetDistance - mainRange;
        if (targetYOffset<=blastRangeNear||targetXOffset<=blastRangeNear){
            return blastDamageNear;
        }
        else if (targetYOffset<=blastRangeFar||targetXOffset<=blastRangeFar) {
            return blastDamageFar;
        }
        return 0;
            
    }

    /* public int calculateHit(double targetDistance, double targetXOffset, double chargeState) {
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
    } */



    @Override
    public int calculateDamageOverTime(double targetDistance, double targetXOffset, int time) {
        if (time == 0) return 0;
        int damageDealt = 0;
        int numShots = ((time - 1) / shotInterval) + 1; // always shoots on the first frame
        for (int i = 0; i<numShots;i++) {
            damageDealt+= calculateDamage(targetDistance, targetXOffset); // change i to Calculate hit when implemented. 
        }
        return damageDealt;
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
