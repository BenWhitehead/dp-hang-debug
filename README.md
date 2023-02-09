# dp-hang-detection

Minimized Repro for https://github.com/googleapis/java-storage/issues/1737

## Pre-requisites

1. JDK 11+
2. Maven 3+

## Building

```
mvn -Pshade clean package
```

## Running

```bash
GOOGLE_CLOUD_ENABLE_DIRECT_PATH_XDS=true \
    java -jar target/dp-hang-debug-1.0-SNAPSHOT-shade.jar\
    <gs_object_uri> \
    2>&1 | tee verbose.log
```

