package com.lxf;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer languageId;
    @Column
    private String des;

//    @ManyToMany(targetEntity = Developer.class)
//    @JoinTable(name = "middle_develop_language",
//            joinColumns = @JoinColumn(name = "language_id", referencedColumnName = "languageId"),
//            inverseJoinColumns = @JoinColumn(name = "develop_id", referencedColumnName = "developerId"))
    @ManyToMany(mappedBy = "languages")
    private List<Developer> developers = new ArrayList<>();

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", des='" + des + '\'' +
                ", developers=" + developers +
                '}';
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }
}
