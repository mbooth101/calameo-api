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

import com.gpm.calameo.CalameoConfig;

public class CalameoConfigImpl implements CalameoConfig {
  private String endpoint = "http://api.calameo.com/1.0";
  private String key = "";
  private String secret = "";
  private String subscription = "";
  private long expires = 600000L;

  @Override
  public String getEndpoint() {
    return endpoint;
  }

  @Override
  public void setEndpoint(final String endpoint) {
    this.endpoint = endpoint;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public void setKey(final String key) {
    this.key = key;
  }

  @Override
  public String getSecret() {
    return secret;
  }

  @Override
  public void setSecret(final String secret) {
    this.secret = secret;
  }

  @Override
  public String getSubscription() {
    return subscription;
  }

  @Override
  public void setSubscription(final String subscription) {
    this.subscription = subscription;
  }

  @Override
  public long getExpires() {
    return expires;
  }

  @Override
  public void setExpires(final long expires) {
    this.expires = expires;
  }
}
