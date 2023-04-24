package SplatoonWeapons;

public class Splatling extends BaseShooter implements Weapon {
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
    /* 
     * Time (in frames) that it takes for the Splatling to reach the first ring of its reticle while charging. (default of 50)
     */
    private final int chargeRing1;

    /* 
     * Time (in frames) that it takes for the Splatling to reach the second ring of its reticle while charging. (default of 75)
     */
    private final int chargeRing2;
    /* 
    Time (in frames) that it takes to fire the Splatling's full burst at one full ring
     */
    private final int timeRing1;
    /* 
    Multiplier used in calculating the increase in frames gained per charge value
     */
    private final int timeMultiplier;

    public Splatling(String weaponName, int baseDamage, int shotInterval, int falloffStartingFrame, int falloffEndingFrame, int minimumDamage, double deviationMinOuterChance, double deviationMaxOuterChance, double deviationChangePerShot, double deviationAngle, double initialVelocity, int initialVelocityTime, double slowVelocity, int chargeRing1, int chargeRing2, int timeRing1, int timeMultiplier) {

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

        if (!(chargeRing1 >= 0)) {
            throw new IllegalArgumentException("Charge Time for Ring 1 must be at least 0");
        }
        if (!(chargeRing2 >= 0)) {
            throw new IllegalArgumentException("Charge Time for Ring 2 must be at least 0");
        }
        if (!(chargeRing2 > chargeRing1)) {
            throw new IllegalArgumentException("Charge Time for Ring 2 must be greater than that of the Charge Time for Ring 1");
        }
        if (!(timeRing1 >= 0)) {
            throw new IllegalArgumentException("Fire Time for Ring 1 must be at least 0");
        }
        if (!(timeMultiplier >= 0)) {
            throw new IllegalArgumentException("Time Multiplier must be at least 0");
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
        this.chargeRing1 = chargeRing1;
        this.chargeRing2 = chargeRing2;
        this.timeRing1 = timeRing1;
        this.timeMultiplier = timeMultiplier;
    }
    public int get_fire_time(int currentCharge) {
        /* 
            So Splatlings have an annoying factor in how their velocity and time spent shooting is directly related to their charge rate
            So I wipped up this in order to calculate their fire rate based off of their charge time
        */
        if (currentCharge==0) return 0;
        double timeIncrement = timeRing1/chargeRing1;
        double timeSpentShooting = 0;
        for (int fireTime = 0; fireTime<chargeRing2; fireTime+=timeIncrement) {
            if (fireTime==chargeRing1) timeIncrement*=timeMultiplier;
            timeSpentShooting+=timeIncrement;
            if (currentCharge==fireTime) break;
        }
        timeSpentShooting=Math.round(timeSpentShooting);
        return ((int)timeSpentShooting);
    }

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