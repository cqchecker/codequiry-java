package src;

import src.model.Account;
import src.model.Check;
import src.model.Overview;
import src.model.SubmissionResults;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        CodequirySDK api = new CodequirySDK("c08cf5c30a8bf835daa62db519410a1e77c10addd9ac2532809b668ae712755f");
        Account account = api.getAccount();
        System.out.println(account.getEmail());

        Check check = api.getCheck(123);
        System.out.println(check.getJobId());

        List<Check> checks = api.getChecks();
        if (!checks.isEmpty()) {
            System.out.println(checks.get(0).getStatusId());
        }

        //Check check = api.startCheck(123);
        System.out.println(check.getStatusId());

        Overview overview = api.getOverview(123);
        System.out.println(overview.getOverviewURL());

        SubmissionResults results = api.getResults(123, "SID");
        System.out.println(results.getMax());


        api.checkListen("JOB_ID", System.out::println);
    }
}
