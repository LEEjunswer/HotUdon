package com.HotUdon.controller.API;

import com.HotUdon.config.oauth.PrincipalDetails;
import com.HotUdon.dto.NotificationDTO;
import com.HotUdon.model.Member;
import com.HotUdon.service.notification.NotificationService;
import com.HotUdon.service.register.RegisterService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/notification-api")
public class NotificationApiController {

    private final NotificationService notificationService;

    private boolean logincheck(@AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails == null){
            return false;
        }
        return true;
    }

    @PostMapping("/dibs")
    public ResponseEntity<Map<String,String>> productDibs(@AuthenticationPrincipal PrincipalDetails principalDetails
                 , @RequestParam Long registerId, HttpSession session){

        if(!logincheck(principalDetails)){
                return null;
         }
        Map<String,String> response = new HashMap<>();
        Member member = principalDetails.getMember();
      String check = notificationService.dibsProduct(member,registerId);
        switch (check) {
            case "중복" -> {
                response.put("msg", "이미 찜 상품이 등록되어 있습니다.");
                return ResponseEntity.ok(response);
            }
            case "본인" -> {
                response.put("fail", "본인 상품에 찜을 하실 수 없습니다.");
                return ResponseEntity.ok(response);
            }
            case "성공" -> {
                response.put("msg", "정상적으로 상품이 찜되었습니다.");
                List<NotificationDTO> myDibsProducts = notificationService.myDibsProducts(member.getId());
                session.setAttribute("myDibs", myDibsProducts.size());
                return ResponseEntity.ok(response);
            }
        }
        response.put("msg","잠시 후에 다시 등록해주세요.");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/undibs")
    public ResponseEntity<Map<String,String>> productUndibs(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam Long registerId, HttpSession session){
        if(!logincheck(principalDetails)){
            return null;
        }
        Map<String,String> response = new HashMap<>();
        Member member = principalDetails.getMember();
       String check = notificationService.undibsProduct(member,registerId);
       if(check.equals("성공")){
           List<NotificationDTO> myDibsProducts = notificationService.myDibsProducts(member.getId());
           session.setAttribute("myDibs", myDibsProducts.size());
           response.put("msg","정상적으로 상품이 찜에서 제거되었습니다. ");
           return ResponseEntity.ok(response);
       }
       response.put("msg","잠시 후에 다시 이용해주세요 ");
       return  ResponseEntity.ok(response);
    }

}
