package dev.aicoach.AiCoachfullstack;

import dev.aicoach.AiCoachfullstack.exceptions.PDFErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.ParagraphPdfDocumentReader;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Component;


@Component
public class IngestionService implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(IngestionService.class);
    private final VectorStore vectorStore;  //create Vector store
//    private final ResourcePatternResolver resourceResolver;

//    now read the file from docs for ingestion
//    @Value("classPath:/docs/research.pdf")
//    private Resource pdfResource;

    //Multiple resources process
//    @Value("classPath:/docs/*.pdf")
//    private String pdfResource;

    @Value("${pdf.resources.pattern}")
    private Resource[] pdfResources;
//    private String pdfResource;

    public IngestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) throws Exception {
        if (pdfResources == null || pdfResources.length == 0) {
            log.warn("No PDF file found for ingestion with pattern = {}", pdfResources);
        }

        TextSplitter textSplitter = new TokenTextSplitter();

        for (Resource pdfResource : pdfResources) {
            try {
                log.info("Processing PDF: {}", pdfResource.getFilename());
                // Try using ParagraphPdfDocumentReader
                var pdfReader = new ParagraphPdfDocumentReader(pdfResource);
                vectorStore.accept(textSplitter.apply(pdfReader.get()));
            } catch (Exception e) {
                log.warn("ParagraphPdfDocumentReader failed for {}. Attempting fallback reader.", pdfResource.getFilename());
                try {
                    // Use fallback readers
                    var tikaReader = new TikaDocumentReader(pdfResource);
                    vectorStore.accept(textSplitter.apply(tikaReader.get()));
                } catch (Exception fallbackException) {
                    // Handle error using a utility class
                    PDFErrorHandler.handleError(pdfResource, fallbackException);
                }
            }
        }

        log.info("VectorStore successfully loaded with data from {} documents!", pdfResources.length);
    }
}

//    public IngestionService(VectorStore vectorStore, ResourcePatternResolver resourceResolver) {
//        this.vectorStore = vectorStore;
//        this.resourceResolver = resourceResolver;
//    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        Resource[] pdfResources = resourceResolver.getResources((pdfResource));
//
//        if(pdfResources.length == 0) {
//            log.warn("No PDF file found for ingestion with pattern = {}", pdfResources);
//            return;
//        }
//        TextSplitter textSplitter = new TokenTextSplitter();
//
//        for(Resource pdfResource : pdfResources){
//            try{
//                log.info("Processing pdf: {}", pdfResource);
//                // Use TikaDocumentReader for parsing PDF content
//                var tikaReader = new TikaDocumentReader(pdfResource);
//
//                // Use PagePdfDocumentReader instead of ParagraphPdfDocumentReader
////                var pdfReader = new PagePdfDocumentReader(pdfResource);
////                var pdfReader = new ParagraphPdfDocumentReader(pdfResource);  //Read the current PDF
//                vectorStore.accept(textSplitter.apply(tikaReader.get()));  //Process and ingest content into VectorStore
//
//            } catch (Exception e) {
//                log.error("Failed to process PDF : {}. Error : {}", pdfResource, e.getMessage());
//            }
//        }


/**    --Single PDF File ---
        var pdfReader = new ParagraphPdfDocumentReader(pdfResource);  // PDF reader that divide a document into small paragraphs
        TextSplitter textSplitter = new TokenTextSplitter();
        vectorStore.accept(textSplitter.apply(pdfReader.get()));
//        log.info("VectorStore loaded with data !! ");
**/
//    }
//}
