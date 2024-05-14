package com.packt.cardatabase;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletResponse;

public class Demo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {
        LOGGER.info("Hello, World!");
        // downloadAttachment();
    }

    public void downloadAttachment(String imagePathString, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ImageOutputStream imageOutputStream = null;
        ImageWriter imageWriter = null;
        String imageFormaString = "";
        IIORegistry registry = IIORegistry.getDefaultInstance();
        Iterator<ImageWriterSpi> serviceProviders = registry.getServiceProviders(ImageWriterSpi.class, false);
        while (serviceProviders.hasNext()) {
            ImageWriterSpi next = serviceProviders.next();
            LOGGER.error("------支持压缩的格式为description：{} format names：{}", next.getDescription(Locale.ENGLISH),
                    Arrays.toString(next.getFormatNames()));
        }
        float imageQuality = 0.5f;
        try {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            imageWriter = ImageIO.getImageWritersByFormatName(imageFormaString).next();
            imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            imageWriter.setOutput(imageOutputStream);
            ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
            imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imageWriteParam.setCompressionQuality(imageQuality);
            imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (imageOutputStream != null) {
                try {
                    imageOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (imageWriter != null) {
                imageWriter.dispose();
            }
        }
    }
}
