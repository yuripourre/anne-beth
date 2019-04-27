package com.harium.annebeth.laundry;

import com.harium.etyl.Etyl;
import com.harium.etyl.commons.context.Application;
//import com.harium.etyl.sound.MultimediaLoader;
import com.harium.etyl.util.PathHelper;
import com.harium.etyl.util.io.IOHelper;

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
        //MultimediaLoader.getInstance().setUrl(IOHelper.FILE_PREFIX + PathHelper.currentDirectory());
        return new MainMenu(w, h);
        //return new InGame(w, h);
        //return new Credits(w, h);
    }
}
