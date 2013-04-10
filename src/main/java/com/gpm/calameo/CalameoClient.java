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

public interface CalameoClient {

  public CalameoConfig getConfig();

  public ItemList<Publication> getPublicationList(final int start, final int step) throws CalameoException;

  public ItemList<Subscriber> getSubscriberList(final int start, final int step) throws CalameoException;

  public Publication getPublication(final String bookId) throws CalameoException;

  public Subscriber getSubscriber(final String login) throws CalameoException;

  public void activateSubscriber(final String login) throws CalameoException;

  public void deactivateSubscriber(final String login) throws CalameoException;

  public Subscriber addSubscriber(final String login, final String password, final String firstName, final String lastName,
      final String email, final boolean isActive, final String extras) throws CalameoException;

  public Subscriber updateSubscriber(final String login, final String newLogin, final String password, final String firstName,
      final String lastName, final String email, final boolean isActive, final String extras) throws CalameoException;

  public void deleteSubscriber(final String login) throws CalameoException;

  public Session authSubscriberSession(final String login) throws CalameoException;

  public Session checkSubscriberSession(final String sessionId) throws CalameoException;

  public void deleteSubscriberSession(final String sessionId) throws CalameoException;
}
