package in.clayfish.printful.models.includable;

import java.io.Serializable;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class ImageSize implements Serializable {

    private int height;
    private int width;

    public ImageSize(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
