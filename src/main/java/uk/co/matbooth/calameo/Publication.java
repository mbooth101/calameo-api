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

public class Publication {
  private String ID;
  private String AccountID;
  private String SubscriptionID;
  private String Name;
  private String Description;
  private String Category;
  private String Format;
  private String Dialect;
  private String Status;
  private int IsPublished;
  private int IsPrivate;
  private String AuthID;
  private int AllowMini;
  private String Date;
  private String Pages;
  private String Width;
  private String Height;
  private String PictureUrl;
  private String ThumbUrl;
  private String PublicUrl;
  private String ViewUrl;

  public Publication() {
  }

  public String getId() {
    return ID;
  }

  public String getAccountId() {
    return AccountID;
  }

  public String getSubscriptionId() {
    return SubscriptionID;
  }

  public String getName() {
    return Name;
  }

  public String getDescription() {
    return Description;
  }

  public String getCategory() {
    return Category;
  }

  public String getFormat() {
    return Format;
  }

  public String getDialect() {
    return Dialect;
  }

  public String getStatus() {
    return Status;
  }

  public int getIsPublished() {
    return IsPublished;
  }

  public int getIsPrivate() {
    return IsPrivate;
  }

  public String getAuthID() {
    return AuthID;
  }

  public int getAllowMini() {
    return AllowMini;
  }

  public String getDate() {
    return Date;
  }

  public String getPages() {
    return Pages;
  }

  public String getWidth() {
    return Width;
  }

  public String getHeight() {
    return Height;
  }

  public String getPictureUrl() {
    return PictureUrl;
  }

  public String getThumbUrl() {
    return ThumbUrl;
  }

  public String getPublicUrl() {
    return PublicUrl;
  }

  public String getViewUrl() {
    return ViewUrl;
  }

  @Override
  public String toString() {
    return String.format("{ ID: %s, Account ID: %s, Subscription ID: %s, Name: %s, Description: %s}", getId(), getAccountId(),
        getSubscriptionId(), getName(), getDescription());
  }
}
