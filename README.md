zookeeper-testing
=================

A very simple way to use an embedded ZooKeeper server for tests.

Maven
-----

* Add the following Maven repository:

		<repository>
			<id>federecio-snapshots</id>
			<url>https://repository-federecio1.forge.cloudbees.com/snapshot/</url>
		</repository>

* Add the Maven dependency:

		<dependency>
			<groupId>com.federecio</groupId>
			<artifactId>zookeeper-testing</artifactId>
			<version>0.1-SNAPSHOT</version>
		</dependency>

Usage
-----

* As a ClassRule:

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

__Note__: in this case ZooKeeper server will stop after all tests have finished.

* As a Rule:

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

__Note__: in this case ZooKeeper server will stop after each test has finished and it will start before the following test executes.

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

