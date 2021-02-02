package rentalbook;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Reserve_table")
public class Reserve {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userNm;
    private Long bookId;
    private String bookNm;

    @PostPersist
    public void onPostPersist(){
        Subscribed subscribed = new Subscribed();
        BeanUtils.copyProperties(this, subscribed);
        subscribed.setStatus("Booking");
        subscribed.publishAfterCommit();


    }

    @PreRemove
    public void onPreRemove(){
        Checked checked = new Checked();
        BeanUtils.copyProperties(this, checked);
        checked.setStatus("On rental");
        checked.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public String getBookNm() {
        return bookNm;
    }

    public void setBookNm(String bookNm) {
        this.bookNm = bookNm;
    }




}
