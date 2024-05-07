package com.omgd.grpcclient;

import java.io.File;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Example {
    public static void main(String[] args) throws Exception {
        String name = "xxx-grpc";
        File protoFile = new File("./src/main/resources/EventService.proto");
        String protoFileContent = Resources.toString(protoFile.toURI().toURL(), Charsets.UTF_8);
        String address = "127.0.0.1:8889";
//        String address = "esbdev.inpolus.ru:8889";
        int connections = 1;

        // register client
        ClientConfig clientConfig = ClientConfig.create()
                .name(name)
                .protoFileContent(protoFileContent)
                .address(address)
                .connections(connections)
                .build();
        Client.registerClient(clientConfig);

        // grpc call
        String api = "EventService/process";
        // in this example message should be only JSON-type
        String message = "{\"payload\":\"test\"}";
        int timeout = 5000;
        Client client = Client.create()
                .name(name)
                .api(api)
                .paramsJson(message)
                .timeout(timeout)
                .build();
        Response response = client.execute();
        System.out.println(response);
    }
}
