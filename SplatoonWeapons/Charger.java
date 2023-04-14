package SplatoonWeapons;

public class Charger implements Weapon {

    private String weaponName;
    private int damagePerHit;

    public Charger(String weaponName, int damagePerHit) {
        this.weaponName = weaponName;
        this.damagePerHit = damagePerHit;
    }

    public int getDamagePerHit() {
        return damagePerHit;
    }

    public void setDamagePerHit(int damagePerHit) {
        this.damagePerHit = damagePerHit;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }
}
