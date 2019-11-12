
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

/**
 * Collection class
 * object of collection
 */
public class Collection {
  /**
   * collection data members
   */
  private String collectionName;
  private Long id;
  private String description;
  private String createdBy;
  private String createdById;

  /**
   * static keys
   */
  public static final String COLLECTION_NAME = "collectionName";
  public static final String ID = "id";
  public static final String DESCRIPTION = "description";
  public static final String CREATED_BY = "createdBy";
  public static final String CREATED_BY_ID = "createdById";

  /**
   * constructor
   * @param builder
   */
  // We use a Builder pattern here to simplify and standardize construction of Person objects.
  private Collection(Builder builder) {
    this.collectionName = builder.collectionName;
    this.id = builder.id;
    this.description = builder.description;
    this.createdBy = builder.createdBy;
    this.createdById = builder.createdById;

  }

  /**
   * collection builder
   * build a collection
   */
  public static class Builder {
    private String collectionName;
    private Long id;
    private String description;
    private String createdBy;
    private String createdById;

    /**
     * collection name
     * @param collectionName
     * @return
     */
    public Builder collectionName(String collectionName) {
      this.collectionName = collectionName;
      return this;
    }

    /**
     * collection id
     * @param id
     * @return
     */
    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    /**
     * collection description
     * @param description
     * @return
     */
    public Builder description(String description){
      this.description = description;
      return this;
    }

    /**
     * collection created by
     * @param createdBy
     * @return
     */
    public Builder createdBy(String createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    /**
     * collection created by a specific user
     * @param createdById
     * @return
     */
    public Builder createdById(String createdById) {
      this.createdById = createdById;
      return this;
    }

    /**
     * build collection
     * @return
     */
    public Collection build() {
      return new Collection(this);
    }
  }

  /**
   * collection name getter
   * @return
   */
  public String getCollectionName() {
    return collectionName;
  }

  /**
   * collection name setter
   * @param collectionName
   */
  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  /**
   * collection id getter
   * @return
   */
  public Long getId() {
    return id;
  }

  /**
   * collection id setter
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * collection description getter
   * @return
   */
  public String getDescription() {
    return description;
  }

  /**
   * collection description setter
   * @param description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * collection created by getter
   * @return
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * collection created by setter
   * @return
   */
  public String getCreatedById() {
    return createdById;
  }

  /**
   * collection created by setter
   * @param createdBy
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * collection created by a specific user
   * @param createdById
   */
  public void setCreatedById(String createdById) {
    this.createdById = createdById;
  }

  /**
   * to String method
   * @return
   */
  @Override
  public String toString() {
    return
        "Collection Name: " + collectionName  + "Collection Description: " + description + ", Added by: " + createdBy;
  }
}
// [END example]

