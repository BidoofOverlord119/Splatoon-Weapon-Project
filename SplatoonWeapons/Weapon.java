package SplatoonWeapons;

public interface Weapon {

    String getWeaponName();

    // damage is always represented as an integer with the tenths in the final place
    // for example, 34.3 damage is represented as 343
    int getBaseDamage();

    String toString();
}