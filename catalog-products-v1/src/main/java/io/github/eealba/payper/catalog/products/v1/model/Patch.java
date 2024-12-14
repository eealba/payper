package io.github.eealba.payper.catalog.products.v1.model;


import java.util.Objects;

public class Patch {

    
    private final Op op;
    
    private final String path;
    
    private final Object value;
    
    private final String from;

    private Patch(Builder builder) {
        path = builder.path;
        value = builder.value;
        from = builder.from;
        op = Objects.requireNonNull(builder.op);
    }

    /**
     * The operation.
     */
    
    public Op op() {
        return op;
    }

    /**
     * The <a href="https://tools.ietf.org/html/rfc6901">JSON Pointer</a> to the target document location at which to 
complete the operation.
     */
    
    public String path() {
        return path;
    }

    /**
     * The value to apply. The <code>remove</code> operation does not require a value.
     */
    
    public Object value() {
        return value;
    }

    /**
     * The <a href="https://tools.ietf.org/html/rfc6901">JSON Pointer</a> to the target document location from which 
to move the value. Required for the <code>move</code> operation.
     */
    
    public String from() {
        return from;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Op op;
        private String path;
        private Object value;
        private String from;

        /**
         * The operation.
         */
        
        public Builder op(Op value) {
            this.op = value;
            return this;
        }

        /**
         * The <a href="https://tools.ietf.org/html/rfc6901">JSON Pointer</a> to the target document location at which to 
complete the operation.
         */
        
        public Builder path(String value) {
            this.path = value;
            return this;
        }

        /**
         * The value to apply. The <code>remove</code> operation does not require a value.
         */
        
        public Builder value(Object value) {
            this.value = value;
            return this;
        }

        /**
         * The <a href="https://tools.ietf.org/html/rfc6901">JSON Pointer</a> to the target document location from which 
to move the value. Required for the <code>move</code> operation.
         */
        
        public Builder from(String value) {
            this.from = value;
            return this;
        }

        public Patch build() {
            return new Patch(this);
        }

    }
    /**
     * The operation.
     */
    public enum Op {
        add,
        remove,
        replace,
        move,
        copy,
        test
    }
}

