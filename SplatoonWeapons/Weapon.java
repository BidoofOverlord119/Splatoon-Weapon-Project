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

    //String getFullStats();

    // All weapon classes should also implement a static method called createWeapon which returns a Weapon,
    // and asks the user interactively for stats of that weapon

    String toString();
}