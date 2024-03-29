package de.verdox.noisesuite;

import de.verdox.noisesuite.noise.CircularBiomeDistribution;

public class Main {
    public static void main(String[] args) {
        new DensityFunctionImageGenerator("simple", 3000, 5000, 5000, CircularBiomeDistribution.INSTANCE).buildImage();
    }
}
