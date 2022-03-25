package com.qrcode.qrcodeapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qrcode.qrcodeapp.model.GooglePayRequestBean;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class QrCodeService {

    int size = 350;
    String imageType = "png";

    private static String QRCODE_PATH = "C:\\Users\\pmrav\\Desktop\\qrcode\\";

    public String writeQRCode(GooglePayRequestBean requestBody) throws Exception {
        String qrcode = QRCODE_PATH + requestBody.getUserName() + "-QRCODE.png";
        QRCodeWriter writer = new QRCodeWriter();

        //object to string
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringEmp = new StringWriter();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.writeValue(stringEmp, requestBody);
        
        
        BitMatrix bitMatrix = writer.encode(stringEmp.toString(),BarcodeFormat.QR_CODE, size, size);
        Path path = FileSystems.getDefault().getPath(qrcode);
        MatrixToImageWriter.writeToPath(bitMatrix, imageType, path);
        return "QRCODE is generated successfully....";

        //return byte[]
//        return toByteArrayAutoClosable(MatrixToImageWriter.toBufferedImage(bitMatrix),imageType);
    }

    private static byte[] toByteArrayAutoClosable(BufferedImage image, String type) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            ImageIO.write(image, type, out);
            return out.toByteArray();
        }
    }

    public String readQRCode(String qrcodeImageName) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new File(QRCODE_PATH+qrcodeImageName));
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();
    }


}
