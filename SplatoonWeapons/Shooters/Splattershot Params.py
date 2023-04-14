#ValueMax and ValueMin in DamageParam are



GameParameters = {
    "DamageParam": {
      "$type": "spl__BulletShooterDamageParam",
      "ReduceEndFrame": 40, # Last frame where damage changes
      "ReduceStartFrame": 8, # First frame of damage fall off
      "ValueMax": 360, # Maximum possible damage 
      "ValueMin": 180  # Minimum possible damage
    },
    "MoveParam": {
      "$type": "spl__BulletSimpleMoveParam",
      "FreeGravity": 0.016, # Don't know
      "GoStraightStateEndMaxSpeed": 1.4495, # Set Velocity after GoStraightToBrakeStateFrame fames
      "GoStraightToBrakeStateFrame": 4, # Number of frames before Velocity starts to decrease
      "SpawnSpeed": 2.2 # Initial Velocity
    },
    "WeaponParam": {
      "$type": "spl__WeaponShooterParam",
      "InkConsume": 0.0092, # Ink Consumption per shot
      "Jump_DegBiasDecreaseStartFrame": 25, # Frame that target reticle starts shrinking after jumping 
      "Jump_DegBiasEndFrame": 70, # Frame that target reticle finishes shrinking after jumping
      "Jump_DegBiasMax": 0.4, # The chance to shoot towards the outer reticle while jumping
      "Jump_DegSwerve": 12, # The angle that shots can deviate while jumping
      "MoveSpeed": 0.072, # Movement speed per frame
      "ShotGuideFrame": 8,  # Don't know
      "SquidShotShorteningFrame": 1, # I'm assuming this is the time to shoot from squid form?
      "Stand_DegBiasDecrease": 0.015, # Don't know
      "Stand_DegBiasKf": 0.01, # Don't know
      "Stand_DegBiasMin": 0.01, # Don't know
      "Stand_DegSwerve": 6 # The angle that shots can deviate while on the ground
    }
}

# The variables that I believe aren't going to be used
Unused_GameParameters = {
    "CollisionParam": {
      "$type": "spl__BulletSimpleCollisionParam",
      "ChangeFrameForField": 0,
      "EndRadiusForField": 0.2,
      "EndRadiusForPlayer": 0.2,
      "FriendThroughFrameForPlayer": 0,
      "InitRadiusForField": 0.2,
      "InitRadiusForPlayer": 0.2
    },
    "DamageParam": {
      "$type": "spl__BulletShooterDamageParam",
      "ReduceEndFrame": 40, # Last frame where damage changes
      "ReduceStartFrame": 8, # First frame of damage fall off
      "ValueMax": 360, # Maximum possible damage 
      "ValueMin": 180  # Minimum possible damage
    },
    "MainEffectiveRangeUpParam": {
      "$type": "spl__PlayerGearSkillParam_MainEffectiveRangeUp"
    },
    "MainWeaponSetting": {
      "$type": "spl__PlayerGearSkillParam_MainWeaponSetting"
    },
    "MoveParam": {
      "$type": "spl__BulletSimpleMoveParam",
      "FreeGravity": 0.016, # Don't know
      "GoStraightStateEndMaxSpeed": 1.4495, # Set Velocity after GoStraightToBrakeStateFrame fames
      "GoStraightToBrakeStateFrame": 4, # Number of frames before Velocity starts to decrease
      "SpawnSpeed": 2.2 # Initial Velocity
    },
    "PaintParam": {
      "$type": "spl__BulletShooterPaintParam",
      "DepthScaleMax": 2.24,
      "DepthScaleMaxBreakFree": 2.24,
      "DepthScaleMin": 1.31,
      "DepthScaleMinBreakFree": 1.12,
      "DistanceMiddle": 1.1,
      "WidthHalfFar": 1.71,
      "WidthHalfMiddle": 1.93,
      "WidthHalfNear": 1.93
    },
    "SplashPaintParam": {
      "$type": "spl__BulletSplashShooterPaintParam",
      "DepthMaxDropHeight": 3,
      "DepthMinDropHeight": 10,
      "WidthHalf": 1.472,
      "WidthHalfNearest": 2.0608
    },
    "SplashSpawnParam": {
      "$type": "spl__BulletSplashShooterSpawnParam",
      "ForceSpawnNearestAddNumArray": [4],
      "SpawnBetweenLength": 9.2,
      "SpawnNearestLength": 1.2,
      "SpawnNum": 1.5,
      "SplitNum": 8
    },
    "WallDropCollisionPaintParam": {
      "$type": "spl__BulletWallDropCollisionPaintParam",
      "PaintRadiusFall": 0.65,
      "PaintRadiusGround": 0.6,
      "PaintRadiusShock": 1.56
    },
    "WallDropMoveParam": {
      "$type": "spl__BulletWallDropMoveParam",
      "FallPeriodFirstFrameMax": 40,
      "FallPeriodFirstFrameMin": 20,
      "FallPeriodFirstTargetSpeed": 0.06,
      "FallPeriodLastFrameMax": 35,
      "FallPeriodLastFrameMin": 15,
      "FallPeriodSecondFrame": 10,
      "FallPeriodSecondTargetSpeed": 0.06,
      "FreeGravityType": "value_0_008"
    },
    "WeaponParam": {
      "$type": "spl__WeaponShooterParam",
      "InkConsume": 0.0092, # Ink Consumption per shot
      "Jump_DegBiasDecreaseStartFrame": 25, # Frame that target reticle starts shrinking after jumping 
      "Jump_DegBiasEndFrame": 70, # Frame that target reticle finishes shrinking after jumping
      "Jump_DegBiasMax": 0.4, # The chance to shoot towards the outer reticle while jumping
      "Jump_DegSwerve": 12, # The angle that shots can deviate while jumping
      "MoveSpeed": 0.072, # Movement speed per frame
      "ShotGuideFrame": 8,  # Don't know
      "SquidShotShorteningFrame": 1, # I'm assuming this is the time to shoot from squid form?
      "Stand_DegBiasDecrease": 0.015, # Don't know
      "Stand_DegBiasKf": 0.01, # Don't know
      "Stand_DegBiasMin": 0.01, # Don't know
      "Stand_DegSwerve": 6 # The angle that shots can deviate while on the ground
    },
    "spl__PlayerGearSkillParam_ActionSpecUp_ReduceJumpSwerveRate": {
      "$type": "spl__PlayerGearSkillParam_ActionSpecUp_ReduceJumpSwerveRate"
    },
    "spl__SpawnBulletAdditionMovePlayerParam": {
      "$type": "spl__SpawnBulletAdditionMovePlayerParam",
      "ZRate": 2
    }
}
