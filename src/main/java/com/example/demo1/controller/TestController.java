package com.example.demo1.controller;

import com.example.demo1.model.User;
import com.example.demo1.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestController {
    @Autowired
    private TestRepository repository;

    private User user = new User();

//    @RequestMapping(path = "/register")
//    public List<User> register(){
//        return repository.findAll();
//    }

    @RequestMapping("/index")
    public String index(HttpSession httpSession){
        httpSession.getAttribute("userLogin");
        return "index";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/login")
    public String login(){
        String str = "";
        String name = user.getName();
        if(name!=null){
            str = "index";
        }else {
            str="login";
        }
        return str;
    }

    @RequestMapping("/uregister")
    public String register(@RequestParam(value = "name",required = false) String name, @RequestParam(value = "password", required = false) String password,
                            @RequestParam(value = "password2",required = false) String password2,@RequestParam(value = "email",required = false) String email,
                            @RequestParam(value = "tel",required = false) String tel,@RequestParam(value = "gender",required = false) String gender){
            String str = "";
            if(password.equals(password2)){
                password = DigestUtils.md5DigestAsHex(password.getBytes());
                user = repository.findByName(name);
                if(user == null){
                    User user = new User();
                    user.setName(name);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setTel(tel);
                    user.setGender(gender);
                    repository.save(user);
                    return "注册成功";
                }else {
                    return  "该用户名已被注册过！！";
                }
            }else {
                return "两次密码不相同，注册失败";
            }
    }

    @RequestMapping("/ulogin")
    public String login(HttpSession httpSession,@RequestParam("name") String name,@RequestParam("password") String password){
        user = repository.findByname(name);
        String str = "";
        if(user==null){
            str = "该用户不存在";
        }else if(!password.equals(user.getPassword())){
            str="密码错误！";
        }else{
            httpSession.setAttribute("userlogin",user);
            str="登录成功！";
        }
        return str;
    }

    @RequestMapping("/test")
    public String test(){
        String str="";
        str= repository.findUserByEmail("111");
        return str;
    }
}
