package com.HotUdon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    @Column(name="category_id",nullable = false)
    private Long id;
    
    @Comment("상품 카테고리 대분류")  // 밑에랑 어떻게 줘야될지 고민
    private String CategoryBigName;

    @Comment("상품 카테고리 중분류")
    private String CategoryMiddleName;
                
    @Comment("상품태그 만들기")
    @ElementCollection //배열 준 이유는 #을 강제적으로 줘서 # 배열 하나씩 추가할 예정 
    private List<String> productTag;
}
