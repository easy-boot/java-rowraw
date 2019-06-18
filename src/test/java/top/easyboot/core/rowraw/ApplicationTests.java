package top.easyboot.core.rowraw;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;

public class ApplicationTests {
    @Test
    public void contextLoads() {

       test1();
       test2();
       test3();
       test4();
       test5();
       test6();
       test7();
       test8();
       test9();
       test10();
       test12();
       test13();
       test14();
       test15();
       test16();
       test17();
    }
    @Test
    public void test1(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        System.out.println(new String(RowRawUtil.stringify(rowRawEntity)));
        if (!new String(RowRawUtil.stringify(rowRawEntity)).equals("\r\n\r\n")){
            fail("testState");
        }
    }
    @Test
    public void test2(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setStatus("200");
        RowRawEntity entity2 = RowRawUtil.parse(RowRawUtil.stringify(rowRawEntity));
        System.out.println(rowRawEntity);
        System.out.println(entity2);
    }
    @Test
    public void test3(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setStatus("200");
        rowRawEntity.setStatusText("OK");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);

        System.out.println(rowRawEntity);
        System.out.println(entity2);

        assertFalse("getStatus", !"200".equals(entity2.getStatus()));
        assertFalse("getStatusText", !"OK".equals(entity2.getStatusText()));
    }
    @Test
    public void test4(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setStatus("200");
        rowRawEntity.setStatusText("OK");
        rowRawEntity.setHeaders(new HashMap<>());
        rowRawEntity.getHeaders().put("ab", "adsfafaf as sda  sd  a a sg");
        rowRawEntity.getHeaders().put("cdef", "adsfafaf as sda  sd  a a sg");
        rowRawEntity.getHeaders().put("sdfsss", "adsfafaf as sda  sd  a a sg");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);

        System.out.println(rowRawEntity);
        System.out.println(entity2);

        assertFalse("getStatus", !"200".equals(entity2.getStatus()));
        assertFalse("getStatusText", !"OK".equals(entity2.getStatusText()));
        String h1 = rowRawEntity.getHeaders().toString();
        String h2 = entity2.getHeaders().toString();
        System.out.println("h1:"+h1);
        System.out.println("h2:"+h2);
        assertFalse("getHeaders", !h1.equals(h2));
    }
    @Test
    public void test5(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setBody("abcdergf\ndafggg\nass".getBytes());
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getBody", !Arrays.equals(rowRawEntity.getBody(), entity2.getBody()));
    }
    @Test
    public void test6(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setStatus("300");
        rowRawEntity.setBody("abcdergf\ndafggg\nass".getBytes());
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getStatus", !"300".equals(entity2.getStatus()));
        assertFalse("getBody", !Arrays.equals(rowRawEntity.getBody(), entity2.getBody()));
    }
    @Test
    public void test7(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setStatus("300");
        rowRawEntity.setHeaders(new HashMap<>());
        rowRawEntity.setBody("abcdergf\ndafggg\nass".getBytes());
        rowRawEntity.getHeaders().put("aa","aa");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getStatus", !"300".equals(entity2.getStatus()));
        assertFalse("version", !"1.0".equals(entity2.getVersion()));
        assertFalse("getBody", !Arrays.equals(rowRawEntity.getBody(), entity2.getBody()));
        String h1 = rowRawEntity.getHeaders().toString();
        String h2 = entity2.getHeaders().toString();
        System.out.println("h1:"+h1);
        System.out.println("h2:"+h2);
        assertFalse("getHeaders", !h1.equals(h2));
    }
    @Test
    public void test8(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setStatus("300");
        rowRawEntity.setHeaders(new HashMap<>());
        rowRawEntity.setBody("abcdergf\ndafggg\nass".getBytes());
        rowRawEntity.getHeaders().put("aa","aa");
        rowRawEntity.getHeaders().put("a23222a","a2222a");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getStatus", !"300".equals(entity2.getStatus()));
        assertFalse("getBody", !Arrays.equals(rowRawEntity.getBody(), entity2.getBody()));
        String h1 = rowRawEntity.getHeaders().toString();
        String h2 = entity2.getHeaders().toString();
        System.out.println("h1:"+h1);
        System.out.println("h2:"+h2);
        assertFalse("getHeaders", !h1.equals(h2));
    }
    @Test
    public void test9(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setStatus("300");
        rowRawEntity.setHeaders(new HashMap<>());
        rowRawEntity.getHeaders().put("aa","aa");
        rowRawEntity.getHeaders().put("a23222a","a2222a");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getStatus", !"300".equals(entity2.getStatus()));
        String h1 = rowRawEntity.getHeaders().toString();
        String h2 = entity2.getHeaders().toString();
        System.out.println("h1:"+h1);
        System.out.println("h2:"+h2);
        assertFalse("getHeaders", !h1.equals(h2));
    }
    @Test
    public void test10(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setMethod("GET");
        rowRawEntity.setHeaders(new HashMap<>());
        rowRawEntity.getHeaders().put("aa","aa");
        rowRawEntity.getHeaders().put("a23222a","a2222a");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getMethod", !"GET".equals(entity2.getMethod()));
        assertFalse("!getMethod", "GET1".equals(entity2.getMethod()));
        assertFalse("version", !"1.0".equals(entity2.getVersion()));
        String h1 = rowRawEntity.getHeaders().toString();
        String h2 = entity2.getHeaders().toString();
        System.out.println("h1:"+h1);
        System.out.println("h2:"+h2);
        assertFalse("getHeaders", !h1.equals(h2));
    }
    @Test
    public void test11(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setMethod("PUT");
        rowRawEntity.setHeaders(new HashMap<>());
        rowRawEntity.getHeaders().put("aa","aa");
        rowRawEntity.getHeaders().put("a23222a","a2222a");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getMethod", !"PUT".equals(entity2.getMethod()));
        assertFalse("!getMethod", "GET".equals(entity2.getMethod()));
        String h1 = rowRawEntity.getHeaders().toString();
        String h2 = entity2.getHeaders().toString();
        System.out.println("h1:"+h1);
        System.out.println("h2:"+h2);
        assertFalse("getHeaders", !h1.equals(h2));
    }
    @Test
    public void test12(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setMethod("PUT");
        rowRawEntity.setPath("/dsfs/sdf/sf/sf");
        rowRawEntity.setHeaders(new HashMap<>());
        rowRawEntity.getHeaders().put("aa","aa");
        rowRawEntity.getHeaders().put("a23222a","a2222a");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getPath", !rowRawEntity.getPath().equals(entity2.getPath()));
        assertFalse("getMethod", !"PUT".equals(entity2.getMethod()));
        assertFalse("!getMethod", "GET".equals(entity2.getMethod()));
        String h1 = rowRawEntity.getHeaders().toString();
        String h2 = entity2.getHeaders().toString();
        System.out.println("h1:"+h1);
        System.out.println("h2:"+h2);
        assertFalse("getHeaders", !h1.equals(h2));
    }
    @Test
    public void test13(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setPath("/dsfs/sdf/sf/sf");
        rowRawEntity.setHeaders(new HashMap<>());
        rowRawEntity.getHeaders().put("aa","aa");
        rowRawEntity.getHeaders().put("a23222a","a2222a");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getPath", !rowRawEntity.getPath().equals(entity2.getPath()));
        String h1 = rowRawEntity.getHeaders().toString();
        String h2 = entity2.getHeaders().toString();
        System.out.println("h1:"+h1);
        System.out.println("h2:"+h2);
        assertFalse("getHeaders", !h1.equals(h2));
    }
    @Test
    public void test14(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setPath("/dsfs/sdf/sf/sf");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getPath", !rowRawEntity.getPath().equals(entity2.getPath()));
    }
    @Test
    public void test15(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setPath("/dsfs/sdf/sf/sf");
        rowRawEntity.setBody("s4df4a4f\n\nasdfasdfasfa\r\nsfasd dasfas af \r\nf".getBytes());
        rowRawEntity.setHeaders(new HashMap<>());
        rowRawEntity.getHeaders().put("aa","aa");
        rowRawEntity.getHeaders().put("a23222a","a2222a");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getPath", !rowRawEntity.getPath().equals(entity2.getPath()));
        String h1 = rowRawEntity.getHeaders().toString();
        String h2 = entity2.getHeaders().toString();
        System.out.println("h1:"+h1);
        System.out.println("h2:"+h2);
        assertFalse("getHeaders", !h1.equals(h2));
        assertFalse("getBody", !Arrays.equals(rowRawEntity.getBody(), entity2.getBody()));
    }
    @Test
    public void test16(){
        RowRawEntity rowRawEntity = new RowRawEntity();
        rowRawEntity.setStatus("405");
        rowRawEntity.setPath("/dsfs/sdf/sf/sf");
        rowRawEntity.setBody("s4df4a4f\n\nasdfasdfasfa\r\nsfasd dasfas af \r\nf".getBytes());
        rowRawEntity.setHeaders(new HashMap<>());
        rowRawEntity.getHeaders().put("aa","aa");
        rowRawEntity.getHeaders().put("a23222a","a2222a");
        byte[] res = RowRawUtil.stringify(rowRawEntity);
        System.out.println("res:"+new String(res));

        RowRawEntity entity2 = RowRawUtil.parse(res);


        System.out.println(rowRawEntity);
        System.out.println(entity2);
        assertFalse("getPath", entity2.getPath()!=null);
        assertFalse("getStatus", !"405".equals(entity2.getStatus()));
        assertFalse("!getStatus", "4035".equals(entity2.getStatus()));
        String h1 = rowRawEntity.getHeaders().toString();
        String h2 = entity2.getHeaders().toString();
        System.out.println("h1:"+h1);
        System.out.println("h2:"+h2);
        assertFalse("getHeaders", !h1.equals(h2));
        assertFalse("getBody", !Arrays.equals(rowRawEntity.getBody(), entity2.getBody()));
    }
    @Test
    public void test17(){
        String res ="aa: 3\r\n" +
                "s: sd\r\n" +
                "\r\n" +
                "sdafasdfasdfass";
        RowRawEntity entity2 = RowRawUtil.parse(res.getBytes());
        System.out.println(entity2);
    }
}
