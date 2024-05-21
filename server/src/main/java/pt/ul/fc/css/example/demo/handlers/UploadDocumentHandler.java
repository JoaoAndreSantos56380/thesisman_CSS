package pt.ul.fc.css.example.demo.handlers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import pt.ul.fc.css.example.demo.entities.Document;
import pt.ul.fc.css.example.demo.entities.DocumentType;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.repositories.ThesisExecutionRepository;
import pt.ul.fc.css.example.demo.services.Storage.FileSystemStorageService;

@Component
public class UploadDocumentHandler {

    @Autowired
    private ThesisExecutionRepository executionRepository;

    @Autowired
    private FileSystemStorageService storageService;

    public ThesisExecution submitProposalDocument(long executionid, MultipartFile file) {
        ThesisExecution execution = executionRepository.findById(executionid).orElse(null);

        if(execution == null) {
            return null;
        }

        Set<Document> existingDocuments = execution.getDocuments();

        for(Document doc : existingDocuments) {
            if(doc.getFilename().equals(file.getOriginalFilename())) {
                return null;
            }
        }

        storageService.store(file);

        Document newDocument = new Document(file.getOriginalFilename(), DocumentType.PROPOSAL);
		
        execution.addDocument(newDocument);

        executionRepository.save(execution);
        return execution;
    }

    public ThesisExecution submitFinalDocument(long executionid, MultipartFile file) {
        ThesisExecution execution = executionRepository.findById(executionid).orElse(null);

        if(execution == null) {
            return null;
        }

        Set<Document> existingDocuments = execution.getDocuments();

        for(Document doc : existingDocuments) {
            if(doc.getFilename().equals(file.getOriginalFilename())) {
                return null;
            }

            if(doc.getType() == DocumentType.FINAL) {
                return null;
            }
        }

        storageService.store(file);

        Document newDocument = new Document(file.getOriginalFilename(), DocumentType.FINAL);
		
        execution.addDocument(newDocument);

        executionRepository.save(execution);
        return execution;
    }

    
}
