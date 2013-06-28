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

package com.google.api.services.calmuserendpoint.model;

import com.google.api.client.json.GenericJson;

/**
 * Model definition for CalmUser.
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
public final class CalmUser extends GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Text aboutMe;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<String> languages;

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
  private java.util.List<String> reviews;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<String> skills;

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
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private String title;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Float totalReview;

  /**

   * The value returned may be {@code null}.
   */
  public Text getAboutMe() {
    return aboutMe;
  }

  /**

   * The value set may be {@code null}.
   */
  public CalmUser setAboutMe(Text aboutMe) {
    this.aboutMe = aboutMe;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public java.util.List<String> getLanguages() {
    return languages;
  }

  /**

   * The value set may be {@code null}.
   */
  public CalmUser setLanguages(java.util.List<String> languages) {
    this.languages = languages;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getMail() {
    return mail;
  }

  /**

   * The value set may be {@code null}.
   */
  public CalmUser setMail(String mail) {
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
  public CalmUser setName(String name) {
    this.name = name;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public java.util.List<String> getReviews() {
    return reviews;
  }

  /**

   * The value set may be {@code null}.
   */
  public CalmUser setReviews(java.util.List<String> reviews) {
    this.reviews = reviews;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public java.util.List<String> getSkills() {
    return skills;
  }

  /**

   * The value set may be {@code null}.
   */
  public CalmUser setSkills(java.util.List<String> skills) {
    this.skills = skills;
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
  public CalmUser setStudentProjects(java.util.List<String> studentProjects) {
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
  public CalmUser setTeacherProjects(java.util.List<String> teacherProjects) {
    this.teacherProjects = teacherProjects;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public String getTitle() {
    return title;
  }

  /**

   * The value set may be {@code null}.
   */
  public CalmUser setTitle(String title) {
    this.title = title;
    return this;
  }

  /**

   * The value returned may be {@code null}.
   */
  public Float getTotalReview() {
    return totalReview;
  }

  /**

   * The value set may be {@code null}.
   */
  public CalmUser setTotalReview(Float totalReview) {
    this.totalReview = totalReview;
    return this;
  }

}
