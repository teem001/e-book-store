package com.example.mybookstor.config.paypal;



import java.util.HashMap;
import java.util.Map;


public class PayPalConfig {
    private String clientId;
    private String clientSecret;
    private String mode;

    public Map<String, String> payPalConfigMap()
    {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode",mode);
        return configMap;
    }


}
