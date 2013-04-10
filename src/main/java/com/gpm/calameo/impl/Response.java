/*
 * Copyright 2013 Mat Booth <mbooth@apache.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gpm.calameo.impl;

abstract class Response<T> {
  private String requestid;
  private int requests;
  private String status;
  private T content;
  private ResponseError error;

  public Response() {
  }

  public String getRequestId() {
    return requestid;
  }

  public int getRequests() {
    return requests;
  }

  public String getStatus() {
    return status;
  }

  public T getContent() {
    return content;
  }

  public ResponseError getError() {
    return error;
  }

  @Override
  public String toString() {
    if (getContent() != null) {
      return String.format("{ Request ID: %s, Status: %s, Content: %s }", getRequestId(), getStatus(), getContent());
    } else if (getError() != null) {
      return String.format("{ Request ID: %s, Status: %s, Error: %s }", getRequestId(), getStatus(), getError());
    } else {
      return String.format("{ Request ID: %s, Status: %s }", getRequestId(), getStatus());
    }
  }
}
