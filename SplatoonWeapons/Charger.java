package SplatoonWeapons;

public class Charger implements Weapon {
    private final String weaponName;
    private final int baseDamage;
    private final int minDamage;
    private final int maxDamage;

    private final double minRange;
    private final double maxRange;
    private final double fullRange;

    private final int framesToCharge;

    public Charger(String weaponName, int baseDamage, int minDamage, int maxDamage, double fullRange, double minRange, double maxRange, int framesToCharge) {
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
        if (chargeState >= 1.005) throw new IllegalArgumentException("Charge state cannot be greater than 1.0");
        if (Math.abs(chargeState - 1.0) < 0.005) { // has to be done due to floating point precision
            return getBaseDamage();
        } else {
            int damageDifference = getMaxDamage() - getMinDamage();
            return (int) (getMinDamage() + (damageDifference * chargeState));
        }
    }

    public double calculateRange(double chargeState) {
        if (chargeState >= 1.005) throw new IllegalArgumentException("Charge state cannot be greater than 1.0");
        if (Math.abs(chargeState - 1.0) < 0.005) {
            return getFullRange();
        } else {
            double rangeDifference = getMaxRange() - getMinRange();
            return (getMinRange() + (rangeDifference * chargeState));
        }
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
