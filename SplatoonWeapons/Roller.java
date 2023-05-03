package SplatoonWeapons;

public class Roller implements Weapon {
    private final String weaponName;

    /* Time that it takes to wind up the Roller's swing */
    private final int windUpTime;

    private final double[] innerDistances;
    private final int[] innerDamages;
    private final double innerAngle;
    private final double[] outerDistances;
    private final int[] outerDamages;

    private static final int SWING_TIME = 7; // swing time seems to be a constant of 7 frames


    public Roller(String weaponName, int windUpTime, double[] innerDistances, int[] innerDamages, double innerAngle,
                  double[] outerDistances, int[] outerDamages) {
        if (!(windUpTime >= 1)) {
            throw new IllegalArgumentException("Wind up time must be at least 1");
        }

        this.weaponName = weaponName;
        this.windUpTime = windUpTime;

        this.innerDistances = innerDistances;
        this.innerDamages = innerDamages;
        this.innerAngle = innerAngle;
        this.outerDistances = outerDistances;
        this.outerDamages = outerDamages;
    }

    public int calculateDistanceFalloff(double distanceToTarget, double targetAngle) {
        // this method is unexplainable, just believe me when I say that it works
        if (!(distanceToTarget >= 0)) {
            throw new IllegalArgumentException("Distance must be at least 0");
        }

        double[] distances;
        int[] damages;

        if (targetAngle <= innerAngle) {
            distances = innerDistances;
            damages = innerDamages;
        } else {
            distances = outerDistances;
            damages = outerDamages;
        }

        double upperDistance;
        double lowerDistance;
        int upperDamage;
        int lowerDamage;

        if (distanceToTarget <= distances[0]) {
            return damages[0];
        } else if (distanceToTarget <= distances[1]) {
            upperDistance = distances[0];
            lowerDistance = distances[1];
            upperDamage = damages[0];
            lowerDamage = damages[1];
        } else if (distanceToTarget <= distances[2]) {
            upperDistance = distances[1];
            lowerDistance = distances[2];
            upperDamage = damages[1];
            lowerDamage = damages[2];
        } else if (distanceToTarget <= distances[3]) {
            upperDistance = distances[2];
            lowerDistance = distances[3];
            upperDamage = damages[2];
            lowerDamage = damages[3];
        } else {
            return 0;
        }

        double distanceDifference = lowerDistance - upperDistance;
        int damageDifference = upperDamage - lowerDamage;

        return (int) (upperDamage - (damageDifference * ((distanceToTarget - upperDistance) / distanceDifference)));
    }

    @Override
    public int calculateDamageOverTime(double targetDistance, double targetXOffset, int time) {
        if (!(time >= 0)) {
            throw new IllegalArgumentException("Time cannot be negative");
        }
        if (time == 0) return 0;

        return 0;
    }

    @Override
    public int getBaseDamage() {
        return innerDamages[0];
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
        return 60 / (double) (windUpTime + SWING_TIME);
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
