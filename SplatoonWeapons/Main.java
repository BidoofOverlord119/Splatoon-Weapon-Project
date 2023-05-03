package SplatoonWeapons;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Weapon> currentWeapons = addWeapons();

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

    private static ArrayList<Weapon> addWeapons() {
        ArrayList<Weapon> currentWeapons = new ArrayList<>();
        boolean finishedAdding = false;

        while (!finishedAdding) {
            System.out.print("Would you like to choose an (e)xisting or create a (n)ew one? ");
            boolean createNew = false;
            boolean hasResponded = false;
            while (!hasResponded) {
                String response = Utils.nextLine().toLowerCase();
                if (response.equals("n") || response.equals("new")) {
                    createNew = true;
                    hasResponded = true;
                } else if (response.equals("e") || response.equals("existing")) {
                    hasResponded = true;
                } else {
                    System.out.println("Invalid response, please try again.");
                }
            }

            if (createNew) {
                try {
                    currentWeapons.add(selectCreateWeapon());
                } catch (IllegalArgumentException e) {
                    System.out.println("Looks like one of the options you gave wasn't quite right. The error is: " +
                            e.getMessage());
                }
            } else {
                currentWeapons.add(chooseWeapon());
            }

            System.out.print("Would you like to choose another weapon? ");
            String response = Utils.nextLine().toLowerCase();
            if (!(response.equals("y") || response.equals("yes"))) {
                finishedAdding = true;
            }
        }

        return currentWeapons;
    }

    private static Weapon selectCreateWeapon() {
        System.out.println("""
                Please choose a weapon type:
                1. Shooter
                2. Charger
                3. Splatling
                4. Blaster""");

        switch (Utils.getInt(1, 4)) {
            case 1 -> {
                return Shooter.createWeapon();
            }
            case 2 -> {
                return null;
            }
            case 3 -> {
                return null;
            }
            case 4 -> {
                return null;
            }
            default -> {
                return null;
            }
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
