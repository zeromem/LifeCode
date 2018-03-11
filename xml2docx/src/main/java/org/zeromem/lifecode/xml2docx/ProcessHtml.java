package org.zeromem.lifecode.xml2docx;

import org.apache.commons.io.FileUtils;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.safety.Cleaner;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import javax.xml.transform.TransformerException;
import java.io.*;

/**
 * Created by zeromem on 2018/3/10.
 */
public class ProcessHtml {
    public static final String DIR = "C:\\Users\\zeromem\\Desktop";

    public static void main(String[] args) throws IOException, TransformerException {
//        0. transform xml to html.
//        FormatXmlWithXsl.transforXmlWithXsl(DIR + "\\C2.xml", DIR + "\\xh.xsl", DIR + "\\C2_format.html");
//        1. use jsoup to format above html.

        String path = DIR + "\\C2_soup.html";
        Document doc = Jsoup.parse(new File(path), "UTF-8");
        InputStream is = new FileInputStream(path);
        POIFSFileSystem poifs = new POIFSFileSystem();
        DirectoryEntry directory = poifs.getRoot();
        directory.createDocument("WordDocument", is);

        FileOutputStream fos = new FileOutputStream(DIR + "\\poi_out.doc");
        poifs.writeFilesystem(fos);
        poifs.close();
        fos.close();
        is.close();
        System.exit(1);

        System.out.println("转换word文件完成!");
        Element body = doc.body();
        NodeVisitor nv = new NodeVisitor() {
            public void head(Node node, int depth) {
                System.out.println(node.nodeName());
                Node parent = node.parent();
                if (parent instanceof Element) {
                    Element eleParent = (Element) parent;
                    System.out.println("parent attr: " + eleParent.attributes() + "===");
                }
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    Attributes attributes = textNode.attributes();
                    if (attributes != null) {
                        System.out.println("~~~~~~~~~~attr~~~~~~~~~~~");
                        for (Attribute attribute : attributes) {
                            System.out.println(attribute);
                        }
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    System.out.printf("==========%d===========\n", depth);
                    System.out.println(textNode.getWholeText());
                    System.out.println();
                } else if (node instanceof Element) {

                }
            }

            public void tail(Node node, int depth) {
            }
        };
        body.traverse(nv);
    }
}

