package com.example.demo.controler;

import com.example.demo.model.dataList;
import com.example.demo.services.dataListServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class dataListControler {

    @Autowired
    private dataListServices datalistservices;
    @RequestMapping("/")
    public String getPage(){
        return "layout";
    }


    @RequestMapping(method = RequestMethod.GET,value = "/index")
    public ArrayList<dataList> getdata(Model model){
        ArrayList<dataList> data=datalistservices.getdata();
        model.addAttribute("datalist",data);
                return data;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/creates")
    public String getCreate(){
        return "create";
    }
    @RequestMapping(method = RequestMethod.POST,value = "/create")
    public String createNewPost(dataList newPost)
    {
        datalistservices.createPost(newPost);
        return "redirect:/index";
    }
    @RequestMapping(method = RequestMethod.DELETE,value = "/delete")
    public String deletePost(@RequestParam(name = "postId") Integer postId){
        datalistservices.deletePost(postId);
        return "redirect:/index";
    }
    @RequestMapping(method = RequestMethod.GET,value = "/edit")
    public String editPost(@RequestParam(name = "postId") Integer postId,Model model){
        dataList data = datalistservices.getPost(postId);
        model.addAttribute("datalist",data);
        return "edit";
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/edit")
    public String editPostSubmit(@RequestParam(name = "postId") Integer postId,dataList updatedPost){
        updatedPost.setId(postId);
        datalistservices.updatedPost(updatedPost);
        return "redirect:/index";
    }
    @GetMapping("/search")
    public String getProduct(Model model, @ModelAttribute("myFormObject") dataList myFormObject, BindingResult result) {
        ArrayList<dataList> listdata = this.datalistservices.getsearchdata(myFormObject.getFullName());
        model.addAttribute("datalist", listdata);
        return "redirect:/index";
    }
}
