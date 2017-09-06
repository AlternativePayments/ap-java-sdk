package com.alternativepayments.models.transaction;

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

    /**
     * Endpoint for creating transaction using hosted pages.
     */
    public static final String API_HOSTED_PAGES_ENDPOINT = "transactions/hosted/";

    private final Customer customer;
    private final String customerId;
    private final Payment payment;
    private final String paymentId;
    private final String token;
    private final int amount;
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
    private final boolean isRecurring;
    private String initialTransactionId;

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
     * @param paymentId paymentId for transaction
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
     * @param isRecurring is transaction recurring or not
     * @param initialTransactionId initial transaction id
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
            @JsonProperty("paymentId") final String paymentId,
            @JsonProperty("token") final String token,
            @JsonProperty("amount") final int amount,
            @JsonProperty("currency") final String currency,
            @JsonProperty("merchantPassThruData") final String merchantPassThruData,
            @JsonProperty("merchantTransactionId") final String merchantTransactionId,
            @JsonProperty("description") final String description,
            @JsonProperty("ipAddress") final String ipAddress,
            @JsonProperty("status") final String status,
            @JsonProperty("redirectUrls") final RedirectUrls redirectUrls,
            @JsonProperty("redirectUrl") final String redirectUrl,
            @JsonProperty("phoneverification") final PhoneVerification phoneverification,
            @JsonProperty("preauthorization") final Preauthorization preauthorization,
            @JsonProperty("isRecurring") final boolean isRecurring,
            @JsonProperty("initialTransactionId") final String initialTransactionId) {
        super(id, mode, created, updated);
        this.customer = customer;
        this.customerId = customerId;
        this.payment = payment;
        this.paymentId = paymentId;
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
        this.isRecurring = isRecurring;
        this.initialTransactionId = initialTransactionId;
    }

    /**
     * Builder class for Transaction.
     *
     */
    public static class Builder {
        // Required parameters
        private final int amount;
        private final String currency;
        private String ipAddress;

        // Optional parameters - initialize with default values
        private Customer customer;
        private String customerId;
        private String merchantPassThruData;
        private String merchantTransactionId;
        private String description;
        private String status;
        private RedirectUrls redirectUrls;
        private String redirectUrl;
        private PhoneVerification phoneverification;
        private Preauthorization preauthorization;
        private boolean isRecurring;
        private String initialTransactionId;
        private Payment payment;
        private String paymentId;
        private String token;

        /**
         * Constructor that takes on all mandatory parameters.
         *
         * @param amount amount for the transaction
         * @param currency currency for the transaction
         * @param ipAddress ip address
         */
        public Builder(final int amount, final String currency,
                final String ipAddress) {
            this.amount = amount;
            this.currency = currency;
            this.ipAddress = ipAddress;
        }

        /**
         * Set payment for building object.
         *
         * @param payment of the transaction
         *
         * @return Builder
         */
        public Builder payment(final Payment payment) {
            this.payment = payment;
            return this;
        }

        /**
         * Set paymentId for building object.
         *
         * @param paymentId for the transaction
         *
         * @return Builder
         */
        public Builder paymentId(final String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        /**
         * Set token for building object.
         *
         * @param token of the transaction
         *
         * @return Builder
         */
        public Builder token(final String token) {
            this.token = token;
            return this;
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
        public Builder phoneVerification(final PhoneVerification phoneverification) {
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
         * Set recurring for transaction.
         *
         * @param isRecurring is transaction recurring
         *
         * @return Builder
         */
        public Builder isRecurring(final boolean isRecurring) {
            this.isRecurring = isRecurring;
            return this;
        }

        /**
         * Set initial transaction id.
         *
         * @param initialTransactionId initial transaction id
         *
         * @return Builder
         */
        public Builder initialTransactionId(final String initialTransactionId) {
            this.initialTransactionId = initialTransactionId;
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
        this.amount = builder.amount;
        this.currency = builder.currency;

        // Optional parameters
        this.payment = builder.payment;
        this.token = builder.token;
        this.paymentId = builder.paymentId;
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
        this.isRecurring = builder.isRecurring;
        this.initialTransactionId = builder.initialTransactionId;
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
     * @return paymentId for transaction.
     */
    public String getPaymentId() {
        return paymentId;
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
    public int getAmount() {
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

    /**
     * @return the initialTransactionId
     */
    public String getInitialTransactionId() {
        return initialTransactionId;
    }

    /**
     * @param initialTransactionId the initialTransactionId to set
     */
    public void setInitialTransactionId(String initialTransactionId) {
        this.initialTransactionId = initialTransactionId;
    }

    /**
     * @return the isRecurring
     */
    public boolean isRecurring() {
        return isRecurring;
    }
}
