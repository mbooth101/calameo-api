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

public class CalameoException extends Exception {
  private static final long serialVersionUID = 1L;

  private int code;

  public CalameoException() {
    super();
  }

  public CalameoException(String message) {
    super(message);
  }

  public CalameoException(String message, Throwable cause) {
    super(message, cause);
  }

  public CalameoException(Throwable cause) {
    super(cause);
  }

  public CalameoException(int code, String message) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    if (getCode() > 0) {
      return String.format("(%d) %s", getCode(), super.getMessage());
    } else {
      return super.getMessage();
    }
  }
}
