package SplatoonWeapons;

public interface Weapon {

    String getWeaponName();

    // damage is always represented as an integer with the tenths in the final place
    // for example, 34.3 damage is represented as 343
    int getBaseDamage();

    // shots per second
    // for charging weapons, refers to a full charge
    double getBaseFireRate();

    int calculateDamageOverTime(double targetDistance, double targetXOffset, int time);

    String toString();
}