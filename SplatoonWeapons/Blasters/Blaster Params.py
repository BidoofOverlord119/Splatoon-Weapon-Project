GameParameters = {
    "BlastParam": {
        "type": "spl__BulletBlastParam",
        "CrossPaintCheckLength": 0, # Don't know
        "CrossPaintRadius": 0, # Don't know
        "DamageAttackerPriority": "true", # Don't know
        "DamageLinear": "true", # Probably means that the damage it deals is linear.
        "DistanceDamage": [{"Damage": 700, "Distance": 0.94}, {"Damage": 500, "Distance": 3.3}], # AOE Damage Values and how far they activate
        "KnockBackParam": {
            "Accel": 700, # Don't know
            "Bias": 0.8, # Don't know
            "Distance": 3.3, # Don't know
            "PaintRadius": 0 # Don't know
        }
    },
    "BlasterBurstParam": {
        "type": "spl__BulletBlasterBurstParam",
        "SplashDropPaintShotColHitRadius": 2.5,  # Radius of impact against the wall?
        "SplashRoundAxisXArray": [-90, -45, 0, 45], # X coords for the levels of splash 
        "SplashRoundAxisYArray": [0, 30, 60, 90, 120] # Y coords for the levels of splash damage
    },
    "DamageParam": {
        "type": "spl__BulletShooterDamageParam",
        "ReduceEndFrame": 99, # Last frame where damage changes
        "ReduceStartFrame": -1, # First frame of damage fall off
        "ValueMax": 1250, # Maximum possible damage (direct hit)
        "ValueMin": 1250  # Minimum possible damage (direct hit)
    },
    "MoveParam": {
        "type": "spl__BulletSimpleMoveParam",
        "FreeGravity": 0.016, # Don't know
        "GoStraightStateEndMaxSpeed": 0.9131, # Set Velocity after GoStraightToBrakeStateFrame fames
        "GoStraightToBrakeStateFrame": 9, # Number of frames before Velocity starts to decrease
        "SpawnSpeed": 0.945 # Initial Velocity
    },
    "WeaponParam": {
        "type": "spl__WeaponShooterParam",
        "InkConsume": 0.1, # Ink Consumption per shot
        "InkRecoverStop": 60, # Unknown as of now
        "Jump_DegBiasDecreaseStartFrame": 25, # Frame that target reticle starts shrinking after jumping 
        "Jump_DegBiasEndFrame": 70, # Frame that target reticle finishes shrinking after jumping
        "Jump_DegBiasMax": 0.5, # The chance to shoot towards the outer reticle while jumping
        "Jump_DegSwerve": 10, # The angle that shots can deviate while jumping
        "MoveSpeed": 0.045, # Movement speed per frame while firing
        "PostDelayFrame": 4, # Don't know
        "PreDelayFrame_HumanShot": 10, # Don't know
        "PreDelayFrame_SquidShot": 15, # Don't know
        "RepeatFrame": 50, # Don't know
        "ShotGuideFrame": 13, # Don't know
        "Stand_DegBiasDecrease": 0, # Don't know
        "Stand_DegBiasKf": 0, # Don't know
        "Stand_DegBiasMax": 0, # Don't know
        "Stand_DegBiasMin": 0, # Don't know
        "Stand_DegSwerve": 0 # The angle that shots can deviate while on the ground
    },
}




