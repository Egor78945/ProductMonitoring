package com.example.product_processor_service.configuration.web.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebClientEnvironment {
    private final String WEB_USER_AGENT;
    private final int WEB_VIEW_WIDTH;
    private final int WEB_VIEW_HEIGHT;

    public WebClientEnvironment(@Value("${web.context.user-agent}") String WEB_USER_AGENT,
                                @Value("${web.context.view.size.width}") int WEB_VIEW_WIDTH,
                                @Value("${web.context.view.size.height}") int WEB_VIEW_HEIGHT) {
        this.WEB_USER_AGENT = WEB_USER_AGENT;
        this.WEB_VIEW_WIDTH = WEB_VIEW_WIDTH;
        this.WEB_VIEW_HEIGHT = WEB_VIEW_HEIGHT;
    }

    public String getWEB_USER_AGENT() {
        return WEB_USER_AGENT;
    }

    public int getWEB_VIEW_WIDTH() {
        return WEB_VIEW_WIDTH;
    }

    public int getWEB_VIEW_HEIGHT() {
        return WEB_VIEW_HEIGHT;
    }
}
