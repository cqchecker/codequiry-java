package src.model;

public class PeerMatch {
    private Double id;
    private Double submissionId;
    private Double submissionIdMatched;
    private String similarity;
    private String matchedSimilarity;
    private String file;
    private String fileMatched;
    private Double lineStart;
    private Double lineEnd;
    private Double tokens;
    private String createdAt = null;
    private String updatedAt = null;
    private Double line_matched_start;
    private Double lineMatchedEnd;
    private Double matchType;

    public Double getId() {
        return id;
    }

    public Double getSubmissionId() {
        return submissionId;
    }

    public Double getSubmissionIdMatched() {
        return submissionIdMatched;
    }

    public String getSimilarity() {
        return similarity;
    }

    public String getMatchedSimilarity() {
        return matchedSimilarity;
    }

    public String getFile() {
        return file;
    }

    public String getFileMatched() {
        return fileMatched;
    }

    public Double getLineStart() {
        return lineStart;
    }

    public Double getLineEnd() {
        return lineEnd;
    }

    public Double getTokens() {
        return tokens;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Double getLine_matched_start() {
        return line_matched_start;
    }

    public Double getLineMatchedEnd() {
        return lineMatchedEnd;
    }

    public Double getMatchType() {
        return matchType;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public void setSubmissionId(Double submissionId) {
        this.submissionId = submissionId;
    }

    public void setSubmissionIdMatched(Double submissionIdMatched) {
        this.submissionIdMatched = submissionIdMatched;
    }

    public void setSimilarity(String similarity) {
        this.similarity = similarity;
    }

    public void setMatchedSimilarity(String matchedSimilarity) {
        this.matchedSimilarity = matchedSimilarity;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setFileMatched(String fileMatched) {
        this.fileMatched = fileMatched;
    }

    public void setLineStart(Double lineStart) {
        this.lineStart = lineStart;
    }

    public void setLineEnd(Double lineEnd) {
        this.lineEnd = lineEnd;
    }

    public void setTokens(Double tokens) {
        this.tokens = tokens;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLine_matched_start(Double line_matched_start) {
        this.line_matched_start = line_matched_start;
    }

    public void setLineMatchedEnd(Double lineMatchedEnd) {
        this.lineMatchedEnd = lineMatchedEnd;
    }

    public void setMatchType(Double matchType) {
        this.matchType = matchType;
    }
}