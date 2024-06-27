package com.HotUdon.mapper;

import com.HotUdon.dto.NotificationDTO;
import com.HotUdon.model.Member;
import com.HotUdon.model.Notification;
import com.HotUdon.model.Register;

public class NotificationMapper {

    public static NotificationDTO mapEntityToDto(Notification notification){
        if(notification == null){
            return  null;
        }
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(notification.getId());
        notificationDTO.setDibs(notification.isDibs());
        notificationDTO.setContent(notification.getContent());
        notificationDTO.setMemberId(notification.getMember().getId());
        notificationDTO.setRegisterId(notification.getRegister().getId());
        notificationDTO.setRegDate(notification.getRegDate());
        return notificationDTO;
    }
    public static Notification mapDtoToEntity(NotificationDTO notificationDTO, Register register, Member member){
        if(notificationDTO == null){
            return null;
        }
        Notification notification =new Notification();
        notification.setId(notificationDTO.getId());
        notification.setContent(notificationDTO.getContent());
        notification.setDibs(notificationDTO.isDibs());
        notification.setRegDate(notificationDTO.getRegDate());
        notification.setMember(member);
        notification.setRegister(register);
        return notification;
    }
}
