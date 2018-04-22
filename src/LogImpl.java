import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class LogImpl extends LogEntry implements Log {

    private static LogImpl logImplInstance = null;
    private Map<String, Integer> addressCountMap = new TreeMap<>();
    private StringBuilder responseBuilder = new StringBuilder();

    protected LogImpl() {
    }

    protected static LogImpl getLogImplInstance() {
        if(logImplInstance == null) {
            logImplInstance = new LogImpl();
        }
        return logImplInstance;
    }

    @Override
    public LogEntry getHostAndStatus(final String logEntry) {
        String[] entryItems = logEntry.split(" ");
        if (entryItems.length >= 7) {
            String status = entryItems[entryItems.length - 2];
            String host = entryItems[0];
            return new Builder().setStatus(status).setHost(host).build();
        }
        return null;
    }

    @Override
    public Map<String, Integer> searchSuccessfulRequests(final String inputFileName, final String responseStatus) {
        try (Stream<String> stringStream = Files.lines(Paths.get(inputFileName))) {

            stringStream.forEach(entry -> {

                LogEntry logEntry = getHostAndStatus(entry);
                if (logEntry.getStatus().equals(responseStatus)) {
                    String host = logEntry.getHost();

                    if (!addressCountMap.containsKey(host)) {
                        addressCountMap.put(host, 1);
                    } else {
                        int count = addressCountMap.get(host);
                        addressCountMap.put(host, ++count);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressCountMap;
    }

    @Override
    public void outputResult(String outputFileName, final Map<String, Integer> addressMap) {
        addressMap.entrySet().forEach(stringStringEntry -> {
            String output = stringStringEntry.getKey() + "\t\t\t" + stringStringEntry.getValue() + "\n";
            responseBuilder.append(output);
            System.out.println(output);
        });

        byte[] bytes = responseBuilder.toString().getBytes();
        try {
            Files.write(Paths.get(outputFileName), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
