package io.github.eealba.payper.catalog.products.v1.internal;

import io.github.eealba.payper.catalog.products.v1.api.Products;
import io.github.eealba.payper.catalog.products.v1.model.ErrorDefault;
import io.github.eealba.payper.catalog.products.v1.model.Product;
import io.github.eealba.payper.catalog.products.v1.model.ProductCollection;
import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.RequestSpecsFactory;
import io.github.eealba.payper.core.Spec;

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
        var spec = new Spec<>(CreateProduct.class, payper, "/v1/catalogs/products",
                Product.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);

    }

    /**
     * Lists products.
     *
     * @return the list products request specification
     */
    @Override
    public ListProducts list() {
        var spec = new Spec<>(ListProducts.class, payper, "/v1/catalogs/products",
                ProductCollection.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().get(spec);
    }

    /**
     * Gets a product.
     *
     * @return the get product request specification
     */
    @Override
    public GetProduct get() {
        var spec = new Spec<>(GetProduct.class, payper, "/v1/catalogs/products/{id}",
                Product.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().get(spec);
    }

    /**
     * Updates a product.
     *
     * @return the update product request specification
     */
    @Override
    public UpdateProduct update() {
        var spec = new Spec<>(UpdateProduct.class, payper, "/v1/catalogs/products/{id}",
                Void.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().patch(spec);
    }

}
