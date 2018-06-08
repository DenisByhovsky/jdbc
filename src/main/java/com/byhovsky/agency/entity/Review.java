package com.byhovsky.agency.entity;

/**
 * Review
 *
 * @author Denis Byhovsky
 */
public class Review {
    private int reviewId;
    private String content;
    private Tour tour;

    public Review(int reviewId, String content, Tour tour) {
        this.reviewId = reviewId;
        this.content = content;
        this.tour = tour;
    }

    /**
     * Getter for property 'reviewId'.
     *
     * @return Value for property 'reviewId'.
     */
    public int getReviewId() {
        return reviewId;
    }

    /**
     * Setter for property 'reviewId'.
     *
     * @param reviewId Value to set for property 'reviewId'.
     */
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * Getter for property 'content'.
     *
     * @return Value for property 'content'.
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter for property 'content'.
     *
     * @param content Value to set for property 'content'.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter for property 'tour'.
     *
     * @return Value for property 'tour'.
     */
    public Tour getTour() {
        return tour;
    }

    /**
     * Setter for property 'tour'.
     *
     * @param tour Value to set for property 'tour'.
     */
    public void setTour(Tour tour) {
        this.tour = tour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (reviewId != review.reviewId) return false;
        if (content != null ? !content.equals(review.content) : review.content != null) return false;
        return tour != null ? tour.equals(review.tour) : review.tour == null;
    }

    @Override
    public int hashCode() {
        int result = reviewId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (tour != null ? tour.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", content='" + content + '\'' +
                ", tour=" + tour +
                '}';
    }
}
