package com.digital.model.api;

public class HttpSms {

    private static final String user = "digital";

    private static final String password = "dgt@2017";

    protected final String sender;

    protected final String receiver;

    protected final String content;

    protected String dlurl;

    protected String charset;

    protected String timetosend;

    protected String climsgid;

    protected String numericsender;

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    protected HttpSms(String sender, String receiver, String content,
                      String dlurl,
                      String charset,
                      String timetosend,
                      String climsgid,
                      String numericsender) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.dlurl = dlurl;
        this.charset = charset;
        this.timetosend = timetosend;
        this.climsgid = climsgid;
        this.numericsender = numericsender;
    }

    public static class Builder {

        private final String sender;

        private final String receiver;

        private final String content;

        private String dlurl;

        private String charset;

        private String timetosend;

        private String climsgid;

        private String numericsender;

        public Builder(String sender, String receiver, String content,String charset) {
            this.sender = sender;
            this.receiver = receiver;
            this.content = content;
            this.charset = charset;
        }

        public Builder(String receiver, String content) {
            this.sender = "Sanlam vie";
            this.receiver = receiver;
            this.content = content;
        }

        public Builder setDlurl(String dlurl) {
            this.dlurl = dlurl;
            return this;
        }

        public Builder setCharset(String charset) {
            this.charset = charset;
            return this;
        }

        public Builder setTimetosend(String timetosend) {
            this.timetosend = timetosend;
            return this;
        }

        public Builder setClimsgid(String climsgid) {
            this.climsgid = climsgid;
            return this;
        }

        public Builder setNumericsender(String numericsender) {
            this.numericsender = numericsender;
            return this;
        }

        public HttpSms build() {
            return new HttpSms(sender, receiver, content, dlurl, charset, timetosend, climsgid, numericsender);
        }
    }

    public String toGetParamString() {
        return String.format("user=%s&password=%s&sender=%s&receiver=%s&content=%s&charset=%s", user, password, sender, receiver, content,charset);
    }


}