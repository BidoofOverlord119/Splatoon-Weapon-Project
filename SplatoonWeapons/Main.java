package SplatoonWeapons;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What is the distance of the target? ");
        double targetDistance = input.nextDouble();
        System.out.print("What is the X offset of the target (0 for none)? ");
        double targetXOffset = input.nextDouble();
        System.out.print("How many frames should the weapon fire for (60 FPS)? ");
        int time = input.nextInt();
        for (Weapon w: Weapons.weapons) {
            System.out.println(w);
            System.out.printf("Weapon dealt %.1f damage over %d frames (%.2f seconds) to a target %.1f units away, " +
                    "with X offset %.2f%n", w.calculateDamageOverTime(targetDistance, targetXOffset, time) / 10.0,
                    time, time / 60.0, targetDistance, targetXOffset);
            System.out.println();
        }
    }
}
