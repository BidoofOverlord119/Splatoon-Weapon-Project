package SplatoonWeapons;

public class Weapons {
    public static final Shooter splattershot = new Shooter(
            "Splattershot", 360, 6,
            8, 40, 180,
            0.01, 0.25, 0.01, 6.0,
            2.2, 4, 1.4495
    );

    public static final Shooter jetSquelcher = new Shooter(
            "Jet Squelcher", 320, 8,
            9, 25, 160,
            0.02, 0.25, 0.015, 2.5,
            3.36, 5, 2.232
    );

    public static final Shooter aerospray = new Shooter(
            "Aerospray MG", 240, 4,
            8, 24, 120,
            0.06, 0.5, 0.03, 13,
            2.2, 3, 1.8945
    );

    public static final Charger splatCharger = new Charger(
            "Splat Charger", 1600, 400, 800,
            24.037, 9.033, 24.037,
            60
    );

    public static final Charger eliter4k = new Charger(
            "E-Liter 4K", 1800, 400, 800,
            29.05, 9.045, 29.05,
            92
    );

    public static final Charger bamboozler = new Charger(
            "Bamboozler 14 Mk I", 850, 850, 300,
            19.564, 19.564, 19.564,
            20
    );
    public static final Splatling heavySplatling = new Splatling(
            "Heavy Splatling", 300, 4,
            11, 19, 150,
            0.01, 0.33, 0.02,
            3.3, 1.05, 8,
            1.5105, 50, 75, 72, 2, 300
    );

    public static final Splatling hydraSplatling = new Splatling(
            "Hydra Splatling", 320, 4,
            11, 19, 160,
            0.01, 0.30, 0.02,
            3, 1.05, 8,
            1.94, 120, 150, 120, 4, 400);

    public static final Splatling miniSplatling = new Splatling(
            "Mini Splatling", 320, 4,
            11, 19, 160,
            0.01, 0.30, 0.02,
            4, 1.05, 8,
            1.105, 20, 30, 36, 2, 320);

    public static final Blaster blaster = new Blaster(
            "Blaster", 1250, (0.945 * 9) + (0.9131 * 2), 50, 10,
            700, 500, 0.94, 3.3
    );

    public static final Weapon[] weapons = new Weapon[]{splattershot, jetSquelcher, aerospray, splatCharger,
            eliter4k, bamboozler, heavySplatling, hydraSplatling, blaster};
}
