package de.ceifert.indomitable;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by christoph on 06.09.16.
 */
public class DpadButton extends TextButton {
    private boolean isOverChange;

    public DpadButton(String title, TextButtonStyle style) {
        super(title, style);
    }

    public void setIsOver() {
        this.isOverChange = true;
    }

    public void unsetIsOver() {
        this.isOverChange = false;
    }

    @Override
    public boolean isOver() {
        return isOverChange ? true : getClickListener().isOver();
    }
}
