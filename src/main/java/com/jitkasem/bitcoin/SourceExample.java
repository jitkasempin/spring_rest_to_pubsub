package com.jitkasem.bitcoin;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import com.jitkasem.bitcoin.model.Etherum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.*;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@EnableBinding(Source.class)
@RestController
public class SourceExample {

    @Autowired
    private Source source;

    private static final Logger log = LoggerFactory.getLogger(SourceExample.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong fSeq = new AtomicLong();

    @RequestMapping("/greeting")
    public EthbookData greeting(@RequestParam(value="name", defaultValue="World") String name) throws IOException {


        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Etherum> response = restTemplate.exchange(
                "https://min-api.cryptocompare.com/data/pricehistorical?fsym=ETH&tsyms=BTC,USD,EUR",
                HttpMethod.GET, entity, Etherum.class);


        Etherum et = response.getBody();

        EthbookData ethbookData = new EthbookData(counter.incrementAndGet(),
                                                  (float)et.getCrypto().getUSD(),
                                                  LocalDateTime.now());


        this.source.output().send(new GenericMessage<>(ethbookData));

        return ethbookData;

    }
}