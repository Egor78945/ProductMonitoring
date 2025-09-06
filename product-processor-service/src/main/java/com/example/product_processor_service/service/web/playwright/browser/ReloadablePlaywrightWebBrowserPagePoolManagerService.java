package com.example.product_processor_service.service.web.playwright.browser;

import com.microsoft.playwright.Page;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class ReloadablePlaywrightWebBrowserPagePoolManagerService extends PlaywrightWebBrowserPagePoolManager {
    private final PlaywrightWebBrowserContextHolder webBrowserContextHolder;
    private final BlockingQueue<Page> pageQueue;

    public ReloadablePlaywrightWebBrowserPagePoolManagerService(PlaywrightWebBrowserContextHolder webBrowserContextHolder) {
        this.webBrowserContextHolder = webBrowserContextHolder;
        pageQueue = new ArrayBlockingQueue<>(20);
    }

    @Override
    public Page getPage() {
        if(!pageQueue.isEmpty()){
            return pageQueue.poll();
        } else {
            return webBrowserContextHolder.getBrowserContext().newPage();
        }
    }

    @Override
    public void reloadPage() {
        if(pageQueue.size() < 20) {
            pageQueue.offer(webBrowserContextHolder.getBrowserContext().newPage());
        }
    }

    @Override
    public void reloadPage(Page page) {
        if(pageQueue.size() < 20) {
            pageQueue.offer(page);
        }
    }

    @PostConstruct
    public void initPagePool() {
        for(int i = 0; i < 20; i++) {
            pageQueue.offer(webBrowserContextHolder.getBrowserContext().newPage());
        }
    }
}
