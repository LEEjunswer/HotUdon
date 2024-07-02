package com.HotUdon.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Comment;

import jakarta.persistence.*;
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
    @JsonBackReference
    private Register register;
}
