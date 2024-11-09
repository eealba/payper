package io.github.eealba.payper.subscriptions.v1.model;


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

    
    public Op op() {
        return op;
    }

    
    public String path() {
        return path;
    }

    
    public Object value() {
        return value;
    }

    
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

        
        public Builder op(Op value) {
            this.op = value;
            return this;
        }

        
        public Builder path(String value) {
            this.path = value;
            return this;
        }

        
        public Builder value(Object value) {
            this.value = value;
            return this;
        }

        
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

