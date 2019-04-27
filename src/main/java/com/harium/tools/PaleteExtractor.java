package com.harium.tools;

import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PaleteExtractor extends Application {

    boolean loaded = false;
    BufferedImage palete = null;

    public PaleteExtractor(int w, int h) {
        super(w, h);
    }

    public void load() {

    }

    public void draw(Graphics g) {
        if (loaded) {
            //g.drawImage(palete, w / 2 - palete.getWidth() / 2, h / 2 - palete.getHeight() / 2);
        } else {
            g.setColor(Color.BLUE);
            g.drawStringX("Drop images to extract palete!", w / 4);
        }
    }

    @Override
    public void dropFiles(int x, int y, List<File> files) {
        for (File file : files) {

            try {
                System.out.println("Extracting palete from: " + file.getName());
                BufferedImage in = ImageIO.read(file);
                palete = extract(in);

                String filename = file.getName().substring(0, file.getName().indexOf("."));
                System.out.println("Extract: " + filename);
                //File out = new File(file.getParent() + File.separator + filename + "-4x.png");
                //ImageIO.write(scaled, "PNG", out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        loaded = true;
    }

    private BufferedImage extract(BufferedImage in) {
        Set<Integer> colors = new HashSet<Integer>();

        for (int j = 0; j < in.getHeight(); j++) {
            for (int i = 0; i < in.getWidth(); i++) {
                int color = in.getRGB(i, j);
                colors.add(color);
            }
        }

        int size = 10;
        int columns = 2;
        int rows = colors.size() / columns;

        BufferedImage palete = new BufferedImage(columns * size, rows * size, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = palete.createGraphics();
        int index = 0;
        for (Integer color : colors) {
            int x = index % columns;
            int y = index / columns;

            g.setColor(new java.awt.Color(color));
            g.fillRect(x * size, y * size, size, size);

            index++;
        }
        g.dispose();

        return palete;
    }
}
