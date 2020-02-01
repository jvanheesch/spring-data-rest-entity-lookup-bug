package com.github.jvanheesch.spring.data.rest.model;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Entity
public class EutrDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @RestResource(exported = false)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "WOOD_COMPOSITION_ID")
    private WoodComposition woodComposition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WoodComposition getWoodComposition() {
        return woodComposition;
    }

    public void setWoodComposition(WoodComposition woodComposition) {
        this.woodComposition = woodComposition;
    }
}
