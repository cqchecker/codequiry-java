package src.model;

import java.util.List;

public class Overview {

    private String overviewURL;
    private List<Submission> submissions;
    private List<BarData> barData;

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public List<BarData> getBarData() {
        return barData;
    }

    public void setBarData(List<BarData> barData) {
        this.barData = barData;
    }

    public String getOverviewURL() {
        return overviewURL;
    }

    public void setOverviewURL(String overviewURL) {
        this.overviewURL = overviewURL;
    }
}
