package rentalbook;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Rental_table")
public class Rental {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userNm;
    private Long bookId;
    private String bookNm;

    @PostPersist
    public void onPostPersist(){
        Rentaled rentaled = new Rentaled();
        BeanUtils.copyProperties(this, rentaled);
        rentaled.setStatus("Start Rental");
        rentaled.publishAfterCommit();


    }

    @PostRemove
    public void onPostRemove(){
        Returned returned = new Returned();
        BeanUtils.copyProperties(this, returned);
        returned.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        rentalbook.external.Return rtn = new rentalbook.external.Return();
        // mappings goes here
        rtn.setBookId(this.getBookId());
        rtn.setBookNm(this.getBookNm());
        rtn.setUserNm(this.getUserNm());
        rtn.setRentalId(this.getId());
        RentalApplication.applicationContext.getBean(rentalbook.external.ReturnService.class)
            .returnBook(rtn);


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
