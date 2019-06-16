package src;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import io.socket.client.IO;
import io.socket.client.Socket;
import src.exceptions.CodequiryApiException;
import src.model.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CodequirySDK {

    private static final String API_BASE_URL = "https://codequiry.com/api/v1/";
    private static final String API_UPLOAD_URL = "https://codequiry.com/api/v1/check/upload";
    private static final String SOCKETS_BASE_URL = "https://api.codequiry.com/";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<String, String> baseHeaders = new HashMap<String, String>() {{
        put("Content-Type", "application/json");
    }};

    public CodequirySDK(String apiKey) {
        baseHeaders.put("apikey", apiKey);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public Account getAccount() {
        String json = post("account", null, null);
        return mapJsonToObject(json, Account.class);
    }

    public List<Check> getChecks() {
        String json = post("checks", null, null);
        try {
            return objectMapper.readValue(json, new TypeReference <List<Check>>(){});
        } catch (IOException e) {
            throw new CodequiryApiException("Error parsing json response", e);
        }
    }

    public Check createCheck(String checkName, String lang) {
        Map<String, String> params = new HashMap<>();
        params.put("name", checkName);
        params.put("language", lang);
        String json = post("check/create", params, null);
        return mapJsonToObject(json, Check.class);
    }

    public CheckStatus startCheck(Integer checkId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("check_id", checkId);
        String json = post("check/start", params, null);
        return mapJsonToObject(json, CheckStatus.class);
    }

    public Check getCheck(Integer checkId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("check_id", checkId);
        String json = post("check/get", params, null);
        return mapJsonToObject(json, Check.class);
    }

    public Overview getOverview(Integer checkId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("check_id", checkId);
        String json = post("check/overview", params, null);
        return mapJsonToObject(json, Overview.class);
    }

    public SubmissionResults getResults(Integer checkId, String submissionId) {
        Map<String, Object> params = new HashMap<>();
        params.put("check_id", checkId);
        params.put("submission_id", submissionId);
        String json = post("check/results", params, null);
        return mapJsonToObject(json, SubmissionResults.class);
    }

    public void checkListen(String jobId, MessageHandler handler) {
        try {
            Socket socket = IO.socket(SOCKETS_BASE_URL);
            socket.on(Socket.EVENT_CONNECT, args -> {
                socket.emit("job-check", " {'jobid': " + jobId+ "}");
                socket.disconnect();
            })
            .on("job-status", handler::handleMessage)
            .on(Socket.EVENT_DISCONNECT, args -> {});

            socket.connect();
        } catch (URISyntaxException e) {
            throw new CodequiryApiException("Error connecting to socket.", e);
        }
    }

    public UploadResult upload(Integer checkId, String filePath) {
        String boundaryString = "----" + UUID.randomUUID() + "----";
        File file = new File(filePath);
        Map<String, String> headers = new HashMap<>(baseHeaders);
        headers.put("Content-Type", "multipart/form-data; boundary=" + boundaryString);

        StringBuilder sb = new StringBuilder();

        String content = sb.append(boundaryString).append("\n")
                .append("Content-Disposition: form-data; check_id=").append(checkId)
                .append("\n\n").append(boundaryString).append("\n")
                .append("Content-Disposition: form-data; name=\"file=\";https://filebin.net/8ma28c5daw9mhkv7/Main.zip?t=4skc8ndb").append(filePath)
                .append("\nContent-Type: multipart/form-data\n")
                .toString();//.getBytes();

            String json = post(API_UPLOAD_URL, content, headers);
            return mapJsonToObject(json, UploadResult.class);

    }

    private String post(String path, Object data, Map<String, String> headers) {
        HttpURLConnection con = null;
        try {
            URL url = new URL(path.startsWith("http") ? path : API_BASE_URL.concat(path));

            if (headers == null) {
                headers = new HashMap<>(baseHeaders);
            }

            con = (HttpURLConnection) url.openConnection();
            headers.forEach(con::setRequestProperty);
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            byte[] requestData;
            if (data instanceof byte[]) {
                requestData = (byte[]) data;
            } else {
                requestData = data == null ? new byte[0] : objectMapper.writeValueAsString(data).getBytes(StandardCharsets.UTF_8);
            }

            try (OutputStream os = con.getOutputStream()) {
                os.write(requestData, 0, requestData.length);
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            return response.toString();
        } catch (IOException e) {
            String errorInfo = "";
            if (con != null) {
                java.util.Scanner s = new java.util.Scanner(con.getErrorStream()).useDelimiter("\\A");
                errorInfo = s.hasNext() ? s.next() : "";
            }
            throw new CodequiryApiException("Error making request to " + path + ". Response:" + errorInfo, e);
        }
    }

    private <T> T mapJsonToObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new CodequiryApiException("Error parsing json response", e);
        }
    }

    public interface MessageHandler {
        void handleMessage(Object[] data);
    }
}
