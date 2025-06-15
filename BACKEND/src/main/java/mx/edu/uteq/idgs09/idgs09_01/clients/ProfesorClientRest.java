package mx.edu.uteq.idgs09.idgs09_01.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.Profesor;

@FeignClient(name = "idgs09-02", url = "localhost:8081")
public interface ProfesorClientRest {

}
