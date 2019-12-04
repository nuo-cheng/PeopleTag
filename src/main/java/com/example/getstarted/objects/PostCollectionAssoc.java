package com.example.getstarted.objects;

/**
 * post collection association
 */
public class PostCollectionAssoc {
    public static final String ID="id";
    public static final String COLLECTION_ID="collectionId";
    public static final String POST_ID="postId";
    private Long id;
    private Long collectionId;
    private Long postId;

    /**
     * constructor
     * @param id
     * @param collectionId
     * @param postId
     */
    public PostCollectionAssoc(Long id, Long collectionId, Long postId){
        this.id=id;
        this.collectionId=collectionId;
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
     * get collection id
     * @return
     */
    public Long getCollectionId() {
        return collectionId;
    }

    /**
     * set collection id
     * @param collectionId
     */
    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
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
