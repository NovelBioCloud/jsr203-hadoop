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

import hdfs.jsr203.HadoopFileSystem;

import java.nio.file.attribute.UserPrincipal;

public class HadoopUserPrincipal implements UserPrincipal {

	private org.apache.hadoop.security.UserGroupInformation ugi;
	private final String name;
	// private HadoopFileSystem hdfs;

	public HadoopUserPrincipal(HadoopFileSystem hdfs, String name) {
		this.ugi = org.apache.hadoop.security.UserGroupInformation
				.createRemoteUser(name);
		// this.hdfs = hdfs;
		this.name = name;
	}

	@Override
	public String getName() {
		return this.ugi.getUserName();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null || getClass() != obj.getClass()) {
			return false;
		} else {
			return this.ugi == ((HadoopUserPrincipal) obj).ugi;
		}
	}

	@Override
	public int hashCode() {
		int hash = 948;
        hash = hash * ugi.hashCode();
        return hash;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new HadoopUserPrincipal(null, name);
	}
}
