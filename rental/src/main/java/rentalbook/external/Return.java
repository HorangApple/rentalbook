package rentalbook.external;

public class Return {

    private Long id;
    private Long rentalId;
    private Long bookId;
    private String bookNm;
    private String userNm;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getRentalId() {
        return rentalId;
    }
    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
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
    public String getUserNm() {
        return userNm;
    }
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

}
