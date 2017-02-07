package in.clayfish.printful.models;

import java.util.Date;

import in.clayfish.printful.enums.FileStatus;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class File extends Entity {
    private String stringId;
    private String title;
    private double additionalPrice;

    /**
     * Role of the file in the order
     */
    private String type;
    /**
     * MD5 checksum of the file
     */
    private String hash;

    /**
     * Source URL where the file is downloaded from
     */
    private String url;

    /**
     * File name
     */
    private String filename;

    /**
     * MIME type of the file
     */
    private String mimeType;

    /**
     * Size in bytes
     */
    private int size;

    /**
     * Width in pixels
     */
    private int width;

    /**
     * Height in pixels
     */
    private int height;

    /**
     * integer	Resolution DPI.
     * Note: for vector files this may be indicated as only 72dpi, but it doesn't affect print
     * quality since the vector files are resolution independent.
     */
    private int dpi;

    /**
     * File processing status
     */
    private FileStatus status;

    /**
     * File creation time
     */
    private Date created;

    /**
     * Small thumbnail URL
     */
    private String thumbnailUrl;

    /**
     * Medium preview image URL
     */
    private String previewUrl;

    /**
     * Show file in the Printfile Library (default true)
     */
    private boolean visible;

    public File(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public File(String url) {
        this.url = url;
    }

    public File() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(double additionalPrice) {
        this.additionalPrice = additionalPrice;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public FileStatus getStatus() {
        return status;
    }

    public void setStatus(FileStatus status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String preview_url) {
        this.previewUrl = preview_url;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
