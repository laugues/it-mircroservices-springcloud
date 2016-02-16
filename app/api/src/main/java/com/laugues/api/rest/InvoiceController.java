package com.laugues.api.rest;

import com.laugues.models.Invoice;
import com.laugues.models.builder.InvoiceBuilder;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@RestController
public class InvoiceController {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceController.class);



    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    @Qualifier("loadBalancedRestTemplate")
    private RestOperations restTemplate;


    @Autowired
    private InvoiceBuilder invoiceBuilder;

    @RequestMapping("/")
    @HystrixCommand
    public Invoice getInvoice(@PathVariable int id) {

        LOG.debug("Will call getProduct with Hystrix protection");

        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("WORLD", false);
        LOG.debug("GetProduct from URL: {}", instanceInfo.getHomePageUrl());

        List<Invoice> invoices = invoiceBuilder.getInvoices();
        Invoice invoice = invoices.get(0);
        LOG.debug("Invoice found : {}", invoice);

        return invoice;

    }

    @RequestMapping("/discover")
    public String index() {
        return discoveryClient.getNextServerFromEureka("WORLD", false).getHomePageUrl();
    }

    @GET
    @Path("/get")
    @Produces("application/json")
    public List<Invoice> get() {

        LOG.debug(String.format("Getting all invoices ..."));
        List<Invoice> invoices = invoiceBuilder.getInvoices();
        LOG.debug(String.format("Getting all invoices [%d] DONE.", invoices.size()));

        return invoices;
    }



}