package com.HotUdon.dto;


import com.HotUdon.model.Auction;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuctionDTO {

    private Long id;

    private int endPrice;

    private int bidCnt;

    private boolean auctionCheck;

    private LocalDateTime lastBidDate;

    private RegisterDTO registerDTO;



}
