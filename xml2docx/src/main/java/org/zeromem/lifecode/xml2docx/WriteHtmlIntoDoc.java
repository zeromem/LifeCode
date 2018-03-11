package org.zeromem.lifecode.xml2docx;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;

public class WriteHtmlIntoDoc {
    public static void main(String[] args) {

    }

    /**
     * 把html文件带格式输出到doc文件中
     * @param htmlPath
     * @param outPath 必须是doc格式的输出，不能是docx.
     * @throws IOException
     */
    public static void writHtmlIntoDoc(String htmlPath, String outPath) throws IOException {
        InputStream is = new FileInputStream(htmlPath);
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
