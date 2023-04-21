package SplatoonWeapons;

public class Weapons {
    public static final Shooter splattershot = new Shooter(
            "Splattershot", 360, 6,
            new int[]{8, 40, 180}, // falloff stats: ReduceStartFrame, ReduceEndFrame, ValueMin
            new double[]{0.01, 0.25, 0.01, 6.0},  // deviation stats: Stand_DegBiasMin, Stand_DegBiasMax, Stand_DegBiasKf, Stand_DegSwerve
            new double[]{2.2, 4, 1.4495}); // velocity stats: SpawnSpeed, GoStraightToBrakeStateFrame, GoStraightStateEndMaxSpeed

    public static final Shooter jetSquelcher = new Shooter(
            "Jet Squelcher", 320, 8,
            new int[]{9, 25, 160},
            new double[]{0.02, 0.25, 0.015, 2.5},
            new double[]{3.36, 5, 2.232}
    );

    public static final Shooter aerospray = new Shooter(
            "Aerospray MG", 240, 4,
            new int[]{8, 24, 120},
            new double[]{0.06, 0.5, 0.03, 13},
            new double[]{2.2, 3, 1.8945}
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

    public static final Weapon[] weapons = new Weapon[]{splattershot, jetSquelcher, aerospray, splatCharger,
    eliter4k, bamboozler};
}
