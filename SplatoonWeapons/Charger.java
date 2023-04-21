package SplatoonWeapons;

public class Charger implements Weapon {
    // TODO: COMMENTS!

    private final String weaponName;
    private final int baseDamage;
    private final int minDamage;
    private final int maxDamage;

    private final double minRange;
    private final double maxRange;
    private final double fullRange;

    private final int framesToCharge;

    public Charger(String weaponName, int baseDamage, int minDamage, int maxDamage,
                   double fullRange, double minRange, double maxRange, int framesToCharge) {
        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.fullRange = fullRange;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.framesToCharge = framesToCharge;
    }

    public int calculateDamage(double chargeState) { // chargeState should be 0 to 1 and represents charge percentage
        if (!(chargeState < 1.005 && chargeState >= 0.0)) {
            throw new IllegalArgumentException("Charge state must be 0.0-1.0");
        }
        if (Math.abs(chargeState - 1.0) < 0.005) { // has to be done due to floating point precision
            return getBaseDamage();
        } else {
            int damageDifference = getMaxDamage() - getMinDamage();
            return (int) (getMinDamage() + (damageDifference * chargeState));
        }
    }

    public double calculateRange(double chargeState) {
        if (!(chargeState < 1.005 && chargeState >= 0.0)) {
            throw new IllegalArgumentException("Charge state must be 0.0-1.0");
        }
        if (Math.abs(chargeState - 1.0) < 0.005) {
            return getFullRange();
        } else {
            double rangeDifference = getMaxRange() - getMinRange();
            return (getMinRange() + (rangeDifference * chargeState));
        }
    }

    @Override
    public double getBaseFireRate() {
        return getFramesToCharge() / 60.0;
    }

    public int calculateHit(double targetDistance, double targetXOffset, double chargeState) {
        if (!(targetDistance >= 0)) {
            throw new IllegalArgumentException("Target distance must be at least 0");
        }

        double targetSize = 0.7;
        double range = calculateRange(chargeState);
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
        int fullCharges = time / getFramesToCharge();
        int totalDamage = 0;
        for (int i = 0; i < fullCharges; i++) {
            int currentDamage = calculateHit(targetDistance, targetXOffset, 1.0);
            if (currentDamage != -1) {
                totalDamage += currentDamage;
            }
        }

        if (time % getFramesToCharge() != 0) { // there will be a partial charge
            double partialCharge = (time % getFramesToCharge()) / (double) getFramesToCharge();
            int currentDamage = calculateHit(targetDistance, targetXOffset, partialCharge);
            if (currentDamage != -1) {
                totalDamage += currentDamage;
            }
        }
        return totalDamage;
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

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public double getMinRange() {
        return minRange;
    }

    public double getMaxRange() {
        return maxRange;
    }

    public double getFullRange() {
        return fullRange;
    }

    public int getFramesToCharge() {
        return framesToCharge;
    }
}
