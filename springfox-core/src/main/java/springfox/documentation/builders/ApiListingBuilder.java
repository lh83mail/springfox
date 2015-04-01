/*
 *
 *  Copyright 2015 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package springfox.documentation.builders;

import com.google.common.collect.Ordering;
import springfox.documentation.schema.Model;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Authorization;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Maps.*;
import static com.google.common.collect.Sets.*;
import static springfox.documentation.builders.BuilderDefaults.*;

public class ApiListingBuilder {
  private final Ordering<ApiDescription> descriptionOrdering;
  private String apiVersion;
  private String basePath;
  private String resourcePath;
  private String description;
  private int position;

  private Set<String> produces = newHashSet();
  private Set<String> consumes = newHashSet();
  private Set<String> protocol = newHashSet();
  private List<Authorization> authorizations = newArrayList();
  private List<ApiDescription> apis = newArrayList();
  private Map<String, Model> models = newHashMap();

  /**
   * Update the sorting order for api descriptions
   *
   * @param descriptionOrdering - ordering for the api descriptions
   */
  public ApiListingBuilder(Ordering<ApiDescription> descriptionOrdering) {
    this.descriptionOrdering = descriptionOrdering;
  }

  /**
   * Updates the api version
   *
   * @param apiVersion - api version
   * @return this
   */
  public ApiListingBuilder apiVersion(String apiVersion) {
    this.apiVersion = BuilderDefaults.defaultIfAbsent(apiVersion, this.apiVersion);
    return this;
  }

  /**
   * Updates base path for the api listing
   *
   * @param basePath - base path
   * @return this
   */
  public ApiListingBuilder basePath(String basePath) {
    this.basePath = BuilderDefaults.defaultIfAbsent(basePath, this.basePath);
    return this;
  }

  /**
   * Updates resource path for the api listing
   *
   * @param resourcePath - resource path
   * @return this
   */
  public ApiListingBuilder resourcePath(String resourcePath) {
    this.resourcePath = BuilderDefaults.defaultIfAbsent(resourcePath, this.resourcePath);
    return this;
  }

  /**
   * Replaces the existing media types with new entries that this documentation produces
   *
   * @param mediaTypes - new media types
   * @return this
   */
  public ApiListingBuilder produces(Set<String> mediaTypes) {
    if (mediaTypes != null) {
      this.produces = newHashSet(mediaTypes);
    }
    return this;
  }

  /**
   * Replaces the existing media types with new entries that this documentation consumes
   *
   * @param mediaTypes - new media types
   * @return this
   */
  public ApiListingBuilder consumes(Set<String> mediaTypes) {
    if (mediaTypes != null) {
      this.consumes = newHashSet(mediaTypes);
    }
    return this;
  }

  /**
   * Appends to the exiting collection of supported media types this listing produces
   *
   * @param produces - new media types
   * @return this
   */
  public ApiListingBuilder appendProduces(List<String> produces) {
    this.produces.addAll(nullToEmptyList(produces));
    return this;
  }

  /**
   * Appends to the exiting collection of supported media types this listing consumes
   *
   * @param consumes - new media types
   * @return this
   */
  public ApiListingBuilder appendConsumes(List<String> consumes) {
    this.consumes.addAll(nullToEmptyList(consumes));
    return this;
  }


  /**
   * Appends to the exiting collection of supported protocols
   *
   * @param protocols - new protocols
   * @return this
   */
  public ApiListingBuilder protocols(Set<String> protocols) {
    this.protocol.addAll(nullToEmptySet(protocols));
    return this;
  }

  /**
   * Updates the references to the security definitiosn
   *
   * @param authorizations - security definition references
   * @return this
   */
  public ApiListingBuilder authorizations(List<Authorization> authorizations) {
    if (authorizations != null) {
      this.authorizations = newArrayList(authorizations);
    }
    return this;
  }

  /**
   * Updates the apis
   *
   * @param apis - apis
   * @return
   */
  public ApiListingBuilder apis(List<ApiDescription> apis) {
    if (apis != null) {
      this.apis = descriptionOrdering.sortedCopy(apis);
    }
    return this;
  }

  /**
   * Adds to the models collection
   *
   * @param models - model entries by name
   * @return this
   */
  public ApiListingBuilder models(Map<String, Model> models) {
    this.models.putAll(nullToEmptyMap(models));
    return this;
  }

  /**
   * Updates the description
   *
   * @param description - description of the api listing
   * @return this
   */
  public ApiListingBuilder description(String description) {
    this.description = BuilderDefaults.defaultIfAbsent(description, this.description);
    return this;
  }

  /**
   * Updates the position of the listing
   *
   * @param position - position used to for sorting the listings
   * @return this
   */
  public ApiListingBuilder position(int position) {
    this.position = position;
    return this;
  }

  public ApiListing build() {
    return new ApiListing(apiVersion, basePath,
        resourcePath, produces, consumes, protocol, authorizations, apis, models, description, position);
  }
}