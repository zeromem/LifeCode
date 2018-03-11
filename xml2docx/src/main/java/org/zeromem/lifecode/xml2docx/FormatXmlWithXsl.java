package org.zeromem.lifecode.xml2docx;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Created by zeromem on 2018/3/10.
 * 用xsl将xml转换
 */
public class FormatXmlWithXsl {
    public static final String DIR = "C:\\Users\\zeromem\\Desktop";
    public static void main(String[] args) throws TransformerException {
        transforXmlWithXsl(DIR + "\\C2.xml", DIR + "\\xh.xsl",  DIR + "\\C2_format.html");

    }

    public static void transforXmlWithXsl(String inXml, String inXsl, String outHtml) throws TransformerException {
        File xmlfile = new File(inXml);
        File xslFile = new File(inXsl);
        StreamSource stylesource = new StreamSource(xslFile);
        StreamSource xmlsource = new StreamSource(xmlfile);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);

        // Transform the document and store it in a file
        transformer.setOutputProperty("method", "html");
        transformer.setOutputProperty("encoding", "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        transformer.transform(xmlsource, new StreamResult(new File(outHtml)));
        System.out.println("transform xml to html over.");
    }
}
