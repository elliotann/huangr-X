package com.easysoft.framework.resource.io;

import com.easysoft.framework.utils.FileUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * User: andy
 * Date: 13-12-27
 * Time: 上午11:13
 *
 * @since:
 */
public class XmlReader {
    /**
     * 根据文件不同读取xml文件
     * @param xmlFile
     * @return
     * @throws DocumentException
     */
    public static Document readDocument(String xmlFile) throws DocumentException{
        try {
            if (xmlFile.startsWith("file:")) {
                xmlFile = FileUtil.readFile(xmlFile.replaceAll("file:", ""));
                return DocumentHelper.parseText(xmlFile);
            } else if (xmlFile.startsWith("<?xml version")) {
                return DocumentHelper.parseText(xmlFile);
            }else {
                return loadDocument(xmlFile);
            }
        } catch (DocumentException e) {
            throw e;
        }
    }
    /**
     * 加载xml文件
     *
     * @param xmlFile
     * @return
     */
    private static Document loadDocument(String xmlFile) throws DocumentException {
        Document document = null;
        SAXReader saxReader = new SAXReader();
        File file = new File(xmlFile);
        if (file.exists())
            document = saxReader.read(file);
        return document;
    }
}
