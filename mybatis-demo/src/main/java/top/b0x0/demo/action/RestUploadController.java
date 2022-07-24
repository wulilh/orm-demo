package top.b0x0.demo.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.b0x0.demo.domain.FileEntity;
import top.b0x0.demo.domain.ResponseEntry;
import top.b0x0.demo.service.IFileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 文件上传
 *
 * @author TANG
 * <p>
 * 知识点：
 * @RequestMapping 接受三种类型的uri，如下：
 * 1 可以指定为普通的具体值；
 * 2 可以指定为含有某变量的一类值(URI Template Patterns with Path Variables)；
 * <p>
 * 3 可以指定为含正则表达式的一类值( URI Template Patterns with Regular Expressions);
 * <p>
 * 下面的文件（图片 返回图像）下载就是利用第2方式，可以映射多个图像地址
 */
@RestController
public class RestUploadController {
    private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);

    /**
     * Save the uploaded file to this folder
     */
    private static final String UPLOADED_FOLDER = "/Users/TANG/file/";

    @Autowired
    IFileService fileService;

    /**
     * 单文件上传
     *
     * @param uploadfile /
     * @return /
     */
    @PostMapping("/api/upload")
    public ResponseEntry uploadFile(@RequestParam("file") MultipartFile uploadfile) {
        ResponseEntry entry = new ResponseEntry();
        logger.debug("Single file upload!");
        if (uploadfile.isEmpty()) {
            entry.setCode(ResponseEntry.PAREM_EXCEPTION);
            entry.setMessage("please select a file");
            return entry;
        }
        try {
            saveUploadedFiles(Arrays.asList(uploadfile));
        } catch (IOException e) {
            entry.setCode(ResponseEntry.DATA_EXCEPTION);
            entry.setMessage(e.getMessage());
            logger.debug(e.getMessage());
        }
        return entry;
    }


    /**
     * 多文件上传
     *
     * @param uploadfiles /
     * @return /
     */
    @PostMapping("/api/upload/multi")
    public ResponseEntity<?> uploadFileMulti(@RequestParam("files") MultipartFile[] uploadfiles) {
        logger.debug("Multiple file upload!");
        //获取文件名字
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }
        try {
            saveUploadedFiles(Arrays.asList(uploadfiles));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Successfully uploaded - " +
                uploadedFileName, new HttpHeaders(), HttpStatus.OK);
    }


//	    // maps html form to a Model
//	    @PostMapping("/api/upload/multi/model")
//	    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model) {
//
//	        logger.debug("Multiple file upload! With UploadModel");
//
//	        try {
//
//	            saveUploadedFiles(Arrays.asList(model.getFiles()));
//
//	        } catch (IOException e) {
//	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	        }
//
//	        return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
//
//	    }


    /**
     * 保存文件
     *
     * @param files /
     * @throws IOException
     */
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //next pls
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }

    }


    /**
     * 单文件上传并保存到数据库中
     *
     * @param uploadfile /
     * @return /
     */
    @PostMapping("/upload/file")
    public ResponseEntry upload(@RequestParam("file") MultipartFile uploadfile) {
        StringBuilder builder = new StringBuilder();
        String fileName = String.valueOf(System.currentTimeMillis()) + ".png";
        builder.append("downFile/").append(fileName);

        ResponseEntry result = new ResponseEntry();
        if (uploadfile.isEmpty()) {
            result.setMessage("文件为空");
            result.setCode(ResponseEntry.DATA_EXCEPTION);
        } else {
            try {
                byte[] bytes = uploadfile.getBytes();
                FileEntity fileEntity = new FileEntity();
                fileEntity.setImg(bytes);
                fileEntity.setPath(builder.toString());
                int index = fileService.insertFile(fileEntity);
                if (index == -1) {
                    result.setMessage("文件上传失败");
                }
                result.setBody(builder.toString());
            } catch (IOException e) {
                result.setMessage(e.getMessage());
                result.setCode(ResponseEntry.DATA_EXCEPTION);
            }

        }
        return result;
    }

    /**
     * 下载文件 例如apk包 当然该示例是从数据中获取资源进行下载
     * 请求示例：http://192.168.15.163:8080/downFile/1513741136695.png
     */
    @RequestMapping(value = "/downFile/{path}", method = RequestMethod.GET)
    public void downFile(@PathVariable String path, HttpServletResponse response, HttpServletRequest request) {
        String url = request.getRequestURI();
        FileEntity entity = null;
        String path1 = path;//打印：1513741136695
        url = url.substring(url.indexOf("/") + 1, url.length());
        entity = fileService.selectFile(url);
        if (null != entity) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + path + ".png");// 设置文件名
            response.setContentType("application/octet-stream");
            OutputStream os = null;
            InputStream in = null;
            try {
                os = response.getOutputStream();
                //把字节数组转为流
                in = new ByteArrayInputStream(entity.getImg());
                byte[] temp = new byte[1024];
                int hasRead = -1;
                while ((hasRead = in.read(temp)) != -1) {
                    os.write(temp, 0, hasRead);
                    os.flush();
                }
                os.flush();
                os.close();
                in.close();
            } catch (IOException e) {
                logger.debug(e.getMessage());
            } finally {
                try {
                    if (null != os) {
                        os.close();
                    }
                    if (null != in) {
                        in.close();
                    }
                } catch (IOException e1) {
                    logger.debug(e1.getMessage());
                }
            }

        }
    }


    /**
     * 下载图片，返回图像
     *
     * @param path
     * @param response
     * @param request  请求示例：http://192.168.15.165:8080/imgs/1513741136695.png
     */
    @RequestMapping(value = "/imgs/{path}")
    public void downImg(@PathVariable String path, HttpServletResponse response, HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        builder
                .append("downFile/")
                .append(path)
                .append(".png");
        FileEntity entity = null;
        entity = fileService.selectFile(builder.toString());
        if (null != entity) {
            //设置返回类型为图像
            response.setContentType("image/png");
            try {
                OutputStream os = response.getOutputStream();
                os.write(entity.getImg());
                os.flush();
                os.close();
            } catch (IOException e) {
                logger.debug(e.getMessage());
            }

        }
    }


}
