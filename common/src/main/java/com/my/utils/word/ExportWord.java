package com.my.utils.word;

/**
 * 2022/3/4
 * NJL
 */
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExportWord {
    private XWPFHelperTable xwpfHelperTable = null;
    private XWPFHelper xwpfHelper = null;
    public ExportWord() {
        xwpfHelperTable = new XWPFHelperTable();
        xwpfHelper = new XWPFHelper();
    }
    /**
     * 创建好文档的基本 标题，表格  段落等部分
     * @return
     */
    public XWPFDocument createXWPFDocument() {
        XWPFDocument doc = new XWPFDocument();
        createTitleParagraph(doc);
        createTableParagraph(doc, 10, 6);
        return doc;
    }
    /**
     * 创建表格的标题样式
     * @param document
     */
    public void createTitleParagraph(XWPFDocument document) {
        XWPFParagraph titleParagraph = document.createParagraph();    //新建一个标题段落对象（就是一段文字）
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);//样式居中
        XWPFRun titleFun = titleParagraph.createRun();    //创建文本对象
//        titleFun.setText(titleName); //设置标题的名字
        titleFun.setBold(true); //加粗
        titleFun.setColor("000000");//设置颜色
        titleFun.setFontSize(25);    //字体大小
//        titleFun.setFontFamily("");//设置字体
        //...
        titleFun.addBreak();    //换行
        
        XWPFParagraph titleParagraph2 = document.createParagraph();
        XWPFRun titleFun2 = titleParagraph2.createRun();
        XWPFRun titleFun3 = titleParagraph2.createRun();
        titleFun.setColor("000000");//设置颜色
        titleFun.setFontSize(12);
    }
    /**
     * 设置表格
     * @param document
     * @param rows
     * @param cols
     */
    public void createTableParagraph(XWPFDocument document, int rows, int cols) {
//        xwpfHelperTable.createTable(xdoc, rowSize, cellSize, isSetColWidth, colWidths)
        XWPFTable infoTable = document.createTable(rows, cols);
        xwpfHelperTable.setTableWidthAndHAlign(infoTable, "9072", STJc.CENTER);
        //合并表格
        xwpfHelperTable.mergeCellsHorizontal(infoTable, 1, 1, 5);
        xwpfHelperTable.mergeCellsVertically(infoTable, 0, 3, 6);
        for(int col = 3; col < 7; col++) {
            xwpfHelperTable.mergeCellsHorizontal(infoTable, col, 1, 5);
        }
        //设置表格样式
        List<XWPFTableRow> rowList = infoTable.getRows();
        for(int i = 0; i < rowList.size(); i++) {
            XWPFTableRow infoTableRow = rowList.get(i);
            List<XWPFTableCell> cellList = infoTableRow.getTableCells();
            for(int j = 0; j < cellList.size(); j++) {
                XWPFParagraph cellParagraph = cellList.get(j).getParagraphArray(0);
                cellParagraph.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun cellParagraphRun = cellParagraph.createRun();
                cellParagraphRun.setFontSize(12);
                if(i % 2 != 0) {
                    cellParagraphRun.setBold(true);
                }
            }
        }
        xwpfHelperTable.setTableHeight(infoTable, 560, STVerticalJc.CENTER);
    }

    /**
     * 往表格中填充数据
     * @param dataList
     * @param document
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void exportCheckWord(Map<String, Object> dataList, XWPFDocument document, String savePath) throws IOException {
        XWPFParagraph paragraph = document.getParagraphArray(0);
        XWPFRun titleFun = paragraph.getRuns().get(0);
        titleFun.setText(String.valueOf(dataList.get("TITLE")));
        List<List<Object>> tableData = (List<List<Object>>) dataList.get("TABLEDATA");
        XWPFTable table = document.getTableArray(0);
        fillTableData(table, tableData);
        xwpfHelper.saveDocument(document, savePath);
    }
    /**
     * 往表格中填充数据
     * @param table
     * @param tableData
     */
    public void fillTableData(XWPFTable table, List<List<Object>> tableData) {
        List<XWPFTableRow> rowList = table.getRows();
        for(int i = 0; i < tableData.size(); i++) {
            List<Object> list = tableData.get(i);
            List<XWPFTableCell> cellList = rowList.get(i).getTableCells();
            for(int j = 0; j < list.size(); j++) {
                XWPFParagraph cellParagraph = cellList.get(j).getParagraphArray(0);
                XWPFRun cellParagraphRun = cellParagraph.getRuns().get(0);
                cellParagraphRun.setText(String.valueOf(list.get(j)));
            }
        }
    }
    
    
    public byte[] exportCheckWord2(Map<String, Object> dataList, XWPFDocument document) throws IOException {
        XWPFParagraph paragraph = document.getParagraphArray(0);
        XWPFRun titleFun = paragraph.getRuns().get(0);
        titleFun.setText(String.valueOf(dataList.get("TITLE")));
    
        XWPFParagraph paragraph1 = document.getParagraphArray(1);
        XWPFRun fileFun = paragraph1.getRuns().get(0);
        fileFun.setText("工程单位：aa,施工标段：bb,监察单位：cc");
        
//        List<List<Object>> tableData = (List<List<Object>>) dataList.get("TABLEDATA");
//        XWPFTable table = document.getTableArray(0);
//        fillTableData(table, tableData);
        return xwpfHelper.saveDocumentToByteOutputStream(document);
    }
}
