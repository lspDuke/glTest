package com.guli.edu.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Admin.
 * @date 2020/5/8
 */
@Api(description="讲师管理")
@CrossOrigin//跨域
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherAdminController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    public R list(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }
    @ApiOperation(value = "跟据讲师ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name="id", value = "讲师ID",required = true)
            @PathVariable String id){
         teacherService.removeById(id);
         return R.ok();
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<Teacher> pageParam = new Page<>(page, limit);

        teacherService.page(pageParam, null);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }
}
