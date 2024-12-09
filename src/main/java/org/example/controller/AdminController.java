package org.example.controller;

import com.github.pagehelper.PageInfo;
import org.example.entity.Admin;
import org.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/login.do")
    public Integer login(String adminAccount, String password, HttpSession session){
        return adminService.login(adminAccount, password,session);
    }

    @RequestMapping("/addAdmin.action")
    public Integer addAdmin(Admin admin){
        return adminService.addAdmin(admin);
    }

    @RequestMapping("/getCurrentInfo.action")
    public Admin getCurrentInfo(HttpSession session){
        //方案一：从数据库获取  信息实时准确，但慢
        Admin admin = (Admin)session.getAttribute("admin");
        return adminService.getCurrentInfo(admin.getId());
        //方案二：从会话对象session中获取   信息不实时准确，快（可以更改信息后强制重新登录以更新session
//        Admin admin = (Admin)session.getAttribute("admin");
//        return admin;
    }

    @RequestMapping("/updatePassword.action")
    public Integer updatePassword(String oldpass,String newpass,HttpSession session){
        return adminService.updatePassword(oldpass,newpass,session);
    }

    @RequestMapping("/fileUpload.action")
    public String fileUpload(@RequestParam("picture") MultipartFile file,HttpSession session)
            throws IOException {
        Admin admin=(Admin)session.getAttribute("admin");
        File f=new File("F:\\ShiXunResourses\\"+admin.getAdminAccount()+".jpg");
        file.transferTo(f);
        return admin.getAdminAccount()+".jpg";
    }


    @RequestMapping("/updateCurrentAdmin.action")
    public Integer updateAdmin(Admin admin)  {
        return adminService.updateCurrentAdmin(admin);
    }


    @RequestMapping("/searchAllAdmin.action")
    public PageInfo<Admin> searchAllAdmin(Integer pageIndex)  {
        return adminService.searchAllAdmin(pageIndex);
    }

    @RequestMapping("/delAdminById.action")
    public Integer delAdminById(Integer id)  {
        return adminService.delAdminById(id);
    }

    @RequestMapping("/updateAdminStatus.action")
    public Integer updateAdminStatus(Integer id,Integer status)  {
        return adminService.updateAdminStatus(id,status);
    }

    @RequestMapping("/getAdminById.action")
    public Admin getCurrentInfo(Integer id)  {
        return adminService.getCurrentInfo(id);
    }

    @RequestMapping("/delAdmins.action")
    public Integer delAdmins(String ids)  {
//        String[] idArr = ids.split(",");
//        for (String id : idArr) {
//            adminService.delAdminById(Integer.parseInt(id));
//        }
        return adminService.delAdmins(ids);
    }

    @RequestMapping("/logout.action")
    public Integer logout(HttpSession session)  {
        session.removeAttribute("admin");
        session.invalidate();
        return 1;
    }

}

