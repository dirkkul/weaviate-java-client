package technology.semi.weaviate.client.v1.schema.api;

import technology.semi.weaviate.client.Config;
import technology.semi.weaviate.client.base.BaseClient;
import technology.semi.weaviate.client.base.Client;
import technology.semi.weaviate.client.base.Response;
import technology.semi.weaviate.client.v1.schema.model.Class;

public class ClassCreator extends BaseClient<Class> implements Client<Boolean>  {

  private Class clazz;

  public ClassCreator(Config config) {
    super(config);
  }

  public ClassCreator withClass(Class clazz) {
    this.clazz = clazz;
    return this;
  }

  @Override
  public Boolean run() {
    Response<Class> resp = sendPostRequest("/schema", clazz, Class.class);
    return resp.getStatusCode() == 200;
  }
}
