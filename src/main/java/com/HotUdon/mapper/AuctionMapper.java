package com.HotUdon.mapper;

import com.HotUdon.dto.AuctionDTO;
import com.HotUdon.model.Auction;

public class AuctionMapper {
    public static Auction mapDtoToEntity(AuctionDTO auctionDTO) {
        if(auctionDTO == null){
            return  null;
        }
        Auction auction = new Auction();
        auction.setId(auctionDTO.getId());
        auction.setEndPrice(auctionDTO.getEndPrice());
        auction.setBidCnt(auctionDTO.getBidCnt());
        auction.setLastBidDate(auctionDTO.getLastBidDate());
        return auction;
    }
    public static AuctionDTO mapEntityToDTO(Auction auction) {
        if(auction == null){
            return  null;
        }
        AuctionDTO auctionDTO = new AuctionDTO();
        auctionDTO.setBidCnt(auction.getBidCnt());
        auctionDTO.setEndPrice(auction.getEndPrice());
        auctionDTO.setLastBidDate(auction.getLastBidDate());
        return auctionDTO;
    }
}
