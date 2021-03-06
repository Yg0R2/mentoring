package com.epam.training;

public final class ConfigKeys {

    // API URLs

    public static final String API_URL = "/api";

    public static final String CREDIT_CARD_API_URL = API_URL + "/creditCard";

    public static final String CREDIT_CARD_API_PAY_URL = CREDIT_CARD_API_URL + "/pay";

    public static final String PAYFRIEND_API_URL = API_URL + "/payFriend";

    public static final String PAYFRIEND_API_GET_USER_URL = PAYFRIEND_API_URL + "/getUser";

    public static final String PAYFRIEND_API_PAY_URL = PAYFRIEND_API_URL + "/pay";

    // Credit Card remote service URLs

    public static final String CREDIT_CARD_SERVICE_URL = "http://mshm_cc:8080";

    public static final String CREDIT_CARD_PAY_SERVICE_URL = CREDIT_CARD_SERVICE_URL + "/pay";

    // PayFriend remote service URLs

    public static final String PAYFRIEND_SERVICE_URL = "http://mshm_payfriend:8080";

    public static final String PAYFRIEND_GET_USER_URL = PAYFRIEND_SERVICE_URL + "/getUser";

    public static final String PAYFRIEND_PAY_SERVICE_URL = PAYFRIEND_SERVICE_URL + "/pay";

}
