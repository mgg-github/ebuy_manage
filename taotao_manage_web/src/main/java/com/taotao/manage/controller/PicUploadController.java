package com.taotao.manage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.bean.PicUploadResult;
import com.taotao.manage.service.PropertiesService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by zm on 5/24/17.
 */
@Controller
@RequestMapping("/pic/upload")
public class PicUploadController {
    @Autowired
    private PropertiesService propertiesService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PicUploadController.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String[] IMAGE_TYPE = {".bmp",".jpg",".jpeg",".gif",".png"};
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile uploadFile) throws JsonProcessingException {
        PicUploadResult result = new PicUploadResult();
        result.setError(1);

        String originalFilename = uploadFile.getOriginalFilename();
        boolean isLegal = true;
        if(uploadFile==null||uploadFile.isEmpty()){
            result.setMessage("Bad Request!");
            isLegal = false;
        }

        if(isLegal) {
            isLegal =false;
            for (String type :
                    IMAGE_TYPE) {
                if (originalFilename.endsWith(type)) {
                    isLegal = true;
                    break;
                }
            }
        }

        String ext = StringUtils.substringAfterLast(originalFilename, ".");
        File file = getFile(ext);
        if(isLegal)
        try {

            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("Pic file upload [{}] to [{}] ....",originalFilename);
            }

            uploadFile.transferTo(file);
            BufferedImage bufferedImage = ImageIO.read(file);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            result.setWidth(width+"");
            result.setHeight(height+"");
            result.setUrl(propertiesService.getIMG_BASE_URL()+StringUtils.substringAfterLast(file.getPath(),propertiesService.getIMG_UPLOAD_PATH()));
            result.setMessage("OK");
            result.setError(0);
            return MAPPER.writeValueAsString(result);
        } catch (IOException e) {
            e.printStackTrace();
            result.setMessage("Internal Server Error!");
            if (file.exists()){
                file.delete();
            }
        }
        return MAPPER.writeValueAsString(result);
    }

    private File getFile(String ext){
        DateTime dateTime = new DateTime(new Date());
        String path = propertiesService.getIMG_UPLOAD_PATH()+dateTime.toString("yyyy")+File.separator+dateTime.toString("MM")+File.separator+dateTime.toString("dd")+File.separator+dateTime.toString("HH")+File.separator;
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String newFileName = dateTime.toString("yyyyMMddHHmmssSSSS")+ RandomUtils.nextInt(100,9999)+"."+ext;


        return new File(dir,newFileName);
    }
}
