package top.easyboot.core.rowraw;

import java.util.Map;

public class RowRawEntity {
    private Map<String, String> headers;
    private String protocol;
    private String version;
    private String status;
    private String statusText;
    private String startSource;
    private String path;
    private String method;
    private byte[] body;

    public RowRawEntity() {
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getStartSource() {
        return startSource;
    }

    public void setStartSource(String startSource) {
        this.startSource = startSource;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "protocol:"+(protocol==null?"":protocol)+"\n"+
                "method:"+(method==null?"":method)+"\n"+
                "path:"+(path==null?"":path)+"\n"+
                "statusText:"+(statusText==null?"":statusText)+"\n"+
                "status:"+(status==null?"":status)+"\n"+
                "headers:"+(headers==null?"":headers.toString())+"\n"+
                "body:"+(body == null?"":new String(body))+"\n"
                ;
    }
}
