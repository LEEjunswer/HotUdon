package com.HotUdon.mapper;

import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.model.Register;

public class RegisterMapper {

    public static RegisterDTO mapEntityToDTO(Register register){
        if(register == null){
            return  null;
        }
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setId(register.getId());
        registerDTO.setPowerRegister(register.getPowerRegister());
        registerDTO.setInfo(register.getInfo());
        registerDTO.setPrice(register.getPrice());
        registerDTO.setTitle(register.getTitle());
        registerDTO.setSellerLocation(register.getSellerLocation());
        registerDTO.setProductStatus(register.getProductStatus());
        registerDTO.setProductMethod(register.getProductMethod());
        registerDTO.setMemberDTO(MemberMapper.mapEntityToDTO(register.getMember()));
        registerDTO.setAuctionDTO(AuctionMapper.mapEntityToDTO(register.getAuction()));
        return registerDTO;
    }
    public static Register mapDtoToEntity(RegisterDTO registerDTO){
        if(registerDTO == null){
             return  null;
        }
        Register register = new Register();
        register.setId(registerDTO.getId());
        register.setPowerRegister(registerDTO.getPowerRegister());
        register.setInfo(registerDTO.getInfo());
        register.setPrice(registerDTO.getPrice());
        register.setTitle(registerDTO.getTitle());
        register.setSellerLocation(registerDTO.getSellerLocation());
        register.setAuction(AuctionMapper.mapDtoToEntity(registerDTO.getAuctionDTO()));
        register.setMember(MemberMapper.mapDtoToEntity(registerDTO.getMemberDTO()));
        register.setProductMethod(registerDTO.getProductMethod());

        return register;
    }
}
