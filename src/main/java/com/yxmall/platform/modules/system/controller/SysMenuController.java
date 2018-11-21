package com.yxmall.platform.modules.system.controller;


import com.yxmall.platform.common.constant.CommonConstant;
import com.yxmall.platform.common.exception.BaseException;
import com.yxmall.platform.common.utils.Result;
import com.yxmall.platform.common.validator.ValidatorUtils;
import com.yxmall.platform.common.validator.group.AddGroup;
import com.yxmall.platform.common.validator.group.UpdateGroup;
import com.yxmall.platform.modules.system.entity.SysMenu;
import com.yxmall.platform.modules.system.service.SysMenuService;
import com.yxmall.platform.modules.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单管理(SysMenu)表控制层
 *
 * @author qing.wang.o
 * @since 2018-09-27 10:14:05
 */
@RestController
@RequestMapping("menu")
@Api(value = "系统菜单接口", description = "包含系统功能菜单获取")
public class SysMenuController extends AbstractController {

    @Autowired
    private SysMenuService sysMenuService;


    @GetMapping("/list")
    @ApiOperation(value = "获取菜单树列表", notes = "获取菜单树列表包含功能权限")
    private List<SysMenu> getMenuTreeList() {
        return sysMenuService.getMenuTreeList();
    }

    @DeleteMapping("/delete/{id:\\d+}")
    @ApiOperation(value = "删除菜单", notes = "根据ID删除菜单")
    private Result deleteMenu(@PathVariable(name = "id") Long id) {
        return sysMenuService.deleteMenuById(id);
    }

    @GetMapping("/get/{id:\\d+}")
    @ApiOperation(value = "获取菜单信息", notes = "根据ID获取菜单信息")
    private SysMenu getMenuInfo(@PathVariable(name = "id") Long id) {
        return sysMenuService.getById(id);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "修改菜单", notes = "根据ID修改菜单")
    private Result updateMenu(@RequestBody SysMenu sysMenu) {
        validateMenu(sysMenu);
        boolean flag = sysMenuService.updateById(sysMenu);
        return Result.isEditSuccess(flag);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    private Result addMenu(@RequestBody SysMenu sysMenu) {
        validateMenu(sysMenu);
        boolean flag = sysMenuService.save(sysMenu);
        return Result.isAddSuccess(flag);
    }


    /**
     * 验证菜单是否符合逻辑
     */
    private void validateMenu(SysMenu menu) {
        if(StringUtils.isBlank(menu.getTitle())){
            throw new BaseException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new BaseException("上级菜单不能为空");
        }

        //菜单
        if(menu.getType() == CommonConstant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getPath())) {
                throw new BaseException("菜单URL不能为空");
            }
        }
            //上级菜单类型
        int parentType = CommonConstant.MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }
        //目录、菜单
        if(menu.getType() == CommonConstant.MenuType.CATALOG.getValue() ||
                menu.getType() == CommonConstant.MenuType.MENU.getValue()){
            if(parentType != CommonConstant.MenuType.CATALOG.getValue()){
                throw new BaseException("上级菜单只能为目录类型");
            }
            return ;
        }

        //按钮
        if(menu.getType() == CommonConstant.MenuType.BUTTON.getValue()){
            if(parentType != CommonConstant.MenuType.MENU.getValue()){
                throw new BaseException("上级菜单只能为菜单类型");
            }
            return ;
        }
    }
}