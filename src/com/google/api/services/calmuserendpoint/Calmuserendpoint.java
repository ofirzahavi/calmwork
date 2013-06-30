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
 * This file was generated.
 *  with google-apis-code-generator 1.4.0 (build: 2013-06-26 16:27:34 UTC)
 *  on 2013-06-30 at 11:02:51 UTC 
 */

package com.google.api.services.calmuserendpoint;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.common.base.Preconditions;

/**
 * Service definition for Calmuserendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link CalmuserendpointRequestInitializer} to initialize global parameters via its
 * {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.Builder}.
 * </p>
 *
 * <p>
 * Upgrade warning: this class now extends {@link com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient}, whereas in prior
 * version 1.8 it extended {@link com.google.api.client.googleapis.services.GoogleClient}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Calmuserendpoint extends AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    Preconditions.checkState(GoogleUtils.VERSION.equals("1.13.2-beta"),
        "You are currently running with version %s of google-api-client. " +
        "You need version 1.13.2-beta of google-api-client to run version " +
        "1.13.2-beta of the  library.", GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://biuninja2013.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "calmuserendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   * @deprecated (scheduled to be removed in 1.13)
   */
  @Deprecated
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport
   * @param jsonFactory JSON factory
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Calmuserendpoint(HttpTransport transport, JsonFactory jsonFactory,
      HttpRequestInitializer httpRequestInitializer) {
    super(transport,
        jsonFactory,
        DEFAULT_ROOT_URL,
        DEFAULT_SERVICE_PATH,
        httpRequestInitializer,
        false);
  }

  /**
   * @param transport HTTP transport
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @param rootUrl root URL of the service
   * @param servicePath service path
   * @param jsonObjectParser JSON object parser
   * @param googleClientRequestInitializer Google request initializer or {@code null} for none
   * @param applicationName application name to be sent in the User-Agent header of requests or
   *        {@code null} for none
   * @param suppressPatternChecks whether discovery pattern checks should be suppressed on required
   *        parameters
   */
  Calmuserendpoint(HttpTransport transport,
      HttpRequestInitializer httpRequestInitializer,
      String rootUrl,
      String servicePath,
      JsonObjectParser jsonObjectParser,
      GoogleClientRequestInitializer googleClientRequestInitializer,
      String applicationName,
      boolean suppressPatternChecks) {
    super(transport,
        httpRequestInitializer,
        rootUrl,
        servicePath,
        jsonObjectParser,
        googleClientRequestInitializer,
        applicationName,
        suppressPatternChecks);
  }

  @Override
  protected void initialize(AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * An accessor for creating requests from the CalmUserEndpoint collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Calmuserendpoint calmuserendpoint = new Calmuserendpoint(...);}
   *   {@code Calmuserendpoint.CalmUserEndpoint.List request = calmuserendpoint.calmUserEndpoint().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public CalmUserEndpoint calmUserEndpoint() {
    return new CalmUserEndpoint();
  }

  /**
   * The "calmUserEndpoint" collection of methods.
   */
  public class CalmUserEndpoint {

    /**
     * Create a request for the method "calmUserEndpoint.getCalmUser".
     *
     * This request holds the parameters needed by the the calmuserendpoint server.  After setting any
     * optional parameters, call the {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.GetCalmUser#execute()} method to invoke the remote
     * operation.
     *
     * @param id
     * @return the request
     */
    public GetCalmUser getCalmUser(String id) throws java.io.IOException {
      GetCalmUser result = new GetCalmUser(id);
      initialize(result);
      return result;
    }

    public class GetCalmUser extends CalmuserendpointRequest<com.google.api.services.calmuserendpoint.model.CalmUser> {

      private static final String REST_PATH = "calmuser/{id}";

      /**
       * Create a request for the method "calmUserEndpoint.getCalmUser".
       *
       * This request holds the parameters needed by the the calmuserendpoint server.  After setting any
       * optional parameters, call the {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.GetCalmUser#execute()} method to invoke the remote
       * operation. <p> {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.GetCalmUser#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must be called to
       * initialize this instance immediately after invoking the constructor. </p>
       *
       * @param id
       * @since 1.13
       */
      protected GetCalmUser(String id) {
        super(Calmuserendpoint.this, "GET", REST_PATH, null, com.google.api.services.calmuserendpoint.model.CalmUser.class);
        this.id = Preconditions.checkNotNull(id, "Required parameter id must be specified.");
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public GetCalmUser setAlt(String alt) {
        return (GetCalmUser) super.setAlt(alt);
      }

      @Override
      public GetCalmUser setFields(String fields) {
        return (GetCalmUser) super.setFields(fields);
      }

      @Override
      public GetCalmUser setKey(String key) {
        return (GetCalmUser) super.setKey(key);
      }

      @Override
      public GetCalmUser setOauthToken(String oauthToken) {
        return (GetCalmUser) super.setOauthToken(oauthToken);
      }

      @Override
      public GetCalmUser setPrettyPrint(Boolean prettyPrint) {
        return (GetCalmUser) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public GetCalmUser setQuotaUser(String quotaUser) {
        return (GetCalmUser) super.setQuotaUser(quotaUser);
      }

      @Override
      public GetCalmUser setUserIp(String userIp) {
        return (GetCalmUser) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private String id;

      /**

       */
      public String getId() {
        return id;
      }

      public GetCalmUser setId(String id) {
        this.id = id;
        return this;
      }

    }
    /**
     * Create a request for the method "calmUserEndpoint.insertCalmUser".
     *
     * This request holds the parameters needed by the the calmuserendpoint server.  After setting any
     * optional parameters, call the {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.InsertCalmUser#execute()} method to invoke the remote
     * operation.
     *
     * @param content the {@link com.google.api.services.calmuserendpoint.model.CalmUser}
     * @return the request
     */
    public InsertCalmUser insertCalmUser(com.google.api.services.calmuserendpoint.model.CalmUser content) throws java.io.IOException {
      InsertCalmUser result = new InsertCalmUser(content);
      initialize(result);
      return result;
    }

    public class InsertCalmUser extends CalmuserendpointRequest<com.google.api.services.calmuserendpoint.model.CalmUser> {

      private static final String REST_PATH = "calmuser";

      /**
       * Create a request for the method "calmUserEndpoint.insertCalmUser".
       *
       * This request holds the parameters needed by the the calmuserendpoint server.  After setting any
       * optional parameters, call the {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.InsertCalmUser#execute()} method to invoke the remote
       * operation. <p> {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.InsertCalmUser#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must be called to
       * initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.google.api.services.calmuserendpoint.model.CalmUser}
       * @since 1.13
       */
      protected InsertCalmUser(com.google.api.services.calmuserendpoint.model.CalmUser content) {
        super(Calmuserendpoint.this, "POST", REST_PATH, content, com.google.api.services.calmuserendpoint.model.CalmUser.class);
      }

      @Override
      public InsertCalmUser setAlt(String alt) {
        return (InsertCalmUser) super.setAlt(alt);
      }

      @Override
      public InsertCalmUser setFields(String fields) {
        return (InsertCalmUser) super.setFields(fields);
      }

      @Override
      public InsertCalmUser setKey(String key) {
        return (InsertCalmUser) super.setKey(key);
      }

      @Override
      public InsertCalmUser setOauthToken(String oauthToken) {
        return (InsertCalmUser) super.setOauthToken(oauthToken);
      }

      @Override
      public InsertCalmUser setPrettyPrint(Boolean prettyPrint) {
        return (InsertCalmUser) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public InsertCalmUser setQuotaUser(String quotaUser) {
        return (InsertCalmUser) super.setQuotaUser(quotaUser);
      }

      @Override
      public InsertCalmUser setUserIp(String userIp) {
        return (InsertCalmUser) super.setUserIp(userIp);
      }

    }
    /**
     * Create a request for the method "calmUserEndpoint.listCalmUser".
     *
     * This request holds the parameters needed by the the calmuserendpoint server.  After setting any
     * optional parameters, call the {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.ListCalmUser#execute()} method to invoke the remote
     * operation.
     *
     * @return the request
     */
    public ListCalmUser listCalmUser() throws java.io.IOException {
      ListCalmUser result = new ListCalmUser();
      initialize(result);
      return result;
    }

    public class ListCalmUser extends CalmuserendpointRequest<com.google.api.services.calmuserendpoint.model.CollectionResponseCalmUser> {

      private static final String REST_PATH = "calmuser";

      /**
       * Create a request for the method "calmUserEndpoint.listCalmUser".
       *
       * This request holds the parameters needed by the the calmuserendpoint server.  After setting any
       * optional parameters, call the {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.ListCalmUser#execute()} method to invoke the remote
       * operation. <p> {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.ListCalmUser#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must be called to
       * initialize this instance immediately after invoking the constructor. </p>
       *
       * @since 1.13
       */
      protected ListCalmUser() {
        super(Calmuserendpoint.this, "GET", REST_PATH, null, com.google.api.services.calmuserendpoint.model.CollectionResponseCalmUser.class);
      }

      @Override
      public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
        return super.executeUsingHead();
      }

      @Override
      public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
        return super.buildHttpRequestUsingHead();
      }

      @Override
      public ListCalmUser setAlt(String alt) {
        return (ListCalmUser) super.setAlt(alt);
      }

      @Override
      public ListCalmUser setFields(String fields) {
        return (ListCalmUser) super.setFields(fields);
      }

      @Override
      public ListCalmUser setKey(String key) {
        return (ListCalmUser) super.setKey(key);
      }

      @Override
      public ListCalmUser setOauthToken(String oauthToken) {
        return (ListCalmUser) super.setOauthToken(oauthToken);
      }

      @Override
      public ListCalmUser setPrettyPrint(Boolean prettyPrint) {
        return (ListCalmUser) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public ListCalmUser setQuotaUser(String quotaUser) {
        return (ListCalmUser) super.setQuotaUser(quotaUser);
      }

      @Override
      public ListCalmUser setUserIp(String userIp) {
        return (ListCalmUser) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private String cursor;

      /**

       */
      public String getCursor() {
        return cursor;
      }

      public ListCalmUser setCursor(String cursor) {
        this.cursor = cursor;
        return this;
      }

      @com.google.api.client.util.Key
      private Integer limit;

      /**

       */
      public Integer getLimit() {
        return limit;
      }

      public ListCalmUser setLimit(Integer limit) {
        this.limit = limit;
        return this;
      }

    }
    /**
     * Create a request for the method "calmUserEndpoint.removeCalmUser".
     *
     * This request holds the parameters needed by the the calmuserendpoint server.  After setting any
     * optional parameters, call the {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.RemoveCalmUser#execute()} method to invoke the remote
     * operation.
     *
     * @param id
     * @return the request
     */
    public RemoveCalmUser removeCalmUser(String id) throws java.io.IOException {
      RemoveCalmUser result = new RemoveCalmUser(id);
      initialize(result);
      return result;
    }

    public class RemoveCalmUser extends CalmuserendpointRequest<com.google.api.services.calmuserendpoint.model.CalmUser> {

      private static final String REST_PATH = "calmuser/{id}";

      /**
       * Create a request for the method "calmUserEndpoint.removeCalmUser".
       *
       * This request holds the parameters needed by the the calmuserendpoint server.  After setting any
       * optional parameters, call the {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.RemoveCalmUser#execute()} method to invoke the remote
       * operation. <p> {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.RemoveCalmUser#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must be called to
       * initialize this instance immediately after invoking the constructor. </p>
       *
       * @param id
       * @since 1.13
       */
      protected RemoveCalmUser(String id) {
        super(Calmuserendpoint.this, "DELETE", REST_PATH, null, com.google.api.services.calmuserendpoint.model.CalmUser.class);
        this.id = Preconditions.checkNotNull(id, "Required parameter id must be specified.");
      }

      @Override
      public RemoveCalmUser setAlt(String alt) {
        return (RemoveCalmUser) super.setAlt(alt);
      }

      @Override
      public RemoveCalmUser setFields(String fields) {
        return (RemoveCalmUser) super.setFields(fields);
      }

      @Override
      public RemoveCalmUser setKey(String key) {
        return (RemoveCalmUser) super.setKey(key);
      }

      @Override
      public RemoveCalmUser setOauthToken(String oauthToken) {
        return (RemoveCalmUser) super.setOauthToken(oauthToken);
      }

      @Override
      public RemoveCalmUser setPrettyPrint(Boolean prettyPrint) {
        return (RemoveCalmUser) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public RemoveCalmUser setQuotaUser(String quotaUser) {
        return (RemoveCalmUser) super.setQuotaUser(quotaUser);
      }

      @Override
      public RemoveCalmUser setUserIp(String userIp) {
        return (RemoveCalmUser) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private String id;

      /**

       */
      public String getId() {
        return id;
      }

      public RemoveCalmUser setId(String id) {
        this.id = id;
        return this;
      }

    }
    /**
     * Create a request for the method "calmUserEndpoint.updateCalmUser".
     *
     * This request holds the parameters needed by the the calmuserendpoint server.  After setting any
     * optional parameters, call the {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.UpdateCalmUser#execute()} method to invoke the remote
     * operation.
     *
     * @param content the {@link com.google.api.services.calmuserendpoint.model.CalmUser}
     * @return the request
     */
    public UpdateCalmUser updateCalmUser(com.google.api.services.calmuserendpoint.model.CalmUser content) throws java.io.IOException {
      UpdateCalmUser result = new UpdateCalmUser(content);
      initialize(result);
      return result;
    }

    public class UpdateCalmUser extends CalmuserendpointRequest<com.google.api.services.calmuserendpoint.model.CalmUser> {

      private static final String REST_PATH = "calmuser";

      /**
       * Create a request for the method "calmUserEndpoint.updateCalmUser".
       *
       * This request holds the parameters needed by the the calmuserendpoint server.  After setting any
       * optional parameters, call the {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.UpdateCalmUser#execute()} method to invoke the remote
       * operation. <p> {@link com.google.api.services.calmuserendpoint.Calmuserendpoint.CalmUserEndpoint.UpdateCalmUser#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must be called to
       * initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.google.api.services.calmuserendpoint.model.CalmUser}
       * @since 1.13
       */
      protected UpdateCalmUser(com.google.api.services.calmuserendpoint.model.CalmUser content) {
        super(Calmuserendpoint.this, "PUT", REST_PATH, content, com.google.api.services.calmuserendpoint.model.CalmUser.class);
      }

      @Override
      public UpdateCalmUser setAlt(String alt) {
        return (UpdateCalmUser) super.setAlt(alt);
      }

      @Override
      public UpdateCalmUser setFields(String fields) {
        return (UpdateCalmUser) super.setFields(fields);
      }

      @Override
      public UpdateCalmUser setKey(String key) {
        return (UpdateCalmUser) super.setKey(key);
      }

      @Override
      public UpdateCalmUser setOauthToken(String oauthToken) {
        return (UpdateCalmUser) super.setOauthToken(oauthToken);
      }

      @Override
      public UpdateCalmUser setPrettyPrint(Boolean prettyPrint) {
        return (UpdateCalmUser) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public UpdateCalmUser setQuotaUser(String quotaUser) {
        return (UpdateCalmUser) super.setQuotaUser(quotaUser);
      }

      @Override
      public UpdateCalmUser setUserIp(String userIp) {
        return (UpdateCalmUser) super.setUserIp(userIp);
      }

    }

  }

  /**
   * Builder for {@link com.google.api.services.calmuserendpoint.Calmuserendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport
     * @param jsonFactory JSON factory
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(HttpTransport transport, JsonFactory jsonFactory,
        HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link com.google.api.services.calmuserendpoint.Calmuserendpoint}. */
    @Override
    public Calmuserendpoint build() {
      return new Calmuserendpoint(getTransport(),
          getHttpRequestInitializer(),
          getRootUrl(),
          getServicePath(),
          getObjectParser(),
          getGoogleClientRequestInitializer(),
          getApplicationName(),
          getSuppressPatternChecks());
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    /**
     * Set the {@link CalmuserendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setCalmuserendpointRequestInitializer(
        CalmuserendpointRequestInitializer calmuserendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(calmuserendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
