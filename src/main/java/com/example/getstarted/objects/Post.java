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

// [START example]

/**
 * Person class
 * object of a person
 */
public class Post {
  /**
   * Person class data members
   */
  private String title;
  private String content;
  private String createdBy;
  private String createdById;

  private String url;

  private Long id;
  private String imageUrl;

  /**
   * static keys
   */
  public static final String TITLE = "title";
  public static final String CREATED_BY = "createdBy";
  public static final String CREATED_BY_ID = "createdById";
  public static final String CONTENT = "content";
  public static final String ID = "id";

  public static final String URL = "url";
  public static final String IMAGE_URL = "imageUrl";




  // [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of Person objects.

  /**
   * constructor
   * use a Builder pattern here to simplify and standardize construction of Collection objects.
   * @param builder
   */
  private Post(Builder builder) {
    this.title = builder.title;
    this.content = builder.content;
    this.createdBy = builder.createdBy;
    this.createdById = builder.createdById;

    this.url = builder.url;
    this.id = builder.id;
    this.imageUrl = builder.imageUrl;


  }

  /**
   * builder
   */
  public static class Builder {
    private String title;
    private String content;
    private String createdBy;
    private String createdById;
    private Long id;
    private String imageUrl;
    private String url;

    /**
     * build first name
     * @param title
     * @return
     */
    public Builder title(String title) {
      this.title = title;
      return this;
    }

    /**
     * build last name
     * @param content
     * @return
     */
    public Builder content(String content) {
      this.content = content;
      return this;
    }

    /**
     * build createdBy
     * @param createdBy
     * @return
     */
    public Builder createdBy(String createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    /**
     * build createById
     * @param createdById
     * @return
     */
    public Builder createdById(String createdById) {
      this.createdById = createdById;
      return this;
    }

    /**
     * build description
     * @param url
     * @return
     */
    public Builder url(String url) {
      this.url = url;
      return this;
    }

    /**
     * build id
     * @param id
     * @return
     */
    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    /**
     * build image URL
     * @param imageUrl
     * @return
     */
    public Builder imageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
      return this;
    }

    /**
     * build Person
     * @return
     */
    public Post build() {
      return new Post(this);
    }
  }


  /**
   * createBy getter
   * @return
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * createBy setter
   * @param createdBy
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * createById getter
   * @return
   */
  public String getCreatedById() {
    return createdById;
  }

  /**
   * createById setter
   * @param createdById
   */
  public void setCreatedById(String createdById) {
    this.createdById = createdById;
  }

  /**
   * id getter
   * @return
   */
  public Long getId() {
    return id;
  }

  /**
   * id setter
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * image URL getter
   * @return
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * image URL setter
   * @param imageUrl
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getUrl() {
    return url;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  // [END builder]

  /**
   * to String
   * @return
   */
  @Override
  public String toString() {
    return
        "Title: " + title + ", Content: " + content + ", Added by: " + createdBy;
  }
}
