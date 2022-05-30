public class Configuration {
    private ImageFixing.BoundaryType type;
    private String originalImagePath;
    private String fixedImagePath;
    private String maskImagePath;
    private Double z;
    private Double epsilon;
    private ImageFixing.Mode mode;

    public Configuration(ImageFixing.BoundaryType type, String originalImagePath, String fixedImagePath, String maskImagePath, Double z, Double epsilon, ImageFixing.Mode mode) {
        this.type = type;
        this.originalImagePath = originalImagePath;
        this.fixedImagePath = fixedImagePath;
        this.maskImagePath = maskImagePath;
        this.z = z;
        this.epsilon = epsilon;
        this.mode = mode;
    }
    public Configuration(String originalImagePath, String fixedImagePath, String maskImagePath) {
        this.type = ImageFixing.BoundaryType.EIGHT;
        this.originalImagePath = originalImagePath;
        this.fixedImagePath = fixedImagePath;
        this.maskImagePath = maskImagePath;
        this.z = 3.0;
        this.epsilon = 0.01;
        this.mode = ImageFixing.Mode.SECOND;
    }

    public ImageFixing.BoundaryType getType() {
        return type;
    }

    public String getOriginalImagePath() {
        return originalImagePath;
    }

    public String getFixedImagePath() {
        return fixedImagePath;
    }

    public String getMaskImagePath() {
        return maskImagePath;
    }

    public Double getZ() {
        return z;
    }

    public Double getEpsilon() {
        return epsilon;
    }

    public ImageFixing.Mode getMode() {
        return mode;
    }
}
