package com.example.getstarted.objects;

import java.util.Date;

public class Comment {
    private String content;
    private String createdBy;
    private String createdById;
    private Long id;
    private Date timeCreated;

    public static final String CONTENT="content";
    public static final String CREATEDBY="createdBy";
    public static final String CREATEDBYID="createdById";
    public static final String ID="id";
    public static final String TIMECREATED="timeCreated";

    private Comment(Builder builder){
        this.content=builder.content;
        this.createdBy=builder.createdBy;
        this.createdById=builder.createdById;
        this.id=builder.id;
        this.timeCreated=builder.timeCreated;
    }

    public static class Builder{
        private String content;
        private String createdBy;
        private String createdById;
        private Long id;
        private Date timeCreated;

        public Builder content(String content){
            this.content=content;
            return this;
        }

        public Builder createdBy(String createdBy){
            this.createdBy=createdBy;
            return this;
        }

        public Builder createdById(String createdById){
            this.createdById=createdById;
            return this;
        }

        public Builder id(Long id){
            this.id=id;
            return this;
        }

        public Builder timeCreated(Date timeCreated){
            this.timeCreated=timeCreated;
            return this;
        }

        public Comment build(){
            return new Comment(this);
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public String toString() {
        return "Content: " + content + ", Added by: " + createdBy;
    }
}
