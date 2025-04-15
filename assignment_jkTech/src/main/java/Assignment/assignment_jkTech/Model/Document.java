package Assignment.assignment_jkTech.Model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Builder;

@Entity
@Builder
public class Document {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    private String author;
	    private String type;
	    private LocalDateTime uploadDate;
	    @Column(columnDefinition = "LONGTEXT")
	    private String content;

		public Document() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Document(Long id, String title, String author, String type, LocalDateTime uploadDate, String content) {
			super();
			this.id = id;
			this.title = title;
			this.author = author;
			this.type = type;
			this.uploadDate = uploadDate;
			this.content = content;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public LocalDateTime getUploadDate() {
			return uploadDate;
		}

		public void setUploadDate(LocalDateTime uploadDate) {
			this.uploadDate = uploadDate;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

}
