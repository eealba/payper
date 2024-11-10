package io.github.eealba.payper.subscriptions.v1.model;


import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

public class AddressPortable {

    @JasonerProperty("address_line_1")
    private final String addressLine1;
    @JasonerProperty("address_line_2")
    private final String addressLine2;
    @JasonerProperty("address_line_3")
    private final String addressLine3;
    @JasonerProperty("admin_area_4")
    private final String adminArea4;
    @JasonerProperty("admin_area_3")
    private final String adminArea3;
    @JasonerProperty("admin_area_2")
    private final String adminArea2;
    @JasonerProperty("admin_area_1")
    private final String adminArea1;
    @JasonerProperty("postal_code")
    private final String postalCode;
    @JasonerProperty("country_code")
    private final CountryCode countryCode;
    @JasonerProperty("address_details")
    private final AddressDetails addressDetails;

    private AddressPortable(Builder builder) {
        addressLine1 = builder.addressLine1;
        addressLine2 = builder.addressLine2;
        addressLine3 = builder.addressLine3;
        adminArea4 = builder.adminArea4;
        adminArea3 = builder.adminArea3;
        adminArea2 = builder.adminArea2;
        adminArea1 = builder.adminArea1;
        postalCode = builder.postalCode;
        addressDetails = builder.addressDetails;
        countryCode = Objects.requireNonNull(builder.countryCode);
    }

    @JasonerProperty("address_line_1")
    public String addressLine1() {
        return addressLine1;
    }

    @JasonerProperty("address_line_2")
    public String addressLine2() {
        return addressLine2;
    }

    @JasonerProperty("address_line_3")
    public String addressLine3() {
        return addressLine3;
    }

    @JasonerProperty("admin_area_4")
    public String adminArea4() {
        return adminArea4;
    }

    @JasonerProperty("admin_area_3")
    public String adminArea3() {
        return adminArea3;
    }

    @JasonerProperty("admin_area_2")
    public String adminArea2() {
        return adminArea2;
    }

    @JasonerProperty("admin_area_1")
    public String adminArea1() {
        return adminArea1;
    }

    @JasonerProperty("postal_code")
    public String postalCode() {
        return postalCode;
    }

    @JasonerProperty("country_code")
    public CountryCode countryCode() {
        return countryCode;
    }

    @JasonerProperty("address_details")
    public AddressDetails addressDetails() {
        return addressDetails;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String addressLine1;
        private String addressLine2;
        private String addressLine3;
        private String adminArea4;
        private String adminArea3;
        private String adminArea2;
        private String adminArea1;
        private String postalCode;
        private CountryCode countryCode;
        private AddressDetails addressDetails;

        @JasonerProperty("address_line_1")
        public Builder addressLine1(String value) {
            this.addressLine1 = value;
            return this;
        }

        @JasonerProperty("address_line_2")
        public Builder addressLine2(String value) {
            this.addressLine2 = value;
            return this;
        }

        @JasonerProperty("address_line_3")
        public Builder addressLine3(String value) {
            this.addressLine3 = value;
            return this;
        }

        @JasonerProperty("admin_area_4")
        public Builder adminArea4(String value) {
            this.adminArea4 = value;
            return this;
        }

        @JasonerProperty("admin_area_3")
        public Builder adminArea3(String value) {
            this.adminArea3 = value;
            return this;
        }

        @JasonerProperty("admin_area_2")
        public Builder adminArea2(String value) {
            this.adminArea2 = value;
            return this;
        }

        @JasonerProperty("admin_area_1")
        public Builder adminArea1(String value) {
            this.adminArea1 = value;
            return this;
        }

        @JasonerProperty("postal_code")
        public Builder postalCode(String value) {
            this.postalCode = value;
            return this;
        }

        @JasonerProperty("country_code")
        public Builder countryCode(CountryCode value) {
            this.countryCode = value;
            return this;
        }

        @JasonerProperty("address_details")
        public Builder addressDetails(AddressDetails value) {
            this.addressDetails = value;
            return this;
        }

        public AddressPortable build() {
            return new AddressPortable(this);
        }

    }
public static class AddressDetails {

    @JasonerProperty("street_number")
    private final String streetNumber;
    @JasonerProperty("street_name")
    private final String streetName;
    @JasonerProperty("street_type")
    private final String streetType;
    @JasonerProperty("delivery_service")
    private final String deliveryService;
    @JasonerProperty("building_name")
    private final String buildingName;
    @JasonerProperty("sub_building")
    private final String subBuilding;

    private AddressDetails(Builder builder) {
        streetNumber = builder.streetNumber;
        streetName = builder.streetName;
        streetType = builder.streetType;
        deliveryService = builder.deliveryService;
        buildingName = builder.buildingName;
        subBuilding = builder.subBuilding;

    }

    @JasonerProperty("street_number")
    public String streetNumber() {
        return streetNumber;
    }

    @JasonerProperty("street_name")
    public String streetName() {
        return streetName;
    }

    @JasonerProperty("street_type")
    public String streetType() {
        return streetType;
    }

    @JasonerProperty("delivery_service")
    public String deliveryService() {
        return deliveryService;
    }

    @JasonerProperty("building_name")
    public String buildingName() {
        return buildingName;
    }

    @JasonerProperty("sub_building")
    public String subBuilding() {
        return subBuilding;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String streetNumber;
        private String streetName;
        private String streetType;
        private String deliveryService;
        private String buildingName;
        private String subBuilding;

        @JasonerProperty("street_number")
        public Builder streetNumber(String value) {
            this.streetNumber = value;
            return this;
        }

        @JasonerProperty("street_name")
        public Builder streetName(String value) {
            this.streetName = value;
            return this;
        }

        @JasonerProperty("street_type")
        public Builder streetType(String value) {
            this.streetType = value;
            return this;
        }

        @JasonerProperty("delivery_service")
        public Builder deliveryService(String value) {
            this.deliveryService = value;
            return this;
        }

        @JasonerProperty("building_name")
        public Builder buildingName(String value) {
            this.buildingName = value;
            return this;
        }

        @JasonerProperty("sub_building")
        public Builder subBuilding(String value) {
            this.subBuilding = value;
            return this;
        }

        public AddressDetails build() {
            return new AddressDetails(this);
        }

    }

}


}

