/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * Warning! This file is generated. Modify at your own risk.
 */

package com.google.api.services.userendpoint.model;

import com.google.api.client.json.GenericJson;

/**
 * Model definition for User.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the . For a detailed explanation see:
 * <a href="http://code.google.com/p/google-api-java-client/wiki/Json">http://code.google.com/p/google-api-java-client/wiki/Json</a>
 * </p>
 *
 * <p>
 * Upgrade warning: starting with version 1.12 {@code getResponseHeaders()} is removed, instead use
 * {@link com.google.api.client.http.json.JsonHttpRequest#getLastResponseHeaders()}
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class User extends GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String mail;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String name;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String password;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<String> studentProjects;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<String> teacherProjects;

  /**

   * The value returned may be {@code null}.
   */
  public String getMail() {
    return mail;
  }

  /**

   * The value set may be {@code null}.
   */
  public User setMail(String mail) {
    this.mail = mail;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getName() {
    return name;
  }

  /**

   * The value set may be {@code null}.
   */
  public User setName(String name) {
    this.name = name;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getPassword() {
    return password;
  }

  /**

   * The value set may be {@code null}.
   */
  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public java.util.List<String> getStudentProjects() {
    return studentProjects;
  }

  /**

   * The value set may be {@code null}.
   */
  public User setStudentProjects(java.util.List<String> studentProjects) {
    this.studentProjects = studentProjects;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public java.util.List<String> getTeacherProjects() {
    return teacherProjects;
  }

  /**

   * The value set may be {@code null}.
   */
  public User setTeacherProjects(java.util.List<String> teacherProjects) {
    this.teacherProjects = teacherProjects;
    return this;
  }

}
