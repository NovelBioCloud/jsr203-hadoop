/**
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements. See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership. The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package hdfs.jsr203;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;

/**
 * 
 */
public class HadoopFileStore extends FileStore {

    private HadoopFileSystem system;

    public HadoopFileStore(HadoopPath path) {
        this.system = path.getFileSystem();
    }

    @Override
    public String name() {
        return this.system.getHDFS().getCanonicalServiceName();
    }

    @Override
    public String type() {
        return "HDFS";
    }

    @Override
    public boolean isReadOnly() {
        return this.system.isReadOnly();
    }

    @Override
    public long getTotalSpace() throws IOException {
        return this.system.getHDFS().getStatus().getCapacity();
    }

    @Override
    public long getUsableSpace() throws IOException {
        return this.system.getHDFS().getStatus().getRemaining();
    }

    @Override
    public long getUnallocatedSpace() throws IOException {
        return this.system.getHDFS().getStatus().getRemaining();
    }

    @Override
    public boolean supportsFileAttributeView(Class<? extends FileAttributeView> type) {
        return supportsFileAttributeView(type.getSimpleName());
    }

    @Override
    public boolean supportsFileAttributeView(String name) {
        return this.system.supportedFileAttributeViews().contains(name);
    }

    @Override
    public <V extends FileStoreAttributeView> V getFileStoreAttributeView(Class<V> type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getAttribute(String attribute) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("/");
        sb.append(" (");
        sb.append(system.getHDFS().getCanonicalServiceName());
        sb.append(")");
        return sb.toString();
    }

}
