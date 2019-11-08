
/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.getstarted.objects;


public class Collection {
  // [START collection]
  private String collectionName;
  private Long id;
  private String description;
  private String createdBy;
  private String createdById;

  // [END collection]
  // [START keys]
  public static final String COLLECTION_NAME = "collectionName";
  public static final String ID = "id";
  public static final String DESCRIPTION = "description";
  public static final String CREATED_BY = "createdBy";
  public static final String CREATED_BY_ID = "createdById";

  // [END keys]

  // [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of Person objects.
  private Collection(Builder builder) {
    this.collectionName = builder.collectionName;
    this.id = builder.id;
    this.description = builder.description;
    this.createdBy = builder.createdBy;
    this.createdById = builder.createdById;

  }
  // [END constructor]

  // [START builder]
  public static class Builder {
    private String collectionName;
    private Long id;
    private String description;
    private String createdBy;
    private String createdById;

    public Builder collectionName(String collectionName) {
      this.collectionName = collectionName;
      return this;
    }

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder description(String description){
      this.description = description;
      return this;
    }

    public Builder createdBy(String createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder createdById(String createdById) {
      this.createdById = createdById;
      return this;
    }

    public Collection build() {
      return new Collection(this);
    }
  }

  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public String getCreatedById() {
    return createdById;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public void setCreatedById(String createdById) {
    this.createdById = createdById;
  }

  // [END builder]
  @Override
  public String toString() {
    return
        "Collection Name: " + collectionName  + "Collection Description: " + description + ", Added by: " + createdBy;
  }
}
// [END example]

