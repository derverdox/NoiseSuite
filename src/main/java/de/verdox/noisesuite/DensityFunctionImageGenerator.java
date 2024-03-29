package de.verdox.noisesuite;

import de.verdox.noisesuite.noise.Noise;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DensityFunctionImageGenerator {
    private final String name;
    private final int blockRadius;
    private final int width;
    private final int height;
    private final Noise noise1;

    private static final double min = -1.0;
    private static final double max = 1.0;

    public DensityFunctionImageGenerator(String name, int blockRadius, int width, int height, Noise noise) {
        this.name = name;
        this.blockRadius = blockRadius;
        this.width = width;
        this.height = height;
        noise1 = noise;
    }

    public void buildImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Iteriere über alle Pixel im Bild
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Frage den Wert von compute an der aktuellen Position ab
                double value = noise1.compute(blockRadius, x - width / 2, y - height / 2);

                // Interpoliere die Farbe basierend auf dem Wert
                Color color = interpolateColor(value, min, max);

                // Setze die Farbe des Pixels im Bild
                image.setRGB(x, y, color.getRGB());
            }
        }

        // Speichere das generierte Bild
        File outputFile = new File(name + ".png");
        try {
            ImageIO.write(image, "png", outputFile);
            System.out.println("Bild erfolgreich generiert: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern des Bildes: " + e.getMessage());
        }
    }

    // Interpoliere die Farbe basierend auf dem Wert und den Min/Max-Werten
    public static Color interpolateColor(double value, double min, double max) {
        // Berechne den Anteil des Wertes zwischen min und max
        double proportion = (value - min) / (max - min);

        // Interpoliere die Farbe basierend auf dem Wert
        int red, green, blue;
        if (proportion < 0.5) {
            red = 0;
            green = (int) (255 * 2 * proportion);
            blue = (int) (255 * (1 - 2 * proportion));
        } else {
            red = (int) (255 * 2 * (proportion - 0.5));
            green = (int) (255 * (1 - 2 * (proportion - 0.5)));
            blue = 0;
        }

        // Begrenze die Farbwerte auf den Bereich [0, 255]
        red = Math.min(Math.max(red, 0), 255);
        green = Math.min(Math.max(green, 0), 255);
        blue = Math.min(Math.max(blue, 0), 255);

        return new Color(red, green, blue);
    }

}
