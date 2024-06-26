package com.HotUdon.controller.Front;

import com.HotUdon.service.file.FileUploadService;
import com.HotUdon.util.AddressParser;
import com.HotUdon.config.oauth.PrincipalDetails;
import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.model.Member;
import com.HotUdon.service.register.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;
    private final FileUploadService fileUploadService;

    private boolean logincheck(@AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails == null){
            return false;
        }
        return true;
    }

    @GetMapping("/addProduct")
    public String addProductForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        if(!logincheck(principalDetails)){
            return "member/loginForm";
        }
        Member member = principalDetails.getMember();
        String addressExtraction = AddressParser.addressExtraction(member.getAddress());
        model.addAttribute("address",addressExtraction);
        model.addAttribute("member",member);
        return "product/addProduct";
    }
    @PostMapping("/addProduct")
    public String addProduct(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam(value = "imageFiles", required = false) List<MultipartFile> multipartFiles, RegisterDTO registerDTO, Model model) throws IOException {
        if(!logincheck(principalDetails)){
            return "member/loginForm";
        }
        Member member = principalDetails.getMember();
       Long content = registerService.save(registerDTO,member);
       if(multipartFiles != null) {
           fileUploadService.saveFiles(multipartFiles, registerDTO);
       }
        return "redirect:/product/content?"+content;
    }
    @GetMapping("/content/{id}")
    public String content(@PathVariable("id")Long id, Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){
      RegisterDTO registerDTO = registerService.findById(id);
        if(logincheck(principalDetails)){
         Member member = principalDetails.getMember();
        model.addAttribute("m",member);
        model.addAttribute("product",registerDTO);
            return "product/content";
        }
        model.addAttribute("product",registerDTO);
        return "product/content";
    }

}
