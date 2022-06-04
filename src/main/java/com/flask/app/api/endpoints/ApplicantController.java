package com.flask.app.api.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flask.app.api.dto.ApplicantDto;
import com.flask.app.core.entity.ApplicantEntity;
import com.flask.app.core.service.ApplicantService;
import com.flask.app.core.service.PdfService;
import com.flask.app.remote.api.GithubClient;
import com.flask.app.remote.model.GithubModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("applicant")
@Slf4j
public class ApplicantController {


    private final ApplicantService applicantService;
    private final ObjectMapper objectMapper;
    private final PdfService pdfService;
    private final GithubClient githubClient;

    public ApplicantController(ApplicantService applicantService, ObjectMapper objectMapper, PdfService pdfService, GithubClient githubClient) {
        this.applicantService = applicantService;
        this.objectMapper = objectMapper;
        this.pdfService = pdfService;
        this.githubClient = githubClient;
    }

    @PostMapping
    public ApplicantDto addApplicant(@RequestBody ApplicantDto applicantDto){
        log.info("Add Applicant api called");
        ApplicantEntity applicantEntity = objectMapper.convertValue(applicantDto, ApplicantEntity.class);

        applicantEntity = applicantService.addApplicant(applicantEntity);

        log.info("Add Applicant api call ended");
        return objectMapper.convertValue(applicantEntity, ApplicantDto.class);
    }

    @DeleteMapping("/{id}")
    public ApplicantDto deleteApplicant(@PathVariable Integer id){
        return null;
    }

    @GetMapping("/{id}")
    public ApplicantDto getApplicant(@PathVariable Integer id){
        ApplicantEntity applicantEntity = applicantService.getApplicantsById(id);
        return objectMapper.convertValue(applicantEntity, ApplicantDto.class);
    }

    @GetMapping("generate-pdf/{email}")
    public void generatePdf(@PathVariable String email){
        log.info("Generate PDF api called");
        pdfService.generateAndDownloadPdf(email);
        log.info("PDF generated");
    }

    @GetMapping
    public List<ApplicantDto> getApplicant(){
        log.info("Get Applicant list api called");
        List<ApplicantEntity> allApplicants = applicantService.getAllApplicants();
        List<ApplicantDto> applicantDtoList = new ArrayList<>();
        for (ApplicantEntity entity: allApplicants){
            ApplicantDto applicantDto = objectMapper.convertValue(entity, ApplicantDto.class);
            applicantDtoList.add(applicantDto);
        }
        log.info("Get Applicant list api call end");
        return applicantDtoList;
   }
}
