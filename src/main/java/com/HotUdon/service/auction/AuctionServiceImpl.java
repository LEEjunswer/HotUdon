package com.HotUdon.service.auction;

import com.HotUdon.dto.AuctionDTO;

import com.HotUdon.dto.MemberDTO;
import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.mapper.AuctionMapper;
import com.HotUdon.mapper.RegisterMapper;
import com.HotUdon.model.Auction;
import com.HotUdon.model.Register;
import com.HotUdon.repository.auction.AuctionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService{
        private final AuctionRepository auctionRepository;

    @Override
    @Transactional
    public AuctionDTO save(RegisterDTO registerDTO) {
        Register register = RegisterMapper.mapDtoToEntity(registerDTO);
        Auction auction = Auction.builder()
                        .register(register)
                .endPrice(register.getPrice())
                .build();
        auction =auctionRepository.save(auction);
        return AuctionMapper.mapEntityToDTO(auction);
    }



}
