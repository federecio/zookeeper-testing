zookeeper-testing
=================

A very simple way to use an embedded ZooKeeper server for tests.

Usage
-----

As a ClassRule:

    @ClassRule
    public static final EmbeddedZooKeeper ZK = new EmbeddedZooKeeper();

    @Test
    public void test1() {
    	// ZK is running
    }

    @Test
    public void test2() {
       // ZK is running, same instance as test1()
    }

As a Rule:

    @Rule
    public final EmbeddedZooKeeper ZK = new EmbeddedZooKeeper();

	@Test
	public void test1() {
		// ZK is running
	}

	@Test
	public void test2() {
	   // ZK is running, just not the same ZK instance as test1()
	}

Determine ZK connection string
------------------------------

    @ClassRule
    public static final EmbeddedZooKeeper ZK = new EmbeddedZooKeeper();

 	@Test
 	public void test1() {
 		String zkConnection = ZK.getZkConnectionString();
 		....
 	}


Note about ZK port
------------------

    new EmbeddedZooKeeper()     --> use any available port
    new EmbeddedZooKeeper(3456) --> run ZK server using port 3456

