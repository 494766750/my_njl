package my.word;

/**
 * 2022/3/4
 * NJL
 */

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestExportWord {
    
    public static void main(String[] args) throws Exception {
        ExportWord ew = new ExportWord();
        XWPFDocument document = ew.createXWPFDocument();
        List<List<Object>> list = new ArrayList<List<Object>>();
        
//        List<Object> tempList = new ArrayList<Object>();
//        tempList.add("����");
//        tempList.add("��xx");
//        tempList.add("�Ա�");
//        tempList.add("��");
//        tempList.add("��������");
//        tempList.add("2018-10-10");
//        list.add(tempList);
//        tempList = new ArrayList<Object>();
//        tempList.add("���֤��");
//        tempList.add("36073xxxxxxxxxxx");
//        list.add(tempList);
//        tempList = new ArrayList<Object>();
//        tempList.add("������");
//        tempList.add("����");
//        tempList.add("����");
//        tempList.add("��");
//        tempList.add("���");
//        tempList.add("��");
//        list.add(tempList);
//        tempList = new ArrayList<Object>();
//        tempList.add("������ʷ");
//        tempList.add("��");
//        list.add(tempList);
        
        Map<String, Object> dataList = new HashMap<String, Object>();
        dataList.put("TITLE", "我是标题---------------------");
        dataList.put("TABLEDATA", list);
        ew.exportCheckWord2(dataList, document, "E:/expWordTest.docx");
        System.out.println("文档生成成功");
    }
}
