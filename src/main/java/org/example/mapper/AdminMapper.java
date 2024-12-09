package org.example.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.*;
import org.example.entity.Admin;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface AdminMapper {
    public Admin getAdminByAdminAccount(String adminAccount);
    public Integer addAdmin(Admin admin);

    @Update("update t_admin set errorCount=errorCount+1 where id=#{id}")
    public Integer updatePasswordErrorCount(Integer id);

    @Update("update t_admin set errorCount=0")
    public Integer initPasswordErrorCount();

    @Update("update t_admin set password=#{password} where id=#{id}")
    public Integer updatePassword(@Param("id") Integer id,@Param("password") String newpass);

    public Admin getCurrentInfo(Integer id);

    public Integer updateCurrentAdmin(Admin admin);

    public List<Admin> searchAllAdmin();

    @Delete("delete from t_admin where id=#{id}")
    public Integer delAdminById(Integer id);

    @Update("update t_admin set status=#{status} where id=#{id}")
    public Integer updateAdminStatus(Integer id,Integer status);

    @Delete("delete from t_admin where id in (${ids})")
    public Integer delAdmins(String ids);
}

