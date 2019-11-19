package com.example.getstarted.objects;

public class PostPersonAssoc {
    public static final String ID="id";
    public static final String PERSON_ID="personId";
    public static final String POST_ID="postId";
    private Long id;
    private Long personId;
    private Long postId;

    public PostPersonAssoc(Long id,Long personId,Long postId){
        this.id=id;
        this.personId=personId;
        this.postId=postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
