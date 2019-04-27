package com.harium.tools;

import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ScaleUp extends Application {

    public ScaleUp(int w, int h) {
        super(w, h);
    }

    public void load() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawStringX("Drop images to scale!", w / 4);
    }

    @Override
    public void dropFiles(int x, int y, List<File> files) {
        for (File file : files) {

            try {
                BufferedImage in = ImageIO.read(file);

                BufferedImage scaled = scale4x(in);

                String filename = file.getName().substring(0, file.getName().indexOf("."));
                System.out.println("Saving: " + filename);
                File out = new File(file.getParent() + File.separator + filename + "-4x.png");
                ImageIO.write(scaled, "PNG", out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedImage scale2x(BufferedImage in) {
        return scalePixelPerfect(in, 2);
    }

    private BufferedImage scale4x(BufferedImage in) {
        return scalePixelPerfect(in, 4);
    }

    private BufferedImage scalePixelPerfect(BufferedImage in, int scale) {
        BufferedImage scaled = new BufferedImage(
                in.getWidth() * scale, in.getHeight() * scale, BufferedImage.TYPE_INT_ARGB);

        for (int j = 0; j < in.getHeight(); j++) {
            for (int i = 0; i < in.getWidth(); i++) {
                int rgb = in.getRGB(i, j);

                int ix = i * scale, iy = j * scale;

                for (int sy = 0; sy < scale; sy++) {
                    for (int sx = 0; sx < scale; sx++) {
                        scaled.setRGB(ix + sx, iy + sy, rgb);
                    }
                }
            }
        }
        return scaled;
    }
}
