GameParameters= {
        "BodyParam": {
            "type": "spl__BulletRollerBodyParam",
            "Damage": 1250, # Damage dealt when rolling over the opponent
            "PaintParam": { 
                "SpeedMax":0.132, # The speed at which the player moves while dashing
                "WidthHalfMax":2.8 # Don't know, probably does not matter.
            },
        },
        "WeaponWideSwingParam": {
            "type": "spl__WeaponRollerSwingParam",
            "GuideParam": {
                "WidthScale":1.5},
            "InkConsume":0.085, # Ink consumed by swinging the roller while on the ground
            "InkRecoverStop":45, # No clue, cannot find equivilent  on wiki
            "SwingFrame":21, # Frame length of lifting the roller to swing
            "SwingMoveSpeed":0.048 # While swinging the speed the player is set to
        },
        "WideSwingUnitGroupParam": {
            "type": "spl__BulletRollerInkWideSwingUnitGroupParam",
            "DamageParam": {
                "DamageRejectEndFrame":45, # The final frame at which a projectile's damage is reduced
                "DamageRejectRate":0.5, # The Damage Reduction per frame
                "DamageRejectStartFrame":25, # The first frame of damage reduction for a projectile
                "Inside": {
                    "DamageHighDistance":7.2, # Upper middle threshold for damage range
                    "DamageHighValue":1000, # Damage for the upper middle threshold
                    "DamageLowDistance":7.2, # The lower middle threshold for damage range
                    "DamageLowValue":1000, # Damage for the lower middle threshold
                    "DamageMaxDistance":4.2, # Distance that starts damage reduction, also the last distance at which max damage is dealt
                    "DamageMaxValue":1500, # Damage dealt at the starting distance of damage reduction
                    "DamageMinDistance":10.7, # Distance at which the damage uses the Minimum Damage Value
                    "DamageMinValue":350, # The Minimum Damage Value
                    "Degree":12, # The Max Angle that a projectile can be at before ink starts doing reduced damage
                        # Ink flung from the center of the roller does the greatest damage. Ink that is flung at an angle greater than 12.0 degrees to the left or right will have lower damage. This low-damage ink starts losing damage after traveling a distance of 2.0 units and stops losing damage at 9.2 units. The maximum damage is 100 and the minimum damage is 35.
                    "DepletionDamageRate":0.25, # Rate at which Damage is reduced for said angles
                    "FinalDamageMinValue":350, # Lowest damage for said angles
                },
                "Outside": {
                "DamageHighDistance":7.2, # Upper middle threshold for damage range for the outer angle projectiles 
                "DamageHighValue":500, # Damage for the upper middle threshold for the outer angle projectiles
                "DamageLowDistance":7.2, # Lower middle threshold for damage range for the outer angle projectiles
                "DamageLowValue":500, # Damage for the lower middle threshold for the outer angle projectiles
                "DamageMaxDistance":2, # Distance that starts damage reduction, also the last distance at which max damage is dealt for the outer angle projectiles
                "DamageMaxValue":1000, # Damage dealt at the starting distance of damage reduction for the outer angle projectiles
                "DamageMinDistance":9.2, # Distance at which the damage uses the Minimum Damage Value for the outer angle projectiles
                "DamageMinValue":350, # The Minimum Damage Value for the outer angle projectiles
                "DamageRate":1, # No clue
                "DepletionDamageRate":0.25, # Rate at which Damage is reduced for the outer angle projectiles 
                "FinalDamageMinValue":350 # Lowest damage for the outer angle projectiles
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
            "Unit":{ # Do not know what any of the following parameters are.
                "0": {
                "BulletNum":12,
                "DepletionBulletNum":3, # Don't know
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