package technology.semi.weaviate.integration.client.misc;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import technology.semi.weaviate.client.Config;
import technology.semi.weaviate.client.WeaviateClient;
import technology.semi.weaviate.client.v1.misc.api.model.Meta;

public class ClientMiscTest {

  String address;

  @ClassRule
  public static DockerComposeContainer compose = new DockerComposeContainer(
          new File("src/test/resources/docker-compose-test.yaml")
  ).withExposedService("weaviate_1", 8080, Wait.forHttp("/v1/.well-known/ready").forStatusCode(200));

  @Before
  public void before() {
    String host = compose.getServiceHost("weaviate_1", 8080);
    Integer port = compose.getServicePort("weaviate_1", 8080);
    address = host + ":" + port;
  }

  @Test
  public void testMiscLivenessEndpoint() throws IOException {
    // given
    Config config = new Config("http", address);
    WeaviateClient client = new WeaviateClient(config);
    // when
    Boolean livenessCheck = client.misc().liveChecker().run();
    // then
    Assert.assertTrue(livenessCheck);
  }

  @Test
  public void testMiscReadinessEndpoint() throws IOException {
    // given
    Config config = new Config("http", address);
    WeaviateClient client = new WeaviateClient(config);
    // when
    Boolean readinessCheck = client.misc().readyChecker().run();
    // then
    Assert.assertTrue(readinessCheck);
  }

  @Test
  public void testMiscMetaEndpoint() throws IOException {
    // given
    Config config = new Config("http", address);
    WeaviateClient client = new WeaviateClient(config);
    // when
    Meta meta = client.misc().metaGetter().run();
    // then
    Assert.assertEquals("http://[::]:8080", meta.getHostname());
    Assert.assertEquals("1.2.1", meta.getVersion());
    Assert.assertEquals("{text2vec-contextionary={version=en0.16.0-v1.0.2, wordCount=818072.0}}", meta.getModules().toString());
  }
}
