package SplatoonWeapons;

public class Blaster implements Weapon {

    private String weaponName;
    private int damagePerHit;

    public Blaster(String weaponName, int damagePerHit) {
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
