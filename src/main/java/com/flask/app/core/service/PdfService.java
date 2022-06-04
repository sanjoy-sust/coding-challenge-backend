package com.flask.app.core.service;

import com.flask.app.core.entity.ApplicantEntity;
import com.flask.app.core.entity.ProjectEntity;
import com.flask.app.remote.api.GithubClient;
import com.flask.app.remote.model.GithubModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfService {
    private final ApplicantService applicantService;
    private final GithubClient githubClient;

    public PdfService(ApplicantService applicantService, GithubClient githubClient) {
        this.applicantService = applicantService;
        this.githubClient = githubClient;
    }

    public void generateAndDownloadPdf(String email){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("applicant.pdf"));

            ApplicantEntity applicantEntity = applicantService.getApplicantsByEmail(email);

            document.open();
            Paragraph horizontalAlignCell = new Paragraph(new Phrase("Applicant Information"));
            horizontalAlignCell.setAlignment(Element.ALIGN_CENTER);
            document.add(horizontalAlignCell);
            document.add(new Phrase("Name: "));
            document.add(new Phrase(applicantEntity.getName()));
            document.add(new Paragraph());
            document.add(new Phrase("Work Email:  "));
            document.add(new Phrase(applicantEntity.getWorkEmailAddress()));
            document.add(new Paragraph());
            document.add(new Phrase("Github User:  "));
            document.add(new Phrase(applicantEntity.getGithubUser()));
            document.add(new Paragraph());
            document.add(new Phrase("Github Image:  "));
            GithubModel model =  githubClient.getProfileDetails(applicantEntity.getGithubUser());
            byte[] avatar = githubClient.getAvatar(URI.create(model.getAvatarUrl()));
            Image img = Image.getInstance(avatar);
            img.scalePercent(10);
            img.setAlignment(Image.ALIGN_CENTER);
            document.add(img);
            document.add(new Paragraph());
            document.add(new Paragraph("Project Information"));
            PdfPTable table = new PdfPTable(9);
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            addTableHeader(table);
            for (ProjectEntity projectEntity: applicantEntity.getProjects()) {
                addRows(table, projectEntity );
            }
            document.add(table);
            document.close();
        }catch (IOException | DocumentException ioException ){
            System.out.println(ioException);
        }

    }

    private void addRows(PdfPTable table, ProjectEntity projectEntity) {
        table.addCell(projectEntity.getName());
        table.addCell(projectEntity.getEmploymentMode().getMode());
        table.addCell(projectEntity.getEmploymentCapacity().getCapacity());
        table.addCell(projectEntity.getDurationInDays().toString());
        table.addCell(projectEntity.getStartYear().toString());
        table.addCell(projectEntity.getRole());
        table.addCell(projectEntity.getTeamSize().toString());
        table.addCell(projectEntity.getRepositoryLink());
        table.addCell(projectEntity.getLiveUrl());
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Name", "employmentMode", "employmentCapacity","durationInDays","startYear", "role","teamSize","repositoryLink","liveUrl")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

/*    private void addCustomRows(PdfPTable table)
            throws URISyntaxException, BadElementException, IOException {
        GithubModel model =  githubClient.getProfileDetails("sanjoy-sust");
        byte[] avatar = githubClient.getAvatar(URI.create(model.getAvatarUrl()));

        Image img = Image.getInstance(avatar);
        img.scalePercent(10);

        PdfPCell imageCell = new PdfPCell(img);
        table.addCell(imageCell);


        table.addCell(horizontalAlignCell);

        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);
    }*/
}
