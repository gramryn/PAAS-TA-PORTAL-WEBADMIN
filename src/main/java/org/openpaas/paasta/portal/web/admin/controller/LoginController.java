package org.openpaas.paasta.portal.web.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Login Controller
 *
 * @author nawkm
 * @version 1.0
 * @since 2016.4.4 최초작성
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);



    /**
     * 로그인 화면
     *
     * @param error   the error
     * @param logout  the logout
     * @param locale  the locale
     * @param request the request
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout,
                                  Locale locale, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        if (error != null) {
            mv.addObject("error", "아이디 또는 비밀번호가 맞지 않습니다.");
        }

        if (logout != null) {
            mv.addObject("message", "성공적으로 로그아웃 되었습니다.");
        }

        LOGGER.info("ROLE_ADMIN : " + request.isUserInRole("ROLE_ADMIN"));
        if(!request.isUserInRole("ROLE_ADMIN")){
            mv.setViewName("/index");
        }else {
//            mv.setViewName("redirect:/main");
            mv.setViewName("redirect:/dashboard");
        }
        return mv;
    }


    /**
     * 로그인처리 후 첫 화면
     *
     * @return ModelAndView model
     */
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView homePage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if (!request.isUserInRole("ROLE_ADMIN")) {
            mv.setViewName("redirect:/index");
        } else {
            mv.setViewName("/main/main");
        }

        return mv;
    }


}
