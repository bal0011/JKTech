package Assignment.assignment_jkTech.service;

import Assignment.assignment_jkTech.Model.Document;
import Assignment.assignment_jkTech.Repository.DocumentRepository;
import Assignment.assignment_jkTech.Service.DocumentService;

import org.apache.tika.Tika;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class DocumentServiceTest {

	 private final DocumentRepository documentRepository = mock(DocumentRepository.class);
	    private final DocumentService documentService = new DocumentService();
	    private final Tika tika = new Tika();

	    @Test
	    void testIngestTextFile() throws Exception {
	        String testContent = "Spring Boot document test.";
	        MockMultipartFile file = new MockMultipartFile(
	                "file", "test.txt", "text/plain", testContent.getBytes(StandardCharsets.UTF_8)
	        );

	        documentService.ingestDocument(file, "Jane Smith", "Guide");

	        verify(documentRepository, times(1)).save(Mockito.argThat(document ->
	                document.getAuthor().equals("Jane Smith") &&
	                document.getType().equals("Guide") &&
	                document.getContent().contains("Spring Boot")
	        ));
	    }
	}