package com.lpf.driver.tools;

import java.io.File;
import java.io.IOException;


import jxl.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class JxlFun {

	/*##############################################################################################################
	 * 功能：读取xls文件中指定位置的数据
	 * 输入：文件路径,Sheet名称，行号，列号
	 * 输出：字符串形式的单元格内容
	 */
	public static String readText(String filename,String sheetname,int row,int col) throws BiffException, IOException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		Sheet sheet = wb.getSheet(sheetname);
		Cell cell = sheet.getCell(col-1, row-1);
		String str = cell.getContents();
		//String str = cell.getType();//获取单元格中数据类型
		wb.close();
		return str;
	}

	/*##############################################################################################################
	 * 功能：创建新的xls文件
	 * 输入：文件路径,Sheet名称
	 * 输出：无
	 */
	public static void createXls(String filename,String sheetname) throws IOException, WriteException {
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename));
		WritableSheet ws = wwb.createSheet(sheetname, 0);
		ws.getSettings().setShowGridLines(false);//不显示风格线
		wwb.write();
		wwb.close();
	}

	/*##############################################################################################################
	 * 功能：合并单元格
	 * 输入：文件路径,Sheet名称,左上角位置，右下角位置
	 * 输出：无
	 */
	public static void mergeCells(String filename,String sheetname, int col, int row, int col2, int row2) throws IOException, WriteException, BiffException {
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);
		ws.mergeCells(col-1, row-1, col2-1, row2-1);
		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：向原有xls文件中追加数据
	 * 输入：文件路径,Sheet名称，行号，列号，要写入的字符串
	 * 输出：无
	 */
	public synchronized static void appendText(String filename,String sheetname,int row,int col,String str) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		Label label = new Label(col-1,row-1,str);
		ws.addCell(label);
		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：向原有xls文件中写入标题
	 * 输入：文件路径,Sheet名称，行号，列号，要写入的字符串
	 * 输出：无
	 */
	public synchronized static void appendFormatTitle(String filename,String sheetname,int row,int col,String str) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		WritableFont wf = new WritableFont(WritableFont.TIMES, 20, WritableFont.BOLD, false, UnderlineStyle.SINGLE_ACCOUNTING, Colour.BLACK);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.CENTRE); // 设置水平居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置垂直居中
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		Label label = new Label(col-1, row-1, str, wcf);
		ws.addCell(label);
		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：向原有xls文件中写入表头
	 * 输入：文件路径,Sheet名称，行号，列号，要写入的字符串
	 * 输出：无
	 * 日期：2017-11-7
	 */
	public synchronized static void appendFormatHead(String filename,String sheetname,int row,int col,String str) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		WritableFont wf = new WritableFont(WritableFont.TIMES, 15, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.CENTRE); // 设置水平居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置垂直居中
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		wcf.setBackground(Colour.GRAY_80);
		Label label = new Label(col-1, row-1, str, wcf);
		ws.addCell(label);
		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：写入PASS
	 * 输入：文件路径,Sheet名称，行号，列号
	 * 输出：无
	 * 日期：2017-11-7
	 */
	public synchronized static void appendFormatPass(String filename,String sheetname,int row,int col) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		WritableFont wf = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.GREEN);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.CENTRE); // 设置水平居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置垂直居中
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		Label label = new Label(col-1, row-1, "PASS", wcf);
		ws.addCell(label);
		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：写入fail
	 * 输入：文件路径,Sheet名称，行号，列号
	 * 输出：无
	 * 日期：2017-11-7
	 */
	public synchronized static void appendFormatFail(String filename,String sheetname,int row,int col) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		WritableFont wf = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.CENTRE); // 设置水平居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置垂直居中
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		Label label = new Label(col-1, row-1, "FAIL", wcf);
		ws.addCell(label);
		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：写入skip
	 * 输入：文件路径,Sheet名称，行号，列号
	 * 输出：无
	 * 日期：2017-11-7
	 */
	public synchronized static void appendFormatSkip(String filename,String sheetname,int row,int col) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		WritableFont wf = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.DARK_YELLOW);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.CENTRE); // 设置水平居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置垂直居中
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		Label label = new Label(col-1, row-1, "SKIP", wcf);
		ws.addCell(label);
		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：向原有xls文件中追加数据
	 * 输入：文件路径,Sheet名称，行号，列号，要写入的字符串
	 * 输出：无
	 * 日期：2017-11-7
	 */
	public synchronized static void appendFormatText(String filename,String sheetname,int row,int col,String str) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		WritableFont wf = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.CENTRE); // 设置水平居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置垂直居中
		wcf.setWrap(true); //开启自动换行
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		Label label = new Label(col-1, row-1, str, wcf);
		ws.addCell(label);
		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：向原有xls文件中追加异常错误信息
	 * 输入：文件路径,Sheet名称，行号，列号，要写入的字符串
	 * 输出：无
	 */
	public synchronized static void appendFormatFailText(String filename,String sheetname,int row,int col,String str) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		WritableFont wf = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.LEFT); // 设置水平左对齐
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置垂直居中
		wcf.setWrap(true); //开启自动换行
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		Label label = new Label(col-1, row-1, str, wcf);
		ws.addCell(label);
		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：向原有xls文件中追加异常跳过信息
	 * 输入：文件路径,Sheet名称，行号，列号，要写入的字符串
	 * 输出：无
	 */
	public synchronized static void appendFormatSkipText(String filename,String sheetname,int row,int col,String str) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		WritableFont wf = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.DARK_YELLOW);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.LEFT); // 设置水平左对齐
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置垂直居中
		wcf.setWrap(true); //开启自动换行
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		Label label = new Label(col-1, row-1, str, wcf);
		ws.addCell(label);
		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：向原有xls文件中写入统计信息
	 * 输入：文件路径,Sheet名称，行号，列号，要写入的字符串
	 * 输出：无
	 * 日期：2017-11-10
	 */
	public synchronized static void appendFormatStatistical(String filename,String sheetname,int row,int col,String str) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		WritableFont wf = new WritableFont(WritableFont.TIMES, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE2);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.LEFT); // 设置水平居左
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 设置垂直居中
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
		Label label = new Label(col-1, row-1, str, wcf);
		ws.addCell(label);
		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：设置列宽
	 * 输入：文件路径,Sheet名称，列宽
	 * 输出：无
	 */
	public synchronized static void setColumnView(String filename,String sheetname) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		ws.setColumnView(0, 60);
		ws.setColumnView(1, 12);
		ws.setColumnView(2, 100);
		ws.setColumnView(3, 10);
		wwb.write();
		wwb.close();
		wb.close();
	}


	/*##############################################################################################################
	 * 功能：向原有xls文件中追加数字
	 * 输入：文件路径,Sheet名称，行号，列号，要写入的数字
	 * 输出：无
	 */
	public synchronized static void appendNumber(String filename,String sheetname,int row,int col,double d) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		jxl.write.Number labelN = new jxl.write.Number(col-1, row-1, d);
		ws.addCell(labelN);

		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：向原有xls文件中追加数字，保留两位小数
	 * 输入：文件路径,Sheet名称，行号，列号，要写入的数字
	 * 输出：保留两位小数写入
	 */
	public synchronized static void appendFormatNumber(String filename,String sheetname,int row,int col,double d) throws BiffException, IOException, WriteException{
		Workbook wb = Workbook.getWorkbook(new File(filename));
		WritableWorkbook wwb = Workbook.createWorkbook(new File(filename),wb);
		WritableSheet ws = wwb.getSheet(sheetname);

		NumberFormat nf = new NumberFormat("#.##");
		WritableCellFormat wcfN = new WritableCellFormat(nf);
		jxl.write.Number labelNF = new jxl.write.Number(col-1, row-1, d, wcfN);
		ws.addCell(labelNF);

		wwb.write();
		wwb.close();
		wb.close();
	}

	/*##############################################################################################################
	 * 功能：获取xls文件指定sheet的总行数
	 * 输入：文件路径,Sheet名称
	 * 输出：指定Sheet的总行数
	 */
	public static int readRows(String filename,String sheetname) throws BiffException, IOException{
		Workbook workbook = Workbook.getWorkbook(new File(filename));
		Sheet sheet = workbook.getSheet(sheetname);
		int rows = sheet.getRows();
		workbook.close();
		return rows;
	}

	/*##############################################################################################################
	 * 功能：获取xls文件指定sheet的总列数
	 * 输入：文件路径,Sheet名称
	 * 输出：指定Sheet的总列数
	 */
	public static int readColumns(String filename,String sheetname) throws BiffException, IOException {
		Workbook workbook = Workbook.getWorkbook(new File(filename));
		Sheet sheet = workbook.getSheet(sheetname);
		int columns = sheet.getColumns();
		workbook.close();
		return columns;
	}

}