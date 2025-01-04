package io.github.eealba.payper.catalog.products.v1.internal;

import io.github.eealba.payper.catalog.products.v1.api.Products;
import io.github.eealba.payper.catalog.products.v1.model.ErrorDefault;
import io.github.eealba.payper.catalog.products.v1.model.PatchRequest;
import io.github.eealba.payper.catalog.products.v1.model.Product;
import io.github.eealba.payper.catalog.products.v1.model.ProductCollection;
import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;
import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.spec.RequestSpecImpl;

class ProductsImpl implements Products {
    private final Payper payper;

    ProductsImpl(Payper payper) {
        this.payper = payper;
    }

    /**
     * Creates a product.
     *
     * @return the create product request specification
     */
    @Override
    public CreateProduct create() {
        return new CreateProductImpl(payper);
    }

    /**
     * Lists products.
     *
     * @return the list products request specification
     */
    @Override
    public ListProducts list() {
        return new ListProductsImpl(payper);
    }

    /**
     * Gets a product.
     *
     * @return the get product request specification
     */
    @Override
    public GetProduct get() {
        return new GetProductImpl(payper);
    }

    /**
     * Updates a product.
     *
     * @return the update product request specification
     */
    @Override
    public UpdateProduct update() {
        return new UpdateProductImpl(payper);
    }

    private static class CreateProductImpl extends RequestSpecImpl<CreateProduct, ProductRequestPOST, Product, ErrorDefault>
            implements CreateProduct {
        public CreateProductImpl(Payper payper) {
            super(payper, "/v1/catalogs/products", Product.class, ErrorDefault.class);
        }
        @Override
        protected PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }
    private static class ListProductsImpl extends
            RequestSpecImpl<ListProducts, Void, ProductCollection, ErrorDefault>
            implements ListProducts {
        private ListProductsImpl(Payper payper) {
            super(payper, "/v1/catalogs/products", ProductCollection.class, ErrorDefault.class);
        }


        @Override
        protected PayperRequest.Method getMethod() {
            return PayperRequest.Method.GET;
        }
    }
    private static class GetProductImpl extends RequestSpecImpl<GetProduct, Void, Product, ErrorDefault>
            implements GetProduct {
        private GetProductImpl(Payper payper) {
            super(payper, "/v1/catalogs/products/{id}", Product.class, ErrorDefault.class);
        }


        @Override
        protected PayperRequest.Method getMethod() {
            return PayperRequest.Method.GET;
        }
    }

    private static class UpdateProductImpl extends RequestSpecImpl<UpdateProduct, PatchRequest, Void, ErrorDefault>
            implements UpdateProduct {
        private UpdateProductImpl(Payper payper) {
            super(payper, "/v1/catalogs/products/{id}", Void.class, ErrorDefault.class);
        }

        @Override
        protected PayperRequest.Method getMethod() {
            return PayperRequest.Method.PATCH;
        }
    }
}
