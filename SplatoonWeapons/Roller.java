package SplatoonWeapons;

public class Roller implements Weapon {
    private final String weaponName;
    private final int baseDamage;
    private final int shotInterval;
    /* Time that it takes to wind up the Roller's swing */
    private final int swingTime;

    
    public Roller(String weaponName, int baseDamage, int shotInterval, int swingTime) {
        if (!(baseDamage >= 0)) {
            throw new IllegalArgumentException("Base damage must be at least 0");
        }
        if (!(shotInterval >= 1)) {
            throw new IllegalArgumentException("Shot interval must be at least 1");
        }
        if (!(swingTime >= 1)) {
            throw new IllegalArgumentException("Swing Time must be at least 1");
        }

        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.shotInterval = shotInterval;
        this.swingTime = swingTime;
    }

    public int calculateFalloff(double distance, int baseDamage, int falloffStartingDistance, int falloffEndingDistance,
                                int minimumDamage) {
        /* This works a bit differently from primarily projectile based weapons and has fall off based on distance
        * This is going to take a bit to implement
        * */
        if (!(distance >= 0)) {
            throw new IllegalArgumentException("Distance must be at least 0");
        }
        if (!(baseDamage >= 0)) {
            throw new IllegalArgumentException("Base damage must be at least 0");
        }

        if (!(falloffStartingDistance >= 0)) {
            throw new IllegalArgumentException("Falloff starting frame must be at least 0");
        }
        if (!(falloffEndingDistance >= falloffStartingDistance)) {
            throw new IllegalArgumentException("Falloff ending frame must be at least equal to ending frame");
        }
        if (!(minimumDamage >= 0)) {
            throw new IllegalArgumentException("Falloff minimum damage must be at least 0");
        }

        if (distance <= falloffStartingDistance) {
            return baseDamage;
        } else if (distance > falloffEndingDistance) {
            return minimumDamage;
        }

        return 0;
    }

    @Override
    public int calculateDamageOverTime(double targetDistance, double targetXOffset, int time) {
        if (!(time >= 0)) {
            throw new IllegalArgumentException("Time cannot be negative");
        }
        if (time == 0) return 0;

        int damageDealt = 0;
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
        /* 6 frames of swing
         * Varying number of (21 for splatroller) frames in wind-up before swing
         */
        int interval = swingTime+6;
        return 60 / (double) interval;
    }

    @Override
    public String getFullStats() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("%s roller, dealing %.1f damage per swing and %.1f swings per second",
                getWeaponName(), getBaseDamage() / 10.0, getBaseFireRate());
    }
}
