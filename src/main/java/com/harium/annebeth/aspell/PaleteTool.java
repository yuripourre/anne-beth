package com.harium.annebeth.aspell;

import com.harium.annebeth.aspell.tools.PaleteExtractor;
import com.harium.annebeth.aspell.tools.ScaleUp;
import com.harium.etyl.Etyl;
import com.harium.etyl.commons.context.Application;

public class PaleteTool extends Etyl {

    public PaleteTool() {
        super(1024, 576);
    }

    public static void main(String[] args) {
        PaleteTool main = new PaleteTool();
        main.setTitle("Extract Palete");
        main.init();
    }

    public Application startApplication() {
        return new PaleteExtractor(w, h);
    }
}
