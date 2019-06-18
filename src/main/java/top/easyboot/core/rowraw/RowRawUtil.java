package top.easyboot.core.rowraw;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RowRawUtil {
    public static String r = "\r";
    public static String n = "\n";
    public static String rn = r + n;
    public static String formatStatusExp = "^([A-Z]+)\\/(\\d)\\.(\\d) (\\d{3}) (.*)$";
    public static String formatRequestExp = "^([A-Z_]+) (.+) ([A-Z]+)\\/(\\d)\\.(\\d)$";
    public static Pattern formatStatus = Pattern.compile(formatStatusExp);
    public static Pattern formatRequest = Pattern.compile(formatRequestExp);

    public static RowRawEntity parse(byte[] bytes){
        RowRawEntity entity = new RowRawEntity();
        ByteArrayInputStream baosI = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream baosO = new ByteArrayOutputStream();
        byte []pos=new byte[1];
        boolean isR = false;
        boolean isN = false;
        boolean isCheckStart = true;
        HashMap<String, String> headersMap = new HashMap<>();
        entity.setHeaders(headersMap);

        try {
            while(( baosI.read(pos))!= -1) {
                if (r.equals(new String(pos))){
                    isR = true;
                    if (isN){
                        isCheckStart = parseHeader(entity, baosO, isCheckStart);
                        baosO.reset();
                    }
                }else if (n.equals(new String(pos))){
                    if (isN == true){
                        // 结束头部分
                        baosO.reset();
                        while(( baosI.read(pos))!= -1) {
                            baosO.write(pos);
                            }
                    }else{
                        isN = true;
                    }
                }else if(isN && isR){
                    isR = false;
                    isN = false;
                    isCheckStart = parseHeader(entity, baosO, isCheckStart);
                    baosO.reset();
                    baosO.write(pos);
                }else{
                    baosO.write(pos);
                }
            }
            entity.setBody(baosO.toByteArray());
            baosI.close();
            baosO.close();
        }catch (IOException e){

        }
        return entity;
    }
    private static boolean parseHeader(RowRawEntity entity, ByteArrayOutputStream baos, boolean isCheckStart){
        if (isCheckStart && entity.getProtocol() == null){
            String startSource = new String(baos.toByteArray());
            Matcher matcher = formatRequest.matcher(startSource);
            if (matcher.matches()) {
                entity.setMethod(matcher.group(1));
                entity.setPath(matcher.group(2));
                entity.setProtocol(matcher.group(3));
                entity.setVersion(matcher.group(4)+"."+matcher.group(5));
            }else{
                matcher = formatStatus.matcher(startSource);
                if (matcher.matches()) {
                    entity.setStartSource(startSource);
                    entity.setProtocol(matcher.group(1));
                    entity.setVersion(matcher.group(2)+"."+matcher.group(3));
                    entity.setStatus(matcher.group(4));
                    entity.setStatusText(matcher.group(5));
                }else{
                    String[] t = new String(baos.toByteArray()).trim().split(":", 2);
                    if (!t[0].isEmpty()){
                        entity.getHeaders().put(t[0], t[1]);
                    }
                }
            }
            isCheckStart = false;
        }else{
            String[] t = new String(baos.toByteArray()).trim().split(":", 2);
            entity.getHeaders().put(t[0], t[1]);
        }
        return isCheckStart;
    }
    /**
     * 编码
     * @param rawEntity 请求实体
     * @return 数据
     */
    public static byte[] stringify (RowRawEntity rawEntity){
        byte[] data = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            String state;
            String protocol = rawEntity.getProtocol();
            String version = rawEntity.getVersion();
            if (version ==null||version.isEmpty()){
                version = "1.0";
            }
            if (protocol ==null||protocol.isEmpty()){
                protocol = "ROWRAW";
            }
            if (rawEntity.getStatus() != null || rawEntity.getStatusText() != null){
                String status = rawEntity.getStatus();
                String statusText = rawEntity.getStatusText();
                // 响应
                state = protocol + "/" + version + " " + (status == null || status.isEmpty() ? "200":status) + " " + (statusText == null || statusText.isEmpty() ? "OK":statusText);
            }else if (rawEntity.getMethod() != null || rawEntity.getPath() != null){
                String method = rawEntity.getMethod();
                String path = rawEntity.getPath();
                // 请求
                state = (method == null || method.isEmpty()? "GET": method) + " " + (path == null || path.isEmpty()? "/": path) + " " + protocol + "/" + version;
            }else{
                state = null;
            }

            if (state != null){
                baos.write(state.getBytes());
                baos.write(rn.getBytes());
            }
            Map<String, String> headers = rawEntity.getHeaders();
            if (headers != null){
                for (String key : headers.keySet()) {
                    baos.write((key + ":" + headers.get(key)).getBytes());
                    baos.write(rn.getBytes());
                }
            }
            if (baos.size() == 0){
                baos.write(rn.getBytes());
            }
            baos.write(rn.getBytes());
            if (rawEntity.getBody()!=null){

                baos.write(rawEntity.getBody());
            }
            data = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return data;
    }

    public static ByteBuffer string2ByteBuffer(String str) {
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        return buffer;
    }

    public static String byteBuffer2String(ByteBuffer buffer) {
        byte[] bytes = new byte[buffer.limit()];
        buffer.position(0);
        buffer.get(bytes, 0, bytes.length);
        String data = new String(bytes);
        return data;
    }
    public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }
    public static char byteToChar(byte[] b) {
        char c = (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
        return c;
    }
}
