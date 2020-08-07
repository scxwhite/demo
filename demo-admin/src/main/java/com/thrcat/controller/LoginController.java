package com.thrcat.controller;

import com.thrcat.Constants;
import com.thrcat.User;
import com.thrcat.annotion.UncheckLogin;
import com.thrcat.model.JsonResponse;
import com.thrcat.service.UserService;
import com.thrcat.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * desc:
 *
 * @author scx
 * @create 2020/08/06
 */
@RestController
@Validated
@RequestMapping("/login/")
public class LoginController extends BaseController {


    @Autowired
    private UserService userService;

    /**
     * 方便调试设置为get请求
     * <p>
     * http://localhost:8080/login/in?username=sucx&password=3cat
     * <p>
     * <p>
     * {
     * "code": 20000,
     * "data": "登陆成功",
     * "success": true,
     * "message": "查询成功"
     * }
     *
     * @param response response
     * @param username username
     * @param password password
     * @return JsonResponse
     */
    @RequestMapping(value = "in", method = RequestMethod.GET)
    @UncheckLogin
    public JsonResponse loginIn(HttpServletResponse response,
                                @NotBlank(message = "用户名不允许为空") String username,
                                @NotBlank(message = "密码不允许为空") String password) {
        User user = userService.findUser(username, password);
        if (user == null) {
            return failed("用户或密码错误,请检查");
        }
        Cookie cookie = new Cookie(Constants.TOKEN_NAME, JwtUtils.createToken(username, String.valueOf(user.getId()), user.getDepId() == null ? Constants.DEFAULT_ID : String.valueOf(user.getDepId()), user.getUsername()));
        cookie.setMaxAge(Constants.LOGIN_TIME_OUT);
        cookie.setPath("/");
        response.addCookie(cookie);
        return success("登陆成功");
    }


}
