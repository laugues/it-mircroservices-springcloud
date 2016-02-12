package org.demo.rest;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoverController {

    @Autowired
    private DiscoveryClient discoveryClient;

    public String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("BACK", false);
        return instance.getHomePageUrl();
    }

    @RequestMapping("/discover")
    public String index() {
        return serviceUrl();
    }

}