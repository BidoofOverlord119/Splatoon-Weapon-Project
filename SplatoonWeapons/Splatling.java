package SplatoonWeapons;

public class Splatling extends BaseShooter implements Weapon {
    // TODO: COMMENTS AND BOUNDS CHECKING!

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
    private final double initialVelocityMin; // SpawnSpeed
    private final double initialVelocityMax; // SpawnSpeedFirstLastAndSecond
    private final int initialVelocityTime;
    private final double slowVelocity;

    /*
     * Time (in frames) that it takes for the Splatling to charge the first ring of its reticle while charging. (default of 50)
     */
    private final int chargeRing1; // ChargeFrame_First

    /*
     * Time (in frames) that it takes for the Splatling to charge the second ring of its reticle while charging. (default of 75)
     */
    private final int chargeRing2; // ChargeFrame_Second

    /* 
    Time (in frames) that it takes to fire the Splatling's first (fully charged) ring
     */
    private final int timeRing1; // MaxShootingFrame_First

    /* 
    Time (in frames) that it takes to fire the Splatling's first (fully charged) ring
     */
    private final int timeRing2; // MaxShootingFrame_Second

    /* 
    Damage dealt per hit when the Splatling is fully charged.
    */
    private final int fullChargeDamage;

    public Splatling(String weaponName, int baseDamage, int shotInterval, int falloffStartingFrame,
                     int falloffEndingFrame, int minimumDamage, double deviationMinOuterChance,
                     double deviationMaxOuterChance, double deviationChangePerShot, double deviationAngle,
                     double initialVelocityMin, double initialVelocityMax, int initialVelocityTime, double slowVelocity,
                     int chargeRing1, int chargeRing2, int timeRing1, int timeRing2, int fullChargeDamage) {

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

        if (!(initialVelocityMin > 0)) {
            throw new IllegalArgumentException("Starting velocity minimum value must be greater than 0");
        }
        if (!(initialVelocityMax >= initialVelocityMin)) {
            throw new IllegalArgumentException("Starting velocity maximum must be greater than 0");
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
        if (!(timeRing2 >= 0)) {
            throw new IllegalArgumentException("Time Multiplier must be at least 0");
        }
        if (!(fullChargeDamage >= 0)) {
            throw new IllegalArgumentException("Full Charge Damage must be at least 0");
        }
        if (!(fullChargeDamage >= baseDamage)) {
            throw new IllegalArgumentException("Full Charge Damage must be at least greater than Base Damage");
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
        this.initialVelocityMin = initialVelocityMin;
        this.initialVelocityMax = initialVelocityMax;
        this.initialVelocityTime = initialVelocityTime;
        this.slowVelocity = slowVelocity;
        this.chargeRing1 = chargeRing1;
        this.chargeRing2 = chargeRing2;
        this.timeRing1 = timeRing1;
        this.timeRing2 = timeRing2;
        this.fullChargeDamage = fullChargeDamage;
    }

    // When in the first ring, shot velocity scales linearly from initialVelocityMin to initialVelocityMax.
    // When in the second ring, it's always initialVelocityMax.
    public double calculateInitialVelocity(int currentCharge) {
        if (currentCharge >= chargeRing1) {
            return initialVelocityMax;
        }
        double scalingFactor = currentCharge / (double) chargeRing1;
        return initialVelocityMin + ((initialVelocityMax - initialVelocityMin) * scalingFactor);
    }

    // The firing time of a Splatling is done in two phases.
    // In the first ring, it scales from 0 to timeRing1.
    // In the second ring, it scales from timeRing1 to timeRing2.
    public int calculateFireTime(int currentCharge) {
        int ring2Difference = chargeRing2 - chargeRing1;
        double firstRingState = Math.min(currentCharge, chargeRing1) / (double) chargeRing1;
                                 // This keeps it in the bound 0-ring2Difference
        double secondRingState = Math.min(Math.max(currentCharge - chargeRing1, 0), ring2Difference)
                    / (double) (ring2Difference);

        int firstRingTime = (int) (timeRing1 * firstRingState);
        int secondRingTime = (int) ((timeRing2 - timeRing1) * secondRingState);

        return firstRingTime + secondRingTime;
    }

    // Calculates how much damage will be done to a target by a single charge.
    public int calculateSingleCharge(double targetDistance, double targetXOffset, int chargeTime) {
        if (chargeTime == 0) return 0;
        int firingTime = calculateFireTime(chargeTime); // Calculates the time that the Splatling spends firing
        // Current damage should be fullChargeDamage if this is a full charge, baseDamage if it isn't
        int currentDamage = chargeTime >= chargeRing2 ? fullChargeDamage : baseDamage;
        int damageDealt = 0;
        int numShots = ((firingTime - 1) / shotInterval) + 1; // always shoots on the first frame
        for (int i = 0; i < numShots; i++) {
            damageDealt += calculateHit(targetDistance, targetXOffset, i, currentDamage, falloffStartingFrame,
                    falloffEndingFrame, minimumDamage, deviationMinOuterChance, deviationMaxOuterChance,
                    deviationChangePerShot, deviationAngle, calculateInitialVelocity(chargeTime),
                    initialVelocityTime, slowVelocity);
        }
        return damageDealt;
    }

    @Override
    public int calculateDamageOverTime(double targetDistance, double targetXOffset, int time) {
        int fullCycleTime = chargeRing2 + timeRing2; // represents the time it takes to fully charge then fire all shots
        int numFullCharges = time / fullCycleTime;
        int partialCharge = time % fullCycleTime;
        int totalDamage = 0;

        // do full charges
        for (int i = 0; i < numFullCharges; i++) {
            totalDamage += calculateSingleCharge(targetDistance, targetXOffset, chargeRing2);
        }

        if (partialCharge != 0) {
            totalDamage += calculateSingleCharge(targetDistance, targetXOffset, partialCharge);
        }
        return totalDamage;
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
        return String.format("%s, dealing %.1f damage per shot and %.1f shots per second",
                getWeaponName(), getBaseDamage() / 10.0, getBaseFireRate());
    }
}