package Assignment.assignment_jkTech.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Assignment.assignment_jkTech.Model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    
	Page<Document> findByAuthorContainingIgnoreCaseAndTypeContainingIgnoreCase(String author, String type, Pageable pageable);

	@Query(
    	    value = "SELECT * FROM document WHERE LOWER(content) LIKE LOWER(CONCAT('%', :keyword, '%'))",
    	    countQuery = "SELECT COUNT(*) FROM document WHERE LOWER(content) LIKE LOWER(CONCAT('%', :keyword, '%'))",
    	    nativeQuery = true
    	)
    	Page<Document> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
