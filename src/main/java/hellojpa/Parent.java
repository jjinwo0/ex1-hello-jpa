package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id @GeneratedValue
    private Long id;

    private String name;

    //cascade: 엔티티를 영속화할 때 연관된 엔티티도 함께 영속화하는 편리함을 제공
    //단, 단일 엔티티에 종속적일때만 사용하며 다른 엔티티와도 연관관계를 맺고 있다면 사용하면 안됨. (단일 소유자여야 함: Parent가 Child의 단일 소유자)
    //라이프사이클이 거의 유사할 때 사용

    //orphanRemoval: 고아 객체
    //참조하는 곳이 하나일 때만 사용해야 하며, 엔티티가 개인 소유여야 함
    //CascadeType.REMOVE처럼 동작한다.
    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child){
        childList.add(child);
        child.setParent(this);
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
