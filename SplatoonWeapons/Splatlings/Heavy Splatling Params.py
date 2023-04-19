GameParameters = {
    "DamageParam":  {
        "type": "spl__BulletSpinnerDamageParam",
        "ReduceEndFrame": 19, # Last frame where damage changes during fall off
        "ReduceStartFrame": 11, # First frame of damage fall off
        "ValueFullChargeMax": 300, # Maximum possible damage at full charge
        "ValueMax": 300, # Maximum possible damage 
        "ValueMin": 150 # Minimum possible damage
    },

    "MoveParam":  {
        "type": "spl__BulletSpinnerMoveParam",
        "GoStraightStateEndMaxSpeed": 1.5105, # Set Velocity after GoStraightToBrakeStateFrame fames
        "GoStraightToBrakeStateFrame": 8, # Number of frames before Velocity starts to decrease
        "SpawnSpeed": 1.05, # Initial Velocity minimum for first ring
        "SpawnSpeedFirstLastAndSecond": 2.1, # Max initial velocity for first ring, and initial velocity for second charge ring 
        "SpawnSpeedRandomBias": 0.2, # Don't know, but probably a weight for smth related to the first ring velocity
        "SpawnSpeedRandomRate": 0.12 # Don't know, but it is related to velocity
    },

    "WeaponParam":  {
        "type": "spl__WeaponSpinnerParam",
        "InkConsume": 0.225, # Ink Consumption per shot
        "InkRecoverStop": 40, # Frame cooldown before Ink starts regenerating after firing.
        "Jump_DegBiasDecreaseStartFrame": 25, # Frame that target reticle starts shrinking after jumping 
        "Jump_DegBiasEndFrame": 70, # Frame that target reticle finishes shrinking after jumping
        "Jump_DegBiasMax": 0.3, # The chance to shoot towards the outer reticle while jumping
        "Jump_DegSwerve": 7, # The angle that shots can deviate while jumping
        "MoveSpeed": 0.07, # Movement speed per frame while firing
        
        #Presumably ignore any of the following variables
        "PitchDegBias": 0.4, # Don't know
        "PitchDegSwerve": 1.6, # Don't know
        "PostDelayFrame": 4, # Don't know
        "PreDelayFrame_SquidShot": 4, # Don't know
        "RepeatFrame": 4, # Don't know
        "ShotGuideFrame": 11, # Don't know
        "Stand_DegBiasMax": 0.3, # Don't know
        "Stand_DegBiasMin": 0.1, # Don't know
        "Stand_DegSwerve": 3.3, # Don't know
        "VelGnd_Bias_Charge": 0.9, # Don't know
        "VelGnd_DownRt_Charge": 0.05 # Don't know
    }
}

UnusedParameters = {

    "CollisionParam":  {
        "type": "spl__BulletSimpleCollisionParam",
        "ChangeFrameForField": 0,
        "EndRadiusForField": 0.2,
        "EndRadiusForPlayer": 0.2,
        "FriendThroughFrameForPlayer": 0,
        "InitRadiusForField": 0.2,
        "InitRadiusForPlayer": 0.2
    },

    "DamageParam":  {
        "type": "spl__BulletSpinnerDamageParam",
        "ReduceEndFrame": 19,
        "ReduceStartFrame": 11,
        "ValueFullChargeMax": 300,
        "ValueMax": 300,
        "ValueMin": 150
    },

    "MainEffectiveRangeUpParam":  {
        "type": "spl__PlayerGearSkillParam_MainEffectiveRangeUp_Spinner"
    },

        "MainWeaponSetting":  {
        "type": "spl__PlayerGearSkillParam_MainWeaponSetting",
        "Overwrite_MoveVelRt_Shot_High": 1.35,
        "Overwrite_MoveVelRt_Shot_Low": 1,
        "Overwrite_MoveVelRt_Shot_Mid": 1.175
    },

    "MoveParam":  {
        "type": "spl__BulletSpinnerMoveParam",
        "GoStraightStateEndMaxSpeed": 1.5105,
        "GoStraightToBrakeStateFrame": 8,
        "SpawnSpeed": 1.05,
        "SpawnSpeedFirstLastAndSecond": 2.1,
        "SpawnSpeedRandomBias": 0.2,
        "SpawnSpeedRandomRate": 0.12
    },

    "PaintParam":  {
        "type": "spl__BulletShooterPaintParam",
        "DepthScaleMax": 1.83,
        "DepthScaleMaxBreakFree": 1.67,
        "DepthScaleMin": 1.17,
        "DepthScaleMinBreakFree": 1.17,
        "DistanceMiddle": 1.1,
        "HeightUseDepthScaleMaxBreakFree": 3,
        "WidthHalfFar": 2.04,
        "WidthHalfMiddle": 2.16,
        "WidthHalfNear": 2.16
    },

    "SplashPaintParam":  {
        "type": "spl__BulletSplashShooterPaintParam",
        "DepthMaxDropHeight": 3,
        "DepthMinDropHeight": 10,
        "DepthScaleMax": 1.6,
        "DepthScaleMin": 1.6,
        "WidthHalf": 1.449,
        "WidthHalfNearest": 1.85472
    },

    "SplashSpawnParam":  {
        "type": "spl__BulletSplashShooterSpawnParam",
        "ForceSpawnNearestAddNumArray": [],
        "SpawnBetweenLength": 20,
        "SpawnNearestLength": 0,
        "SpawnNum": 1,
        "SplitNum": 8
    },

    "WallDropCollisionPaintParam":  {
        "type": "spl__BulletWallDropCollisionPaintParam",
        "PaintRadiusFall": 0.65,
        "PaintRadiusGround": 0.6,
        "PaintRadiusShock": 1.3
    },

    "WallDropMoveParam":  {
        "type": "spl__BulletWallDropMoveParam",
        "FallPeriodFirstFrameMax": 30,
        "FallPeriodFirstFrameMin": 15,
        "FallPeriodFirstTargetSpeed": 0.06,
        "FallPeriodLastFrameMax": 30,
        "FallPeriodLastFrameMin": 15,
        "FallPeriodSecondFrame": 5,
        "FallPeriodSecondTargetSpeed": 0.06
    },

    "WeaponParam":  {
        "type": "spl__WeaponSpinnerParam",
        "InkConsume": 0.225,
        "InkRecoverStop": 40,
        "Jump_DegBiasDecreaseStartFrame": 25,
        "Jump_DegBiasEndFrame": 70,
        "Jump_DegBiasMax": 0.3,
        "Jump_DegSwerve": 7,
        "MoveSpeed": 0.07,
        "PitchDegBias": 0.4,
        "PitchDegSwerve": 1.6,
        "PostDelayFrame": 4,
        "PreDelayFrame_SquidShot": 4,
        "RepeatFrame": 4,
        "ShotGuideFrame": 11,
        "Stand_DegBiasMax": 0.3,
        "Stand_DegBiasMin": 0.1,
        "Stand_DegSwerve": 3.3,
        "VelGnd_Bias_Charge": 0.9,
        "VelGnd_DownRt_Charge": 0.05
    },

    "spl__BulletShooterTailLengthParam":  {
        "type": "spl__BulletShooterTailLengthParam",
        "MaxLengthFrame": 3
    },

        "spl__SpawnBulletAdditionMovePlayerParam":  {
        "type": "spl__SpawnBulletAdditionMovePlayerParam"
    }
}