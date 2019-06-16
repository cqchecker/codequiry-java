package src.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Submission {
    private Double id;
    private String filename;
    private Double status_id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String result1;
    private String result2;
    private String result3;
    private String totalResult;
    private List<SubmissionResults> submissionresults = new ArrayList <> ();
    private List<SubmissionFile> submissionfiles = new ArrayList <> ();

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Double getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Double status_id) {
        this.status_id = status_id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getResult3() {
        return result3;
    }

    public void setResult3(String result3) {
        this.result3 = result3;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }

    public List<SubmissionResults> getSubmissionresults() {
        return submissionresults;
    }

    public void setSubmissionresults(List<SubmissionResults> submissionresults) {
        this.submissionresults = submissionresults;
    }

    public List<SubmissionFile> getSubmissionfiles() {
        return submissionfiles;
    }

    public void setSubmissionfiles(List<SubmissionFile> submissionfiles) {
        this.submissionfiles = submissionfiles;
    }
}