/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package x;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.base.Stopwatch;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class Main {
  static {
    org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger();
    org.slf4j.bridge.SLF4JBridgeHandler.install();
  }
  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args)
      throws Exception {
    LOGGER.info("args = {}", Arrays.toString(args));
    try (Storage s = StorageOptions.grpc().setAttemptDirectPath(true).build().getService()) {
      BlobId id = BlobId.fromGsUtilUri(args[0]);
      doGet(s, id);
      doGet(s, id);
      doGet(s, id);
    }
  }

  private static void doGet(Storage s, BlobId id) {
    Stopwatch sw = Stopwatch.createStarted();
    Blob blob = s.get(id);
    Stopwatch stop = sw.stop();
    LOGGER.info("Fetching object metadata took " + stop);
  }
}
