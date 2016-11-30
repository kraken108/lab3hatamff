/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jakob
 */
public class Review {
    
    private String reviewId;
    private String rating;
    private String comment;
    
    public Review(String reviewId, String rating, String comment){
        this.reviewId=reviewId;
        this.rating=rating;
        this.comment=comment;
    }

    /**
     * @return the reviewId
     */
    public String getReviewId() {
        return reviewId;
    }

    /**
     * @param reviewId the reviewId to set
     */
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
}
