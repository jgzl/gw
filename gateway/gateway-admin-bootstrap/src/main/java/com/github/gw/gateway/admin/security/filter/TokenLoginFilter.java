package com.github.gw.gateway.admin.security.filter;

import com.alibaba.fastjson.JSON;
import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.github.gw.common.core.constant.SecurityConstants;
import com.github.gw.common.core.exception.CaptchaException;
import com.github.gw.common.core.utils.JacksonUtil;
import com.github.gw.gateway.admin.system.vo.LoginUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * token登陆模块
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/24
 */
@Slf4j
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final CaptchaService captchaService;

    public TokenLoginFilter(AuthenticationManager authenticationManager, CaptchaService captchaService) {
        super(authenticationManager);
        this.captchaService = captchaService;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(SecurityConstants.LOGIN_PATH, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        // 用以支持json登录
        if (req.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || req.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = req.getInputStream()) {
                LoginUserVo authenticationBean = JacksonUtil.getInstance().readValue(is, LoginUserVo.class);
                authRequest = new UsernamePasswordAuthenticationToken(
                        authenticationBean.getUsername(), authenticationBean.getPassword());
            } catch (IOException e) {
                log.error("IO发生异常:", e);
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            } finally {
                setDetails(req, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        } else {
            return super.attemptAuthentication(req, res);
        }
    }

    /**
     * 校验验证码
     *
     * @param request
     */
    private void validate(HttpServletRequest request) {
        String captchaVerification = request.getParameter("captchaVerification");
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(captchaVerification);
        ResponseModel response = captchaService.verification(captchaVO);
        if (response.isSuccess() == false) {
            //验证码校验失败，返回信息告诉前端
            //repCode  0000  无异常，代表成功
            //repCode  9999  服务器内部异常
            //repCode  0011  参数不能为空
            //repCode  6110  验证码已失效，请重新获取
            //repCode  6111  验证失败
            //repCode  6112  获取验证码失败,请联系管理员
            String result = JSON.toJSONString(response);
            log.error("验证码校验失败,返回结果为为:{}", result);
            throw new CaptchaException(result);
        }
    }

}
