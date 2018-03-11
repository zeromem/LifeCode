package org.zeromem.lifecode.xml2docx;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by zeromem on 2018/3/10.
 */
public class XSLUtil {

    private static String xmlPath = "C:\\Users\\zeromem\\Desktop\\C2.xml";
    private static String xslPath = "C:\\Users\\zeromem\\Desktop\\xh.xsl";

    public static void main(String[] args) {
        XSLUtil d4j = new XSLUtil();
        String str = d4j.transformDocument(xmlPath, xslPath);
        System.out.println(str);
    }

    /**
     * 使用xsl转化xml
     * @param xmlPath
     * @param xslPath
     * @return
     */
    public String transformDocument(String xmlPath, String xslPath) {
        return this.transformDocument(new File(xmlPath), new File(xslPath));
    }

    /**
     * 使用xsl文件转化xml文件
     * @param xmlFile
     * @param xslFile
     * @return
     */
    public String transformDocument(File xmlFile, File xslFile){
        String result = null;
        if(xmlFile == null || !xmlFile.exists() || xslFile == null || !xslFile.exists()){
            return result;
        }
        Document doc = null;
        try {
            doc = parseXmlFileToDocument(xmlFile);
        } catch (DocumentException e) {
            System.err.println("Cannot parse XML to Document.");
            e.printStackTrace();
        }
        Document resultDoc = null;
        try {
            resultDoc = this.transformDocument(doc, xslFile);
        } catch (TransformerException e) {
            System.err.println("Cannot parse doc with xsl.");
            e.printStackTrace();
        }
        result = parseDoc2String(resultDoc);
        return result;
    }

    /**
     * 将xml文件转换为Doc对象
     * @param xmlFile
     * @return
     * @throws DocumentException
     */
    public Document parseXmlFileToDocument(File xmlFile)
            throws DocumentException {
        if (xmlFile != null) {
            Document doc = null;
            SAXReader reader = new SAXReader();
            doc = reader.read(xmlFile);
            return doc;
        }
        return null;
    }

    /**
     * 将xml转换为Doc对象
     * @param xmlPath
     * @return
     * @throws DocumentException
     */
    public Document parseXmlFileToDocument(String xmlPath)
            throws DocumentException {
        File file = new File(xmlPath);
        return this.parseXmlFileToDocument(file);
    }

    /**
     * 使用xsl文件转化xml文件
     * @param doc
     * @param xslFile
     * @return
     * @throws TransformerException
     */
    public Document transformDocument(Document doc, File xslFile) throws TransformerException {
        if(doc == null || !xslFile.exists() || xslFile == null){
            return null;
        }
        TransformerFactory factory = TransformerFactory.newInstance();
        Document transformerDoc = null;
        Transformer transformer = factory.newTransformer(new StreamSource(xslFile));
        DocumentSource docSource = new DocumentSource(doc);
        DocumentResult docResult = new DocumentResult();
        transformer.transform(docSource, docResult);
        transformerDoc = docResult.getDocument();
        return transformerDoc;
    }

    /**
     * 将doc转为string
     * @param transformDoc
     * @return
     */
    private String parseDoc2String(Document transformDoc) {
        if(transformDoc == null){
            return null;
        }
        StringWriter strWriter = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        format.setExpandEmptyElements(false);
        XMLWriter xmlWriter = new XMLWriter(strWriter, format);
        try {
            xmlWriter.write(transformDoc);
            xmlWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strWriter.toString();
    }

}
