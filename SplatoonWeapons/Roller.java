package SplatoonWeapons;

public class Roller implements Weapon {

    private String weaponName;
    private double damagePerHit;

    public Roller(String weaponName, double damagePerHit) {
        this.weaponName = weaponName;
        this.damagePerHit = damagePerHit;
    }

    public double getDamagePerHit() {
        return damagePerHit;
    }

    public void setDamagePerHit(double damagePerHit) {
        this.damagePerHit = damagePerHit;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }
}
