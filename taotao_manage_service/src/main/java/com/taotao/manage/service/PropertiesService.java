package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by zm on 5/25/17.
 */
@Service
public class PropertiesService {
    @Value("${IMG_UPLOAD_PATH}")
    String IMG_UPLOAD_PATH;
    @Value("${IMG_BASE_URL}")
    String IMG_BASE_URL;

    public String getIMG_UPLOAD_PATH() {
        return IMG_UPLOAD_PATH;
    }

    public String getIMG_BASE_URL() {
        return IMG_BASE_URL;
    }
}
