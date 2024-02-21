package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */

@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传: {}",file);


        try {
            // 原始文件名
            String originalFileName = file.getOriginalFilename();
            // 获取原始文件名的后缀
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString() + extension;

            file.transferTo(new File("C:\\Users\\Olaf\\Desktop\\ITHeima\\sky-take-out\\images\\" + objectName));

            return Result.success("C:\\Users\\Olaf\\Desktop\\ITHeima\\sky-take-out\\images\\" + objectName);

        } catch (IOException e) {
            log.error("文件上传失败, {}",e);
        }
        return null;
    }

}
