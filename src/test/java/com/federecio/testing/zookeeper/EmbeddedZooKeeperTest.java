package com.federecio.testing.zookeeper;

import java.util.Collections;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmbeddedZooKeeperTest {

    @Rule
    public final EmbeddedZooKeeper ZK = new EmbeddedZooKeeper();

    @Test
    public void testZooKeeperIsRunning() throws Exception {
        ZkClient client = new ZkClient(ZK.getZkConnectionString());
        try {
            client.create("/test", null, CreateMode.PERSISTENT);
            client.create("/test/1", null, CreateMode.PERSISTENT);
            assertEquals(Collections.singletonList("1"), client.getChildren("/test"));
        } finally {
            client.close();
        }
    }
}