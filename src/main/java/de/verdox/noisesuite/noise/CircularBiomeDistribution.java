package de.verdox.noisesuite.noise;

public class CircularBiomeDistribution implements Noise {
    public static final CircularBiomeDistribution INSTANCE = new CircularBiomeDistribution();
    @Override
    public double compute(int blockRadius, int x, int z) {
/*
        int blockRadius = (int) (3000 + Math.sin(3 * x) + Math.sin(Math.PI * x) + Math.sin(4 * Math.E * x) + Math.sin(3 * z) + Math.sin(Math.PI * z) + Math.sin(4 * Math.E * z));
*/

        double scale = 2 * Math.PI / blockRadius;
        double distance = Math.sqrt(x * x + z * z);
        double angle = distance * scale;

        return Math.cos(angle + Math.PI);
    }
}
