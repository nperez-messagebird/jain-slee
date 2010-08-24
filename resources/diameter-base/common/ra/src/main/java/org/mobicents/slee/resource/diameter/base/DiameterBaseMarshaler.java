/*
 * JBoss, Home of Professional Open Source
 * 
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a full listing
 * of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU General Public License, v. 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * v. 2.0 along with this distribution; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
package org.mobicents.slee.resource.diameter.base;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.slee.resource.ActivityHandle;
import javax.slee.resource.FireableEventType;

/**
 * 
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
public class DiameterBaseMarshaler implements javax.slee.resource.Marshaler {

  /*
   * (non-Javadoc)
   * @see javax.slee.resource.Marshaler#getEstimatedEventSize(javax.slee.resource.FireableEventType, java.lang.Object)
   */
  public int getEstimatedEventSize(FireableEventType eventType, Object event) {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   * @see javax.slee.resource.Marshaler#getEstimatedHandleSize(javax.slee.resource.ActivityHandle)
   */
  public int getEstimatedHandleSize(ActivityHandle handle) {
    return ((DiameterActivityHandle) handle).getId().length();
  }

  /*
   * (non-Javadoc)
   * @see javax.slee.resource.Marshaler#getEventBuffer(javax.slee.resource.FireableEventType, java.lang.Object)
   */
  public ByteBuffer getEventBuffer(FireableEventType eventType, Object event) {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   * @see javax.slee.resource.Marshaler#marshalEvent(javax.slee.resource.FireableEventType, java.lang.Object, java.io.DataOutput)
   */
  public void marshalEvent(FireableEventType eventType, Object event, DataOutput out) throws IOException {
    throw new UnsupportedOperationException();
  }


  /*
   * (non-Javadoc)
   * @see javax.slee.resource.Marshaler#marshalHandle(javax.slee.resource.ActivityHandle, java.io.DataOutput)
   */
  public void marshalHandle(ActivityHandle handle, DataOutput out) throws IOException {
    DiameterActivityHandle dah = (DiameterActivityHandle)handle;
    out.writeUTF(dah.getId());
  }

  /*
   * (non-Javadoc)
   * @see javax.slee.resource.Marshaler#releaseEventBuffer(javax.slee.resource.FireableEventType, java.lang.Object, java.nio.ByteBuffer)
   */
  public void releaseEventBuffer(FireableEventType eventType, Object event, ByteBuffer buffer) {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   * @see javax.slee.resource.Marshaler#unmarshalEvent(javax.slee.resource.FireableEventType, java.io.DataInput)
   */
  public Object unmarshalEvent(FireableEventType eventType, DataInput in) throws IOException {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   * @see javax.slee.resource.Marshaler#unmarshalHandle(java.io.DataInput)
   */
  public ActivityHandle unmarshalHandle(DataInput in) throws IOException {
    String id = in.readUTF();
    return new DiameterActivityHandle(id);
  }

}