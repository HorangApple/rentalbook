package rentalbook;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReserveRepository extends PagingAndSortingRepository<Reserve, Long>{

    List<Reserve> findByBookIdOrBookNm(Long BookId, String BookNm);

}