package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.BaseObject;
import com.harium.annebeth.aspell.object.base.OpenableObject;
import com.harium.annebeth.aspell.ui.DialogManager;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class Washer extends OpenableObject {

    boolean hasSock = false;
    boolean hasPile = false;
    boolean hasDetergent = false;
    boolean hasSoftener = false;

    private static final Color BACKGROUND = new Color(0x32, 0x2b, 0x28);

    private ImageLayer sock;
    private ImageLayer pile;

    public Washer(int x, int y) {
        super(LanguageManager.objectName(Dictionary.WASHER), x, y, 80, 96);
        layer = new ImageLayer("objects/washer.png");
        openLayer = new ImageLayer("objects/washer_open.png");
        sock = new ImageLayer("objects/washer_sock.png");
        pile = new ImageLayer("objects/washer_pile.png");
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(x, y, w, h);

        if (hasPile) {
            pile.simpleDraw(g, x + 24, y + 44);
        }
        if (hasSock) {
            sock.simpleDraw(g, x + 14, y + 54);
        }

        super.draw(g);
    }

    @Override
    public void onUse(BaseObject with) {
        if (!hasSock || !hasPile || !hasSoftener || !hasDetergent) {
            DialogManager.addDialog("It's not ready yet.");
        } else {
            if (isOpen()) {
                DialogManager.addDialog("It should be closed first.");
            } else {
                // TODO Special Effects
                DialogManager.addDialog("Uh oh, this is strange.");
            }
        }
    }
}
