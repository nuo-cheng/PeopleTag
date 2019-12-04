package com.example.getstarted.objects;

import java.util.Date;

/**
 * comment
 */
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

    /**
     * constructor
     * @param builder
     */
    private Comment(Builder builder){
        this.content=builder.content;
        this.createdBy=builder.createdBy;
        this.createdById=builder.createdById;
        this.id=builder.id;
        this.timeCreated=builder.timeCreated;
    }

    /**
     * builder
     */
    public static class Builder{
        private String content;
        private String createdBy;
        private String createdById;
        private Long id;
        private Date timeCreated;

        /**
         * content
         * @param content
         * @return
         */
        public Builder content(String content){
            this.content=content;
            return this;
        }

        /**
         * created by
         * @param createdBy
         * @return
         */
        public Builder createdBy(String createdBy){
            this.createdBy=createdBy;
            return this;
        }

        /**
         * created by id
         * @param createdById
         * @return
         */
        public Builder createdById(String createdById){
            this.createdById=createdById;
            return this;
        }

        /**
         * id
         * @param id
         * @return
         */
        public Builder id(Long id){
            this.id=id;
            return this;
        }

        /**
         * time created
         * @param timeCreated
         * @return
         */
        public Builder timeCreated(Date timeCreated){
            this.timeCreated=timeCreated;
            return this;
        }

        /**
         * build
         * @return
         */
        public Comment build(){
            return new Comment(this);
        }
    }

    /**
     * get content
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * set content
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * get created by
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * get created by
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * get created by id
     * @return
     */
    public String getCreatedById() {
        return createdById;
    }

    /**
     * set created by id
     * @param createdById
     */
    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    /**
     * get id
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * set id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get time created
     * @return
     */
    public Date getTimeCreated() {
        return timeCreated;
    }

    /**
     * set time created
     * @param timeCreated
     */
    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    /**
     * to string
     * @return
     */
    @Override
    public String toString() {
        return "Content: " + content + ", Added by: " + createdBy;
    }
}
