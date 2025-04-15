package Assignment.assignment_jkTech.Controllor;

import Assignment.assignment_jkTech.Model.Document;
import Assignment.assignment_jkTech.Repository.DocumentRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    void testUploadEndpoint() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.txt", "text/plain", "This is a test document.".getBytes()
        );

        mockMvc.perform(multipart("/api/documents/upload")
                .file(file)
                .param("author", "Test User")
                .param("type", "Guide"))
                .andExpect(status().isOk());
    }
}
