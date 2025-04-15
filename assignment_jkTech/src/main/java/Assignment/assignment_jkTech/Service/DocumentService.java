package Assignment.assignment_jkTech.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Assignment.assignment_jkTech.Model.Document;
import Assignment.assignment_jkTech.Repository.DocumentRepository;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Service
public class DocumentService {
	private final Logger logger = LoggerFactory.getLogger(DocumentService.class);
	
	@Autowired	
    private DocumentRepository documentRepository;
    private Tika tika = new Tika();
/*
    @Async
    public void ingestDocument(MultipartFile file, String author, String type) {
        try {
        	 String content = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                     .lines().collect(Collectors.joining("\n"));

             Document document = new Document();
            document.setTitle(file.getOriginalFilename());
            document.setAuthor(author);
            document.setType(type);
            document.setUploadDate(LocalDateTime.now());
            document.setContent(content);
            logger.info("contents {} :", content);
            System.out.println("Parsed content length: " + content.length());
            documentRepository.save(document);
            System.out.println("Document saved successfully.");
        } catch (Exception e) {
           // System.err.println("Error while ingesting document: " + e.getMessage());
            logger.error(" error while writting content");
            
            e.printStackTrace();
        }
    }
    
    */

   /*
    @Async
    public void ingestDocument(MultipartFile file, String author, String type) {
        try {
            // Use Apache Tika for extracting content (works for .txt, .docx, .pdf)
            String content = tika.parseToString(file.getInputStream());

            Document document = new Document();
            document.setTitle(file.getOriginalFilename());
            document.setAuthor(author);
            document.setType(type);
            document.setUploadDate(LocalDateTime.now());
            	content = tika.parseToString(file.getInputStream());
            document.setContent(content);

            logger.info("Extracted content:\n{}", content);
            documentRepository.save(document);
        } catch (Exception e) {
            logger.error("Error while extracting content: {}", e.getMessage(), e);
        }
    }
    */
    
    @Async
    public void ingestDocument(MultipartFile file, String author, String type) {
        try {
            byte[] fileBytes = file.getBytes();
            String detectedType = tika.detect(fileBytes);
            logger.info("Detected content type: {}", detectedType);

            String content = tika.parseToString(new ByteArrayInputStream(fileBytes));
            logger.info("Extracted content:\n{}", content);

            Document document = new Document();
            document.setTitle(file.getOriginalFilename());
            document.setAuthor(author);
            document.setType(type);
            document.setUploadDate(LocalDateTime.now());
            document.setContent(content);

            documentRepository.save(document);
        } catch (Exception e) {
            logger.error("Error while extracting content: {}", e.getMessage(), e);
        }
    }


    
    public Page<Document> searchDocuments(String keyword, Pageable pageable) {
        return documentRepository.searchByKeyword(keyword, pageable);
    }

    public Page<Document> filterDocuments(String author, String type, Pageable pageable) {
        return documentRepository.findByAuthorContainingIgnoreCaseAndTypeContainingIgnoreCase(author, type, pageable);
    }
}