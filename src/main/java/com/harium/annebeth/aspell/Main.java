package com.harium.annebeth.aspell;

import com.harium.annebeth.aspell.tools.ScaleUp;
import com.harium.etyl.Etyl;
import com.harium.etyl.commons.context.Application;

public class Main extends Etyl {

    public Main() {
        super(1024, 576);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setTitle("Anne Beth: Laundry Day");
        main.init();
    }

    public Application startApplication() {
        return new InGame(w, h);
        //return new ScaleUp(w, h);
    }
}
