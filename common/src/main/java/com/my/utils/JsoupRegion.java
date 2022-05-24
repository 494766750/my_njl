package com.my.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupRegion {
    /** 整数 */
    private static final String V_INTEGER = "^-?[1-9]\\d*$";
    
    static class Region {
        public String getCode() {
            return code;
        }
        
        public void setCode(String code) {
            this.code = code;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getParenId() {
            return parenId;
        }
        
        public void setParenId(String parenId) {
            this.parenId = parenId;
        }
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        private String code;
        private String name;
        private String type;// 乡镇类型
        private String parenId;
        List<Region> regions;
        
        public List<Region> getRegions() {
            return regions;
        }
        
        public void setRegions(List<Region> regions) {
            this.regions = regions;
        }
    }
    
    /**
     * @说明: url2Document
     * @param @param url
     * @param @return
     * @param @throws IOException
     * @return Document
     * @throws
     */
    public static Document url2Doc(String url) throws IOException {
        // 此种方式403
        // return JsoupRegion.connect(url).get();
        // return JsoupRegion.connect(url).timeout(600 * 1000)
        // .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
        //
        return Jsoup.connect(url).timeout(600 * 1000).get();
    }
    
    /**
     * 验证是否是整数
     *
     * @param value
     *            要验证的字符串 要验证的字符串
     * @return 若是是符合格式的字符串,返回 <b>true </b>,不然为 <b>false </b>
     */
    public static boolean Integer(String value) {
        return match(V_INTEGER, value);
    }
    
    /**
     * @param regex
     *            正则表达式字符串
     * @param str
     *            要匹配的字符串
     * @return 若是str 符合 regex的正则表达式格式,返回true, 不然返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    /**
     * 追加文件：使用FileWriter
     *
     * @param fileName
     * @param content
     */
    public static void appendFile(String fileName, String content) {
        FileWriter writer = null;
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter(fileName, true);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @说明: 获取省份
     * @param @param url
     * @param @return
     * @param @throws IOException
     * @return List<Region>
     * @throws
     */
    private static List<Region> getProvince(String url) throws IOException {
        List<Region> list = new ArrayList<Region>();
        Document doc = url2Doc(url);
        Elements proviceTr = doc.getElementsByAttributeValue("class", "provincetr");// 经过css获取tr
        for (Element e : proviceTr) {
            Elements tds = e.select("a[href]");
            for (Element element : tds) {
                Region region = new Region();
                // region.setCode("13");
                region.setCode(element.attr("href").substring(0, 2));
                region.setName(element.text().replaceAll("<br />", ""));
                region.setType("");
                region.setParenId("0");
                list.add(region);
            }
        }
        return list;
    }
    
    /**
     * @说明: 获取省份下的市
     * @param @param url
     * @param @return
     * @param @throws IOException
     * @return List<Region>
     * @throws
     */
    private static List<Region> getCity(String url) throws IOException {
        List<Region> list = new ArrayList<Region>();
        Document doc = url2Doc(url);
        Elements proviceTr = doc.getElementsByAttributeValue("class", "citytr");// 经过css获取tr
        for (Element e : proviceTr) {
            Elements tds = e.select("a[href]");
            for (Element element : tds) {
                if (Integer(element.text())) {
                    continue;
                }
                Region region = new Region();
                String code = element.attr("href").substring(3, 7);
                region.setCode(code);
                region.setName(element.text());
                region.setParenId(code.substring(0, 2));
                region.setType("");
                list.add(region);
            }
        }
        return list;
    }
    
    /**
     * @说明: 县
     * @param @param url
     * @param @return
     * @param @throws IOException
     * @return List<Region>
     * @throws
     */
    private static List<Region> getCounty(String url) throws IOException {
        List<Region> list = new ArrayList<Region>();
        Document doc = url2Doc(url);
        Elements proviceTr = doc.getElementsByAttributeValue("class", "countytr");// 经过css获取tr
        for (Element e : proviceTr) {
            Elements tds = e.select("a[href]");
            for (Element element : tds) {
                if (Integer(element.text())) {
                    continue;
                }
                Region region = new Region();
                String code = element.attr("href").substring(3, 9);
                region.setCode(code);
                region.setName(element.text());
                region.setParenId(code.substring(0, 4));
                region.setType("");
                list.add(region);
            }
        }
        return list;
    }
    
    /**
     * @说明: 镇
     * @param @param url
     * @param @return
     * @param @throws IOException
     * @return List<Region>
     * @throws
     */
    private static List<Region> getTown(String url) throws IOException {
        List<Region> list = new ArrayList<Region>();
        Document doc = url2Doc(url);
        Elements proviceTr = doc.getElementsByAttributeValue("class", "towntr");// 经过css获取tr
        for (Element e : proviceTr) {
            Elements tds = e.select("a[href]");
            for (Element element : tds) {
                if (Integer(element.text())) {
                    continue;
                }
                Region region = new Region();
                String code = element.attr("href").substring(3, 12);
                region.setCode(code);
                region.setName(element.text());
                region.setParenId(code.substring(0, 6));
                region.setType("");
                list.add(region);
            }
        }
        return list;
    }
    
    /**
     * @说明: 村
     * @param @param url
     * @param @return
     * @param @throws IOException
     * @return List<Region>
     * @throws
     */
    private static List<Region> getVillage(String url) throws IOException {
        List<Region> list = new ArrayList<Region>();
        Document doc = url2Doc(url);
        Elements proviceTr = doc.getElementsByAttributeValue("class", "villagetr");// 经过css获取tr
        for (Element e : proviceTr) {
            Elements trs = e.select("tr");
            for (Element element : trs) {
                Elements tds = element.select("td");
                Region region = new Region();
                for (Element element2 : tds) {
                    String value = element2.text();
                    if (Integer(value) && value.length() == 3) {
                        region.setType(element2.text());
                    }
                    if (Integer(value) && value.length() > 3) {
                        region.setCode(value);
                        region.setParenId(value.substring(0, 9));
                    } else {
                        region.setName(value);
                    }
                    
                }
                list.add(region);
                
            }
        }
        return list;
    }
    
    //    public static void main(String[] args) throws IOException {
//        List<Region> all = new ArrayList<Region>();
//        List<Region> province = getProvince("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/index.html");
//        all.addAll(province);
//        List<String> done = new ArrayList<String>();//用来存已经抓取过的省份
//        for (Region regionProvince : province) {// 遍历省
//            if (done.contains(regionProvince.getCode())) {
//                continue;
//            }
//            System.out.println(regionProvince.getCode() + regionProvince.getName());
//            appendFile("E:/" + regionProvince.getCode() + regionProvince.getName() + ".txt", regionProvince.getCode() + "||" + regionProvince.getName() + "||"
//                    + regionProvince.getParentId() + "||" + regionProvince.getType() + "\r\n");
//
//            List<Region> city = getCity("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/" + regionProvince.getCode() + ".html");
//            for (Region regionCity : city) {// 遍历市
//                System.out.println(regionCity.getCode() + "||" + regionCity.getName());
//                appendFile("E:/" + regionProvince.getCode() + regionProvince.getName() + ".txt", regionCity.getCode() + "||" + regionCity.getName() + "||"
//                        + regionCity.getParentId() + "||" + regionCity.getType() + "\r\n");
//
//                List<Region> county = getCounty("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/" + regionProvince.getCode() + "/"
//                        + regionCity.getCode() + ".html");
//                all.addAll(county);
//                for (Region regionCounty : county) {// 遍历县
//                    appendFile("E:/" + regionProvince.getCode() + regionProvince.getName() + ".txt", regionCounty.getCode() + "||" + regionCounty.getName()
//                            + "||" + regionCounty.getParentId() + "||" + regionCounty.getType() + "\r\n");
//
//                    List<Region> town = getTown("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/" + regionProvince.getCode() + "/"
//                            + regionCity.getCode().substring(2, 4) + "/" + regionCounty.getCode() + ".html");
//                    all.addAll(town);
//                    for (Region regionTown : town) {// 遍历镇
//                        appendFile("E:/" + regionProvince.getCode() + regionProvince.getName() + ".txt", regionTown.getCode() + "||" + regionTown.getName()
//                                + "||" + regionTown.getParentId() + "||" + regionTown.getType() + "\r\n");
//
//                        List<Region> village = getVillage("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/" + regionProvince.getCode() + "/"
//                                + regionCity.getCode().substring(2, 4) + "/" + regionCounty.getCode().substring(4, 6) + "/" + regionTown.getCode() + ".html");
//                        all.addAll(village);
//                        for (Region regionVillage : village) {// 遍历村
//                            appendFile(
//                                    "E:/" + regionProvince.getCode() + regionProvince.getName() + ".txt",
//                                    regionVillage.getCode() + "||" + regionVillage.getName() + "||" + regionVillage.getParentId() + "||"
//                                            + regionVillage.getType() + "\r\n");
//
//                        }
//                    }
//
//                }
//
//            }
//        }
//    }
    public static void main(String[] args) throws Exception {
//            String regionProvince = "410103";
        Region regionProvince = new Region();
        regionProvince.setCode("410103");
        List<Region> town = getTown("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2021/" + regionProvince.getCode().substring(0, 2)+ "/"
                + regionProvince.getCode().substring(2, 4) + "/" + regionProvince.getCode() + ".html");
        for (Region regionTown : town) {// 遍历镇
            regionTown.setParenId("410103");
            List<Region> village = getVillage("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2021/" + regionProvince.getCode().substring(0, 2) + "/"
                    + regionProvince.getCode().substring(2, 4) + "/" + regionProvince.getCode().substring(4, 6) + "/" + regionTown.getCode() + ".html");
            for (Region regionVillage : village) {// 遍历村
                regionVillage.setParenId(regionTown.getCode());
            }
            regionTown.setRegions(village);
        }
        List<Object> objects = Collections.singletonList(town);
        String s = new JSONArray(objects).toJSONString();
        System.out.println("---->" + s.length());
        System.out.println("---->" + s.substring(1,s.length()-1));
    }
}
