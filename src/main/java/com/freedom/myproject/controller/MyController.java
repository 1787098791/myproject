package com.freedom.myproject.controller;

import com.freedom.myproject.po.ComplaintMessage;
import com.freedom.myproject.po.RemindMessage;
import com.freedom.myproject.service.UtilsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class MyController {

    static String UPLOAD_PATH = "/static/upload/";

    /**
     * wangEditor的Controller
     * @param editorFile
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile editorFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();

        // 获取文件后缀
        String fileName = editorFile.getOriginalFilename();
        //System.out.println(fileName+"23333");
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));

        // 文件存放路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        System.out.println(filePath);
        InetAddress ia=null;
        try {
            ia = InetAddress.getLocalHost();
            //System.out.println(ia.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // 判断路径是否存在，不存在则创建文件夹
        //目标文件夹创建
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //目标文件创建
        // 将文件写入目标
        file = new File(filePath, UUID.randomUUID() + fileSuffix);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            editorFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取服务端路径
        String serverPath = String.format("%s://%s:%s%s%s", request.getScheme(), ia.getHostAddress(), request.getServerPort(), request.getContextPath(), UPLOAD_PATH);
        //System.out.println(request.getContextPath()+"233333");
        // 返回给 wangEditor 的数据格式
        result.put("errno", 0);
        result.put("data", new String[]{serverPath + file.getName()});
        return result;
    }

    @Autowired
    UtilsService  utilsService;
    /**
     * 使用wangEditor发表评论入库的Controller(如果字符串太长，数据库的类型要设置为tinytext,text,mediumtext,longtext)
     * @param complaintMessage
     * @return
     */
    @ResponseBody
    @RequestMapping("saveComplaintMessage")
    /*注意，这里从前端传过来的数据必须要用JSON的形式，若用别的形式会导致带有HTML标签的字符串无法映射成功*/
    public Object save(@RequestBody ComplaintMessage complaintMessage){
        int i = utilsService.insertComplaintMessage(complaintMessage);
        if (i==1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 用户帐户余额提醒
     * @param remindMessage
     * @return
     */
    @ResponseBody
    @RequestMapping("remindMessageInside")
    public Object remindMessageInside(@RequestBody RemindMessage remindMessage){
        int i = utilsService.insertRemindMessage(remindMessage);
        if (i==1){
            return true;
        }else{
            return false;
        }
    }



}