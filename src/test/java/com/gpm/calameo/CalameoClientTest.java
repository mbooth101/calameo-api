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

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gpm.calameo.impl.CalameoClientImpl;
import com.gpm.calameo.impl.CalameoConfigImpl;

public class CalameoClientTest {

  private static CalameoClient calcli;
  private static CalameoConfig calcon;

  @BeforeClass
  @Parameters(value = { "calameo.key", "calameo.secret", "calameo.subscription" })
  public static void createClient(String calameoKey, String calameoSecret, String calameoSubscription) {
    calcon = new CalameoConfigImpl();
    calcon.setKey(calameoKey);
    calcon.setSecret(calameoSecret);
    calcon.setSubscription(calameoSubscription);
    calcli = new CalameoClientImpl(calcon);
  }

  @Test(groups = { "subscriberTests" })
  public void getAllPublications() throws CalameoException {
    // Get a list of publications
    ItemList<Publication> pubs = calcli.getPublicationList(0, 15);
    Assert.assertNotEquals(pubs, null);
    Assert.assertEquals(pubs.getStart(), 0);
    Assert.assertEquals(pubs.getStep(), 15);
    Assert.assertNotEquals(pubs.getItems(), null);
  }

  @Test(groups = { "subscriberTests" })
  public void getAllSubscribers() throws CalameoException {
    // Get a list of subscribers
    ItemList<Subscriber> subs = calcli.getSubscriberList(0, 15);
    Assert.assertNotEquals(subs, null);
    Assert.assertEquals(subs.getStart(), 0);
    Assert.assertEquals(subs.getStep(), 15);
    Assert.assertNotEquals(subs.getItems(), null);
  }

  @Test(groups = { "subscriberTests" })
  public void addNewSubscriber() throws CalameoException {
    // Create a new subscriber
    Subscriber sub = calcli.addSubscriber("mbooth", "password", "Mat", "Booth", "mbooth@example.com", true, "extra");
    Assert.assertNotEquals(sub, null);
    Assert.assertEquals(sub.getEmail(), "mbooth@example.com");
    Assert.assertEquals(sub.getLogin(), "mbooth");
    Assert.assertEquals(sub.getPassword(), "password");
    Assert.assertEquals(sub.getFirstName(), "Mat");
    Assert.assertEquals(sub.getLastName(), "Booth");
    Assert.assertEquals(sub.getIsActive(), 1);
    Assert.assertEquals(sub.getExtras(), "extra");
  }

  @Test(groups = { "subscriberTests" }, dependsOnMethods = { "addNewSubscriber" })
  public void updateExistingSubscriber() throws CalameoException {
    // Change some subscriber details
    Subscriber sub = calcli.updateSubscriber("mbooth", "mbooth1", "password1", "Mat1", "Booth1", "mbooth1@example.com", true, "extra1");
    Assert.assertNotEquals(sub, null);
    Assert.assertEquals(sub.getEmail(), "mbooth1@example.com");
    Assert.assertEquals(sub.getLogin(), "mbooth1");
    Assert.assertEquals(sub.getPassword(), "password1");
    Assert.assertEquals(sub.getFirstName(), "Mat1");
    Assert.assertEquals(sub.getLastName(), "Booth1");
    Assert.assertEquals(sub.getIsActive(), 1);
    Assert.assertEquals(sub.getExtras(), "extra1");
  }

  @Test(groups = { "subscriberTests" }, dependsOnMethods = { "updateExistingSubscriber" })
  public void deactivateExistingSubscriber() throws CalameoException {
    // Clear the active flag on a subscriber
    calcli.deactivateSubscriber("mbooth1");
    Subscriber sub = calcli.getSubscriber("mbooth1");
    Assert.assertNotEquals(sub, null);
    Assert.assertEquals(sub.getIsActive(), 0);
  }

  @Test(groups = { "subscriberTests" }, dependsOnMethods = { "deactivateExistingSubscriber" })
  public void activateExistingSubscriber() throws CalameoException {
    // Set the active flag on a subscriber
    calcli.activateSubscriber("mbooth1");
    Subscriber sub = calcli.getSubscriber("mbooth1");
    Assert.assertNotEquals(sub, null);
    Assert.assertEquals(sub.getIsActive(), 1);
  }

  @Test(groups = { "subscriberTests" }, dependsOnMethods = { "activateExistingSubscriber" })
  public void startCheckAndKillSubscriberSession() throws CalameoException {
    // Create a session for a subscriber
    Session auth = calcli.authSubscriberSession("mbooth1");
    Assert.assertNotEquals(auth, null);
    Assert.assertEquals(auth.getSubscriptionId(), calcon.getSubscription());
    Assert.assertEquals(auth.getSubscriberLogin(), "mbooth1");
    // Check session exists
    Session check = calcli.checkSubscriberSession(auth.getId());
    Assert.assertNotEquals(check, null);
    Assert.assertEquals(check.getId(), auth.getId());
    Assert.assertEquals(check.getSubscriptionId(), auth.getSubscriptionId());
    Assert.assertEquals(check.getSubscriberLogin(), auth.getSubscriberLogin());
    // Delete session
    calcli.deleteSubscriberSession(auth.getId());
    // Session should not exist
    try {
      calcli.checkSubscriberSession(auth.getId());
    } catch (CalameoException e) {
      Assert.assertEquals(e.getCode(), 610, "Unexpected error code");
    }
    // Deleting session is idempotent
    calcli.deleteSubscriberSession(auth.getId());
  }

  @Test(groups = { "subscriberTests" }, dependsOnMethods = { "startCheckAndKillSubscriberSession" })
  public void deleteExistingSubscriber() throws CalameoException {
    // Delete subscriber
    calcli.deleteSubscriber("mbooth1");
    // Confirm subscriber is gone
    try {
      calcli.getSubscriber("mbooth1");
    } catch (CalameoException e) {
      Assert.assertEquals(e.getCode(), 601, "Unexpected error code");
    }
    // Deleting subscriber for some reason is NOT idempotent
    try {
      calcli.deleteSubscriber("mbooth1");
    } catch (CalameoException e) {
      Assert.assertEquals(e.getCode(), 601, "Unexpected error code");
    }
  }
}
