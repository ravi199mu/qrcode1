package com.qrcode.qrcodeapp.controller;

import com.qrcode.qrcodeapp.model.GooglePayRequestBean;
import com.qrcode.qrcodeapp.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class QrCodeController {


    @Autowired
    private QrCodeService qrCodeService;

//    @GetMapping(value = "/qrcode")
//    public @ResponseBody byte[] getQrCode(@RequestBody GooglePayRequestBean requestBean) throws Exception {
//        return qrCodeService.writeQRCode(requestBean);
//    }

    @GetMapping(value = "/qrcode1")
    public @ResponseBody String getQrCode(@RequestBody GooglePayRequestBean requestBean) throws Exception {
        return qrCodeService.writeQRCode(requestBean);
    }

    @GetMapping(value = "/qrcodeval")
    public @ResponseBody String getQrCodeValue(@RequestParam String imageName) throws Exception {
        return qrCodeService.readQRCode(imageName);
    }
}
