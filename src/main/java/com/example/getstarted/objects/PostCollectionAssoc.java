package com.example.getstarted.objects;

public class PostCollectionAssoc {
    public static final String ID="id";
    public static final String COLLECTION_ID="personId";
    public static final String POST_ID="postId";
    private Long id;
    private Long collectionId;
    private Long postId;

    public PostCollectionAssoc(Long id, Long collectionId, Long postId){
        this.id=id;
        this.collectionId=collectionId;
        this.postId=postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
