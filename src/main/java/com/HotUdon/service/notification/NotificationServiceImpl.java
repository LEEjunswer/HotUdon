package com.HotUdon.service.notification;

import com.HotUdon.dto.NotificationDTO;
import com.HotUdon.mapper.NotificationMapper;
import com.HotUdon.model.Member;
import com.HotUdon.model.Notification;
import com.HotUdon.model.Register;
import com.HotUdon.repository.member.MemberRepository;
import com.HotUdon.repository.notification.NotificationRepository;
import com.HotUdon.repository.register.RegisterRepository;
import com.HotUdon.service.register.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;
    private final RegisterRepository registerRepository;

    @Override
    public String dibsProduct(Member member, Long registerId) {
        Optional<Notification>  notificationOptional =notificationRepository.findByRegisterId(registerId);
        if(notificationOptional.isPresent()){
            return "중복";
        }
        Optional<Register> registerOptional =   registerRepository.findById(registerId);
       if(registerOptional.isPresent()){
          Register register =registerOptional.get();
          if(Objects.equals(register.getMember().getId(), member.getId())){
            return "본인";
          }
           NotificationDTO notification = new NotificationDTO();
           notification.setContent(register.getTitle() +" 찜이 등록되었습니다.");
           notification.setDibs(true);
           Notification savedNotification = notificationRepository.save(NotificationMapper.mapDtoToEntity(notification,register,member));
           return "성공";
       }

        return null;
    }

    @Override
    public String undibsProduct(Member member, Long registerId) {
        Optional<Notification>  notificationOptional = notificationRepository.findByRegisterId(registerId);
        if(notificationOptional.isPresent()){
            System.out.println("진입체크하자");
            Notification notification = notificationOptional.get();
            notificationRepository.deleteById(notification.getId());
            return "성공";
        }
        return null;
    }

    @Override
    public List<NotificationDTO> myDibsProducts(Long memberId) {
        return notificationRepository.findByMemberIdAndDibsTrue(memberId).stream().map(NotificationMapper :: mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> findByRegisterIdInAndMemberId(List<Long> registerIds, Long memberId) {
        return notificationRepository.findByRegisterIdInAndMemberIdAndDibsTrue(registerIds,memberId).stream().map(NotificationMapper ::mapEntityToDto).collect(Collectors.toList());
    }
}
