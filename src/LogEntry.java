public class LogEntry {

    private String host;
    private String ident;
    private String authuser;
    private String date;
    private String request;
    private String status;
    private String bytes;

    public LogEntry() {
    }

    public LogEntry(final String host,
                   final String ident,
                   final String authuser,
                   final String date,
                   final String request,
                   final String status,
                   final String bytes) {
        this.host = host;
        this.ident = ident;
        this.authuser = authuser;
        this.date = date;
        this.request = request;
        this.status = status;
        this.bytes = bytes;
    }

    public String getHost() {
        return host;
    }

    public String getStatus() {
        return status;
    }

    static class Builder {
        private String host;
        private String ident;
        private String authuser;
        private String date;
        private String request;
        private String status;
        private String bytes;

        public Builder() {
        }

        public Builder setHost(final String host) {
            this.host = host;
            return this;
        }

        public Builder setIdent(final String ident) {
            this.ident = ident;
            return this;
        }

        public Builder setAuthuser(final String authuser) {
            this.authuser = authuser;
            return this;
        }

        public Builder setDate(final String date) {
            this.date = date;
            return this;
        }

        public Builder setRequest(final String request) {
            this.request = request;
            return this;
        }

        public Builder setStatus(final String status) {
            this.status = status;
            return this;
        }

        public Builder setBytes(final String bytes) {
            this.bytes = bytes;
            return this;
        }

        public LogEntry build() {
            return new LogEntry(host, ident, authuser, date, request, status, bytes);
        }
    }
}
