package io.github.eealba.payper.invoices.v2;

import io.github.eealba.payper.invoices.v2.model.Invoice;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.github.eealba.payper.invoices.v2.Util.execute;

public class ModelTest {

    @Test
    void order_request_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/invoice.json", Invoice.class);
    }
}
