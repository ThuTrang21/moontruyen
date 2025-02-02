package com.mt.moontruyen.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mt.moontruyen.utils.SlugUntils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Story> stories;
    private String slug;

    @PrePersist
    @PreUpdate
    private void generateSlug(){
        this.slug = SlugUntils.generateSlugFromName(this.name);
    }
}
