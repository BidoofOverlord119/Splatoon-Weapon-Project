GameParameters= {
        "BodyParam": {
            "type": "spl__BulletRollerBodyParam",
            "CollisionParam": {
                "KnockBackOpponent": {
                    "AccelMax":800,
                    "AccelMin":420,
                    "MyVelocityRate":30,
                    "OpponentVelocityRate":4800
                },
                "KnockBackRollerPlayerDamageOff": {
                    "AccelMax":280,
                    "AccelMin":280,
                    "MyVelocityRate":4800,
                    "OpponentVelocityRate":30
                },
                "KnockBackRollerPlayerDamageOn": {
                    "AccelMax":550,
                    "AccelMin":410,
                    "MyVelocityRate":4800,
                    "OpponentVelocityRate":30
                },
                "WidthHalf": 1.4,
            },
            "Damage": 1250,
            "PaintParam": {
                "SpeedMax":0.132,
                "WidthHalfMax":2.8
            },
        "SideParam": {
            "CheckLength":1.3,
            "Radius":0.75
            },
        },
        "BulletAdditionMovePlayerSplashNearestParam": {
            "type": "spl__SpawnBulletAdditionMovePlayerParam",
            "XRate":0.3,
            "YPlusRate":0,
            "ZRate":0.3,
        },
        "KnockBackByCanopyParam": {
            "type": "spl__BulletRollerBodyKnockBackByCanopyParam",
            "KnockBackRollerPlayerDamageOff": {
                "AccelMax":750,
                "AccelMin":700,
                "MyVelocityRate":4800,
                "OpponentVelocityRate":2000
            },
            "KnockBackRollerPlayerDamageOn": {
                "AccelMax":750,
                "AccelMin":700,
                "MyVelocityRate":4800,
                "OpponentVelocityRate":2000
            }
        },
        "MainEffectiveRangeUpParam": {
            "type": "spl__PlayerGearSkillParam_MainEffectiveRangeUp",
            "High":0.7,
            "Mid":0.49
        },
        "MainWeaponSetting": {
            "type": "spl__PlayerGearSkillParam_MainWeaponSetting"
        },
        "VerticalSwingUnitGroupParam": {
            "type": "spl__BulletRollerInkVerticalSwingUnitGroupParam",
            "DamageParam": {
                "DamageRejectEndFrame":45,
                "DamageRejectRate":0.5,
                "DamageRejectStartFrame":30,
                "Inside": {
                    "DamageHighDistance":8.2,
                    "DamageHighValue":1000,
                    "DamageLowDistance":11.2,
                    "DamageLowValue":500,
                    "DamageMaxDistance":5.2,
                    "DamageMaxValue":1500,
                    "DamageMinDistance":13.2,
                    "DamageMinValue":400,
                    "Degree":5,
                    "FinalDamageMinValue":400
                },
                "Outside": {
                "DamageHighDistance":8.2,
                "DamageHighValue":1000,
                "DamageLowDistance":11.2,
                "DamageLowValue":500,
                "DamageMaxDistance":5.2,
                "DamageMaxValue":1500,
                "DamageMinDistance":13.2,
                "DamageMinValue":400,
                "DamageRate":1,
                "Degree":20,
                "FinalDamageMinValue":400
                },
            },
            "SpawnSplashBetweenLength":4.6,
            "SpawnSplashFirstLength":1.2,
            "SpawnSplashNum":5,
            "SplashNearestParam": {
                "SpawnParam": {
                "MaxHeight":0.2,
                "Offset": {
                    "X":0,
                    "Y":-2,
                    "Z":0.5},
                "PaintDepthScale":1.462,
                "PaintWidthHalf":1.462}
            },
            "SplashPaintParam": {
            "DepthScaleMax":3,
            "DepthScaleMin":3,
            "WidthHalf":1.287,
            "WidthHalfNearest":1.287
            },
            "Unit": {
                "0": {
                    "DepletionSpeedRate":0.7,
                    "FourPetalsCenterRadiusRate":0.4667,
                    "FourPetalsPetalRadiusRate":0.3333,
                    "SpawnPositionOffsetHeight":0.5,
                    "SpawnRotateXDegreeBase":7.5,
                    "SpawnRotateYDegree":0,
                    "SpawnSpeedBase":1.8338
                    },
                "1": {
                    "AfterOffsetSpawnRotateXDegree":-2.5,
                    "AfterOffsetSpawnSpeed":-0.2,
                    "BulletNum":2,
                    "DepletionBulletNum":2,
                    "DepletionSpeedRate":0.7,
                    "FourPetalsCenterRadiusRate":0.4667,
                    "FourPetalsPetalRadiusRate":0.3333,
                    "SpawnPositionHeight":0.25,
                    "SpawnPositionOffsetHeight":-0.25,
                    "SpawnRotateXDegreeBase":5,
                    "SpawnRotateYDegree":0,
                    "SpawnSpeedBase":1.6338},
                "2": {
                    "AfterOffsetSpawnRotateXDegree":-2.5,
                    "AfterOffsetSpawnSpeed":-0.2,
                    "BulletNum":2,
                    "DepletionBulletNum":0,
                    "DepletionSpeedRate":0.7,
                    "SpawnPositionHeight":0.25,
                    "SpawnPositionOffsetHeight":-1.25,
                    "SpawnRotateYDegree":0,
                    "SpawnSpeedBase":1.2338,
                    }
            }
        },
        "WeaponRollParam": {
            "type": "spl__WeaponRollerRollParam",
            "DashFrame":90,
            "InkConsumeMaxPerFrame":0.001,
            "InkConsumeMinPerFrame":0.0002,
            "InkRecoverStop":20,
            "SpeedDash":0.132,
            "SpeedDashTurnBreak":0.108,
            "SpeedInkConsumeMax":0.132,
            "SpeedInkConsumeMin":0.02,
            "SpeedNormal":0.108
        },
        "WeaponVerticalSwingParam": {
            "type": "spl__WeaponRollerSwingParam",
            "InkConsume":0.085,
            "InkRecoverStop":60,
            "SwingFrame":26,
            "SwingMoveSpeed":0.048,
        },
        "WeaponWideSwingParam": {
            "type": "spl__WeaponRollerSwingParam",
            "GuideParam": {
                "WidthScale":1.5},
            "InkConsume":0.085,
            "InkRecoverStop":45,
            "SwingFrame":21,
            "SwingMoveSpeed":0.048
        },
        "WideSwingUnitGroupParam": {
            "type": "spl__BulletRollerInkWideSwingUnitGroupParam",
            "DamageParam": {
                "DamageRejectEndFrame":45,
                "DamageRejectRate":0.5,
                "DamageRejectStartFrame":25,
                "Inside": {
                    "DamageHighDistance":7.2,
                    "DamageHighValue":1000,
                    "DamageLowDistance":7.2,
                    "DamageLowValue":1000,
                    "DamageMaxDistance":4.2,
                    "DamageMaxValue":1500,
                    "DamageMinDistance":10.7,
                    "DamageMinValue":350,
                    "Degree":12,
                    "DepletionDamageRate":0.25,
                    "FinalDamageMinValue":350,
                    "InsideDistanceXZ":1.2
                },
                "Outside": {
                "DamageHighDistance":7.2,
                "DamageHighValue":500,
                "DamageLowDistance":7.2,
                "DamageLowValue":500,
                "DamageMaxDistance":2,
                "DamageMaxValue":1000,
                "DamageMinDistance":9.2,
                "DamageMinValue":350,
                "DamageRate":1,
                "DepletionDamageRate":0.25,
                "FinalDamageMinValue":350
                },
            "SplashNearestParam": {
                "SpawnParam": {
                    "MaxHeight":0.2
                },
                "Offset": {
                    "X":0,
                    "Y":-2,
                    "Z":0.5
                },
                "PaintDepthScale":1.5,
                "PaintWidthHalf":1.5
                },
            "Unit":{
                "0": {
                "BulletNum":12,
                "DepletionBulletNum":3,
                "DepletionSpeedRate":0.5,
                "SpawnPositionOffsetHeight":0,
                "SpawnPositionRandomCube":0.1,
                "SpawnPositionWidth":0.8,
                "SpawnSpeedBase":1.05,
                "SpawnSpeedRandom":0.36,
                "SpawnWideDegree":18,
                "SwerveRateBySpeed":0.05,
                },
                "1": {
                "DepletionSpeedRate":0.5,
                "SpawnPositionOffsetHeight":0,
                "SpawnPositionRandomCube":0.1,
                "SpawnPositionWidth":0.4,
                "SpawnSpeedBase":0.48,
                "SpawnSpeedRandom":0.11,
                "SpawnWideDegree":4,
                "SwerveRateBySpeed":0.1,
                }
            },
        "spl__SpawnBulletAdditionMovePlayerParam": {
        "type": "spl__SpawnBulletAdditionMovePlayerParam",
        "ZRate":2}
        }
    }
}