package rentalbook;

import org.springframework.beans.BeanUtils;
import rentalbook.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    ReserveRepository reserveRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRentaled_Check(@Payload Rentaled rentaled){

        if(rentaled.isMe()){
            System.out.println("##### listener  : " + rentaled.toJson());
            List<Reserve> reserveList = reserveRepository.findByBookIdOrBookNm(rentaled.getBookId(),rentaled.getBookNm());
            if(reserveList.size()>0) {
                for (Reserve reserve : reserveList) {
                    if (reserve.getUserNm().equals(rentaled.getUserNm())) {
                        System.out.println("Confirmed Reservation");
                        reserveRepository.deleteById(reserve.getId());
                    }
                }
            }
        }
    }

}
