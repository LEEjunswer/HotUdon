package com.HotUdon.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("file Pk")
    @Column(name = "file_id",nullable = false)
    private Long id;

    private String fileName;

    private String filePath;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_id", foreignKey = @ForeignKey(name = "FK_FileUpload_Register"))
    private Register register;
}
