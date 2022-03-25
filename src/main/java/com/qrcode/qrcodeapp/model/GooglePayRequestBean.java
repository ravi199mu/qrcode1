package com.qrcode.qrcodeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GooglePayRequestBean {
    private String userName;
    private String mobileNo;
    private String accountType;
    private String accountNo;
}
