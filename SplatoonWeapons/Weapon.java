package SplatoonWeapons;

public interface Weapon {

    void setWeaponName(String weaponName);

    String getWeaponName();

    // Damage in Splatoon is always stored as an integer, then divided by 10 when displayed.
    // For example, when the game shows 34.0 damage it is stored as 340.
    void setDamagePerHit(int damage);

    int getDamagePerHit();

    String toString();
}


