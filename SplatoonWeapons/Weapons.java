package SplatoonWeapons;

public class Weapons {
    public static final Shooter SPLOOSH_O_MATIC = new Shooter(
            "Sploosh-o-matic", 380, 5,
            6, 22, 190,
            0.04, 0.4, 0.02, 12,
            2, 2, 1.782
    );

    public static final Shooter SPLATTERSHOT_JR = new Shooter(
            "Splattershot Jr.", 280, 5,
            8, 24, 140,
            0.04, 0.4, 0.02, 12,
            2.2, 3, 1.8945
    );

    public static final Shooter SPLASH_O_MATIC = new Shooter(
            "Splash-o-matic", 280, 5,
            4, 20, 140,
            0.01, 0.25, 0.01, 0,
            3.92, 2, 1.5815
    );

    public static final Shooter AEROSPRAY = new Shooter(
            "Aerospray", 240, 4,
            8, 24, 120,
            0.06, 0.5, 0.03, 13,
            2.2, 3, 1.8945
    );

    public static final Shooter SPLATTERSHOT = new Shooter(
            "Splattershot", 360, 6,
            8, 40, 180,
            0.01, 0.25, 0.01, 6.0,
            2.2, 4, 1.4495
    );

    public static final Shooter FIFTY_TWO_GAL = new Shooter(
            ".52 Gal", 520, 9,
            11, 27, 300,
            0.02, 0.25, 0.03, 6,
            2.2, 4, 1.8472
    );

    public static final Shooter N_ZAP = new Shooter(
            "N-ZAP", 280, 5,
            8, 24, 140,
            0.01, 0.25, 0.01, 6,
            2.2, 4, 1.4495
    );

    public static final Shooter SPLATTERSHOT_PRO = new Shooter(
            "Splattershot Pro", 420, 8,
            7, 23, 210,
            0.01, 0.25, 0.01, 2.7,
            3.833333, 3, 2.2698
    );

    public static final Shooter NINETY_SIX_GAL = new Shooter(
            ".96 Gal", 620, 12,
            9, 25, 350,
            0.04, 0.3, 0.03, 4,
            2.45, 5, 2.377
    );

    public static final Shooter JET_SQUELCHER = new Shooter(
            "Jet Squelcher", 320, 8,
            9, 25, 160,
            0.02, 0.25, 0.015, 2.5,
            3.36, 5, 2.232
    );

    public static final Shooter SPLATTERSHOT_NOVA = new Shooter(
            "Splattershot Nova", 240, 6,
            10, 26, 120,
            0.1, 0.25, 0.03, 6,
            2.2, 5, 2.2698
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

    // Squeezer is not supported due to its unique first-shot mechanic

    public static final Charger SQUIFFER = new Charger(
            "Squiffer", 1400, 400, 800,
            16.765, 9.261, 16.765, 45
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
            "Bamboozler 14", 850, 850, 300,
            19.564, 19.564, 19.564, 20
    );

    public static final Charger GOO_TUBER = new Charger(
            "Goo Tuber", 1800, 400, 1300,
            19.804, 12.602, 18.804, 75
    );

    // Snipewriter 5H is not supported due to it working completely differently to other chargers

    public static final Splatling MINI_SPLATLING = new Splatling(
            "Mini Splatling", 320, 320, 4,
            11, 19, 160,
            0.01, 0.30, 0.02,
            4, 1.05, 1.5, 8,
            1.105, 20, 30, 42, 84
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

    // Ballpoint Splatling is not supported because of the second ring mechanic

    public static final Splatling NAUTILUS = new Splatling(
            "Nautilus", 320, 320, 4,
            7, 15, 160,
            0.1, 0.3, 0.02,
            3.3, 4.4333, 4.4333, 3,
            1.8215, 38, 76, 60, 120
    );

    public static final Blaster LUNA_BLASTER = new Blaster(
            "Luna Blaster", 1250, (0.87 * 7) + (0.8484 * 2), 40,
            10, 700, 500, 1.5, 3.57
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

    public static final Blaster RAPID_BLASTER = new Blaster(
            "Rapid Blaster", 850, (1.2 * 11) + (1.13305 * 2), 35,
            8, 350, 350, 0.94, 3.3
    );

    public static final Blaster RAPID_BLASTER_PRO = new Blaster(
            "Rapid Blaster Pro", 850, (1.4 * 11) + (1.3426 * 2), 40,
            8, 350, 350, 0.94, 3.3
    );

    public static final Shooter[] SHOOTERS = new Shooter[]{SPLOOSH_O_MATIC, SPLATTERSHOT_JR, SPLASH_O_MATIC, AEROSPRAY,
            SPLATTERSHOT, FIFTY_TWO_GAL, N_ZAP, SPLATTERSHOT_PRO, NINETY_SIX_GAL, JET_SQUELCHER, SPLATTERSHOT_NOVA,
            H3_NOZZLENOSE, L3_NOZZLENOSE};

    public static final Charger[] CHARGERS = new Charger[]{SQUIFFER, SPLAT_CHARGER, ELITER_4K, BAMBOOZLER, GOO_TUBER};

    public static final Splatling[] SPLATLINGS = new Splatling[]{MINI_SPLATLING, HEAVY_SPLATLING, HYDRA_SPLATLING,
            NAUTILUS};

    public static final Blaster[] BLASTERS = new Blaster[]{LUNA_BLASTER, BLASTER, RANGE_BLASTER, CLASH_BLASTER,
            RAPID_BLASTER, RAPID_BLASTER_PRO};

    public static final Weapon[][] TYPES = new Weapon[][]{SHOOTERS, CHARGERS, SPLATLINGS, BLASTERS};
}
