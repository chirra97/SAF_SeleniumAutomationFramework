package Config.PDF;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;
import java.util.LinkedHashMap;

public class PDFWork {


    public static LinkedHashMap<Integer, String> getTextFromPDFFile(String pdfFilePath) {
        LinkedHashMap<Integer, String> pdfFileData_LHM = new LinkedHashMap<Integer, String>();
        PdfReader pdfReaderObj = null;
        try {
            pdfReaderObj = new PdfReader(pdfFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File not found : " + pdfFilePath);
        }
        int pages = pdfReaderObj.getNumberOfPages();

        for (int i = 1; i <= pages; i++) {
            String pageContent = "";
            try {
                pageContent = PdfTextExtractor.getTextFromPage(pdfReaderObj, i);
                pdfFileData_LHM.put(i, pageContent);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Unable to read the data from PDF file page number : " + i);
            }
        }
        pdfReaderObj.close();
        System.out.println("pdfFileData_LHM : "+pdfFileData_LHM);
        return pdfFileData_LHM;
    }

    public static String getTextFromPDFFile(String pdfFilePath, int pageNumber) {
        PdfReader pdfReaderObj = null;
        try {
            pdfReaderObj = new PdfReader(pdfFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("File not found : " + pdfFilePath);
        }
        int pages = pdfReaderObj.getNumberOfPages();

        for (int i = 1; i <= pages; i++) {
            String pageContent = "";
            try {
                if(pageNumber == i) {
                    pageContent = PdfTextExtractor.getTextFromPage(pdfReaderObj, i);
                    return pageContent;
                }else
                    continue;
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Unable to read the data from PDF file page number : " + i);
            }
        }
        pdfReaderObj.close();
        return "";
    }


    public static void main(String[] args) {
        getTextFromPDFFile("C:\\Users\\SureshChirra\\Desktop\\table.pdf");
    }

}
