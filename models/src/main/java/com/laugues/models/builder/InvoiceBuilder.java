package com.laugues.models.builder;

import com.laugues.models.Invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InvoiceBuilder {


    private static final Logger log = Logger.getLogger(InvoiceBuilder.class
            .getName());
    private static final List<String> BATCHES = new ArrayList<String>();
    public static final String BATCH_NAME = "20160101_000";
    public static final int BATCH_NUMBER = 2;
    public static final int INVOICE_NUMBER_PER_BATCH = 2;

    private static List<Invoice> invoices;

    static {
        for (int i = 1; i <= BATCH_NUMBER; i++) {
            BATCHES.add(BATCH_NAME + i);
        }
        buildInvoices();
    }

    public final static List<Invoice> buildInvoices() {
        invoices = new ArrayList<Invoice>();
        Date date = new Date();
        BigDecimal totAmount = new BigDecimal(7850.00);
        BigDecimal hAmount = new BigDecimal(6935.44);

        for (String batch : BATCHES) {

            for (int i = 1; i <= INVOICE_NUMBER_PER_BATCH; i++) {

                String id = batch + "_000" + i;

                log.log(Level.SEVERE, String.format("Creating invoice with id %s....", id));


                Invoice invoice = new Invoice(
                        id,
                        "PAID",
                        date,
                        date,
                        id + new Double(Math.random()).intValue(),
                        totAmount,
                        hAmount,
                        "AIMARGUES",
                        "FI",
                        "EUR",
                        "CODE",
                        date,
                        batch,
                        date,
                        "custom1",
                        "custom2",
                        "custom3",
                        date,
                        date,
                        "1008",
                        "FABEMI",
                        "1005",
                        "CIFFREO BONA",
                        0,
                        "ITESOFT",
                        "FR",
                        false);

                invoices.add(invoice);
                log.log(Level.SEVERE, String.format("Creating invoice with id %s DONE.", id));
            }
        }

        log.log(Level.SEVERE, String.format("Number of invoices created is %d", invoices.size()));

        return invoices;
    }


    public List<Invoice> getInvoices() {
        return invoices;
    }
}
