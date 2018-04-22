public class LogImpl extends LogEntry implements Log {

    private static LogImpl logImplInstance = null;
    protected LogImpl() {

    }
    public static LogImpl getLogImplInstance() {
        if(logImplInstance == null) {
            logImplInstance = new LogImpl();
        }
        return logImplInstance;
    }

    @Override
    public LogEntry getHostAndStatus(final String logEntry) {
        String[] entryItems = logEntry.split(" ");
        String status = entryItems[entryItems.length - 2];
        String host = entryItems[0];
        return new Builder().setStatus(status).setHost(host).build();
    }
}
