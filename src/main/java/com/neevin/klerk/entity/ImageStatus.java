package com.neevin.klerk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "image_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ImageStatusEnum status;

    @ManyToOne
    private Article article;

    public ImageStatus(String uuid, ImageStatusEnum status, Article article) {
        this.uuid = uuid;
        this.status = status;
        this.article = article;
    }

}
