/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

/**
 * 
 */
package org.mobicents.slee.resource.cluster;

import org.infinispan.remoting.transport.Address;
import org.infinispan.tree.Fqn;
import org.restcomm.cache.FqnWrapper;
import org.restcomm.cluster.cache.ClusteredCacheData;
import org.restcomm.cluster.election.ClientLocalListenerElector;

import java.io.Serializable;

/**
 * @author martins
 * 
 */
public class FailOverListener<K extends Serializable, V extends Serializable>
		implements org.restcomm.cluster.FailOverListener {

	private final FaultTolerantResourceAdaptor<K, V> ra;
	private final ReplicatedDataCacheData<K, V> baseCacheData;

	/**
	 * @param ra
	 * @param baseCacheData
	 */
	public FailOverListener(FaultTolerantResourceAdaptor<K, V> ra,
			ReplicatedDataCacheData<K, V> baseCacheData) {
		this.ra = ra;
		this.baseCacheData = baseCacheData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.cluster.ClientLocalListener#failOverClusterMember(org.jgroups
	 * .Address)
	 */
	public void failOverClusterMember(Address address) {
		// nothing to do
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.cluster.ClientLocalListener#getBaseFqn()
	 */
	@SuppressWarnings("unchecked")
	public FqnWrapper getBaseFqn() {
		return new FqnWrapper(baseCacheData.getNodeFqn());
	}

	/*
	 * (non-Javadoc)
	 * @see org.restcomm.cluster.FailOverListener#getElector()
	 */
	public ClientLocalListenerElector getElector() {
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.restcomm.cluster.ClientLocalListener#getPriority()
	 */
	public byte getPriority() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.cluster.ClientLocalListener#lostOwnership(org.mobicents
	 * .cluster.cache.ClusteredCacheData)
	 */
	public void lostOwnership(ClusteredCacheData clusteredCacheData) {
		// nothing to do
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.restcomm.cluster.ClientLocalListener#wonOwnership(org.mobicents.
	 * cluster.cache.ClusteredCacheData)
	 */
	@SuppressWarnings("unchecked")
	public void wonOwnership(ClusteredCacheData clusteredCacheData) {
		ra.failOver((K) clusteredCacheData.getNodeFqn().getLastElement());
	}
}
