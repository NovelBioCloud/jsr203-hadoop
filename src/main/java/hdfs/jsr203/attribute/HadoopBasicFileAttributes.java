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
package hdfs.jsr203.attribute;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.concurrent.TimeUnit;

import org.apache.hadoop.fs.FileStatus;

/**
 * Implementation of BasicFileAttributes.
 */
public class HadoopBasicFileAttributes implements BasicFileAttributes
{
	/** Internal implementation of file status */
	private final FileStatus fileStatus;

	public HadoopBasicFileAttributes(FileStatus fileStatus) {
		this.fileStatus = fileStatus;
	}

	@Override
	public FileTime creationTime() {
		return FileTime.from(this.fileStatus.getModificationTime(), TimeUnit.MILLISECONDS);
	}

	@Override
	public Object fileKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDirectory() {
		return this.fileStatus.isDirectory();
	}

	@Override
	public boolean isOther() {
		return false;
	}

	@Override
	public boolean isRegularFile() {
		return this.fileStatus.isFile();
	}

	@Override
	public boolean isSymbolicLink() {
		return this.fileStatus.isSymlink();
	}

	@Override
	public FileTime lastAccessTime() {
		return FileTime.from(this.fileStatus.getAccessTime(), TimeUnit.MILLISECONDS);
	}

	@Override
	public FileTime lastModifiedTime() {
		return FileTime.from(this.fileStatus.getModificationTime(), TimeUnit.MILLISECONDS);
	}

	@Override
	public long size() {
		return this.fileStatus.getLen();
	}

	@Override
	public String toString() {
		return "[IS DIR : " + this.fileStatus.isDirectory() + "]";
	}

	protected FileStatus getFileStatus() {
		return fileStatus;
	}

}
