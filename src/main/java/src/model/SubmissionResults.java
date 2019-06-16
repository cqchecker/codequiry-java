package src.model;

import java.util.ArrayList;
import java.util.List;

public class SubmissionResults {
    private Submission submission;
    private Double avg;
    private Double max;
    private Double min;
    private List<PeerMatch> peerMatches = new ArrayList<>();
    private List<Object> otherMatches = new ArrayList<>();
    private List<Submission> relatedSubmissions = new ArrayList<>();
    private List<SubmissionFile> submissionFiles = new ArrayList<>();

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public List<PeerMatch> getPeerMatches() {
        return peerMatches;
    }

    public void setPeerMatches(List<PeerMatch> peerMatches) {
        this.peerMatches = peerMatches;
    }

    public List<Object> getOtherMatches() {
        return otherMatches;
    }

    public void setOtherMatches(List<Object> otherMatches) {
        this.otherMatches = otherMatches;
    }

    public List<Submission> getRelatedSubmissions() {
        return relatedSubmissions;
    }

    public void setRelatedSubmissions(List<Submission> relatedSubmissions) {
        this.relatedSubmissions = relatedSubmissions;
    }

    public List<SubmissionFile> getSubmissionFiles() {
        return submissionFiles;
    }

    public void setSubmissionFiles(List<SubmissionFile> submissionFiles) {
        this.submissionFiles = submissionFiles;
    }
}
