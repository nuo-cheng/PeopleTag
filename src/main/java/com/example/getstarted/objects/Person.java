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
public class Person {
  /**
   * Person class data members
   */
  private String first;
  private String last;
  private String createdBy;
  private String createdById;

  private String gender;
  private String jobTitle;
  private String interest;

  private String description;
  private Long id;
  private String imageUrl;

  private String linkedIn;
  private String facebook;
  private String twitter;
  private String instagram;

  /**
   * static keys
   */
  public static final String LAST = "last";
  public static final String CREATED_BY = "createdBy";
  public static final String CREATED_BY_ID = "createdById";
  public static final String DESCRIPTION = "description";
  public static final String ID = "id";
 
  public static final String FIRST = "first";
  public static final String IMAGE_URL = "imageUrl";

  public static final String GENDER = "gender";
  public static final String JOB_TITLE = "jobTitle";
  public static final String INTEREST ="interest";

  public static final String LINKEDIN = "linkedIn";
  public static final String FACEBOOK = "facebook";
  public static final String TWITTER = "twitter";
  public static final String INSTAGRAM = "instagram";


  // [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of Person objects.

  /**
   * constructor
   * use a Builder pattern here to simplify and standardize construction of Collection objects.
   * @param builder
   */
  private Person(Builder builder) {
    this.first = builder.first;
    this.last = builder.last;
    this.createdBy = builder.createdBy;
    this.createdById = builder.createdById;

    this.description = builder.description;
    this.id = builder.id;
    this.imageUrl = builder.imageUrl;
    this.gender = builder.gender;
    this.jobTitle = builder.jobTitle;
    this.interest = builder.interest;

    this.linkedIn = builder.linkedIn;
    this.facebook = builder.facebook;
    this.twitter = builder.twitter;
    this.instagram = builder.instagram;

  }

  /**
   * builder
   */
  public static class Builder {
    private String first;
    private String last;
    private String createdBy;
    private String createdById;
    private String publishedDate;
    private String description;
    private Long id;
    private String imageUrl;
    private String gender;
    private String jobTitle;
    private String interest;
    private String linkedIn;
    private String facebook;
    private String twitter;
    private String instagram;

    /**
     * build first name
     * @param first
     * @return
     */
    public Builder first(String first) {
      this.first = first;
      return this;
    }

    /**
     * build last name
     * @param last
     * @return
     */
    public Builder last(String last) {
      this.last = last;
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
     * @param description
     * @return
     */
    public Builder description(String description) {
      this.description = description;
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
     * build gender
     * @param gender
     * @return
     */
    public Builder gender(String gender) {
      this.gender = gender;
      return this;
    }

    /**
     * build job title
     * @param jobTitle
     * @return
     */
    public Builder jobTitle(String jobTitle) {
      this.jobTitle = jobTitle;
      return this;
    }

    /**
     * build interest
     * @param interest
     * @return
     */
    public Builder interest(String interest) {
      this.interest = interest;
      return this;
    }

    /**
     * build interest
     * @param linkedIn
     * @return
     */
    public Builder linkedIn(String linkedIn) {
      this.linkedIn = linkedIn;
      return this;
    }

    /**
     * build interest
     * @param facebook
     * @return
     */
    public Builder facebook(String facebook) {
      this.facebook = facebook;
      return this;
    }

    /**
     * build interest
     * @param twitter
     * @return
     */
    public Builder twitter(String twitter) {
      this.twitter = twitter;
      return this;
    }

    /**
     * build interest
     * @param instagram
     * @return
     */
    public Builder instagram(String instagram) {
      this.instagram = instagram;
      return this;
    }

    /**
     * build Person
     * @return
     */
    public Person build() {
      return new Person(this);
    }
  }

  /**
   * first name getter
   * @return
   */
  public String getFirst() {
    return first;
  }

  /**
   * first name setter
   * @param first
   */
  public void setFirst(String first) {
    this.first = first;
  }

  /**
   * last name getter
   * @return
   */
  public String getLast() {
    return last;
  }

  /**
   * last name setter
   * @param last
   */
  public void setLast(String last) {
    this.last = last;
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
   * description getter
   * @return
   */
  public String getDescription() {
    return description;
  }

  /**
   * description setter
   * @param description
   */
  public void setDescription(String description) {
    this.description = description;
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

  /**
   * gender getter
   * @return
   */
  public String getGender() {
    return gender;
  }

  /**
   * job title getter
   * @return
   */
  public String getJobTitle() {
    return jobTitle;
  }

  /**
   * interest getter
   * @return
   */
  public String getInterest() {
    return interest;
  }

  /**
   * gender setter
   * @param gender
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * job title setter
   * @param jobTitle
   */
  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  /**
   * interest setter
   * @param interest
   */
  public void setInterest(String interest) {
    this.interest = interest;
  }

  public String getLinkedIn() {
    return linkedIn;
  }

  public String getFacebook() {
    return facebook;
  }

  public String getTwitter() {
    return twitter;
  }

  public String getInstagram() {
    return instagram;
  }

  public void setLinkedIn(String linkedIn) {
    this.linkedIn = linkedIn;
  }

  public void setFacebook(String facebook) {
    this.facebook = facebook;
  }

  public void setTwitter(String twitter) {
    this.twitter = twitter;
  }

  public void setInstagram(String instagram) {
    this.instagram = instagram;
  }

  // [END builder]

  /**
   * to String
   * @return
   */
  @Override
  public String toString() {
    return
        "First: " + first + ", Last: " + last + ", Added by: " + createdBy;
  }
}
