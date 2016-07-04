package com.alternativepayments.models.transaction;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.alternativepayments.models.BaseModel;
import com.alternativepayments.models.customer.Customer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Transaction class for Alternative Payments SDK.
 */
public class Transaction extends BaseModel {

    /**
     * Endpoint for this resource.
     */
    public static final String API_ENDPOINT = "transactions/";

    private final Customer customer;
    private final String customerId;
    private final Payment payment;
    private final String token;
    private final BigDecimal amount;
    private final String currency;
    private final String merchantPassThruData;
    private final String merchantTransactionId;
    private final String description;
    private final String ipAddress;
    private final String status;
    private final RedirectUrls redirectUrls;
    private final String redirectUrl;
    private final PhoneVerification phoneverification;
    private final Preauthorization preauthorization;

    /**
     * Create new Transaction using fields.
     *
     * @param id of transaction
     * @param mode mode used
     * @param created when is this transaction created
     * @param updated when is this transaction updated
     * @param customer customer for transaction
     * @param customerId id for customer
     * @param payment payment for transaction
     * @param token token for transaction
     * @param amount amount for transaction
     * @param currency used for transaction
     * @param merchantPassThruData merchant's identification
     * @param merchantTransactionId unique ID for payment provided by merchant
     * @param description description of transaction
     * @param ipAddress IP address from where transaction was made
     * @param status status of transaction
     * @param redirectUrls redirect URLs transaction
     * @param redirectUrl redirect url for transaction
     * @param phoneverification phoneverification used for transaction
     * @param preauthorization preauthorization used for transaction
     */
    @JsonCreator
    public Transaction(
            @JsonProperty("id") final String id,
            @JsonProperty("mode") final String mode,
            @JsonProperty("created") final DateTime created,
            @JsonProperty("updated") final DateTime updated,
            @JsonProperty("customer") final Customer customer,
            @JsonProperty("customerId") final String customerId,
            @JsonProperty("payment") final Payment payment,
            @JsonProperty("token") final String token,
            @JsonProperty("amount") final BigDecimal amount,
            @JsonProperty("currency") final String currency,
            @JsonProperty("merchantPassThruData") final String merchantPassThruData,
            @JsonProperty("merchantTransactionId") final String merchantTransactionId,
            @JsonProperty("description") final String description,
            @JsonProperty("ipAddress") final String ipAddress,
            @JsonProperty("status") final String status,
            @JsonProperty("redirectUrls") final RedirectUrls redirectUrls,
            @JsonProperty("redirectUrl") final String redirectUrl,
            @JsonProperty("phoneverification") final PhoneVerification phoneverification,
            @JsonProperty("preauthorization") final Preauthorization preauthorization) {
        super(id, mode, created, updated);
        this.customer = customer;
        this.customerId = customerId;
        this.payment = payment;
        this.token = token;
        this.amount = amount;
        this.currency = currency;
        this.merchantPassThruData = merchantPassThruData;
        this.merchantTransactionId = merchantTransactionId;
        this.description = description;
        this.ipAddress = ipAddress;
        this.status = status;
        this.redirectUrls = redirectUrls;
        this.redirectUrl = redirectUrl;
        this.phoneverification = phoneverification;
        this.preauthorization = preauthorization;
    }

    /**
     * Builder class for Transaction.
     *
     */
    public static class Builder {
        // Required parameters
        private final Payment payment;
        private final String token;
        private final BigDecimal amount;
        private final String currency;

        // Optional parameters - initialize with default values
        private Customer customer;
        private String customerId;
        private String merchantPassThruData;
        private String merchantTransactionId;
        private String description;
        private String ipAddress;
        private String status;
        private RedirectUrls redirectUrls;
        private String redirectUrl;
        private PhoneVerification phoneverification;
        private Preauthorization preauthorization;

        /**
         * Constructor that takes on all mandatory parameters.
         *
         * @param payment payment for the transaction
         * @param token token for the transaction
         * @param amount amount for the transaction
         * @param currency currency for the transaction
         */
        public Builder(final Payment payment, final String token, final BigDecimal amount, final String currency) {
            this.payment = payment;
            this.token = token;
            this.amount = amount;
            this.currency = currency;
        }

