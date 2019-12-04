package com.example.getstarted.objects;

import java.util.Date;

public class CommentPostAssoc {
    public static final String ID="id";
    public static final String COMMENTID="commentId";
    public static final String POST_ID="postId";
    public static final String TIMECREATED="timeCreated";
    private Long id;
    private Long commentId;
    private Long postId;
    private Date timeCreated;

    public CommentPostAssoc(Long id, Long commentId, Long postId) {
        this.id = id;
        this.commentId = commentId;
        this.postId = postId;
        this.timeCreated=new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }
}
