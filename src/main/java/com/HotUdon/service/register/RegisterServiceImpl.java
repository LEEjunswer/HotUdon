package com.HotUdon.service.register;

import com.HotUdon.dto.AuctionDTO;
import com.HotUdon.dto.MemberDTO;
import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.mapper.AuctionMapper;
import com.HotUdon.mapper.FileMapper;
import com.HotUdon.mapper.MemberMapper;
import com.HotUdon.mapper.RegisterMapper;
import com.HotUdon.model.Auction;
import com.HotUdon.model.Member;
import com.HotUdon.model.Register;
import com.HotUdon.repository.auction.AuctionRepository;

import com.HotUdon.repository.register.RegisterRepository;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService{

    private final RegisterRepository registerRepository;
    private final AuctionRepository auctionRepository;


    @Override
    @Transactional
    public Long save(RegisterDTO registerDTO, Member member) {
        // Register 객체 생성
        Register register = Register.builder()
                .price(registerDTO.getPrice())
                .powerRegister(registerDTO.getPowerRegister())
                .info(registerDTO.getInfo())
                .title(registerDTO.getTitle())
                .sellerLocation(registerDTO.getSellerLocation())
                .productMethod(registerDTO.getProductMethod())
                .productStatus(registerDTO.getProductStatus())
                .auctionCheck(registerDTO.isAuctionCheck())
                .member(member)
                .build();


        registerRepository.save(register);

        if (registerDTO.isAuctionCheck()) {
            Auction auction = new Auction();
            auction.setEndPrice(register.getPrice());
            auction.setRegister(register);
            auctionRepository.save(auction);
            register.setAuction(auction);
        }
        return register.getId();

    }

    @Override
    public RegisterDTO findById(Long id) {
     Optional<Register> registerOptional = registerRepository.findById(id);
     if(registerOptional.isPresent()){
         Register register = registerOptional.get();
        return RegisterMapper.mapEntityToDTO(register);
     }
     return null;
    }

    @Override
    public Page<RegisterDTO> findBySearchProduct(String search,int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Register> registerPage = registerRepository.searchRegisters(search,pageable);
        return registerPage.map(RegisterMapper::mapEntityToDTO);
    }

    @Override
    public Page<RegisterDTO> getNotSoldOutProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Register> registerPage = registerRepository.getNotSoldOutProduct(pageable);
        return registerPage.map(RegisterMapper :: mapEntityToDTO);
    }

    @Override
    public Page<RegisterDTO> getNotSoldOutProductMyLocation(String location,int page ,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Register> registerPage = registerRepository.getNotSoldOutProductMyLocation(location,pageable);
        return registerPage.map(RegisterMapper :: mapEntityToDTO);
    }


}
