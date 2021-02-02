package rentalbook;

import org.springframework.stereotype.Repository;
import rentalbook.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyHandler{

    @Autowired
    BookRepository bookRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSubscribed_UpdateStatus(@Payload Subscribed subscribed){

        if(subscribed.isMe()){
            System.out.println("##### listener  : " + subscribed.toJson());
            List<Book> bookList = bookRepository.findByBookNmOrId(subscribed.getBookNm(), subscribed.getBookId());
            for(Book book : bookList) {
                book.setStatus("Booking");
                bookRepository.save(book);
                System.out.println("##### "+book.getBookNm()+" is reserved");
            }

        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturnedBook_UpdateStatus(@Payload ReturnedBook returnedBook){

        if(returnedBook.isMe()) {
            System.out.println("##### listener  : " + returnedBook.toJson());
            List<Book> bookList = bookRepository.findByBookNmOrId(returnedBook.getBookNm(), returnedBook.getBookId());
            for (Book book : bookList) {
                book.setStatus("In stock");
                bookRepository.save(book);
                System.out.println("##### " + book.getBookNm() + " is returned");
            }
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverChecked_UpdateStatus(@Payload Checked checked){

        if(checked.isMe()) {
            System.out.println("##### listener  : " + checked.toJson());
            List<Book> bookList = bookRepository.findByBookNmOrId(checked.getBookNm(), checked.getBookId());
            for (Book book : bookList) {
                book.setStatus("On Rental");
                bookRepository.save(book);
                System.out.println("##### " + book.getBookNm() + " is on rental");
            }
        }
    }

}
