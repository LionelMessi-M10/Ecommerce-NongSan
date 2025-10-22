package com.multishop.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Review extends Base {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
    
	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

	@Column(name = "rating")
    private Integer rating;
	
	@Lob
	@Column(name = "comment", columnDefinition = "TEXT")
    private String comment;
	
	@Column(name = "video_url")
	private String videoUrl;
	
	@Lob
	@Column(name = "image_urls", columnDefinition = "TEXT")
	private String imageUrls;
	
//	@Column(name = "is_approved")
//	private Boolean isApproved; // Co the can admin duyet
	
	@Column(name = "helpful_votes")
	private Integer helpfulVotes; // so luong vote tot
	
	@Column(name = "unhelpful_votes")
	private Integer unhelpfulVotes; // so luong vote xau 
	
	@OneToMany(mappedBy = "review", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
	private List<ReviewReplie> reviewReplies;
	
}

