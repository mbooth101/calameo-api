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

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.gpm.calameo.CalameoClient;
import com.gpm.calameo.CalameoConfig;
import com.gpm.calameo.CalameoException;
import com.gpm.calameo.ItemList;
import com.gpm.calameo.Publication;
import com.gpm.calameo.Session;
import com.gpm.calameo.Subscriber;

public class CalameoClientImpl implements CalameoClient {
  private final Log log = LogFactory.getLog(CalameoClientImpl.class);

  private CalameoConfig config;

  public CalameoClientImpl(final CalameoConfig config) {
    this.config = config;
  }

  @Override
  public CalameoConfig getConfig() {
    if (config == null) {
      config = new CalameoConfigImpl();
    }
    return config;
  }

  @Override
  public ItemList<Publication> getPublicationList(final int start, final int step) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.fetchSubscriptionBooks");
    params.put("start", Integer.toString(start));
    params.put("step", Integer.toString(step));
    PublicationListResponse response = executeRequest(PublicationListResponse.class, params);
    return response.getContent();
  }

  @Override
  public ItemList<Subscriber> getSubscriberList(final int start, final int step) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.fetchSubscriptionSubscribers");
    params.put("start", Integer.toString(start));
    params.put("step", Integer.toString(step));
    SubscriberListResponse response = executeRequest(SubscriberListResponse.class, params);
    return response.getContent();
  }

  @Override
  public Publication getPublication(final String bookId) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.getBookInfos");
    params.put("book_id", bookId);
    PublicationResponse response = executeRequest(PublicationResponse.class, params);
    return response.getContent();
  }

  @Override
  public Subscriber getSubscriber(final String login) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.getSubscriberInfos");
    params.put("login", login);
    SubscriberResponse response = executeRequest(SubscriberResponse.class, params);
    return response.getContent();
  }

  @Override
  public void activateSubscriber(final String login) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.activateSubscriber");
    params.put("login", login);
    executeRequest(VoidResponse.class, params);
  }

  @Override
  public void deactivateSubscriber(final String login) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.deactivateSubscriber");
    params.put("login", login);
    executeRequest(VoidResponse.class, params);
  }

  @Override
  public Subscriber addSubscriber(final String login, final String password, final String firstName,
      final String lastName, final String email, final boolean isActive, final String extras) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.addSubscriber");
    params.put("login", login);
    params.put("password", password);
    params.put("firstname", firstName);
    params.put("lastname", lastName);
    params.put("email", email);
    params.put("is_active", isActive ? "1" : "0");
    params.put("extras", extras);
    SubscriberResponse response = executeRequest(SubscriberResponse.class, params);
    return response.getContent();
  }

  @Override
  public Subscriber updateSubscriber(final String login, final String newLogin, final String password,
      final String firstName, final String lastName, final String email, final boolean isActive, final String extras) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.updateSubscriber");
    params.put("login", login);
    params.put("new_login", newLogin);
    params.put("password", password);
    params.put("firstname", firstName);
    params.put("lastname", lastName);
    params.put("email", email);
    params.put("is_active", isActive ? "1" : "0");
    params.put("extras", extras);
    SubscriberResponse response = executeRequest(SubscriberResponse.class, params);
    return response.getContent();
  }

  @Override
  public void deleteSubscriber(final String login) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.deleteSubscriber");
    params.put("login", login);
    executeRequest(VoidResponse.class, params);
  }

  @Override
  public Session authSubscriberSession(final String login) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.authSubscriberSession");
    params.put("login", login);
    SessionResponse response = executeRequest(SessionResponse.class, params);
    return response.getContent();
  }

  @Override
  public Session checkSubscriberSession(final String sessionId) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.checkSubscriberSession");
    params.put("session_id", sessionId);
    SessionResponse response = executeRequest(SessionResponse.class, params);
    return response.getContent();
  }

  @Override
  public void deleteSubscriberSession(final String sessionId) throws CalameoException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("action", "API.deleteSubscriberSession");
    params.put("session_id", sessionId);
    executeRequest(SessionResponse.class, params);
  }

  private <T extends Response<?>> T executeRequest(final Class<T> responseType, final Map<String, String> params) throws CalameoException {
    HttpClient client = null;
    HttpGet get = null;
    InputStreamReader entity = null;
    try {
      // Generate signed params
      Map<String, String> p = new HashMap<String, String>(params);
      p.put("apikey", getConfig().getKey());
      p.put("expires", Long.toString(new Date().getTime() + getConfig().getExpires()));
      p.put("output", "JSON");
      p.put("subscription_id", getConfig().getSubscription());
      Set<String> keys = new TreeSet<String>(p.keySet());
      StringBuilder input = new StringBuilder(getConfig().getSecret());
      for (String key : keys) {
        input.append(key);
        input.append(p.get(key));
      }
      p.put("signature", DigestUtils.md5Hex(input.toString()));
      // Configure HTTP client
      client = new DefaultHttpClient();
      // Configure GET request
      URIBuilder uri = new URIBuilder(getConfig().getEndpoint());
      for (String key : p.keySet()) {
        uri.addParameter(key, p.get(key));
      }
      get = new HttpGet(uri.build());
      log.debug("Request URI: " + get.getURI());
      // Execute request and parse response
      HttpResponse response = client.execute(get);
      entity = new InputStreamReader(response.getEntity().getContent());
      JsonElement parsed = new JsonParser().parse(entity);
      JsonElement responseJson = parsed.getAsJsonObject().get("response");
      T r = new Gson().fromJson(responseJson, responseType);
      log.debug("Response Object: " + r);
      // Return response or throw an error
      if ("error".equals(r.getStatus())) {
        CalameoException e = new CalameoException(r.getError().getCode(), r.getError().getMessage());
        log.error(e.getMessage());
        throw e;
      } else {
        return r;
      }
    } catch (IOException e) {
      log.error(e.getMessage());
      throw new CalameoException(e);
    } catch (URISyntaxException e) {
      log.error(e.getMessage());
      throw new CalameoException(e);
    } finally {
      if (entity != null) {
        try {
          entity.close();
        } catch (IOException e) {
          log.warn(e.getMessage());
        }
      }
      if (get != null) {
        get.releaseConnection();
      }
      if (client != null) {
        client.getConnectionManager().shutdown();
      }
    }
  }
}
