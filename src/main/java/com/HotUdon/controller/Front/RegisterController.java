package com.HotUdon.controller.Front;

import com.HotUdon.dto.FileUploadDTO;
import com.HotUdon.dto.NotificationDTO;
import com.HotUdon.service.file.FileUploadService;
import com.HotUdon.service.notification.NotificationService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;
    private final FileUploadService fileUploadService;
    private final NotificationService notificationService;
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
        return "redirect:/register/content/"+content;
    }
    @GetMapping("/search")
    public String search(@RequestParam String search, Model model, @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Page<RegisterDTO> registerDTOPages = registerService.findBySearchProduct(search, page, size);
        model.addAttribute("products", registerDTOPages);

        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            List<Long> registerIds = registerDTOPages.stream()
                    .map(RegisterDTO::getId)
                    .toList();
            Member member = principalDetails.getMember();
            List<NotificationDTO> notificationDTOList = notificationService.findAllByRegisterIdAndMemberId(registerIds, member.getId());

            List<Long> dibsRegisterIds = notificationDTOList.stream()
                    .map(NotificationDTO::getRegisterId)
                    .toList();
            model.addAttribute("dibsCheck", dibsRegisterIds );
            model.addAttribute("m", member);
        } else {
            model.addAttribute("dibsCheck", new ArrayList<>());
        }
        return "product/sellList";
    }
    @GetMapping("/content/{id}")
    public String content(@PathVariable("id")Long id, Model model){
      RegisterDTO registerDTO = registerService.findById(id);
      List<FileUploadDTO> fileUploadDTOList = fileUploadService.findAllByRegisterId(registerDTO.getId());
        model.addAttribute("files",fileUploadDTOList);
        model.addAttribute("product",registerDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Member member = principalDetails.getMember();
           NotificationDTO notificationDTO = notificationService.findByRegisterIdAndMemberId(registerDTO.getId(), member.getId());
           if(notificationDTO != null) {
               System.out.println("값체크 notificationDTO = " + notificationDTO);
               model.addAttribute("dibCheck", notificationDTO.getRegisterId());
           }
            model.addAttribute("m", member);
            return "product/content";
        }else{
            model.addAttribute("dibsCheck", null);
        }
        return "product/content";
    }

}
