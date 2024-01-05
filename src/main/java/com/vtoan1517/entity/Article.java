package com.vtoan1517.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@Setter
public class Article extends Base {

    @Column(columnDefinition = "varchar(255) default 'Bài viết không có tiêu đề'")
    private String title;

    @Column
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(name = "featured", columnDefinition = "tinyint default 0")
    private boolean featured;

    @Column(columnDefinition = "bigint default 0")
    private long traffic;

    @ManyToOne
    @JoinColumn(name = "access_id", nullable = false)
    private Access access;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();
}
