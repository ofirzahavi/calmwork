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

package com.google.api.services.projectendpoint.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.DateTime;

/**
 * Model definition for Project.
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
public final class Project extends GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Integer budjet;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private DateTime dueDate;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<String> imageIds;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String language;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Integer level;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String name;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String projectId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String status;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String subject;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String teacherId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String userId;

  /**

   * The value returned may be {@code null}.
   */
  public Integer getBudjet() {
    return budjet;
  }

  /**

   * The value set may be {@code null}.
   */
  public Project setBudjet(Integer budjet) {
    this.budjet = budjet;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public DateTime getDueDate() {
    return dueDate;
  }

  /**

   * The value set may be {@code null}.
   */
  public Project setDueDate(DateTime dueDate) {
    this.dueDate = dueDate;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public java.util.List<String> getImageIds() {
    return imageIds;
  }

  /**

   * The value set may be {@code null}.
   */
  public Project setImageIds(java.util.List<String> imageIds) {
    this.imageIds = imageIds;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getLanguage() {
    return language;
  }

  /**

   * The value set may be {@code null}.
   */
  public Project setLanguage(String language) {
    this.language = language;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public Integer getLevel() {
    return level;
  }

  /**

   * The value set may be {@code null}.
   */
  public Project setLevel(Integer level) {
    this.level = level;
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
  public Project setName(String name) {
    this.name = name;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getProjectId() {
    return projectId;
  }

  /**

   * The value set may be {@code null}.
   */
  public Project setProjectId(String projectId) {
    this.projectId = projectId;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getStatus() {
    return status;
  }

  /**

   * The value set may be {@code null}.
   */
  public Project setStatus(String status) {
    this.status = status;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getSubject() {
    return subject;
  }

  /**

   * The value set may be {@code null}.
   */
  public Project setSubject(String subject) {
    this.subject = subject;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getTeacherId() {
    return teacherId;
  }

  /**

   * The value set may be {@code null}.
   */
  public Project setTeacherId(String teacherId) {
    this.teacherId = teacherId;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getUserId() {
    return userId;
  }

  /**

   * The value set may be {@code null}.
   */
  public Project setUserId(String userId) {
    this.userId = userId;
    return this;
  }

}
