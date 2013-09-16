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
package uk.co.matbooth.calameo;

public interface CalameoConfig {

  public String getEndpoint();

  public void setEndpoint(final String endpoint);

  public String getKey();

  public void setKey(final String key);

  public String getSecret();

  public void setSecret(final String secret);

  public String getSubscription();

  public void setSubscription(final String subscription);

  public long getExpires();

  public void setExpires(final long expires);
}
