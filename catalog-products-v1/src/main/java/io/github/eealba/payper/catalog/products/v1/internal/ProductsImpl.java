package io.github.eealba.payper.catalog.products.v1.internal;

import io.github.eealba.payper.catalog.products.v1.api.Products;
import io.github.eealba.payper.catalog.products.v1.model.ErrorDefault;
import io.github.eealba.payper.catalog.products.v1.model.Product;
import io.github.eealba.payper.catalog.products.v1.model.ProductCollection;
import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.client.RequestSpecsFactory;

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
        var spec = PayperProvider.provider().createSpecBuilder(CreateProduct.class, payper,
                        "/v1/catalogs/products")
                .entityClass(Product.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().post(spec);

    }

    /**
     * Lists products.
     *
     * @return the list products request specification
     */
    @Override
    public ListProducts list() {
        var spec = PayperProvider.provider().createSpecBuilder(ListProducts.class, payper,
                        "/v1/catalogs/products")
                .entityClass(ProductCollection.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().get(spec);
    }

    /**
     * Gets a product.
     *
     * @return the get product request specification
     */
    @Override
    public GetProduct get() {
        var spec = PayperProvider.provider().createSpecBuilder(GetProduct.class, payper,
                        "/v1/catalogs/products/{id}")
                .entityClass(Product.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().get(spec);
    }

    /**
     * Updates a product.
     *
     * @return the update product request specification
     */
    @Override
    public UpdateProduct update() {
        var spec = PayperProvider.provider().createSpecBuilder(UpdateProduct.class, payper,
                        "/v1/catalogs/products/{id}")
                .entityClass(Void.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().patch(spec);
    }

}
