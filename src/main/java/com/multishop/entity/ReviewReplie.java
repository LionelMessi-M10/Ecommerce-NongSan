package com.multishop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "review_replies")
public class ReviewReplie extends Base { // Phan hoi danh gia

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "review_id", nullable = false)
	private Review review;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user; // chu shop hoac admin phan hoi
	
	@Lob
	@Column(name = "comment", columnDefinition = "TEXT")
	private String comment;
	
}
