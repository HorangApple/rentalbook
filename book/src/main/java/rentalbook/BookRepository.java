package rentalbook;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>{

    List<Book> findByBookNmOrId(String BookNm, Long Id);

}