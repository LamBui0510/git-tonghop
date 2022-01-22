package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;
import com.example.demo.validate.ValidateUserName;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    ValidateUserName validateUserName;

    @Value("${uploadPart}")
    private String uploadPart;


    @GetMapping("/user")
    public ModelAndView showAll(@RequestParam(defaultValue ="0") int page ){
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("user", iUserService.findAll(PageRequest.of(page,2)));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("role", iRoleService.findAll());
        return modelAndView;
    }
    @PostMapping("create")
    public Object add(@Valid @ModelAttribute(value = "user") User user, BindingResult bindingResult,@RequestParam MultipartFile upImg) {
        validateUserName.validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }
        String filename = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(),new File(uploadPart+"img/" + filename));
            user.setAvatar("img/" +filename);
            iUserService.save(user);

        } catch (IOException e) {
            user.setAvatar("https://image.lag.vn/upload/news/20/11/18/cosplay-nezuko-phien-ban-dam-phat-chet-luon-3_RGKE.jpg");
            iUserService.save(user);
            e.printStackTrace();
        }
        return "redirect:/user";
    }
    @GetMapping ("/delete")
    public ModelAndView delete(@RequestParam long id){
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("user" , iUserService.findById(id) );
        return modelAndView;
    }
    @PostMapping ("/delete")
    public String deleteAlert(@RequestParam long id){
        iUserService.delete(id);
        return "redirect:/user";
    }
    @GetMapping("/edit")
    public ModelAndView showEdit(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("user", iUserService.findById(id));
        modelAndView.addObject("role", iRoleService.findAll());
        return modelAndView;
    }
    @PostMapping("edit")
    public Object editUser(@Valid @ModelAttribute(value = "user") User user, BindingResult bindingResult, @RequestParam MultipartFile upImg) {
        validateUserName.validate(user,bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("edit");
            return modelAndView;
        }
        if (upImg.getSize() != 0) {
            String file1 = uploadPart + user.getAvatar();
            File file = new File(file1);
            file.delete();
            String nameFile = upImg.getOriginalFilename();
            try {
                FileCopyUtils.copy(upImg.getBytes(), new File(uploadPart +"img/"+ nameFile));
                user.setAvatar("img/" + nameFile);
                iUserService.save(user);
            } catch (IOException e) {
                user.setAvatar("https://image.lag.vn/upload/news/20/11/18/cosplay-nezuko-phien-ban-dam-phat-chet-luon-3_RGKE.jpg");
                iUserService.save(user);
                e.printStackTrace();
            }

        }
        return "redirect:/user";
    }

    @PostMapping("/search")
    public ModelAndView searchByName(@RequestParam String search){
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("user", iUserService.searchByName(search));
        return modelAndView;
    }
    @GetMapping("/sortBy")
    public ModelAndView sortByUserName(){
        ModelAndView modelAndView = new ModelAndView("show");
        List<User> userList = iUserService.sortByUserName();
        modelAndView.addObject("user",userList);
        return modelAndView;
    }
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam long id){
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("user",iUserService.findById(id));
        return modelAndView;
    }


}
