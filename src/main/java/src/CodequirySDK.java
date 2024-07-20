package src;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import io.socket.client.IO;
import io.socket.client.Socket;
import src.exceptions.CodequiryApiException;
import src.model.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class CodequirySDK {

    private static final String API_BASE_URL = "https://codequiry.com/api/v1/";
    private static final String API_UPLOAD_URL = "https://codequiry.com/api/v1/check/upload";
    private static final String SOCKETS_BASE_URL = "https://api.codequiry.com/";

    private final ObjectMapper objectMapper = createObjectMapper();
    
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
                String dateStr = jsonParser.getText();
                try {
                	List<DateTimeFormatter> formatters = Arrays.asList(
            		    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            		    new DateTimeFormatterBuilder()
            		        .parseCaseInsensitive()
            		        .appendPattern("MMM d, yyyy h:mm a 'UTC'")
            		        .toFormatter(Locale.ENGLISH)
            		);

            		LocalDateTime date = null;
            		for (DateTimeFormatter formatter : formatters) {
            		    try {
            		        date = LocalDateTime.parse(dateStr, formatter);
            		        break;
            		    } catch (DateTimeParseException e) {
            		        // Ignore and try the next formatter
            		    }
            		}

            		if (date == null) {
            		    throw new IOException("Failed to parse date: " + dateStr);
            		}
            		return date;
                } catch (Exception e) {
                    throw new IOException("Failed to parse date: " + dateStr, e);
                }
            }
        });

        mapper.registerModule(module);
        return mapper;
    }

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

    public UploadResult upload(Integer checkid, String filePath) {
        String boundaryString = "----" + UUID.randomUUID().toString() + "----";
        File file = new File(filePath);
        
        Map<String, String> headers = new HashMap<>(baseHeaders);
        headers.put("Content-Type", "multipart/form-data; boundary=" + boundaryString);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(baos, StandardCharsets.UTF_8));

        // Add check_id part
        pw.append("--" + boundaryString + "\r\n");
        pw.append("Content-Disposition: form-data; name=\"check_id\"\r\n\r\n");
        pw.append(checkid.toString() + "\r\n");

        // Add file part
        pw.append("--" + boundaryString + "\r\n");
        pw.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
        pw.append("Content-Type: " +URLConnection.guessContentTypeFromName(file.getName()) + "\r\n\r\n");
        pw.flush();

        // Write file data
        FileInputStream fis;
		try {
			fis = new FileInputStream(file);
	        byte[] buffer = new byte[4096];
	        int bytesRead;
	        while ((bytesRead  = fis.read(buffer)) != -1) {
	            baos.write(buffer, 0, bytesRead);
	        }
	        fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        pw.append("\r\n"); // Ensure separation before the final boundary
        pw.append("--" + boundaryString + "--\r\n");
        pw.close();

        byte[] multipartContent = baos.toByteArray();

        String json = post(API_UPLOAD_URL, multipartContent, headers);
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
