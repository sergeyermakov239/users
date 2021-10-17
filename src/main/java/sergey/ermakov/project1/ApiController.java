package sergey.ermakov.project1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class ApiController {
    private ArrayList<User> users=new ArrayList<>();


//curl -X POST http://localhost:8080/users -H 'Content-Type: application/json' -d '{"name1":"Sergey","age1":16}'
    @PostMapping("users")
    public void addUser(@RequestBody User user1){
        users.add(user1);
    };
//curl -X DELETE http://localhost:8080/users/1
    @DeleteMapping("users/{index}")
    public void deleteUser(@PathVariable("index") Integer index){
        users.remove((int) index);
    };

    @GetMapping("users/{index}")
    public String getUser(@PathVariable("index") Integer index){
        return users.get(index).print();
    };

    @GetMapping("users")
    public String getUsers(){
        String s="";
        for (User u:users){
            s+=u.print();
        };
        return s;
    };
    //curl -X PUT http://localhost:8080/users/2 -H 'Content-Type: application/json' -d '21'
    @PutMapping("users/{index}")
    public void updateUser(@PathVariable("index") Integer index, @RequestBody Integer age1){
        users.get(index).setAge(age1);
    }

}
