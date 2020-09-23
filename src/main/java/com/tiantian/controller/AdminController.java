package com.tiantian.controller;

import com.github.pagehelper.PageInfo;
import com.tiantian.dto.ResultData;
import com.tiantian.entity.Admin;
import com.tiantian.service.AdminService;
import com.tiantian.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author:Teacher黄
 * @date:Created at 2019/07/04
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;



    /**
     * 员工列表
     * @return
     */
    @RequestMapping("/adminList")
    public ResultData list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit){

        PageInfo<Admin> pageInfo = adminService.list(page,limit);



        return ResultData.ok(pageInfo);
    }


    /**
     * 添加员工的方法
     * @param admin
     * @return
     */
    @PostMapping("/addAdmin")
    public ResultData add(Admin admin){
        return adminService.add(admin);
    }

    /**
     * 员工的启用禁用状态
     */
    @PostMapping(value = "/updateStatus")
    public ResultData updateAdminStatus(Integer adminStatus , Integer id){
        return adminService.updateAdminStatus(adminStatus, id);
    }

    /**
     * 编辑员工状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/admin/editStatus")
    @ResponseBody
    public ResultData editStatus(Integer id, Integer status){
        return adminService.updateStatus(id, status);
    }




    @RequestMapping("/admin/search")
    public String searchList(Model model,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "5") Integer pageSize,
                             String keyword){

        PageInfo<Admin> pageInfo = adminService.searchList(page, pageSize, keyword);
        model.addAttribute("list",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);

        return "admin/admin-list";
    }



    /**
     * 员工编辑页面
     * @return
     */
    @RequestMapping("/updateUser")
    public ResultData updateUser(Admin admin){

        return adminService.update(admin);
    }

    /**
     * 更新员工基本信息的方法
     * @param admin
     * @return
     */
    @RequestMapping("/admin/edit")
    @ResponseBody
    public ResultData edit(Admin admin){
        return adminService.update(admin);
    }

    /**
     * 批量删除
     * @return
     */
    @PostMapping ("/deleteAll")
    public ResultData deleteAll(@RequestParam(name = "ids") String ids){
        // 将ids转换成数组
        String[] idArr = ids.split(",");

        return adminService.batchDelete(idArr);
    }

    /**
     * 单个删除工具栏删除的
     * @return
     */
    @PostMapping(value = "/deleteOne")
    public ResultData deleteOne(Integer id){
        return adminService.deleteOne(id);
    }


    @RequestMapping("/admin/upload")
    @ResponseBody
    public ResultData upload(MultipartFile file){

        return adminService.upload(file);
    }


    /**
     * excel数据的导出
     */
    @RequestMapping("/admin/export")
    public void export(HttpServletResponse response) throws IOException {

        // 查询到所有的数据
        List<Admin> list = adminService.list();

        Workbook workbook = ExcelUtil.exportExcel(list);
        // 告诉浏览器 需要下载文件
        response.setHeader("Content-Disposition","attachment;fileName="+System.currentTimeMillis()+".xlsx");

        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        // 输出表格数据
        workbook.write(outputStream);
        // 关闭资源
        outputStream.close();
        workbook.close();



    }

}
