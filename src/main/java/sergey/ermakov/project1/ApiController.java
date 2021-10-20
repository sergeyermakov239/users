package sergey.ermakov.project1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class ApiController {
    private ArrayList<String> topics=new ArrayList<>();


//curl -X POST http://localhost:8080/topics -H 'Content-Type: application/json' -d 'text'
    @PostMapping("topics")
    public void addTopic(@RequestBody String text){
        topics.add(text);
    };
//curl -X DELETE http://localhost:8080/topics/1
    @DeleteMapping("topics/{index}")
    public void deleteTopic (@PathVariable("index") Integer index){
        topics.remove((int) index);
    };

    @GetMapping("topics/{index}")
    public String getTopic(@PathVariable("index") Integer index){
        return topics.get((int) index);
    };

    @GetMapping("topics")
    public ArrayList<String> getTopics(){
        return topics;
    };
    //curl -X PUT http://localhost:8080/topics/2 -H 'Content-Type: application/json' -d 'text10'
    @PutMapping("topics/{index}")
    public void updateTopic (@PathVariable("index") Integer index, @RequestBody String text){
        topics.remove((int) index);
        topics.add(index,text);
    };
    @GetMapping("topics/count")
    public int countTopics(){
        return topics.size();
    };
    //curl -X DELETE http://localhost:8080/topics/all
    @DeleteMapping("topics/all")
    public void deleteTopics (){
        topics.clear();
    };

}
