package com.HotUdon.controller.Front;

import com.HotUdon.dto.FileUploadDTO;
import com.HotUdon.service.file.FileUploadService;
import com.HotUdon.util.AddressParser;
import com.HotUdon.config.oauth.PrincipalDetails;
import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.model.Member;
import com.HotUdon.service.register.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
/*    private final */

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
       registerDTO.setId(content);
       if(multipartFiles != null) {
           fileUploadService.saveFiles(multipartFiles, registerDTO,member.getLoginId());
       }
        return "redirect:/product/content/"+content;
    }
    @GetMapping("/search")
    public String search(@RequestParam String search,Model model, @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size){
        /*이걸 준 이유는 @AuthenticationPrincipal PrincipalDetails principalDetails 사용할 경우 로그인을 해야지 이용이 가능하다..*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Page<RegisterDTO>  registerDTOPages=registerService.findBySearchProduct(search,page,size);
        model.addAttribute("products",registerDTOPages);
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Member member = principalDetails.getMember();
            model.addAttribute("m",member);
            return "product/sellList";
        }
        return "product/sellList";
    }
    @GetMapping("/content/{id}")
    public String content(@PathVariable("id")Long id, Model model){
      RegisterDTO registerDTO = registerService.findById(id);
        System.out.println("registerDTO.getFiles() = " + registerDTO.getFiles());
      List<FileUploadDTO> fileUploadDTOList = fileUploadService.findAllByRegisterId(registerDTO.getId());
        model.addAttribute("files",fileUploadDTOList);
        model.addAttribute("product",registerDTO);
        return "product/content";
    }

}
