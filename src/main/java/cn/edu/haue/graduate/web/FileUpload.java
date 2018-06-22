package cn.edu.haue.graduate.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * 处理文件上传
 */

@Controller
public class FileUpload {
    @RequestMapping("test")
    public String test(){
        return "admin/excelImport";
    }

    //处理文件上传
    @RequestMapping(value="uploadDeal", method = RequestMethod.POST)
    public @ResponseBody
    void uploadDeal(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) {
        InputStream inputStream =null;

        try{
//        FileUtil.uploadFile(file.getBytes(),filePath,fileName);
          // 获取 excel 文件 的 输入流对象
          inputStream = file.getInputStream();
        }catch (Exception e){
            e.printStackTrace();
        }

//        List<User> users = ExcelUtil.excelToList(inputStream, new User());
//        for(User user:users){
//            System.out.println(user);
//        }
    }
}
