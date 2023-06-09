
GameParameters = {
"DamageParam": {
    "type":  "spl__BulletChargerDamageParam",
    "ValueFullCharge": 1600, # Damage of a Fully Charged shot
    "ValueMaxCharge": 800, # Damage of the highest non-fully charged shot
    "ValueMinCharge": 400 # Damage of a minimally charged/uncharged shot
},
"MoveParam": {
    "type":  "spl__BulletChargerMoveParam",
    "DistanceFullCharge": 24.037, # Distance units a fully charged shot goes
    "DistanceMaxCharge": 24.037, # Distance units a highest non-fully charged shot goes 
    "DistanceMinCharge": 9.033, # Distance units an uncharged shot goes
    "SpawnSpeedFullCharge": 4.8, # The velocity with which a fully charged shot is launched
    "SpawnSpeedMaxCharge": 4.8, # The velocity with which a highest non-fully charged shot is launched
    "SpawnSpeedMinCharge": 2.4 # The velocity with which a uncharged shot is launched
},
"WeaponKeepChargeParam": {
    "type": "spl__WeaponKeepChargeParam",
    "KeepChargeFullFrame": 75, # Number of frames you can keep a charge for 
    "KeepChargePreDelayFrame": 23, # The delay between being Submerged with a stored charge and being able to fire said charge.
    "KeepChargePreDelayFrame_Pre": 18, # Something to do with keeping a charge
    "MuzzleLocalPos": { # Coordinates of the end of the charger?
        "X": -0.314,
        "Y": 0.2105,
        "Z": 2.0176,
        },
},
"WeaponParam": {
    "type":  "spl__WeaponChargerParam",
    "FreezeFrameFullCharge": 1, # Don't know, but its per fully charged shot
    "FreezeFrameMinCharge": 1, # Don't know, but its per uncharged shot
    "InkConsumeFullCharge": 0.18, # Ink Consumption per fully charged shot
    "InkConsumeMinCharge": 0.0225, # Ink Consumption per uncharged shot
    "JumpHeightFullCharge": 0.07, # Jump height when fully charged
    "MoveJumpDownBias": 0.5, # Don't know.
    "MoveSpeedFullCharge": 0.02 # Movement speed multiplier at full charge
}
}


UnusedParameters = {
"CollisionParam": {
    "type":  "spl__BulletSimpleCollisionParam",
    "EndRadiusForField": 0.02,
    "EndRadiusForPlayer": 0.1,
    "InitRadiusForField": 0.02,
    "InitRadiusForPlayer": 0.1
},
"DamageParam": {
    "type":  "spl__BulletChargerDamageParam",
    "ValueFullCharge": 1600,
    "ValueMaxCharge": 800,
    "ValueMinCharge": 400
},
"MainEffectiveRangeUpParam": {
    "type":  "spl__PlayerGearSkillParam_MainEffectiveRangeUp"
},
"MainWeaponSetting": {
    "type":  "spl__PlayerGearSkillParam_MainWeaponSetting"
},
"MoveParam": {
    "type":  "spl__BulletChargerMoveParam",
    "DistanceFullCharge": 24.037,
    "DistanceMaxCharge": 24.037,
    "DistanceMinCharge": 9.033,
    "SpawnSpeedFullCharge": 4.8,
    "SpawnSpeedMaxCharge": 4.8,
    "SpawnSpeedMinCharge": 2.4
},
"PaintParam": {
    "type":  "spl__BulletChargerPaintParam",
    "RadiusFullCharge": 3.263,
    "RadiusMaxCharge": 2.719,
    "RadiusMinCharge": 0.906
},
"SplashPaintParam": {
    "type":  "spl__BulletSplashChargerPaintParam",
    "DepthHalfFullCharge": 1.56,
    "DepthHalfMaxCharge": 1.56,
    "DepthHalfMinCharge": 2.73,
    "RadiusSpawnNearest": 1.2,
    "WidthHalfFullCharge": 1.56,
    "WidthHalfMaxCharge": 1.56,
    "WidthHalfMinCharge": 0.78
},
"SplashSpawnParam": {
    "type":  "spl__BulletSplashChargerSpawnParam",
    "OnTopRateFullCharge": 0.34,
    "OnTopRateMaxCharge": 0.25,
    "OnTopRateMinCharge": 0.125,
    "SkipNum": 1
},
"SplashWallHitParam": {
    "type":  "spl__BulletChargerSplashWallHitParam",
    "WallDropCollisionPaintParam": {
        "PaintRadiusFall": 0.675,
        "PaintRadiusFallMaxCharge": 0.675,
        "PaintRadiusFallMinCharge": 0.45,
        "PaintRadiusGround": 0.4,
        "PaintRadiusShock": 0.9,
        "PaintRadiusShockMaxCharge": 0.9,
        "PaintRadiusShockMinCharge": 0.6,
    },
    "WallDropMoveParam": {
        "FallPeriodFirstFrameMax": 30,
        "FallPeriodFirstFrameMin": 15,
        "FallPeriodFirstTargetSpeed": 0.04,
        "FallPeriodLastFrameMax": 30,
        "FallPeriodLastFrameMin": 15,
        "FallPeriodSecondTargetSpeed": 0.05
    }
},
"WallDropCollisionPaintParam": {
    "type":  "spl__BulletChargerWallDropCollisionPaintParam",
    "PaintRadiusFall": 1.2,
    "PaintRadiusFallMaxCharge": 1.2,
    "PaintRadiusFallMinCharge": 0.8,
    "PaintRadiusShock": 1.8,
    "PaintRadiusShockMaxCharge": 1.8,
    "PaintRadiusShockMinCharge": 1.2
},
"WallDropMoveParam": {
    "type":  "spl__BulletWallDropMoveParam",
    "FallPeriodFirstFrameMin": 15,
    "FallPeriodFirstTargetSpeed": 0.04,
    "FallPeriodLastFrameMax": 30,
    "FallPeriodSecondTargetSpeed": 0.05
},
"WeaponKeepChargeParam": {
    "type": "spl__WeaponKeepChargeParam",
    "KeepChargeFullFrame": 75,
    "KeepChargePreDelayFrame": 23,
    "KeepChargePreDelayFrame_Pre": 18,
    "MuzzleLocalPos": {
        "X": -0.314,
        "Y": 0.2105,
        "Z": 2.0176,
        },
},
"WeaponParam": {
    "type":  "spl__WeaponChargerParam",
    "FreezeFrameFullCharge": 1,
    "FreezeFrameMinCharge": 1,
    "InkConsumeFullCharge": 0.18,
    "InkConsumeMinCharge": 0.0225,
    "JumpHeightFullCharge": 0.07,
    "MoveJumpDownBias": 0.5,
    "MoveSpeedFullCharge": 0.02
},
"spl__WeaponChargerGuideShapeParam": {
    "type":  "spl__WeaponChargerGuideShapeParam"
}
}