package src;

import src.model.Account;
import src.model.Check;
import src.model.CheckStatus;
import src.model.Overview;
import src.model.SubmissionResults;
import src.model.UploadResult;

import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        CodequirySDK api = new CodequirySDK("f60022a70c63c7f77e27572cc4e424f1e55664da2d68a447bca774be85a1bcc8");


        List<Check> checks = api.getChecks();
        System.out.println(checks.size());
        if (!checks.isEmpty()) {
            System.out.println(checks.get(0).getCourseId());
        }
       
       try {
	        Check check = api.createCheck("newcheck1", "39");
	        System.out.println(check.getId());
       } catch(Exception e) {
       	    System.out.println(e.getMessage());
       	
       }

        UploadResult result;
		result = api.upload(95552, "./test.zip");
        System.out.println(result.getData().get(0).getId());
        
        CheckStatus checkStatus = api.startCheck(95552);
        System.out.println(checkStatus.getStatus());
       
        Check check = api.getCheck(95552);
        System.out.println(check.getId());

        Overview overview = api.getOverview(95551);
        System.out.println(overview.getOverviewURL());

        SubmissionResults results = api.getResults(95552, "220707");
        System.out.println(results.getSubmission());


        api.checkListen("0", System.out::println);
    }
}
