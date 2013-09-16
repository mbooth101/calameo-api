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

public class Subscriber {
  private String AccountID;
  private String SubscriptionID;
  private String FirstName;
  private String LastName;
  private String Email;
  private String Login;
  private String Password;
  private int IsActive;
  private String Extras;

  public Subscriber() {
  }

  public String getAccountId() {
    return AccountID;
  }

  public String getSubscriptionId() {
    return SubscriptionID;
  }

  public String getFirstName() {
    return FirstName;
  }

  public String getLastName() {
    return LastName;
  }

  public String getEmail() {
    return Email;
  }

  public String getLogin() {
    return Login;
  }

  public String getPassword() {
    return Password;
  }

  public int getIsActive() {
    return IsActive;
  }

  public String getExtras() {
    return Extras;
  }

  @Override
  public String toString() {
    return String.format(
        "{ Account ID: %s, Subscription ID: %s, Name: %s %s, Login: %s, Password: %s, Email: %s, Active: %d, Extras: %s }", getAccountId(),
        getSubscriptionId(), getFirstName(), getLastName(), getLogin(), getPassword(), getEmail(), getIsActive(), getExtras());
  }
}
