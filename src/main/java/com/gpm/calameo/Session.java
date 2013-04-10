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
package com.gpm.calameo;

public class Session {
  private String ID;
  private String SubscriptionID;
  private String SubscriberLogin;

  public Session() {
  }

  public String getId() {
    return ID;
  }

  public String getSubscriptionId() {
    return SubscriptionID;
  }

  public String getSubscriberLogin() {
    return SubscriberLogin;
  }

  @Override
  public String toString() {
    return String.format("{ ID: %s, Subscription ID: %s, Subscription Login: %s }", getId(), getSubscriptionId(), getSubscriberLogin());
  };
}
