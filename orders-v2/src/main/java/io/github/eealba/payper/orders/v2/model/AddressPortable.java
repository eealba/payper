package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;


/**
 * The portable international postal address. Maps to 
[AddressValidationMetadata](https://github.com/googlei18n/libaddressinput/wiki/AddressValidationMetadata) and HTML 5.1 
[Autofilling form controls: the autocomplete 
attribute](https://www.w3.org/TR/html51/sec-forms.html#autofilling-form-controls-the-autocomplete-attribute).
 */
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

    /**
     * The first line of the address. For example, number or street. For example, `173 Drury Lane`. Required for data 
entry and compliance and risk checks. Must contain the full address.
     */
    @JasonerProperty("address_line_1")
    public String addressLine1() {
        return addressLine1;
    }

    /**
     * The second line of the address. For example, suite or apartment number.
     */
    @JasonerProperty("address_line_2")
    public String addressLine2() {
        return addressLine2;
    }

    /**
     * The third line of the address, if needed. For example, a street complement for Brazil, direction text, such as 
`next to Walmart`, or a landmark in an Indian address.
     */
    @JasonerProperty("address_line_3")
    public String addressLine3() {
        return addressLine3;
    }

    /**
     * The neighborhood, ward, or district. Smaller than `admin_area_level_3` or `sub_locality`. Value is:<ul><li>The 
postal sorting code for Guernsey and many French territories, such as French Guiana.</li><li>The fine-grained 
administrative levels in China.</li></ul>
     */
    @JasonerProperty("admin_area_4")
    public String adminArea4() {
        return adminArea4;
    }

    /**
     * A sub-locality, suburb, neighborhood, or district. Smaller than `admin_area_level_2`. Value is:<ul><li>Brazil. 
Suburb, bairro, or neighborhood.</li><li>India. Sub-locality or district. Street name information is not 
always available but a sub-locality or district can be a very small area.</li></ul>
     */
    @JasonerProperty("admin_area_3")
    public String adminArea3() {
        return adminArea3;
    }

    /**
     * A city, town, or village. Smaller than `admin_area_level_1`.
     */
    @JasonerProperty("admin_area_2")
    public String adminArea2() {
        return adminArea2;
    }

    /**
     * The highest level sub-division in a country, which is usually a province, state, or ISO-3166-2 subdivision. 
Format for postal delivery. For example, `CA` and not `California`. Value, by country, is:<ul><li>UK. A 
county.</li><li>US. A state.</li><li>Canada. A province.</li><li>Japan. A prefecture.</li><li>Switzerland. A 
kanton.</li></ul>
     */
    @JasonerProperty("admin_area_1")
    public String adminArea1() {
        return adminArea1;
    }

    /**
     * The postal code, which is the zip code or equivalent. Typically required for countries with a postal code or 
an equivalent. See [postal code](https://en.wikipedia.org/wiki/Postal_code).
     */
    @JasonerProperty("postal_code")
    public String postalCode() {
        return postalCode;
    }

    /**
     * countryCode
     */
    @JasonerProperty("country_code")
    public CountryCode countryCode() {
        return countryCode;
    }

    /**
     * The non-portable additional address details that are sometimes needed for compliance, risk, or other scenarios 
where fine-grain address information might be needed. Not portable with common third party and open source. 
Redundant with core fields.<br/>For example, `address_portable.address_line_1` is usually a combination of 
`address_details.street_number`, `street_name`, and `street_type`.
     */
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

        /**
         * The first line of the address. For example, number or street. For example, `173 Drury Lane`. Required for data 
entry and compliance and risk checks. Must contain the full address.
         */
        @JasonerProperty("address_line_1")
        public Builder addressLine1(String value) {
            this.addressLine1 = value;
            return this;
        }

        /**
         * The second line of the address. For example, suite or apartment number.
         */
        @JasonerProperty("address_line_2")
        public Builder addressLine2(String value) {
            this.addressLine2 = value;
            return this;
        }

        /**
         * The third line of the address, if needed. For example, a street complement for Brazil, direction text, such as 
`next to Walmart`, or a landmark in an Indian address.
         */
        @JasonerProperty("address_line_3")
        public Builder addressLine3(String value) {
            this.addressLine3 = value;
            return this;
        }

        /**
         * The neighborhood, ward, or district. Smaller than `admin_area_level_3` or `sub_locality`. Value is:<ul><li>The 
postal sorting code for Guernsey and many French territories, such as French Guiana.</li><li>The fine-grained 
administrative levels in China.</li></ul>
         */
        @JasonerProperty("admin_area_4")
        public Builder adminArea4(String value) {
            this.adminArea4 = value;
            return this;
        }

        /**
         * A sub-locality, suburb, neighborhood, or district. Smaller than `admin_area_level_2`. Value is:<ul><li>Brazil. 
Suburb, bairro, or neighborhood.</li><li>India. Sub-locality or district. Street name information is not 
always available but a sub-locality or district can be a very small area.</li></ul>
         */
        @JasonerProperty("admin_area_3")
        public Builder adminArea3(String value) {
            this.adminArea3 = value;
            return this;
        }

        /**
         * A city, town, or village. Smaller than `admin_area_level_1`.
         */
        @JasonerProperty("admin_area_2")
        public Builder adminArea2(String value) {
            this.adminArea2 = value;
            return this;
        }

        /**
         * The highest level sub-division in a country, which is usually a province, state, or ISO-3166-2 subdivision. 
Format for postal delivery. For example, `CA` and not `California`. Value, by country, is:<ul><li>UK. A 
county.</li><li>US. A state.</li><li>Canada. A province.</li><li>Japan. A prefecture.</li><li>Switzerland. A 
kanton.</li></ul>
         */
        @JasonerProperty("admin_area_1")
        public Builder adminArea1(String value) {
            this.adminArea1 = value;
            return this;
        }

        /**
         * The postal code, which is the zip code or equivalent. Typically required for countries with a postal code or 
an equivalent. See [postal code](https://en.wikipedia.org/wiki/Postal_code).
         */
        @JasonerProperty("postal_code")
        public Builder postalCode(String value) {
            this.postalCode = value;
            return this;
        }

        /**
         * countryCode
         */
        @JasonerProperty("country_code")
        public Builder countryCode(CountryCode value) {
            this.countryCode = value;
            return this;
        }

        /**
         * The non-portable additional address details that are sometimes needed for compliance, risk, or other scenarios 
where fine-grain address information might be needed. Not portable with common third party and open source. 
Redundant with core fields.<br/>For example, `address_portable.address_line_1` is usually a combination of 
`address_details.street_number`, `street_name`, and `street_type`.
         */
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

    /**
     * The street number.
     */
    @JasonerProperty("street_number")
    public String streetNumber() {
        return streetNumber;
    }

    /**
     * The street name. Just `Drury` in `Drury Lane`.
     */
    @JasonerProperty("street_name")
    public String streetName() {
        return streetName;
    }

    /**
     * The street type. For example, avenue, boulevard, road, or expressway.
     */
    @JasonerProperty("street_type")
    public String streetType() {
        return streetType;
    }

    /**
     * The delivery service. Post office box, bag number, or post office name.
     */
    @JasonerProperty("delivery_service")
    public String deliveryService() {
        return deliveryService;
    }

    /**
     * A named locations that represents the premise. Usually a building name or number or collection of buildings 
with a common name or number. For example, <code>Craven House</code>.
     */
    @JasonerProperty("building_name")
    public String buildingName() {
        return buildingName;
    }

    /**
     * The first-order entity below a named building or location that represents the sub-premises. Usually a single 
building within a collection of buildings with a common name. Can be a flat, story, floor, room, or apartment.
     */
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

        /**
         * The street number.
         */
        @JasonerProperty("street_number")
        public Builder streetNumber(String value) {
            this.streetNumber = value;
            return this;
        }

        /**
         * The street name. Just `Drury` in `Drury Lane`.
         */
        @JasonerProperty("street_name")
        public Builder streetName(String value) {
            this.streetName = value;
            return this;
        }

        /**
         * The street type. For example, avenue, boulevard, road, or expressway.
         */
        @JasonerProperty("street_type")
        public Builder streetType(String value) {
            this.streetType = value;
            return this;
        }

        /**
         * The delivery service. Post office box, bag number, or post office name.
         */
        @JasonerProperty("delivery_service")
        public Builder deliveryService(String value) {
            this.deliveryService = value;
            return this;
        }

        /**
         * A named locations that represents the premise. Usually a building name or number or collection of buildings 
with a common name or number. For example, <code>Craven House</code>.
         */
        @JasonerProperty("building_name")
        public Builder buildingName(String value) {
            this.buildingName = value;
            return this;
        }

        /**
         * The first-order entity below a named building or location that represents the sub-premises. Usually a single 
building within a collection of buildings with a common name. Can be a flat, story, floor, room, or apartment.
         */
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

