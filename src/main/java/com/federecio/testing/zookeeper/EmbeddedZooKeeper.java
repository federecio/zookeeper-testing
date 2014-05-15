package com.federecio.testing.zookeeper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.junit.rules.ExternalResource;

public class EmbeddedZooKeeper extends ExternalResource {

    private static final String HOST = "localhost";
    private static final int TICK_TIME = 500;
    private static final int MAXCC = 10;
    private final int port;
    private ZooKeeperServer zooKeeperServer;

    public EmbeddedZooKeeper() {
        port = allocatePort();
    }

    public EmbeddedZooKeeper(int port) {
        this.port = port;
    }

    public String getZkConnectionString() {
        return HOST + ':' + port;
    }

    @Override
    protected void before() throws Exception {
        Path snapshotDir = Files.createTempDirectory("zk-snapshot-dir-");
        Path logDir = Files.createTempDirectory("zk-log-dir-");
        zooKeeperServer = new ZooKeeperServer(snapshotDir.toFile(), logDir.toFile(), TICK_TIME);

        NIOServerCnxnFactory factory = new NIOServerCnxnFactory();
        factory.configure(new InetSocketAddress(port), MAXCC);
        factory.startup(zooKeeperServer);
    }

    @Override
    protected void after() {
        try {
            zooKeeperServer.shutdown();
        } catch (Exception ignored) {
        }
    }

    private int allocatePort() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(0);
        try (ServerSocket socket = new ServerSocket()) {
            socket.bind(inetSocketAddress);
            return socket.getLocalPort();
        } catch (IOException e) {
            return -1;
        }
    }
}
