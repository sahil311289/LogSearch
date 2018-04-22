import java.util.Map;

public interface Log {
    LogEntry getHostAndStatus(final String logEntry);

    Map<String, Integer> searchSuccessfulRequests(final String inputFileName, final String responseStatus);

    void outputResult(final String outputFileName, final Map<String, Integer> addressMap);
}
