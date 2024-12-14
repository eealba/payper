package io.github.eealba.payper.catalog.products.v1.model;


import java.util.Objects;

public class LinkDescription {

    
    private final String href;
    
    private final String rel;
    
    private final Method method;

    private LinkDescription(Builder builder) {
        method = builder.method;
        href = Objects.requireNonNull(builder.href);
        rel = Objects.requireNonNull(builder.rel);
    }

    /**
     * The complete target URL. To make the related call, combine the method with this [URI 
Template-formatted](https://tools.ietf.org/html/rfc6570) link. For pre-processing, include the `$`, `(`, and 
`)` characters. The `href` is the key HATEOAS component that links a completed call with a subsequent call.
     */
    
    public String href() {
        return href;
    }

    /**
     * The [link relation type](https://tools.ietf.org/html/rfc5988#section-4), which serves as an ID for a link that 
unambiguously describes the semantics of the link. See [Link 
Relations](https://www.iana.org/assignments/link-relations/link-relations.xhtml).
     */
    
    public String rel() {
        return rel;
    }

    /**
     * The HTTP method required to make the related call.
     */
    
    public Method method() {
        return method;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String href;
        private String rel;
        private Method method;

        /**
         * The complete target URL. To make the related call, combine the method with this [URI 
Template-formatted](https://tools.ietf.org/html/rfc6570) link. For pre-processing, include the `$`, `(`, and 
`)` characters. The `href` is the key HATEOAS component that links a completed call with a subsequent call.
         */
        
        public Builder href(String value) {
            this.href = value;
            return this;
        }

        /**
         * The [link relation type](https://tools.ietf.org/html/rfc5988#section-4), which serves as an ID for a link that 
unambiguously describes the semantics of the link. See [Link 
Relations](https://www.iana.org/assignments/link-relations/link-relations.xhtml).
         */
        
        public Builder rel(String value) {
            this.rel = value;
            return this;
        }

        /**
         * The HTTP method required to make the related call.
         */
        
        public Builder method(Method value) {
            this.method = value;
            return this;
        }

        public LinkDescription build() {
            return new LinkDescription(this);
        }

    }
    /**
     * The HTTP method required to make the related call.
     */
    public enum Method {
        GET,
        POST,
        PUT,
        DELETE,
        HEAD,
        CONNECT,
        OPTIONS,
        PATCH
    }
}

