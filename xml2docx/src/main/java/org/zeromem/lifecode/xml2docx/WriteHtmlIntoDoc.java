package org.zeromem.lifecode.xml2docx;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;

public class WriteHtmlIntoDoc {
    public static void main(String[] args) throws IOException {
        writHtmlIntoDoc("D:\\坚果云\\了了\\xml转doc\\C.html", "D:\\坚果云\\了了\\xml转doc\\out.doc");
    }

    /**
     * 把html文件带格式输出到doc文件中
     * @param htmlPath
     * @param outPath 必须是doc格式的输出，不能是docx.
     * @throws IOException
     */
    public static void writHtmlIntoDoc(String htmlPath, String outPath) throws IOException {
        Document doc = Jsoup.parse(new File(htmlPath), "UTF-8");
        InputStream is = new ByteArrayInputStream(doc.toString().getBytes());
        POIFSFileSystem poifs = new POIFSFileSystem();
        DirectoryEntry directory = poifs.getRoot();
        directory.createDocument("WordDocument", is);

        FileOutputStream fos = new FileOutputStream(new File(outPath));
        poifs.writeFilesystem(fos);

        poifs.close();
        fos.close();
        is.close();
    }
}
