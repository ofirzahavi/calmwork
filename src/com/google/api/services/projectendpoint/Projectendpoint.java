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
 *  on 2013-06-27 at 23:09:37 UTC 
 */

package com.google.api.services.projectendpoint;

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
 * Service definition for Projectendpoint (v1).
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
 * This service uses {@link ProjectendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * <p>
 * Upgrade warning: this class now extends {@link AbstractGoogleJsonClient}, whereas in prior
 * version 1.8 it extended {@link com.google.api.client.googleapis.services.GoogleClient}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Projectendpoint extends AbstractGoogleJsonClient {

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
  public static final String DEFAULT_SERVICE_PATH = "projectendpoint/v1/";

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
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport
   * @param jsonFactory JSON factory
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Projectendpoint(HttpTransport transport, JsonFactory jsonFactory,
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
  Projectendpoint(HttpTransport transport,
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
   * An accessor for creating requests from the ProjectEndpoint collection.
   *
   * <p>The typical use is:</p>
   * <pre>
   *   {@code Projectendpoint projectendpoint = new Projectendpoint(...);}
   *   {@code Projectendpoint.ProjectEndpoint.List request = projectendpoint.projectEndpoint().list(parameters ...)}
   * </pre>
   *
   * @return the resource collection
   */
  public ProjectEndpoint projectEndpoint() {
    return new ProjectEndpoint();
  }

  /**
   * The "projectEndpoint" collection of methods.
   */
  public class ProjectEndpoint {

    /**
     * Create a request for the method "projectEndpoint.getProject".
     *
     * This request holds the parameters needed by the the projectendpoint server.  After setting any
     * optional parameters, call the {@link GetProject#execute()} method to invoke the remote operation.
     *
     * @param id
     * @return the request
     */
    public GetProject getProject(String id) throws java.io.IOException {
      GetProject result = new GetProject(id);
      initialize(result);
      return result;
    }

    public class GetProject extends ProjectendpointRequest<com.google.api.services.projectendpoint.model.Project> {

      private static final String REST_PATH = "project/{id}";

      /**
       * Create a request for the method "projectEndpoint.getProject".
       *
       * This request holds the parameters needed by the the projectendpoint server.  After setting any
       * optional parameters, call the {@link GetProject#execute()} method to invoke the remote
       * operation. <p> {@link GetProject#initialize(AbstractGoogleClientRequest)} must be called to
       * initialize this instance immediately after invoking the constructor. </p>
       *
       * @param id
       * @since 1.13
       */
      protected GetProject(String id) {
        super(Projectendpoint.this, "GET", REST_PATH, null, com.google.api.services.projectendpoint.model.Project.class);
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
      public GetProject setAlt(String alt) {
        return (GetProject) super.setAlt(alt);
      }

      @Override
      public GetProject setFields(String fields) {
        return (GetProject) super.setFields(fields);
      }

      @Override
      public GetProject setKey(String key) {
        return (GetProject) super.setKey(key);
      }

      @Override
      public GetProject setOauthToken(String oauthToken) {
        return (GetProject) super.setOauthToken(oauthToken);
      }

      @Override
      public GetProject setPrettyPrint(Boolean prettyPrint) {
        return (GetProject) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public GetProject setQuotaUser(String quotaUser) {
        return (GetProject) super.setQuotaUser(quotaUser);
      }

      @Override
      public GetProject setUserIp(String userIp) {
        return (GetProject) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private String id;

      /**

       */
      public String getId() {
        return id;
      }

      public GetProject setId(String id) {
        this.id = id;
        return this;
      }

    }
    /**
     * Create a request for the method "projectEndpoint.insertProject".
     *
     * This request holds the parameters needed by the the projectendpoint server.  After setting any
     * optional parameters, call the {@link InsertProject#execute()} method to invoke the remote
     * operation.
     *
     * @param content the {@link com.google.api.services.projectendpoint.model.Project}
     * @return the request
     */
    public InsertProject insertProject(com.google.api.services.projectendpoint.model.Project content) throws java.io.IOException {
      InsertProject result = new InsertProject(content);
      initialize(result);
      return result;
    }

    public class InsertProject extends ProjectendpointRequest<com.google.api.services.projectendpoint.model.Project> {

      private static final String REST_PATH = "project";

      /**
       * Create a request for the method "projectEndpoint.insertProject".
       *
       * This request holds the parameters needed by the the projectendpoint server.  After setting any
       * optional parameters, call the {@link InsertProject#execute()} method to invoke the remote
       * operation. <p> {@link InsertProject#initialize(AbstractGoogleClientRequest)} must be called to
       * initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.google.api.services.projectendpoint.model.Project}
       * @since 1.13
       */
      protected InsertProject(com.google.api.services.projectendpoint.model.Project content) {
        super(Projectendpoint.this, "POST", REST_PATH, content, com.google.api.services.projectendpoint.model.Project.class);
      }

      @Override
      public InsertProject setAlt(String alt) {
        return (InsertProject) super.setAlt(alt);
      }

      @Override
      public InsertProject setFields(String fields) {
        return (InsertProject) super.setFields(fields);
      }

      @Override
      public InsertProject setKey(String key) {
        return (InsertProject) super.setKey(key);
      }

      @Override
      public InsertProject setOauthToken(String oauthToken) {
        return (InsertProject) super.setOauthToken(oauthToken);
      }

      @Override
      public InsertProject setPrettyPrint(Boolean prettyPrint) {
        return (InsertProject) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public InsertProject setQuotaUser(String quotaUser) {
        return (InsertProject) super.setQuotaUser(quotaUser);
      }

      @Override
      public InsertProject setUserIp(String userIp) {
        return (InsertProject) super.setUserIp(userIp);
      }

    }
    /**
     * Create a request for the method "projectEndpoint.listProject".
     *
     * This request holds the parameters needed by the the projectendpoint server.  After setting any
     * optional parameters, call the {@link ListProject#execute()} method to invoke the remote
     * operation.
     *
     * @return the request
     */
    public ListProject listProject() throws java.io.IOException {
      ListProject result = new ListProject();
      initialize(result);
      return result;
    }

    public class ListProject extends ProjectendpointRequest<com.google.api.services.projectendpoint.model.CollectionResponseProject> {

      private static final String REST_PATH = "project";

      /**
       * Create a request for the method "projectEndpoint.listProject".
       *
       * This request holds the parameters needed by the the projectendpoint server.  After setting any
       * optional parameters, call the {@link ListProject#execute()} method to invoke the remote
       * operation. <p> {@link ListProject#initialize(AbstractGoogleClientRequest)} must be called to
       * initialize this instance immediately after invoking the constructor. </p>
       *
       * @since 1.13
       */
      protected ListProject() {
        super(Projectendpoint.this, "GET", REST_PATH, null, com.google.api.services.projectendpoint.model.CollectionResponseProject.class);
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
      public ListProject setAlt(String alt) {
        return (ListProject) super.setAlt(alt);
      }

      @Override
      public ListProject setFields(String fields) {
        return (ListProject) super.setFields(fields);
      }

      @Override
      public ListProject setKey(String key) {
        return (ListProject) super.setKey(key);
      }

      @Override
      public ListProject setOauthToken(String oauthToken) {
        return (ListProject) super.setOauthToken(oauthToken);
      }

      @Override
      public ListProject setPrettyPrint(Boolean prettyPrint) {
        return (ListProject) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public ListProject setQuotaUser(String quotaUser) {
        return (ListProject) super.setQuotaUser(quotaUser);
      }

      @Override
      public ListProject setUserIp(String userIp) {
        return (ListProject) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private String cursor;

      /**

       */
      public String getCursor() {
        return cursor;
      }

      public ListProject setCursor(String cursor) {
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

      public ListProject setLimit(Integer limit) {
        this.limit = limit;
        return this;
      }

    }
    /**
     * Create a request for the method "projectEndpoint.removeProject".
     *
     * This request holds the parameters needed by the the projectendpoint server.  After setting any
     * optional parameters, call the {@link RemoveProject#execute()} method to invoke the remote
     * operation.
     *
     * @param id
     * @return the request
     */
    public RemoveProject removeProject(String id) throws java.io.IOException {
      RemoveProject result = new RemoveProject(id);
      initialize(result);
      return result;
    }

    public class RemoveProject extends ProjectendpointRequest<com.google.api.services.projectendpoint.model.Project> {

      private static final String REST_PATH = "project/{id}";

      /**
       * Create a request for the method "projectEndpoint.removeProject".
       *
       * This request holds the parameters needed by the the projectendpoint server.  After setting any
       * optional parameters, call the {@link RemoveProject#execute()} method to invoke the remote
       * operation. <p> {@link RemoveProject#initialize(AbstractGoogleClientRequest)} must be called to
       * initialize this instance immediately after invoking the constructor. </p>
       *
       * @param id
       * @since 1.13
       */
      protected RemoveProject(String id) {
        super(Projectendpoint.this, "DELETE", REST_PATH, null, com.google.api.services.projectendpoint.model.Project.class);
        this.id = Preconditions.checkNotNull(id, "Required parameter id must be specified.");
      }

      @Override
      public RemoveProject setAlt(String alt) {
        return (RemoveProject) super.setAlt(alt);
      }

      @Override
      public RemoveProject setFields(String fields) {
        return (RemoveProject) super.setFields(fields);
      }

      @Override
      public RemoveProject setKey(String key) {
        return (RemoveProject) super.setKey(key);
      }

      @Override
      public RemoveProject setOauthToken(String oauthToken) {
        return (RemoveProject) super.setOauthToken(oauthToken);
      }

      @Override
      public RemoveProject setPrettyPrint(Boolean prettyPrint) {
        return (RemoveProject) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public RemoveProject setQuotaUser(String quotaUser) {
        return (RemoveProject) super.setQuotaUser(quotaUser);
      }

      @Override
      public RemoveProject setUserIp(String userIp) {
        return (RemoveProject) super.setUserIp(userIp);
      }

      @com.google.api.client.util.Key
      private String id;

      /**

       */
      public String getId() {
        return id;
      }

      public RemoveProject setId(String id) {
        this.id = id;
        return this;
      }

    }
    /**
     * Create a request for the method "projectEndpoint.updateProject".
     *
     * This request holds the parameters needed by the the projectendpoint server.  After setting any
     * optional parameters, call the {@link UpdateProject#execute()} method to invoke the remote
     * operation.
     *
     * @param content the {@link com.google.api.services.projectendpoint.model.Project}
     * @return the request
     */
    public UpdateProject updateProject(com.google.api.services.projectendpoint.model.Project content) throws java.io.IOException {
      UpdateProject result = new UpdateProject(content);
      initialize(result);
      return result;
    }

    public class UpdateProject extends ProjectendpointRequest<com.google.api.services.projectendpoint.model.Project> {

      private static final String REST_PATH = "project";

      /**
       * Create a request for the method "projectEndpoint.updateProject".
       *
       * This request holds the parameters needed by the the projectendpoint server.  After setting any
       * optional parameters, call the {@link UpdateProject#execute()} method to invoke the remote
       * operation. <p> {@link UpdateProject#initialize(AbstractGoogleClientRequest)} must be called to
       * initialize this instance immediately after invoking the constructor. </p>
       *
       * @param content the {@link com.google.api.services.projectendpoint.model.Project}
       * @since 1.13
       */
      protected UpdateProject(com.google.api.services.projectendpoint.model.Project content) {
        super(Projectendpoint.this, "PUT", REST_PATH, content, com.google.api.services.projectendpoint.model.Project.class);
      }

      @Override
      public UpdateProject setAlt(String alt) {
        return (UpdateProject) super.setAlt(alt);
      }

      @Override
      public UpdateProject setFields(String fields) {
        return (UpdateProject) super.setFields(fields);
      }

      @Override
      public UpdateProject setKey(String key) {
        return (UpdateProject) super.setKey(key);
      }

      @Override
      public UpdateProject setOauthToken(String oauthToken) {
        return (UpdateProject) super.setOauthToken(oauthToken);
      }

      @Override
      public UpdateProject setPrettyPrint(Boolean prettyPrint) {
        return (UpdateProject) super.setPrettyPrint(prettyPrint);
      }

      @Override
      public UpdateProject setQuotaUser(String quotaUser) {
        return (UpdateProject) super.setQuotaUser(quotaUser);
      }

      @Override
      public UpdateProject setUserIp(String userIp) {
        return (UpdateProject) super.setUserIp(userIp);
      }

    }

  }

  /**
   * Builder for {@link Projectendpoint}.
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

    /** Builds a new instance of {@link Projectendpoint}. */
    @Override
    public Projectendpoint build() {
      return new Projectendpoint(getTransport(),
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
     * Set the {@link ProjectendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setProjectendpointRequestInitializer(
        ProjectendpointRequestInitializer projectendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(projectendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
