package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name="USER") -> table name이 member가 아닌 user로 넘어가게 됨
public class Member {

    @Id //PK를 알려주기 위함
    private Long id;
    //@Column(name = "username") -> column의 이름이 name이 아닌 username으로 넘어감
    private String name;

    public Member(){

    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
