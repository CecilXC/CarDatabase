package com.packt.cardatabase.web;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

@RestController
public class ImageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    // Fixme: the newly added api will be 404, need to figure out.
    @GetMapping("/downloadAttachment")
    public void downloadAttachment(@RequestParam("imagePathString") String imagePathString, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ImageOutputStream imageOutputStream = null;
        ImageWriter imageWriter = null;
        String imageFormatString = "";

        
        // D:\Workspace\IdeaProjects\cardatabase\photos\chahua387.tif
        try {
            // Path path = Paths.get("/photos/chahua387.tif");
            Path path = Paths.get(imagePathString);
            inputStream =Files.newInputStream(path);
            outputStream = new BufferedOutputStream(response.getOutputStream());
        } catch (Exception e) {
            System.out.println("An error occurred while trying to read the image file");
            e.printStackTrace();
        }
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
            imageWriter = ImageIO.getImageWritersByFormatName(imageFormatString).next();
            imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            imageWriter.setOutput(imageOutputStream);
            ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
            imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imageWriteParam.setCompressionQuality(imageQuality);
            imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (imageOutputStream != null) {
                try {
                    imageOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (imageWriter != null) {
                imageWriter.dispose();
            }
        }
    }

}
