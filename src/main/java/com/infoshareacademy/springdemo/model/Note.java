package com.infoshareacademy.springdemo.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "NOTES")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", length = 1024)
    @NotNull
    private String content;

    @Column(name = "date")
    @NotNull
    private LocalDateTime date;

    public Note() {
    }

    public Note(@NotNull String content,
                @NotNull LocalDateTime date) {
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Note{");
        sb.append("id=").append(id);
        sb.append(", content='").append(content).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}