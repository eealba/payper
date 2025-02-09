package io.github.eealba.payper.invoices.v2.model;

import java.util.List;

/**
 * The HTTP `202 Accepted` response.
 */
public class N202Response {


    
    private final List<LinkDescription> links;

    private N202Response(Builder builder) {
        links = builder.links;
    }

    /**
     * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<LinkDescription> links;

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public N202Response build() {
            return new N202Response(this);
        }

    }

}

