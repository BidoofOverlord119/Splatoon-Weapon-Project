package SplatoonWeapons;

public class Weapons {
    public static final Shooter SPLATTERSHOT = new Shooter(
            "Splattershot", 360, 6,
            8, 40, 180,
            0.01, 0.25, 0.01, 6.0,
            2.2, 4, 1.4495
    );

    public static final Shooter JET_SQUELCHER = new Shooter(
            "Jet Squelcher", 320, 8,
            9, 25, 160,
            0.02, 0.25, 0.015, 2.5,
            3.36, 5, 2.232
    );

    public static final Shooter AEROSPRAY = new Shooter(
            "Aerospray MG", 240, 4,
            8, 24, 120,
            0.06, 0.5, 0.03, 13,
            2.2, 3, 1.8945
    );

    public static final BurstShooter L3_NOZZLENOSE = new BurstShooter(
            "L-3 Nozzlenose", 290, 4,
            8, 24, 145,
            0.01, 0.25, 0.01, 1,
            2.75, 4, 1.568, 3, 8
    );

    public static final BurstShooter H3_NOZZLENOSE = new BurstShooter(
            "H-3 Nozzlenose", 410, 5,
            8, 24, 205,
            0.01, 0.25, 0.01, 1,
            2.875, 4, 2.2698, 3, 20
    );

    public static final Charger SPLAT_CHARGER = new Charger(
            "Splat Charger", 1600, 400, 800,
            24.037, 9.033, 24.037, 60
    );

    public static final Charger ELITER_4K = new Charger(
            "E-Liter 4K", 1800, 400, 800,
            29.05, 9.045, 29.05, 92
    );

    public static final Charger BAMBOOZLER = new Charger(
            "Bamboozler 14 Mk I", 850, 850, 300,
            19.564, 19.564, 19.564, 20
    );

    public static final Splatling HEAVY_SPLATLING = new Splatling(
            "Heavy Splatling", 300, 300, 4,
            11, 19, 150,
            0.01, 0.33, 0.02,
            3.3, 1.05, 2.1, 8,
            1.5105, 50, 75, 72, 144
    );

    public static final Splatling HYDRA_SPLATLING = new Splatling(
            "Hydra Splatling", 320, 400, 4,
            11, 19, 160,
            0.01, 0.30, 0.02,
            3, 1.05, 2.4, 8,
            1.94, 120, 150, 120, 240
    );

    public static final Splatling MINI_SPLATLING = new Splatling(
            "Mini Splatling", 320, 320, 4,
            11, 19, 160,
            0.01, 0.30, 0.02,
            4, 1.05, 1.5, 8,
            1.105, 20, 30, 42, 84
    );

    public static final Blaster BLASTER = new Blaster(
            "Blaster", 1250, (0.945 * 9) + (0.9131 * 2), 50,
            10, 700, 500, 0.94, 3.3
    );

    public static final Blaster RANGE_BLASTER = new Blaster(
            "Range Blaster", 1250, (1.08 * 11) + (1.0073 * 2), 60,
            10, 700, 500, 1, 3.5
    );

    public static final Blaster CLASH_BLASTER = new Blaster(
            "Clash Blaster", 600, (0.935 * 8) + (0.92715 * 2), 20,
            8, 300, 300, 1, 4
    );


    public static final Shooter[] SHOOTERS = new Shooter[]{SPLATTERSHOT, JET_SQUELCHER, AEROSPRAY, H3_NOZZLENOSE,
            L3_NOZZLENOSE};

    public static final Charger[] CHARGERS = new Charger[]{SPLAT_CHARGER, ELITER_4K, BAMBOOZLER};

    public static final Splatling[] SPLATLINGS = new Splatling[]{HEAVY_SPLATLING, HYDRA_SPLATLING, MINI_SPLATLING};

    public static final Blaster[] BLASTERS = new Blaster[]{BLASTER, RANGE_BLASTER, CLASH_BLASTER};

    public static final Weapon[] WEAPONS = new Weapon[]{SPLATTERSHOT, JET_SQUELCHER, AEROSPRAY, H3_NOZZLENOSE,
            L3_NOZZLENOSE, SPLAT_CHARGER, ELITER_4K, BAMBOOZLER, HEAVY_SPLATLING, HYDRA_SPLATLING, MINI_SPLATLING,
            BLASTER, RANGE_BLASTER, CLASH_BLASTER};

    public static final Weapon[][] TYPES = new Weapon[][]{SHOOTERS, CHARGERS, SPLATLINGS, BLASTERS};
}
