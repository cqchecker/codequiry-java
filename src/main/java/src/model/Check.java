package src.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Check {
    private long id;
    private String name;
    private String createdAt;
    private String updatedAt;
    private String statusMessage;
    private int statusId;
    private int jobId;
    private int courseId;
    private int testType;
    private int languageId;
    private int consumption;
    private String consumptionPerLine;
    private int prelim;
    private int files;
    private int lines;
    private long sourcesIndexed;
    private int billableLines;
    private int similarFiles;
    private int matchesFound;
    private int hardComparisons;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public float getStatusId() {
        return statusId;
    }

    public int getJobId() {
        return jobId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getTestType() {
        return testType;
    }

    public int getLanguageId() {
        return languageId;
    }

    public int getConsumption() {
        return consumption;
    }

    public String getConsumptionPerLine() {
        return consumptionPerLine;
    }

    public int getPrelim() {
        return prelim;
    }

    public int getFiles() {
        return files;
    }

    public int getLines() {
        return lines;
    }

    public long getSourcesIndexed() {
        return sourcesIndexed;
    }

    public int getBillableLines() {
        return billableLines;
    }

    public int getSimilarFiles() {
        return similarFiles;
    }

    public int getMatchesFound() {
        return matchesFound;
    }

    public int getHardComparisons() {
        return hardComparisons;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public void setConsumptionPerLine(String consumptionPerLine) {
        this.consumptionPerLine = consumptionPerLine;
    }

    public void setPrelim(int prelim) {
        this.prelim = prelim;
    }

    public void setFiles(int files) {
        this.files = files;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public void setSourcesIndexed(long sourcesIndexed) {
        this.sourcesIndexed = sourcesIndexed;
    }

    public void setBillableLines(int billableLines) {
        this.billableLines = billableLines;
    }

    public void setSimilarFiles(int similarFiles) {
        this.similarFiles = similarFiles;
    }

    public void setMatchesFound(int matchesFound) {
        this.matchesFound = matchesFound;
    }

    public void setHardComparations(int hardComparisons) {
        this.hardComparisons = hardComparisons;
    }
}