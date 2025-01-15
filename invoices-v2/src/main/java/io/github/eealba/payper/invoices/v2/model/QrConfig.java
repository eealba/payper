package io.github.eealba.payper.invoices.v2.model;



/**
 * The configuration for a QR code.
 */
public class QrConfig {


    
    private final Integer width;
    
    private final Integer height;
    
    private final String action;

    private QrConfig(Builder builder) {
        width = builder.width;
        height = builder.height;
        action = builder.action;

    }

    /**
     * The width, in pixels, of the QR code image. Value is from `150` to `500`.
     */
    
    public Integer width() {
        return width;
    }

    /**
     * The height, in pixels, of the QR code image. Value is from `150` to `500`.
     */
    
    public Integer height() {
        return height;
    }

    /**
     * The type of URL for which to generate a QR code. Valid values are `pay` and `details`.
     */
    
    public String action() {
        return action;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Integer width;
        private Integer height;
        private String action;

        /**
         * The width, in pixels, of the QR code image. Value is from `150` to `500`.
         */
        
        public Builder width(Integer value) {
            this.width = value;
            return this;
        }

        /**
         * The height, in pixels, of the QR code image. Value is from `150` to `500`.
         */
        
        public Builder height(Integer value) {
            this.height = value;
            return this;
        }

        /**
         * The type of URL for which to generate a QR code. Valid values are `pay` and `details`.
         */
        
        public Builder action(String value) {
            this.action = value;
            return this;
        }

        public QrConfig build() {
            return new QrConfig(this);
        }

    }

}

