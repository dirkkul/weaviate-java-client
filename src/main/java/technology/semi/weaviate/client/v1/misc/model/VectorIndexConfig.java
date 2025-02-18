package technology.semi.weaviate.client.v1.misc.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VectorIndexConfig {
  Integer cleanupIntervalSeconds;
  Integer efConstruction;
  Integer maxConnections;
  Long vectorCacheMaxObjects;
  Integer ef;
  Boolean skip;
  Integer dynamicEfFactor;
  Integer dynamicEfMax;
  Integer dynamicEfMin;
  Integer flatSearchCutoff;
}
