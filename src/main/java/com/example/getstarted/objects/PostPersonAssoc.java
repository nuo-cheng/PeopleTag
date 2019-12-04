package com.example.getstarted.objects;

/**
 * post person association
 */
public class PostPersonAssoc {
    public static final String ID="id";
    public static final String PERSON_ID="personId";
    public static final String POST_ID="postId";
    private Long id;
    private Long personId;
    private Long postId;

    /**
     * constructor
     * @param id
     * @param personId
     * @param postId
     */
    public PostPersonAssoc(Long id,Long personId,Long postId){
        this.id=id;
        this.personId=personId;
        this.postId=postId;
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
     * get person id
     * @return
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * set person id
     * @param personId
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * get post id
     * @return
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * set post id
     * @param postId
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
