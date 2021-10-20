package sergey.ermakov.project1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class ApiController {
    private ArrayList<Topic> topics=new ArrayList<>();


//curl -X POST http://localhost:8080/topics -H 'Content-Type: application/json' -d 'text'
    @PostMapping("topics")
    public void addTopic(@RequestBody String text){
        topics.add(new Topic(text,new ArrayList<String>()));
    };
//curl -X DELETE http://localhost:8080/topics/1
    @DeleteMapping("topics/{index}")
    public void deleteTopic (@PathVariable("index") Integer index){
        topics.remove((int) index);
    };

    @GetMapping("topics/{index}")
    public String getTopic(@PathVariable("index") Integer index){
        return topics.get((int) index).printTopic();
    };

    @GetMapping("topics")
    public ArrayList<String> getTopics(){
        ArrayList<String> a=new ArrayList<>();
        for (Topic top:topics){
            a.add(top.printTopic());
        };
        return a;
    };
    //curl -X PUT http://localhost:8080/topics/2 -H 'Content-Type: application/json' -d 'text10'
    @PutMapping("topics/{index}")
    public void updateTopic (@PathVariable("index") Integer index, @RequestBody String text){
        topics.remove((int) index);
        topics.add(index,new Topic(text,new ArrayList<String>()));
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

    //curl -X POST http://localhost:8080/topics/1/comments -H 'Content-Type: application/json' -d 'comment1'
    @PostMapping("topics/{index}/comments")
    public void addComment (@PathVariable("index") Integer index,@RequestBody String text ){
        topics.get((int)index).add(text);
    };
    //curl -X DELETE http://localhost:8080/topics/1/2/comments
    @DeleteMapping("topics/{index1}/{index2}/comments")
    public void deleteComment (@PathVariable("index1") Integer index1,@PathVariable("index2") Integer index2){
        topics.get((int) index1).delete(index2);
    };
    //curl -X PUT http://localhost:8080/topics/2/5/comments -H 'Content-Type: application/json' -d 'comment123'
    @PutMapping("topics/{index1}/{index2}/comments")
    public void updateComment(@PathVariable("index1") Integer index1,@PathVariable("index2") Integer index2,@RequestBody String text){
        topics.get((int) index1).update(index2,text);
    };
    @GetMapping("topics/{index}/comments")
    public ArrayList<String> getComments(@PathVariable("index") Integer index){
        return topics.get((int) index).getComments();

    }

}
