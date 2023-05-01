package SplatoonWeapons;

public class BurstShooter extends Shooter implements Weapon {
    public int burstSize;
    public int burstCooldown;

    /**
     * Constructor for the BurstShooter object.
     * All parameters other than burstSize and burstInterval are identical to Shooter.
     *
     * @param burstSize Amount of shots in a single burst. Always 3 shots with the default weapons.
     * @param burstCooldown Cooldown period between shot bursts, from TripleShotSpanFrame.
     */
    public BurstShooter(String weaponName, int baseDamage, int shotInterval, int falloffStartingFrame,
                        int falloffEndingFrame, int minimumDamage, double deviationMinOuterChance,
                        double deviationMaxOuterChance, double deviationChangePerShot, double deviationAngle,
                        double initialVelocity, int initialVelocityTime, double slowVelocity,
                        int burstSize, int burstCooldown) {
        super(weaponName, baseDamage, shotInterval, falloffStartingFrame, falloffEndingFrame, minimumDamage,
                deviationMinOuterChance, deviationMaxOuterChance, deviationChangePerShot, deviationAngle,
                initialVelocity, initialVelocityTime, slowVelocity);

        if (!(burstSize >= 1)) {
            throw new IndexOutOfBoundsException("Burst size must be at least 1");
        }
        if (!(burstCooldown >= 1)) {
            throw new IndexOutOfBoundsException("Burst interval must be at least 1");
        }

        this.burstSize = burstSize;
        this.burstCooldown = burstCooldown;
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
        int numBursts = ((time - 1) / ((shotInterval * burstSize) + burstCooldown)) + 1;

        int shotNumber = 0;
        int damageDealt = 0;
        for (int burst = 0; burst < numBursts; burst++) {
            for (int i = 0; i < burstSize; i++) {
                damageDealt += calculateHit(targetDistance, targetXOffset, shotNumber, baseDamage, falloffStartingFrame,
                        falloffEndingFrame, minimumDamage, deviationMinOuterChance, deviationMaxOuterChance,
                        deviationChangePerShot, deviationAngle, initialVelocity, initialVelocityTime, slowVelocity);
                shotNumber++;
            }
        }

        return damageDealt;
    }

    @Override
    public double getBaseFireRate() {
        return (60 / (double) ((shotInterval * burstSize) + burstCooldown)) * burstSize;
    }

    @Override
    public String toString() {
        return String.format("%s burst shooter, dealing %.1f damage per shot and %.1f shots per second",
                getWeaponName(), getBaseDamage() / 10.0, getBaseFireRate());
    }
}
