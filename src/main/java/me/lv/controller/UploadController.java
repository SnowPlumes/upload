package me.lv.controller;

import com.alibaba.fastjson.JSON;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.lv.config.WhiteConfig;
import me.lv.constants.ResponseConstants;
import me.lv.dto.JsonResponse;
import me.lv.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * @author lzw
 * @date 2018/6/13
 */
@Api(value = "逗比")
@RestController
@RequestMapping("upload")
@Slf4j
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    private UploadService uploadService;
    @Autowired
    private WhiteConfig whiteConfig;

    @ApiOperation(value = "上传图片(payload)")
    @PostMapping(value = "uploadPicture")
    public JsonResponse uploadPicture(HttpServletRequest request) {
        JsonResponse json = new JsonResponse();
        try {
            Part part = request.getPart("file");
            json.setCode(ResponseConstants.SUCCESS);
            json.setMsg("上传成功");
            json.setResults(uploadService.uploadPictureWithPart(part));
        } catch (Exception e) {
            json.setCode(ResponseConstants.FAIL);
            json.setMsg("上传失败");
            logger.error(">>>>>>>> upload error:", e);
        }
        return json;
    }

    @ApiOperation(value = "上传图片(formData)")
    @PostMapping(value = "upload")
    public JsonResponse uploadPicture(@RequestParam(name = "file") MultipartFile file) {
        JsonResponse json = new JsonResponse();
        try {
            json.setCode(ResponseConstants.SUCCESS);
            json.setMsg("上传成功");
            json.setResults(uploadService.uploadPicture(file));
        } catch (Exception e) {
            json.setCode(ResponseConstants.FAIL);
            json.setMsg("上传失败");
            logger.error(">>>>>>>> upload error:", e);
        }
        return json;
    }

    @GetMapping("test")
    public void test() {
        log.info("white = {}", JSON.toJSONString(whiteConfig.getIp()));
    }
}
