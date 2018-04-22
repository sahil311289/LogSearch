import java.util.Map;

public class LogSearch extends LogImpl {
    private LogImpl logImpl;
    private Map<String, Integer> addressCountMap = null;
    private static final String INPUT_FILE_NAME = "carplay_log_09_17_2013.log";
    private static final String OUTPUT_FILE_NAME = "carplay_host_success_requests.log";
    private static final String RESPONSE_STATUS = "200";

    private LogSearch(LogImpl logImpl) {
        this.logImpl = logImpl;
    }

    public static void main(String[] args) {
        LogSearch logSearch = new LogSearch(LogImpl.getLogImplInstance());
        logSearch.addressCountMap = logSearch.logImpl.searchSuccessfulRequests(INPUT_FILE_NAME, RESPONSE_STATUS);
        logSearch.logImpl.outputResult(OUTPUT_FILE_NAME, logSearch.addressCountMap);
    }
}
