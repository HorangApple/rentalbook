package rentalbook;

import rentalbook.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSubscribed_then_CREATE_1 (@Payload Subscribed subscribed) {
        try {
            if (subscribed.isMe()) {
                // view 객체 생성
                Mypage mypage = new Mypage();
                // view 객체에 이벤트의 Value 를 set 함
                mypage.setUserNm(subscribed.getUserNm());
                mypage.setBookNm(subscribed.getBookNm());
                mypage.setBookId(subscribed.getBookId());
                mypage.setRentalId(subscribed.getId());
                mypage.setStatus(subscribed.getStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenChecked_then_UPDATE_1(@Payload Checked checked) {
        try {
            if (checked.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByBookNmOrBookId(checked.getBookNm(), checked.getBookId());
                for(Mypage mypage : mypageList){
                    if(mypage.getUserNm().equals(checked.getUserNm())){
                        // view 객체에 이벤트의 eventDirectValue 를 set 함
                        mypage.setStatus(checked.getStatus());
                        // view 레파지 토리에 save
                        mypageRepository.save(mypage);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRentaled_then_UPDATE_2(@Payload Rentaled rentaled) {
        try {
            if (rentaled.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByBookNmOrBookId(rentaled.getBookNm(), rentaled.getBookId());
                for(Mypage mypage : mypageList){
                    if(mypage.getUserNm().equals(rentaled.getUserNm())){
                        // view 객체에 이벤트의 eventDirectValue 를 set 함
                        mypage.setStatus(rentaled.getStatus());
                        // view 레파지 토리에 save
                        mypageRepository.save(mypage);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReturnedBook_then_UPDATE_3(@Payload ReturnedBook returnedBook) {
        try {
            if (returnedBook.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByBookNmOrBookId(returnedBook.getBookNm(), returnedBook.getBookId());
                for(Mypage mypage : mypageList){
                    if(mypage.getUserNm().equals(returnedBook.getUserNm())){
                        // view 객체에 이벤트의 eventDirectValue 를 set 함
                        mypage.setStatus(returnedBook.getStatus());
                        // view 레파지 토리에 save
                        mypageRepository.save(mypage);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}