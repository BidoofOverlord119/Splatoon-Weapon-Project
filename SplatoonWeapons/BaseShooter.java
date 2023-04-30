package SplatoonWeapons;

public abstract class BaseShooter implements Weapon {
    /**
     * Calculate how much damage a shot should do with falloff.
     * Uses the falloffStats array for falloff parameters.
     *
     * @param frames               How many frames the shot has been in the air.
     * @param baseDamage           Base damage of the weapon, from ValueMax.
     * @param falloffStartingFrame Frame at which falloff starts taking effect.
     * @param falloffEndingFrame   Frame at which falloff stops taking effect.
     * @param minimumDamage        Minimum damage due to falloff.
     * @return Falloff-adjusted damage.
     */
    public int calculateFalloff(int frames, int baseDamage, int falloffStartingFrame, int falloffEndingFrame,
                                int minimumDamage) {
        if (!(frames >= 0)) {
            throw new IllegalArgumentException("Frames must be at least 0");
        }
        if (!(baseDamage >= 0)) {
            throw new IllegalArgumentException("Base damage must be at least 0");
        }

        if (!(falloffStartingFrame >= 0)) {
            throw new IllegalArgumentException("Falloff starting frame must be at least 0");
        }
        if (!(falloffEndingFrame >= falloffStartingFrame)) {
            throw new IllegalArgumentException("Falloff ending frame must be at least equal to ending frame");
        }
        if (!(minimumDamage >= 0)) {
            throw new IllegalArgumentException("Falloff minimum damage must be at least 0");
        }

        if (frames <= falloffStartingFrame) {
            return baseDamage;
        } else if (frames > falloffEndingFrame) {
            return minimumDamage;
        }

        // calculate damage lost per frame
        double lostPerFrame = (baseDamage - minimumDamage) / (double) (falloffEndingFrame - falloffStartingFrame);
        return (int) (baseDamage - ((frames - falloffStartingFrame) * lostPerFrame));
    }

    /**
     * Calculate the horizontal angle that the current shot should be fired at, due to shot deviation.
     * Uses the shotDeviationStats array for shot deviation parameters.
     *
     * @param previousShots           How many shots have been previously fired by the weapon.
     * @param deviationMinOuterChance Minimum chance that the shot goes towards the outer reticle.
     * @param deviationMaxOuterChance Maximum chance that the shot goes towards the outer reticle.
     * @param deviationChangePerShot  The amount that the deviation chance goes up by per shot.
     * @param deviationAngle          The maximum angle that the shot can deviate. Dictates the size of the
     *                                outer reticle.
     * @return The angle that the shot should be fired at.
     */
    public double calculateShotDeviation(int previousShots, double deviationMinOuterChance,
                                         double deviationMaxOuterChance, double deviationChangePerShot,
                                         double deviationAngle) {
        if (!(previousShots >= 0)) {
            throw new IllegalArgumentException("Previous shots must be at least 0");
        }

        if (!(deviationMinOuterChance >= 0.0)) {
            throw new IllegalArgumentException("Shot deviation minimum chance must be at least 0");
        }
        if (!(deviationMinOuterChance <= 1.0)) {
            throw new IllegalArgumentException("Shot deviation minimum chance must be at most 1.0");
        }
        if (!(deviationMaxOuterChance >= deviationMinOuterChance)) {
            throw new IllegalArgumentException("Shot deviation maximum chance must be at least equal to starting chance");
        }
        if (!(deviationMaxOuterChance <= 1.0)) {
            throw new IllegalArgumentException("Shot deviation maximum chance must be at most 1.0");
        }
        if (!(deviationChangePerShot >= 0)) {
            throw new IllegalArgumentException("Shot deviation chance change per shot must be at least 0");
        }
        if (!(deviationAngle >= 0)) {
            throw new IllegalArgumentException("Shot deviation angle must be at least 0");
        }

        double shotDeviationChance = Math.min(deviationMinOuterChance + (deviationChangePerShot * previousShots),
                deviationMaxOuterChance); // enforce the maximum
        if (Math.random() < shotDeviationChance) {
            // Shot deviation seems to be a random angle with an even distribution, but can't confirm
            // Return a random number between -deviationAngle and +deviationAngle
            return ((Math.random() - 0.5) * 2) * deviationAngle;
        } else return 0.0;
    }

