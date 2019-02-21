package cn.codeyang.cms.rest.controller;

import cn.codeyang.common.http.utils.HttpResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author yangzhongyang
 */
@Api(value = "文件上传", description = "文件上传")
@RestController()
@RequestMapping("/api/upload")
@Slf4j
public class UploadController {

	@Value("${file.upload-folder}")
	private String uploadFolder;

	@PostMapping("")
	public ResponseEntity<HttpResult> upload(MultipartFile file){
		log.debug("Rest Upload file : {}", file);

		if (Objects.isNull(file) || file.isEmpty()) {
			log.error("文件不能为空");
			return ResponseEntity.badRequest().build();
		}

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadFolder + File.separator + file.getOriginalFilename());
			if (!Files.isWritable(path)) {
				Files.createDirectories(Paths.get(uploadFolder));
			}

			Files.write(path, bytes);
			log.debug("文件写入成功");

			return ResponseEntity.ok(HttpResult.ok("上传成功"));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.ok(HttpResult.fail("后端异常"));
		}
	}
}
