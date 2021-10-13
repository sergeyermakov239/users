package sergey.ermakov.project1;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class User {

    ArrayList<String> names=new ArrayList<>();
    ArrayList<Integer> ages=new ArrayList<Integer>();



    @PostMapping("users")
    public void createUser(@RequestBody Integer age1,@RequestBody String mes){
        names.add(mes);
        ages.add(age1);
    }

    @GetMapping("books")
    public String getMessages(){
        String s="";
        for (int i=0;i<ages.size();i++){
            s=s+"["+names.get(i)+"_"+ages.get(i)+"] ";
        };
        return  s;
    }

    @GetMapping("books/{index}")
    public String getMessage (@PathVariable("index") Integer i){
        return messages.get((int) i);
    }

    @DeleteMapping("books/{index}")
    public void deleteMessage (@PathVariable("index") Integer i){
        messages.remove((int) i);
    }

    @PutMapping("books/{index}")
    public void putMessage (@PathVariable("index") Integer i, @RequestBody String mes){
        messages.remove((int)i);
        messages.add(i,mes);
    }
}
