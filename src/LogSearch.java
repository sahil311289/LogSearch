import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class LogSearch extends LogImpl {
    private LogImpl logImpl;
    private Map<String, Integer> addressCountMap;
    private StringBuilder responseBuilder = new StringBuilder();
    private static final String INPUT_FILE_NAME = "carplay_log_09_17_2013.log";
    private static final String OUTPUT_FILE_NAME = "carplay_host_success_requests.log";
    private static final String RESPONSE_STATUS = "200";

    private LogSearch(LogImpl logImpl) {
        this.logImpl = logImpl;
        this.addressCountMap = new TreeMap<>();
    }

    public static void main(String[] args) {
        LogSearch logSearch = new LogSearch(LogImpl.getLogImplInstance());

        try(Stream<String> stringStream = Files.lines(Paths.get(INPUT_FILE_NAME))) {

            stringStream.forEach(entry -> {

                LogEntry logEntry = logSearch.logImpl.getHostAndStatus(entry);
                if(logEntry.getStatus().equals(RESPONSE_STATUS)) {
                    String host = logEntry.getHost();

                    if(!logSearch.addressCountMap.containsKey(host)) {
                        logSearch.addressCountMap.put(host, 1);
                    } else {
                        int count = logSearch.addressCountMap.get(host);
                        logSearch.addressCountMap.put(host, ++count);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        logSearch.addressCountMap.entrySet().forEach(stringStringEntry -> {
            String output = stringStringEntry.getKey() + "\t\t\t" + stringStringEntry.getValue() + "\n";
            logSearch.responseBuilder.append(output);
            System.out.println(output);
        });

        byte[] bytes = logSearch.responseBuilder.toString().getBytes();
        try {
            Files.write(Paths.get(OUTPUT_FILE_NAME), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
