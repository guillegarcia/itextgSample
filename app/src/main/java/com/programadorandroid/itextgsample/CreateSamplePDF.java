package com.programadorandroid.itextgsample;

import android.os.Environment;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CreateSamplePDF {

    private final static String PDF_NAME = "sample.pdf";

    public boolean execute() {
        //We use a public file
        File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), PDF_NAME);

        //If the pdf already exist we delete it before create the new one
        if (pdfFile.exists()) {
            boolean fileDeleted = pdfFile.delete();
            if (!fileDeleted) {
                return false;
            }
        }

        //Create the file
        try {
            OutputStream filePdfOutputStream;
            boolean fileCreated = pdfFile.createNewFile();
            if (!fileCreated){
                return false;
            }

            filePdfOutputStream = new FileOutputStream(pdfFile);

            //Declare fonts
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, new BaseColor(0, 0, 0));
            Font subtituloFont = new Font(Font.FontFamily.HELVETICA, 16, Font.NORMAL, new BaseColor(0, 0, 0));

            //Create PDF object
            Document document = new Document(PageSize.A4,30,30,60,0);

            PdfWriter pdfWriter = PdfWriter.getInstance(document, filePdfOutputStream);
            document.open();

            //Title
            Paragraph titleParagraph = new Paragraph("Title",tituloFont);
            titleParagraph.setSpacingAfter(10);
            document.add(titleParagraph);

            //Subtitle
            Paragraph subtitlePragraph = new Paragraph("Subtitle",subtituloFont);
            subtitlePragraph.setSpacingAfter(20);
            document.add(subtitlePragraph);

            //Close the document
            document.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
