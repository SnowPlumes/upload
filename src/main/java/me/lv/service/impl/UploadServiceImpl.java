package me.lv.service.impl;

import me.lv.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lzw
 * @date 2018/6/13
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${upload.location}")
    private String basePath;
    @Value("${upload.server.path}")
    private String serverPath;

    @Override
    public String uploadPicture(MultipartFile file) throws Exception {
        String webPath = "";
        if (!file.isEmpty()) {
            if (file.getContentType().contains("image")) {
                // 获取图片的文件名
                String fileName = file.getOriginalFilename();
                // 获取图片的扩展名
                String extensionName = StringUtils.substringAfter(fileName, ".");
                // 新的图片文件名 = 获取时间戳+"."图片扩展名
                String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;

                String dataStr = new SimpleDateFormat("yyyyMMdd").format(new Date());

                // 文件路径
                String filePath = basePath + dataStr + "/";
                webPath += serverPath + dataStr + "/" + newFileName;
                File dest = new File(filePath, newFileName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                // 上传到指定目录
                file.transferTo(dest);
            }
        }
        return webPath;
    }

    @Override
    public String uploadPictureWithPart(Part part) throws Exception {
        String webPath = "";
        String dataStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String fileName = getFileName(part);

        // 获取图片的扩展名
        String extensionName = StringUtils.substringAfter(fileName, ".");
        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;

        // 文件路径
        String filePath = basePath + dataStr + "/";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        part.write(filePath + File.separator + newFileName);
        webPath += serverPath + dataStr + "/" + newFileName;
        return webPath;
    }

    private String getFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        String fileName = header.substring(header.indexOf("filename=\"") + 10,
                header.lastIndexOf("\""));
        return fileName;
    }
}
