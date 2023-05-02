package SplatoonWeapons;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Weapon> currentWeapons = new ArrayList<>();
        while (true) {
            currentWeapons.add(chooseWeapon());
            System.out.println("Would you like to choose another weapon?");
            String response = Utils.nextLine().toLowerCase();
            if (!(response.equals("y") || response.equals("yes"))) {
                break;
            }
        }

        System.out.print("What is the distance of the target? ");
        double targetDistance = Utils.getDouble(0);
        System.out.print("What is the X offset of the target (0 for none)? ");
        double targetXOffset = Utils.getDouble();
        System.out.print("How many frames should the weapon fire for (60 FPS)? ");
        int time = Utils.getInt(0);
        System.out.println();
        for (Weapon currentWeapon : currentWeapons) {
            System.out.println(currentWeapon);
            System.out.printf("Weapon dealt %.1f damage over %d frames (%.2f seconds) to a target %.1f units away, " +
                    "with X offset %.2f%n", currentWeapon.calculateDamageOverTime(targetDistance, targetXOffset, time)
                    / 10.0, time, time / 60.0, targetDistance, targetXOffset);
            System.out.println();
        }
    }

    private static Weapon chooseWeapon() {
        System.out.println("Please choose a weapon type:");
        for (int i = 0; i < Weapons.TYPES.length; i++) {
            System.out.printf("%d: %s%n", i + 1, Weapons.TYPES[i].getClass().getComponentType().getSimpleName());
        }
        Weapon[] weaponType = Weapons.TYPES[Utils.getInt(1, Weapons.TYPES.length) - 1];

        System.out.println("Please choose a weapon:");
        for (int i = 0; i < weaponType.length; i++) {
            System.out.printf("%d: %s%n", i + 1, weaponType[i].getWeaponName());
        }

        return weaponType[Utils.getInt(1, weaponType.length) - 1];
    }

}
