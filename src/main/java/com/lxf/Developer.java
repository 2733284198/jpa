package com.lxf;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer developerId;
    @Column
    private String name;

    @ManyToMany(targetEntity = Language.class,cascade = CascadeType.ALL)
    @JoinTable(name = "middle_develop_language",
            joinColumns = @JoinColumn(name = "develop_id", referencedColumnName = "developerId"),
            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "languageId"))
    private List<Language> languages = new ArrayList<>();

    @Override
    public String toString() {
        return "Developer{" +
                "developerId=" + developerId +
                ", name='" + name + '\'' +
                ", languages=" + languages +
                '}';
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
