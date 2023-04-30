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
    private final double initialVelocityMin;
    private final double initialVelocityMax;
    private final int initialVelocityTime;
    private final double slowVelocity;
    private final int ring1ChargeTime;
    private final int ring2ChargeTime;
    private final int ring1FireTime;
    private final int ring2FireTime;
    private final int fullChargeDamage;

    /**
     * Constructor for the Splatling object.
     *
     * @param weaponName              Friendly name of the weapon.
     * @param baseDamage              Base damage of the weapon, from ValueMax.
     * @param fullChargeDamage        Damage done upon a full charge, from ValueFullChargeMax. Only used for the
     *                                Hydra Splatling.
     * @param shotInterval            Time (in frames) between shots, from RepeatFrame. Has a default value of 6 if
     *                                not specified by the weapon data.
     * @param falloffStartingFrame    Frame at which falloff starts taking effect, from ReduceStartFrame.
     * @param falloffEndingFrame      Frame at which falloff stops taking effect, from ReduceEndFrame.
     * @param minimumDamage           Minimum damage due to falloff, from ValueMin.
     * @param deviationMinOuterChance Minimum chance that the shot goes towards the outer reticle,
     *                                from Stand_DegBiasMin.
     * @param deviationMaxOuterChance Maximum chance that the shot goes towards the outer reticle,
     *                                from Stand_DegBiasMax. Has a default value of 0.25 if not specified by the
     *                                weapon data.
     * @param deviationChangePerShot  The amount that the deviation chance goes up by per shot, from Stand_DegBiasKf.
     * @param deviationAngle          The maximum angle that the shot can deviate. Dictates the size of the
     *                                outer reticle. From Stand_DegSwerve.
     * @param initialVelocityMin      Initial velocity of the shot for no charge, in distance units per frame.
     *                                From SpawnSpeed.
     * @param initialVelocityMax      Initial velocity of the shot when the 1st ring is full, in distance units
     *                                per frame. From SpawnSpeedFirstAndSecond.
     * @param initialVelocityTime     Amount of frames that the shot will travel at the initial velocity,
     *                                from GoStraightToBrakeStateFrame.
     * @param slowVelocity            The slower velocity that the shot will slow down to after initialVelocityTime.
     *                                From GoStraightStateEndMaxSpeed.
     * @param ring1ChargeTime         How many frames it takes to charge the 1st ring, from ChargeFrame_First.
     *                                Has a default value of 50 if not specified by the weapon data.
     * @param ring2ChargeTime         How many frames it takes to charge the 2nd ring. from ChargeFrame_Second.
     *                                Has a default value of 75 if not specified by the weapon data.
     * @param ring1FireTime           How many frames it takes to fire a fully charged first ring,
     *                                from MaxShootingFrame_First.
     * @param ring2FireTime           How many frames it takes to fire a fully charged second ring,
     *                                from MaxShootingFrame_Second.
     */
    public Splatling(String weaponName, int baseDamage, int fullChargeDamage, int shotInterval, int falloffStartingFrame,
                     int falloffEndingFrame, int minimumDamage, double deviationMinOuterChance,
                     double deviationMaxOuterChance, double deviationChangePerShot, double deviationAngle,
                     double initialVelocityMin, double initialVelocityMax, int initialVelocityTime, double slowVelocity,
                     int ring1ChargeTime, int ring2ChargeTime, int ring1FireTime, int ring2FireTime) {

        if (!(baseDamage >= 0)) {
            throw new IllegalArgumentException("Base damage must be at least 0");
        }
        if (!(fullChargeDamage >= baseDamage)) {
            throw new IllegalArgumentException("Full charge damage must be at least equal to base damage");
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
            throw new IllegalArgumentException("Starting velocity maximum must be at least equal to minimum value");
        }
        if (!(initialVelocityTime > 0)) {
            throw new IllegalArgumentException("Velocity starting value time must be greater than 0");
        }
        if (!(slowVelocity > 0)) {
            throw new IllegalArgumentException("Velocity slow speed must be greater than 0");
        }

        if (!(ring1ChargeTime >= 0)) {
            throw new IllegalArgumentException("Ring 1 charge time must be at least 0");
        }
        if (!(ring2ChargeTime >= ring1ChargeTime)) {
            throw new IllegalArgumentException("Ring 2 charge time must be at least equal to ring 1 charge time");
        }
        if (!(ring1FireTime >= 0)) {
            throw new IllegalArgumentException("Ring 1 fire time must be at least 0");
        }
        if (!(ring2FireTime >= ring1FireTime)) {
            throw new IllegalArgumentException("Ring 2 fire time must be at least equal to ring 1 fire time");
        }

        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.fullChargeDamage = fullChargeDamage;
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
        this.ring1ChargeTime = ring1ChargeTime;
        this.ring2ChargeTime = ring2ChargeTime;
        this.ring1FireTime = ring1FireTime;
        this.ring2FireTime = ring2FireTime;
    }

    /**
     * Calculates the initial velocity that a shot should have, based on the charge time.
     * Initial shot velocity scales from initialVelocityMin for no charge to initialVelocityMax
     * when at least 1 ring is full.
     *
     * @param currentCharge How many frames the weapon was charged for.
     * @return The adjusted initial velocity for the shot.
     */
    public double calculateInitialVelocity(int currentCharge) {
        if (!(currentCharge >= 0)) {
            throw new IndexOutOfBoundsException("Current charge must be at least 0");
        }

        if (currentCharge >= ring1ChargeTime) {
            return initialVelocityMax;
        }
        double scalingFactor = currentCharge / (double) ring1ChargeTime;
        return initialVelocityMin + ((initialVelocityMax - initialVelocityMin) * scalingFactor);
    }

    /**
     * Calculates how long it will take for all shots to fire, based on the charge time.
     * Firing time scales from 0 to ring1FireTime in the first ring, then from ring1FireTime to
     * ring2FireTime in the second ring.
     *
     * @param currentCharge How many frames the weapon was charged for.
     * @return Time (in frames) that the weapon will spend firing all the shots from this charge.
     */
    public int calculateFireTime(int currentCharge) {
        if (!(currentCharge >= 0)) {
            throw new IndexOutOfBoundsException("Current charge must be at least 0");
        }

        int ring2Difference = ring2ChargeTime - ring1ChargeTime;
        double firstRingState = Math.min(currentCharge, ring1ChargeTime) / (double) ring1ChargeTime;
        // This keeps it in the bound 0-ring2Difference
        double secondRingState = Math.min(Math.max(currentCharge - ring1ChargeTime, 0), ring2Difference)
                / (double) (ring2Difference);

        int firstRingTime = (int) (ring1FireTime * firstRingState);
        int secondRingTime = (int) ((ring2FireTime - ring1FireTime) * secondRingState);

        return firstRingTime + secondRingTime;
    }

    /**
     * Calculates how much damage will be done to a target by a single charge.
     *
     * @param targetDistance Distance from the player to the target, in distance units.
     * @param targetXOffset  Left-right offset of the target, in distance units.
     * @param chargeTime     How long the weapon should be charged, in frames.
     * @return Damage dealt by the entire charge.
     */
    public int calculateSingleCharge(double targetDistance, double targetXOffset, int chargeTime) {
        if (chargeTime == 0) return 0;

        int firingTime = calculateFireTime(chargeTime); // Calculates the time that the Splatling spends firing
        // Current damage should be fullChargeDamage if this is a full charge, baseDamage if it isn't
        int currentDamage = chargeTime >= ring2ChargeTime ? fullChargeDamage : getBaseDamage();
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

        // represents the time it takes to fully charge then fire all shots
        int fullCycleTime = ring2ChargeTime + ring2FireTime;
        int numFullCharges = time / fullCycleTime;
        int partialChargeMax = time % fullCycleTime;
        int totalDamage = 0;

        // do full charges
        for (int i = 0; i < numFullCharges; i++) {
            totalDamage += calculateSingleCharge(targetDistance, targetXOffset, ring2ChargeTime);
        }

        if (partialChargeMax != 0) {
            // brute force the charge frames so that charging + fire time fits within the allotted frames
            // there's probably some algebra math-y way to do this, but it should be less than 400 iterations at most
            // so who really cares?
            int partialCharge = partialChargeMax;
            while (partialCharge + calculateFireTime(partialCharge) > partialChargeMax && partialCharge > 0) {
                partialCharge--;
            }
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