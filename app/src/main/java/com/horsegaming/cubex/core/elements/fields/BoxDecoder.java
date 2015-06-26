package com.horsegaming.cubex.core.elements.fields;

import android.graphics.Point;

import com.horsegaming.cubex.core.interfaces.IReDrawable;

/**
 * Created by Horse on 25.06.2015.
 */
public class BoxDecoder implements IBoxGenerator {
    @Override
    public boolean generate(Box[][] matrix, Point basePosition, Point size, Class<? extends IReDrawable> drawer) {
        return true;
    }
}
