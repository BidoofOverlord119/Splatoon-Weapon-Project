package SplatoonWeapons;

public class Shooter implements Weapon {

    public final String weaponName;
    public final int baseDamage;

    // values are starting frame, ending frame, minimum damage
    // falloff is linear (?)
    // represented by values ReduceStartFrame, ReduceEndFrame, ValueMin
    private final int[] falloffStats;

    public Shooter(String weaponName, int baseDamage, int[] falloffStats) {
        this.weaponName = weaponName;
        this.baseDamage = baseDamage;
        this.falloffStats = falloffStats;
    }

    public int calculateFalloff(int frames) {
        if (frames <= falloffStats[0]) {
            return baseDamage;
        } else if (frames > falloffStats[1]) {
            return falloffStats[2];
        }

        // calculate damage lost per frame
        double lostPerFrame =  falloffStats[2] / (double) (falloffStats[1] - falloffStats[0]);
        return (int) (baseDamage - ((frames - falloffStats[0]) * lostPerFrame));
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public String getWeaponName() {
        return weaponName;
    }
}
