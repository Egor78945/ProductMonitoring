package com.example.product_processor_service.util.mapper;

public class UrlMapper {
    public static String extractBaseUrl(String url) {
        int c = 0;
        int i = 0;
        while (i < url.length()) {
            if (url.charAt(i) == '/') {
                c++;
            }
            if (c == 3) {
                break;
            }
            i++;
        }
        return url.substring(0, i);
    }
}
