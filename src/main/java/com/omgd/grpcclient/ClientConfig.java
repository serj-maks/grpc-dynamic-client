package com.omgd.grpcclient;

import com.google.common.base.Strings;

/**
 * grpc client configuration
 */
public class ClientConfig {
    // name of the client
    private String name;
    // String .proto-file
    private String protoFileContent;
    // server address
    private String address;
    // number of connections (1, 5, 1000, etc.)
    private int connections;

    private ClientConfig() {

    }

    public void validate() {
        if (Strings.isNullOrEmpty(name) ||
                Strings.isNullOrEmpty(protoFileContent) ||
                Strings.isNullOrEmpty(address) ||
                connections < 0) {
            throw new IllegalArgumentException("GrpcClientConfig is Illegal");
        }
    }

    public static Builder create() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getProtoFileContent() {
        return protoFileContent;
    }

    public String getAddress() {
        return address;
    }

    public int getConnections() {
        return connections;
    }

    // inner class for builder feature
    public static class Builder {
        private String name;
        private String protoFileContent;
        private String address;
        private int connections;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder protoFileContent(String protoFileContent) {
            this.protoFileContent = protoFileContent;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder connections(int connections) {
            this.connections = connections;
            return this;
        }

        public ClientConfig build() {
            ClientConfig config = new ClientConfig();
            config.name = name;
            config.protoFileContent = protoFileContent;
            config.address = address;
            config.connections = connections;
            return config;
        }
    }
}
