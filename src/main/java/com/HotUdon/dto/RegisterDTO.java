package com.HotUdon.dto;



import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterDTO {

    private Long Id;

    private String title;

    private int price;

    private String info;

    private String sellerLocation;

    private String powerRegister;

    private int productStatus;

    private String productMethod;

    private boolean auctionCheck;
    
    private AuctionDTO auctionDTO;
    private MemberDTO memberDTO;

    private List<FileUploadDTO> files = new ArrayList<>();
}
