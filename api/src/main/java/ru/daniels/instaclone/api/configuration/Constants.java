package ru.daniels.instaclone.api.configuration;


import org.postgresql.util.Base64;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import ru.daniels.instaclone.api.controller.SecController;
import ru.daniels.instaclone.api.security.SecUser;

import java.io.*;
import java.nio.file.Files;

@Component
public class Constants {
    public final static String IMAGES_FOLDER = "images/";
    public final static Long DEFAULT_IMAGE_ID = 1L;
    public final static String URL = "127.0.0.1:8080/";
    public final static String MEDIA_URL = URL + "media/";

    public static String convertAndGetImageName(String base64Image){
        return convertToFIle(base64Image).getName();
    }

    public static File convertToFIle(String base64Image) {
        ClassLoader loader = Constants.class.getClassLoader();
        SecUser user = SecController.getAuthUser();
        String baseImage = base64Image.split(",")[1];
        byte[] bytesImage = Base64.decode(baseImage);
        File file = null;
        try {
            StringBuilder pathBuilder = new StringBuilder();
            pathBuilder
                    .append(user.getId())
                    .append("/")
                    .append(DigestUtils.md5DigestAsHex(bytesImage))
                    .append(".jpg");
            file = new File(loader.getResource("images").getFile(), pathBuilder.toString());
            try(FileOutputStream outputStream = new FileOutputStream(file)){
                outputStream.write(bytesImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
               e.printStackTrace();
        }
        return file;
    }

    public static void createUserDir(Long userId){
        ClassLoader loader = Constants.class.getClassLoader();
        new File(loader.getResource("images").getFile(), userId + "")
                .mkdir();
    }
}