unusedParameters = {
    "BlastParam": {
        "type": "spl__BulletBlastParam",
        "CrossPaintCheckLength": 0,
        "CrossPaintRadius": 0,
        "DamageAttackerPriority": "true",
        "DamageLinear": "true",
        "DistanceDamage": [{"Damage": 700, "Distance": 0.94}, {"Damage": 500, "Distance": 3.3}],
        "KnockBackParam": {
            "Accel": 700,
            "Bias": 0.8,
            "Distance": 3.3,
            "PaintRadius": 0}
    },
    "BlasterBurstParam": {
        "type": "spl__BulletBlasterBurstParam",
        "SplashDropPaintShotColHitRadius": 2.5,
        "SplashRoundAxisXArray": [-90,-45,0,45],
        "SplashRoundAxisYArray": [0, 30, 60, 90, 120]
    },
    "SplashWallDropMoveParam": {
        "FallPeriodFirstFrameMax": 1,
        "FallPeriodFirstFrameMin": 1,
        "FallPeriodFirstTargetSpeed": 0,
        "FallPeriodLastFrameMax": 1,
        "FallPeriodLastFrameMin": 1,
        "FallPeriodSecondFrame": 1,
        "FallPeriodSecondTargetSpeed": 0
    },
    "SplashWallDropPaintParam": {
        "PaintRadiusFall": 0,
        "PaintRadiusGround": 0,
        "PaintRadiusShock": 1.2
    },
    "CollisionParam": {
        "type": "spl__BulletSimpleCollisionParam",
        "ChangeFrameForField": 0,
        "EndRadiusForField": 0.2,
        "EndRadiusForPlayer": 0.2,
        "FriendThroughFrameForPlayer": 1000,
        "InitRadiusForField": 0.2,
        "InitRadiusForPlayer": 0.2
    },
    "DamageParam": {
        "type": "spl__BulletShooterDamageParam",
        "ReduceEndFrame": 99,
        "ReduceStartFrame": -1,
        "ValueMax": 1250,
        "ValueMin": 1250
    },
    "MainEffectiveRangeUpParam": {
        "type": "spl__PlayerGearSkillParam_MainEffectiveRangeUp"
    },
    "MainWeaponSetting": {
        "type": "spl__PlayerGearSkillParam_MainWeaponSetting"
    },
    "MoveParam": {
        "type": "spl__BulletSimpleMoveParam",
        "FreeGravity": 0.016,
        "GoStraightStateEndMaxSpeed": 0.9131,
        "GoStraightToBrakeStateFrame": 9,
        "SpawnSpeed": 0.945
    },
    "PaintParam": {
        "type": "spl__BulletShooterPaintParam",
        "DistanceMiddle": 0,
        "WidthHalfFar": 0,
        "WidthHalfMiddle": 0,
        "WidthHalfNear": 0
    },
    "SplashPaintParam": {
        "type": "spl__BulletSplashShooterPaintParam",
        "DepthMaxDropHeight": 3,
        "DepthMinDropHeight": 10,
        "WidthHalf": 1.62,
        "WidthHalfNearest": 2.43
    },
    "SplashSpawnParam": {
        "type": "spl__BulletSplashShooterSpawnParam",
        "ForceSpawnNearestAddNumArray": "Array[0]",
        "SpawnBetweenLength": 1.5,
        "SpawnNearestLength": 0.5,
        "SpawnNum": 7,
        "SplitNum": 1
    },
    "SplashWallHitParam": {
        "type": "spl__BulletSplashWallHitParam",
        "SpawnParam": {
            "FirstDistance": 1.8,
            "VelocityMinusYRate": 0.4
        },
        "WallDropCollisionPaintParam": {
            "FallPeriodFirstSecondTargetAlp": 0.5,
            "PaintRadiusFall": 1,
            "PaintRadiusShock": 1.4
        },
        "WallDropMoveParam": {
            "FallPeriodFirstFrameMin": 15,
            "FallPeriodFirstTargetSpeed": 0.07,
            "FallPeriodLastFrameMax": 35,
            "FallPeriodLastFrameMin": 20,
            "FallPeriodSecondFrame": 35,
            "FallPeriodSecondTargetSpeed": 0.04
        }
    },
    "WallDropCollisionPaintParam": {
        "type": "spl__BulletWallDropCollisionPaintParam",
        "FallPeriodFirstSecondTargetAlp": 0.5,
        "PaintRadiusFall": 1,
        "PaintRadiusGround": 0.6,
        "PaintRadiusShock": 1.3
    },
    "WallDropMoveParam": {
        "type": "spl__BulletWallDropMoveParam",
        "FallPeriodFirstFrameMax": 30,
        "FallPeriodFirstFrameMin": 15,
        "FallPeriodFirstTargetSpeed": 0.07,
        "FallPeriodLastFrameMax": 35,
        "FallPeriodLastFrameMin": 20,
        "FallPeriodSecondFrame": 35,
        "FallPeriodSecondTargetSpeed": 0.04
    },
    "WeaponParam": {
        "type": "spl__WeaponShooterParam",
        "InkConsume": 0.1,
        "InkRecoverStop": 60,
        "Jump_DegBiasDecreaseStartFrame": 25,
        "Jump_DegBiasEndFrame": 70,
        "Jump_DegBiasMax": 0.5,
        "Jump_DegSwerve": 10,
        "MoveSpeed": 0.045,
        "PostDelayFrame": 4,
        "PreDelayFrame_HumanShot": 10,
        "PreDelayFrame_SquidShot": 15,
        "RepeatFrame": 50,
        "ShotGuideFrame": 13,
        "Stand_DegBiasDecrease": 0,
        "Stand_DegBiasKf": 0,
        "Stand_DegBiasMax": 0,
        "Stand_DegBiasMin": 0,
        "Stand_DegSwerve": 0
    },
    "spl__PlayerGearSkillParam_ActionSpecUp_ReduceJumpSwerveRate": {
        "type": "spl__PlayerGearSkillParam_ActionSpecUp_ReduceJumpSwerveRate",
        "Mid": 0.5
    },
    "spl__SpawnBulletAdditionMovePlayerParam": {
        "type": "spl__SpawnBulletAdditionMovePlayerParam",
        "ZRate": 2,
    }
}