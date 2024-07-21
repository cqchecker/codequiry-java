#### Setting your API Key
```java
Codequiry api = new Codequiry("YOUR API KEY");
```
## Usage
#### Getting account information
```java
Account account = api.getAccount();
System.out.println(account.getUser());
```
#### Getting checks
```java
List<Check> checks = api.getChecks();
if (!checks.isEmpty()) {
    System.out.println(checks.get(0).getStatusId());
}
```
#### Creating checks (specify name and programming language id)
Examples: javascript, java, c-cpp, python, csharp, txt
```java
Check check = api.createCheck("name", "39");
System.out.println(check.getCreatedAt());

```
#### Uploading to a check (specify check_id and file (must be a zip file))
```java
UploadResult uploadResult = api.upload(123, "./Test.zip");
System.out.println(uploadResult.getSubmissionCount());
```
#### Starting a check (specify check_id and if running database check or web check)
```java
Check check = api.startCheck(123);
System.out.println(check.getStatusId());
```
#### Getting a check information/status
```java
Check check = api.getCheck(123);
System.out.println(check.getId());
```
#### Getting results overview
```java
Overview overview = api.getOverview(123);
System.out.println(overview.getOverviewURL());
```
#### Getting specific results of a submission
```java
SubmissionResults results = api.getResults(123, "SID");
System.out.println(results.getMax());
```
## Realtime checking progress - SocketIO
This is an example of the listener, you can call this after getting a check status or after starting a check (both will reutrn a job ID, which you can listen to). Here we will listen to specific CHECK_ID.
```java
api.checkListen("JOB_ID", System.out::println);
```
