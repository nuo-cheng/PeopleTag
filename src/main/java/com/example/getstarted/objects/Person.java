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
public class Person {
  // [START person]
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
  // [END person]
  // [START keys]
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

  // [END keys]

  // [START constructor]
  // We use a Builder pattern here to simplify and standardize construction of Person objects.
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

  }
  // [END constructor]

  // [START builder]
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

    public Builder first(String first) {
      this.first = first;
      return this;
    }

    public Builder last(String last) {
      this.last = last;
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


    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder imageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
      return this;
    }

    public Builder gender(String gender) {
      this.gender = gender;
      return this;
    }

    public Builder jobTitle(String jobTitle) {
      this.jobTitle = jobTitle;
      return this;
    }

    public Builder interest(String interest) {
      this.interest = interest;
      return this;
    }

    public Person build() {
      return new Person(this);
    }
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getCreatedById() {
    return createdById;
  }

  public void setCreatedById(String createdById) {
    this.createdById = createdById;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getGender() {
    return gender;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public String getInterest() {
    return interest;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public void setInterest(String interest) {
    this.interest = interest;
  }

  // [END builder]
  @Override
  public String toString() {
    return
        "First: " + first + ", Last: " + last + ", Added by: " + createdBy;
  }
}
// [END example]
