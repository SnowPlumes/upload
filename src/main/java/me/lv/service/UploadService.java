package me.lv.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;

/**
 * @author lzw
 * @date 2018/6/13
 */
public interface UploadService {

    /**
     * 上传图片
     *
     * @param file
     * @return
     * @throws Exception
     */
    String uploadPicture(MultipartFile file) throws Exception;

    /**
     * 上传
     * 图片在request payload 里面
     *
     * @param part
     * @return
     * @throws Exception
     */
    String uploadPictureWithPart(Part part) throws Exception;
}
