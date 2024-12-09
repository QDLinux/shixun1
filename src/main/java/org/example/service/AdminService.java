package org.example.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.Admin;
import org.example.mapper.AdminMapper;
import org.example.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


/*
 * 登录
 * 先根据账号查询，如果查询得到数据，则证明账号正确，否则账号错误
 *在根据查询得到的密码对比参数密码，如果一直，则密码正确，否则密码错误
 * 返回值：
 * 0账号错误，
 * 1密码错误，
 * 2账号被禁用，
 * 3登录成功
 * 4当日密码次数达到上限，请明日再试
 *
 * */
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Value("${passwordErrorCount}")
    private Integer passwordErrorCount;
    public Integer login(String adminAccount, String password, HttpSession session){
        Admin admin = adminMapper.getAdminByAdminAccount(adminAccount);
        //Admin admin = adminMapper.getAdminByAdminAccount(adminAccount);
        if(admin == null){
            System.out.println("账号不正确");
            return 0;
        }
        if(admin.getErrorCount()>passwordErrorCount){
            System.out.println("当日密码次数达到上限，请明日再试");
            return 4;
        }
        if(!admin.getPassword().equals(MD5Utils.getPwd(password))){
            System.out.println("密码不正确");
            //错误参数
            adminMapper.updatePasswordErrorCount(admin.getId());
            return 1;
        }
        if(admin.getStatus() != 0){
            System.out.println("账号被管理员禁用");
            return 2;
        }
        //登录成功后，记载标记在会话对象中
        session.setAttribute("admin",admin);
        return 3;
    }
    /*
    * 管理员添加
    * 2账号存在
    * 0注册失败
    * 1注册成功
    * */
    public Integer addAdmin(Admin admin){
        Admin a = adminMapper.getAdminByAdminAccount(admin.getAdminAccount());
        //Admin admin = adminMapper.getAdminByAdminAccount(adminAccount);
        if(a != null){
            System.out.println("对不起，账号已存在");
            return 2;
        }
        admin.setPassword(MD5Utils.getPwd(admin.getPassword()));//加密
        return adminMapper.addAdmin(admin) ;
    }

    /*修改密码
    2为原密码错误
    0为修改失败
    1为修改成功
    * */
    public Integer updatePassword(String oldpass,String newpass,HttpSession session){
        Admin admin=(Admin)session.getAttribute("admin");
        if(!admin.getPassword().equals(MD5Utils.getPwd(oldpass))){
            return 2;
        }
        Integer result = adminMapper.updatePassword(admin.getId(),MD5Utils.getPwd(newpass));
        if(result == 1){
//            admin.setPassword(MD5Utils.getPwd(newpass));
//            session.setAttribute("admin",admin);
            session.removeAttribute("admin");
        }
        return result;
    }

    public Admin getCurrentInfo(Integer id){
        return adminMapper.getCurrentInfo(id);
    }

    public Integer updateCurrentAdmin(Admin admin) {
        return adminMapper.updateCurrentAdmin(admin);
    }

    @Value("${pageSize}")
    private Integer pageSize;
    public PageInfo<Admin> searchAllAdmin(Integer pageIndex)  {
        PageHelper.startPage(pageIndex, pageSize);//执行自动帮助分页
        List<Admin> adminlist = adminMapper.searchAllAdmin();
        return new PageInfo<Admin>(adminlist);
    }

    public Integer delAdminById(Integer id)  {
        return adminMapper.delAdminById(id);
    }

    public Integer updateAdminStatus(Integer id,Integer status)  {
        return adminMapper.updateAdminStatus(id,status);
    }

    public Integer delAdmins(String ids)  {
        return adminMapper.delAdmins(ids);
    }

}