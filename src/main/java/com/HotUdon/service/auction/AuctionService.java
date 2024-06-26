package com.HotUdon.service.auction;

import com.HotUdon.dto.AuctionDTO;
import com.HotUdon.dto.RegisterDTO;

public interface AuctionService {
    AuctionDTO save(RegisterDTO registerDTO);
}
