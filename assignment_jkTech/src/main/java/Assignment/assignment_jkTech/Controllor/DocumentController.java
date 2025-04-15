package Assignment.assignment_jkTech.Controllor;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import Assignment.assignment_jkTech.Model.Document;
import Assignment.assignment_jkTech.Service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
	@Autowired
    private  DocumentService documentService;

	
	 @Operation(summary = "Upload a document with metadata")
	    @PostMapping("/upload")
	    public String uploadDocument(
	        @Parameter(description = "Document file to upload") @RequestParam("file") MultipartFile file,
	        @Parameter(description = "Author of the document") @RequestParam String author,
	        @Parameter(description = "Document type") @RequestParam String type) throws Exception {
	        documentService.ingestDocument(file, author, type);
	        return "Upload initiated";
	    }

	    @Operation(summary = "Search documents by keyword in content")
	    @GetMapping("/search")
	    public Page<Document> search(@RequestParam String keyword,
	                                 @RequestParam int page,
	                                 @RequestParam int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return documentService.searchDocuments(keyword, pageable);
	    }

	    @Operation(summary = "Filter documents by author and type")
	    @GetMapping("/filter")
	    public Page<Document> filter(@RequestParam String author,
	                                 @RequestParam String type,
	                                 @RequestParam int page,
	                                 @RequestParam int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return documentService.filterDocuments(author, type, pageable);
	    }
	}
	/*
	@PostMapping("/upload")
	public String uploadDocument(@RequestParam("file") MultipartFile file,
	                             @RequestParam String author,
	                             @RequestParam String type) throws Exception {
	    documentService.ingestDocument(file, author, type);
	    return "Upload initiated";
	}

    @GetMapping("/search")
    public Page<Document> search(@RequestParam String keyword,
                                 @RequestParam int page,
                                 @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return documentService.searchDocuments(keyword, pageable);
    }

    @GetMapping("/filter")
    public Page<Document> filter(@RequestParam String author,
                                 @RequestParam String type,
                                 @RequestParam int page,
                                 @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return documentService.filterDocuments(author, type, pageable);
    }
    
    
    */
 
