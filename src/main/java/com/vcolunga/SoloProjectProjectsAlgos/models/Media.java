package com.vcolunga.SoloProjectProjectsAlgos.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="media")
		public class Media {
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private Long id;
			
			@NotNull
			@Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
			private String name;
			
			private String mediaType;
			
			@NotEmpty(message = "Description must not be empty")
			@Column(columnDefinition = "TEXT")
			private String description;
			
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "list_id")
			private MediaList parentList;
			
			@Column(updatable=false)
			@DateTimeFormat(pattern="yyyy-MM-dd")
			private Date createdAt;
			@DateTimeFormat(pattern="yyyy-MM-dd")
			private Date updatedAt;
			
			public Media() {
			}
			
			@PrePersist
			protected void onCreate(){
				this.createdAt = new Date();
			}
			@PreUpdate
			protected void onUpdate(){
				this.updatedAt = new Date();
			}

			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getMediaType() {
				return mediaType;
			}

			public void setMediaType(String mediaType) {
				this.mediaType = mediaType;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public MediaList getParentList() {
				return parentList;
			}

			public void setParentList(MediaList parentList) {
				this.parentList = parentList;
			}
		}