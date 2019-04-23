package com.harium.annebeth.aspell;

import com.harium.annebeth.aspell.tools.ScaleUp;
import com.harium.etyl.Etyl;
import com.harium.etyl.commons.context.Application;

public class ScaleUpTool extends Etyl {

    public ScaleUpTool() {
        super(1024, 576);
    }

    public static void main(String[] args) {
        ScaleUpTool main = new ScaleUpTool();
        main.setTitle("Scale Pixel Perfect");
        main.init();
    }

    public Application startApplication() {
        //return new InGame(w, h);
        return new ScaleUp(w, h);
    }
}
