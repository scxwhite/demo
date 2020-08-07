package com.thrcat.controller;

import com.thrcat.annotion.AdminCheck;
import com.thrcat.annotion.UncheckLogin;
import com.thrcat.enums.RoleType;
import com.thrcat.model.JsonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:
 *
 * @author scx
 * @create 2020/08/06
 */
@RestController
@RequestMapping("/echo/")
public class HelloController extends BaseController {


    /**
     * 请求 http://localhost:8080/echo/hello 进行登陆校验
     * {
     * "code": 20003,
     * "data": null,
     * "success": false,
     * "message": "未登录"
     * }
     *
     * @return
     */
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public JsonResponse hello() {
        return success("hello");
    }

    /**
     * 请求 http://localhost:8080/echo/hello1 不进行登陆校验
     * {
     * "code": 20000,
     * "data": "hello1",
     * "success": true,
     * "message": "查询成功"
     * }
     *
     * @return
     */
    @RequestMapping(value = "hello1", method = RequestMethod.GET)
    @UncheckLogin
    public JsonResponse hello1() {
        return success("hello1");
    }

    /**
     * 请求超级管理员接口 http://localhost:8080/echo/super
     * {
     * "code": 20002,
     * "data": null,
     * "success": false,
     * "message": "无权限访问SUPER_ADMIN接口"
     * }
     */
    @RequestMapping(value = "super", method = RequestMethod.GET)
    @AdminCheck(roleType = RoleType.SUPER_ADMIN)
    @UncheckLogin
    public JsonResponse superAdminCheck() {
        return success();
    }

    /**
     * 请求管理员接口 http://localhost:8080/echo/manager
     * {
     * "code": 20002,
     * "data": null,
     * "success": false,
     * "message": "无权限访问MANAGER接口"
     * }
     */
    @RequestMapping(value = "manager", method = RequestMethod.GET)
    @AdminCheck(roleType = RoleType.MANAGER)
    @UncheckLogin
    public JsonResponse managerCheck() {
        return success();
    }

    /**
     * 请求用户接口 http://localhost:8080/echo/user
     * {
     * "code": 20002,
     * "data": null,
     * "success": false,
     * "message": "无权限访问USER接口"
     * }
     *
     */
    @RequestMapping(value = "user", method = RequestMethod.GET)
    @AdminCheck(roleType = RoleType.USER)
    @UncheckLogin
    public JsonResponse userCheck() {
        return success();
    }
}
