package springrest.exam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend {
    @Id @GeneratedValue
    private int id;

    private String fname;

    private Integer fage;

    @Builder
    public Friend(int id, String fname, Integer fage) {
        this.id = id;
        this.fname = fname;
        this.fage = fage;
    }

    public Friend update(String fname, Integer fage) {
        this.fname = fname;
        this.fage = fage;
        return this;
    }

}
