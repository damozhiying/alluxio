/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.client.block;

import alluxio.client.block.stream.BlockInStream;
import alluxio.client.block.stream.BlockOutStream;
import alluxio.client.file.FileSystemContext;
import alluxio.client.file.options.InStreamOptions;
import alluxio.client.file.options.OutStreamOptions;
import alluxio.proto.dataserver.Protocol;
import alluxio.wire.WorkerNetAddress;

import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * A factory class to create various block streams in Alluxio.
 */
@NotThreadSafe
public final class StreamFactory {

  private StreamFactory() {} // prevent instantiation

  /**
   * Creates an {@link BlockOutStream} that writes to a block on local worker.
   *
   * @param context the file system context
   * @param blockId the block ID
   * @param blockSize the block size in bytes
   * @param address the Alluxio worker address
   * @param options the out stream options
   * @return the {@link OutputStream} object
   */
  public static BlockOutStream createShortCircuitBlockOutStream(FileSystemContext context,
      long blockId, long blockSize, WorkerNetAddress address, OutStreamOptions options) {
    return BlockOutStream
        .createShortCircuitBlockOutStream(blockId, blockSize, address, context, options);
  }

  /**
   * Creates an {@link BlockOutStream} that writes to a worker via netty.
   *
   * @param context the file system context
   * @param blockId the block ID
   * @param blockSize the block size in bytes
   * @param address the Alluxio worker address
   * @param options the out stream options
   * @return the {@link OutputStream} object
   */
  public static BlockOutStream createNettyBlockOutStream(FileSystemContext context, long blockId,
      long blockSize, WorkerNetAddress address, OutStreamOptions options) {
    return BlockOutStream
        .createNettyBlockOutStream(blockId, blockSize, address, context, options);
  }

  /**
   * Creates an {@link BlockInStream} that reads from a local block.
   *
   * @param context the file system context
   * @param blockId the block ID
   * @param blockSize the block size in bytes
   * @param address the Alluxio worker address
   * @param options the in stream options
   * @return the {@link InputStream} object
   */
  public static BlockInStream createShortCircuitBlockInStream(FileSystemContext context,
      long blockId, long blockSize, WorkerNetAddress address, InStreamOptions options) {
    return BlockInStream
        .createShortCircuitBlockInStream(blockId, blockSize, address, context, options);
  }

  /**
   * Creates an {@link BlockInStream} that reads from a local block.
   *
   * @param context the file system context
   * @param blockId the block ID
   * @param blockSize the block size in bytes
   * @param address the Alluxio worker address
   * @param openUfsBlockOptions the options to open a UFS block, set to null if this is block is
   *        not persisted in UFS
   * @param options the in stream options
   * @return the {@link InputStream} object
   */
  public static BlockInStream createNettyBlockInStream(FileSystemContext context, long blockId,
      long blockSize, WorkerNetAddress address, Protocol.OpenUfsBlockOptions openUfsBlockOptions,
      InStreamOptions options) {
    return BlockInStream
        .createNettyBlockInStream(blockId, blockSize, address, context, openUfsBlockOptions,
            options);
  }
}