    /**
     * Calculate how far a shot should have travelled based on how many frames it has been in the air.
     *
     * @param frames              How many frames the shot has been in the air.
     * @param initialVelocity     Initial velocity of the shot, in distance units per frame.
     * @param initialVelocityTime Amount of frames that the shot will travel at the initial velocity.
     * @param slowVelocity        The slower velocity that the shot will slow down to after initialVelocityTime.
     * @return How far the shot has travelled, in distance units.
     */
    public double calculateDistance(int frames, double initialVelocity, int initialVelocityTime, double slowVelocity) {
        if (!(frames >= 0)) {
            throw new IllegalArgumentException("Frames must be at least 0");
        }

        if (!(initialVelocity > 0)) {
            throw new IllegalArgumentException("Velocity starting value must be greater than 0");
        }
        if (!(initialVelocityTime > 0)) {
            throw new IllegalArgumentException("Velocity starting value time must be greater than 0");
        }
        if (!(slowVelocity > 0)) {
            throw new IllegalArgumentException("Velocity slow speed must be greater than 0");
        }

        int firstPhaseFrames = Math.min(frames, initialVelocityTime);
        int secondPhaseFrames = Math.max(frames - initialVelocityTime, 0);
        // second phase distance seems to max out at 2*slowVelocity units
        // We approximated that it follows the infinite series 1 + 1/2 + 1/4 + 1/8 ....
        double secondPhaseMultiplier = 2 * (1 - (1 / Math.pow(2, secondPhaseFrames)));
        return (initialVelocity * firstPhaseFrames) + (slowVelocity * secondPhaseMultiplier);
    }

    /**
     * Calculates damage dealt by a single shot to a target, based on where the target is.
     * Returns 0 if the shot doesn't hit.
     *
     * @param targetDistance          Distance from the player to the target, in distance units.
     * @param targetXOffset           Left-right offset of the target, in distance units.
     * @param previousShots           How many shots have been fired by the weapon already, for shot deviation.
     * @param baseDamage              Base damage of the weapon.
     * @param falloffStartingFrame    Frame at which falloff starts taking effect.
     * @param falloffEndingFrame      Frame at which falloff stops taking effect.
     * @param minimumDamage           Minimum damage due to falloff.
     * @param deviationMinOuterChance Minimum chance that the shot goes towards the outer reticle.
     * @param deviationMaxOuterChance Maximum chance that the shot goes towards the outer reticle.
     * @param deviationChangePerShot  The amount that the deviation chance goes up by per shot.
     * @param deviationAngle          The maximum angle that the shot can deviate. Dictates the size of the
     *                                outer reticle.
     * @param initialVelocity         Initial velocity of the shot, in distance units per frame.
     * @param initialVelocityTime     Amount of frames that the shot will travel at the initial velocity.
     * @param slowVelocity            The slower velocity that the shot will slow down to after initialVelocityTime.
     * @return Damage dealt by the shot. 0 if a miss.
     */
    public int calculateHit(double targetDistance, double targetXOffset, int previousShots, int baseDamage,
                            int falloffStartingFrame, int falloffEndingFrame, int minimumDamage,
                            double deviationMinOuterChance, double deviationMaxOuterChance,
                            double deviationChangePerShot, double deviationAngle, double initialVelocity,
                            int initialVelocityTime, double slowVelocity) {
        if (!(targetDistance >= 0)) {
            throw new IllegalArgumentException("Target distance must be at least 0");
        }
        if (!(previousShots >= 0)) {
            throw new IllegalArgumentException("Previous shots must be at least 0");
        }

        double targetSize = 0.7; // (horizontal) size of target in distance units
        if (targetDistance > calculateRange(initialVelocity, initialVelocityTime, slowVelocity)) {
            return 0;
        }
        // The shot's angle must be between these two to hit
        // Math.atan is in radians, needs to be converted to degrees
        double targetAngleLeft = Math.toDegrees(Math.atan((targetXOffset - (0.5 * targetSize)) / targetDistance));
        double targetAngleRight = Math.toDegrees(Math.atan((targetXOffset + (0.5 * targetSize)) / targetDistance));
        double shotDeviationAngle = calculateShotDeviation(previousShots, deviationMinOuterChance,
                deviationMaxOuterChance, deviationChangePerShot, deviationAngle);
        if (shotDeviationAngle <= targetAngleLeft || shotDeviationAngle >= targetAngleRight) {
            return 0;
        }
        for (int frames = 0; frames < 60; frames++) {
            // todo: calculate when the shot should stop based on velocity
            // doesn't really seem to matter as this loop should always return at some value
            // 60 frames should be more than enough
            double shotDistance = calculateDistance(frames, initialVelocity, initialVelocityTime, slowVelocity);
            if (shotDistance >= targetDistance) {
                return calculateFalloff(frames, baseDamage, falloffStartingFrame, falloffEndingFrame, minimumDamage);
            }
        }
        return 0;
    }

    /**
     * Calculates the maximum range of the weapon.
     *
     * @return Maximum weapon range in distance units.
     */
    public double calculateRange(double initialVelocity, int initialVelocityTime, double slowVelocity) {
        return (initialVelocity * initialVelocityTime) + (slowVelocity * 2);
    }
}