        /**
         * Set customer for building object.
         *
         * @param customer of the transaction
         *
         * @return Builder
         */
        public Builder customer(final Customer customer) {
            this.customer = customer;
            return this;
        }

        /**
         * Set customerId for building object.
         *
         * @param customerId for the transaction
         *
         * @return Builder
         */
        public Builder customerId(final String customerId) {
            this.customerId = customerId;
            return this;
        }

        /**
         * Set merchantPassThruData for building object.
         *
         * @param merchantPassThruData of the transaction
         *
         * @return Builder
         */
        public Builder merchantPassThruData(final String merchantPassThruData) {
            this.merchantPassThruData = merchantPassThruData;
            return this;
        }

        /**
         * Set merchantTransactionId for building object.
         *
         * @param merchantTransactionId of the transaction
         *
         * @return Builder
         */
        public Builder merchantTransactionId(final String merchantTransactionId) {
            this.merchantTransactionId = merchantTransactionId;
            return this;
        }

        /**
         * Set description for building object.
         *
         * @param description of the transaction
         *
         * @return Builder
         */
        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * Set ipAddress for building object.
         *
         * @param ipAddress of the transaction
         *
         * @return Builder
         */
        public Builder ipAddress(final String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        /**
         * Set status for building object.
         *
         * @param status of the transaction
         *
         * @return Builder
         */
        public Builder status(final String status) {
            this.status = status;
            return this;
        }

        /**
         * Set redirectUrls for building object.
         *
         * @param redirectUrls of the transaction
         *
         * @return Builder
         */
        public Builder redirectUrls(final RedirectUrls redirectUrls) {
            this.redirectUrls = redirectUrls;
            return this;
        }

        /**
         * Set redirect urls for building object.
         *
         * @param redirectUrl of the transaction
         *
         * @return Builder
         */
        public Builder redirectUrls(final String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        /**
         * Set phone verification for building object.
         *
         * @param phoneverification of the transaction
         *
         * @return Builder
         */
        public Builder phoneerification(final PhoneVerification phoneverification) {
            this.phoneverification = phoneverification;
            return this;
        }

        /**
         * Set preauthorization for building object.
         *
         * @param preauthorization of the transaction
         *
         * @return Builder
         */
        public Builder preauthorization(final Preauthorization preauthorization) {
            this.preauthorization = preauthorization;
            return this;
        }

        /**
         * Build method needed to be executed in order for Transaction to be created.
         *
         *
         * @return newly created Transaction
         */
        public Transaction build() {
            return new Transaction(this);
        }

    }

    private Transaction(final Builder builder) {
        // Required parameters
        this.payment = builder.payment;
        this.token = builder.token;
        this.amount = builder.amount;
        this.currency = builder.currency;

        // Optional parameters
        this.customer = builder.customer;
        this.customerId = builder.customerId;
        this.merchantPassThruData = builder.merchantPassThruData;
        this.merchantTransactionId = builder.merchantTransactionId;
        this.description = builder.description;
        this.ipAddress = builder.ipAddress;
        this.status = builder.status;
        this.redirectUrls = builder.redirectUrls;
        this.redirectUrl = builder.redirectUrl;
        this.phoneverification = builder.phoneverification;
        this.preauthorization = builder.preauthorization;
    }

    /**
     * @return customer for transaction.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @return customer id for transaction.
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @return payment for transaction.
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * @return token for transaction.
     */
    public String getToken() {
        return token;
    }

    /**
     * @return amount for transaction.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @return currency for transaction.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @return merchant's data for transaction.
     */
    public String getMerchantPassThruData() {
        return merchantPassThruData;
    }

    /**
     * @return merchant's transaction id.
     */
    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    /**
     * @return description for transaction.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return ip address of the customer.
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @return status for transaction.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return redirect urls for transaction.
     */
    public RedirectUrls getRedirectUrls() {
        return redirectUrls;
    }

    /**
     * @return redirect url for transaction.
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * @return phone verification for transaction.
     */
    public PhoneVerification getPhoneverification() {
        return phoneverification;
    }

    /**
     * @return preauthorization for transaction.
     */
    public Preauthorization getPreauthorization() {
        return preauthorization;
    }
}
