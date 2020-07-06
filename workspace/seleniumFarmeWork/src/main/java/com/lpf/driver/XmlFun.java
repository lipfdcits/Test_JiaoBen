package com.lpf.driver;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlFun {
	/*##############################################################################################################
	 * 功能：返回二级结点的内容，以String类型返回
	 * 输入：文件路径,二级结点名称
	 * 输出：String形式的二级结点的内容
	 */
	public static String readNote(String file_path,String note_name) throws DocumentException{
		String path = file_path;
		SAXReader saxR=new SAXReader();
		Document doc = saxR.read(path);

		Element eleroot = doc.getRootElement();
		Element element = eleroot.element(note_name);
		String text=element.getText();
		return text;
	}

	/*##############################################################################################################
	 * 功能：返回三级结点的内容，以String类型返回
	 * 输入：文件路径,二级结点名称，三级结点名称
	 * 输出：String形式的三级结点的内容
	 */
	public static String readNote(String file_path,String note_name_2,String note_name_3) throws DocumentException{
		String path = file_path;
		SAXReader saxR=new SAXReader();
		Document doc = saxR.read(path);

		Element eleroot = doc.getRootElement();
		Element element_2 = eleroot.element(note_name_2);
		Element element_3 = element_2.element(note_name_3);
		String text=element_3.getText();
		return text;
	}

	/*##############################################################################################################
	 * 功能：返回指定的二级结点的内容列表，以List<String>类型返回
	 * 输入：文件路径,二级结点名称
	 * 输出：List<String>形式的二级结点的内容
	 */
	public static List<String> readNoteList(String file_path,String note_name) throws DocumentException{
		String path = file_path;
		SAXReader saxR=new SAXReader();
		Document doc = saxR.read(path);

		Element eleroot = doc.getRootElement();
		List list = eleroot.elements(note_name);
		List<String> list_string = new ArrayList<String>();
		for(Iterator it = list.iterator();it.hasNext();){
			Element element = (Element)it.next();
			list_string.add(element.getText());
		}
		return list_string;
	}

	/*##############################################################################################################
	 * 功能：返回指定的三级结点的内容列表，以List<String>类型返回
	 * 输入：文件路径,二级结点名称，三级结点名称
	 * 输出：List<String>形式的三级结点的内容
	 */
	public static List<String> readNoteList(String file_path,String note_name_2,String note_name_3) throws DocumentException{
		String path = file_path;
		SAXReader saxR=new SAXReader();
		Document doc = saxR.read(path);

		Element eleroot = doc.getRootElement();
		Element element_2 = eleroot.element(note_name_2);
		List list = element_2.elements(note_name_3);
		List<String> list_string = new ArrayList<String>();
		for(Iterator it = list.iterator();it.hasNext();){
			Element element = (Element)it.next();
			list_string.add(element.getText());
		}
		return list_string;
	}

	/*##############################################################################################################
	 * 功能：修改XML文件，未完......
	 * 输入：文件路径
	 * 输出：
	 */
	public static void fun(String file_path) throws Exception{
		String path = file_path;
		SAXReader saxR=new SAXReader();
		Document doc = saxR.read(path);

		Element eleroot = doc.getRootElement();
		Element element_2 = eleroot.element("loginRight");
		Attribute attribute = element_2.attribute("name");
		System.out.println(attribute.getText());
		element_2.addAttribute("age", "99");
		writer(doc);
		//List list = eleroot.elements();
//		for(Iterator it = eleroot.elementIterator();it.hasNext();){
//			Element element = (Element)it.next();
//			System.out.println(element.getName()+"  "+element.getNodeType()+"  "+element.getText());
//		}

	}

	/*##############################################################################################################
	 * 功能：写入XML文件，未完......
	 * 输入：文件路径
	 * 输出：
	 */
	public static void writer(Document document) throws Exception {
		// 紧凑的格式
		// OutputFormat format = OutputFormat.createCompactFormat();
		// 排版缩进的格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置编码
		format.setEncoding("UTF-8");
		// 创建XMLWriter对象,指定了写出文件及编码格式
		// XMLWriter writer = new XMLWriter(new FileWriter(new
		// File("src//a.xml")),format);
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(new File("bin\\testData\\login.xml")), "UTF-8"), format);
		// 写入
		writer.write(document);
		// 立即写入
		writer.flush();
		// 关闭操作
		writer.close();
	}
}
