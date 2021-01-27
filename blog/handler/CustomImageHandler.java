package com.convertium.blog.handler;

import java.util.Base64;

public class CustomImageHandler {
	
	public String getImgData(byte[] byteData) {
        return Base64.getMimeEncoder().encodeToString(byteData);
    }

}
