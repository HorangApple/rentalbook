
package rentalbook.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="system", url="${api.system.url}")
public interface ReturnService {

    @RequestMapping(method= RequestMethod.POST, path="/returns")
    public void returnBook(@RequestBody Return rtn);

}