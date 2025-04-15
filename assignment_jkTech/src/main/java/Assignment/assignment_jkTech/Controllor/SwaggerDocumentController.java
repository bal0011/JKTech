package Assignment.assignment_jkTech.Controllor;


import Assignment.assignment_jkTech.Model.Document;
import Assignment.assignment_jkTech.Service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/swagger-documents")
@Tag(name = "Document API", description = "Upload, search and filter documents")
public class SwaggerDocumentController {
	@Autowired
    private DocumentService documentService;

    @Operation(summary = "Upload a document", description = "Uploads a file with metadata (author, type) for ingestion")
    @PostMapping("/upload")
    public String uploadDocument(@RequestParam("file") MultipartFile file,
                                 @RequestParam String author,
                                 @RequestParam String type) throws Exception {
        documentService.ingestDocument(file, author, type);
        return "Upload initiated";
    }

    @Operation(summary = "Search documents", description = "Searches document content by keyword")
    @GetMapping("/search")
    public Page<Document> search(@RequestParam String keyword,
                                 @RequestParam int page,
                                 @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return documentService.searchDocuments(keyword, pageable);
    }

    @Operation(summary = "Filter documents", description = "Filters documents by metadata such as author and type")
    @GetMapping("/filter")
    public Page<Document> filter(@RequestParam String author,
                                 @RequestParam String type,
                                 @RequestParam int page,
                                 @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return documentService.filterDocuments(author, type, pageable);
    }
}
