package sergey.ermakov.project1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class ApiController {
    private ArrayList<Topic> topics=new ArrayList<>();
    private ArrayList<Username> users=new ArrayList<>();


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

    //curl -X POST http://localhost:8080/topics/1/comments/Sergey -H 'Content-Type: application/json' -d 'comment1'
    @PostMapping("topics/{index}/comments/{user}")
    public void addComment (@PathVariable("index") Integer index,@PathVariable("user") String user,@RequestBody String text ){
        topics.get((int)index).add(text);
        boolean f=false;
        for (Username u:users){
            if (u.getName().equals(user)){
                u.addComment(new Comment(text,index,((topics.get((int) index)).getComments().size()-1)));
                f=true;
            };
        };
        if (f==false){
            ArrayList<Comment> a=new ArrayList<>();
            a.add(new Comment(text,index,((topics.get((int) index)).getComments().size()-1)));
            users.add(new Username(user,a));
        };
    };
    //curl -X DELETE http://localhost:8080/topics/1/2/comments
    @DeleteMapping("topics/{index1}/{index2}/comments")
    public void deleteComment (@PathVariable("index1") Integer index1,@PathVariable("index2") Integer index2){
        topics.get((int) index1).delete(index2);
        for (Username u:users){
            int i=0;
            for (Comment comment:u.getComments1()){
                if (comment.getIndexOfTopic()==index1&&comment.getIndexOfComment()==index2){
                    u.deleteComment(i);
                };
                i+=1;
            }
        }
    };
    //curl -X PUT http://localhost:8080/topics/2/5/comments -H 'Content-Type: application/json' -d 'comment123'
    @PutMapping("topics/{index1}/{index2}/comments")
    public void updateComment(@PathVariable("index1") Integer index1,@PathVariable("index2") Integer index2,@RequestBody String text){
        topics.get((int) index1).update(index2,text);
        for (Username u:users){
            int i=0;
            for (Comment comment:u.getComments1()){
                if (comment.getIndexOfTopic()==index1&&comment.getIndexOfComment()==index2){
                    u.updateComment(i,new Comment(text,index1,index2));
                };
                i+=1;
            }
        }
    };
    @GetMapping("topics/{index}/comments")
    public ArrayList<String> getComments(@PathVariable("index") Integer index){
        return topics.get((int) index).getComments();

    }


    @GetMapping("topics/comments/users/{index}")
    public ArrayList<String> getCommentsOfUser(@PathVariable("index") Integer index){
        return users.get((int ) index).getComments();
    };
    //curl -X PUT http://localhost:8080/topics/comments/users/1/2 -H 'Content-Type: application/json' -d 'comment123'
    @PutMapping("topics/comments/users/{index1}/{index2}")
    public void updateCommentOfUser(@PathVariable("index1") Integer index1,@PathVariable("index2") Integer index2,@RequestBody String text){
        int indexoftopic=users.get((int) index1).getComment(index2).getIndexOfTopic();
        int indexofcomment=users.get((int) index1).getComment(index2).getIndexOfComment();
        topics.get((int) indexoftopic).update(indexofcomment,text);
        users.get((int) index1).updateComment(index2,new Comment(text,indexoftopic,indexofcomment));
    };
    //curl -X DELETE http://localhost:8080/topics/comments/users/3
    @DeleteMapping("topics/comments/users/{index}")
    public void deleteCommentsOfUser(@PathVariable("index") Integer index){
        for (int i=users.get((int)index).getComments1().size()-1;i>=0;i=i-1){
            int indexoftopic=users.get((int)index).getComments1().get((int)i).getIndexOfTopic();
            int indexofcomment=users.get((int)index).getComments1().get((int)i).getIndexOfComment();
            topics.get((int)indexoftopic).delete((int)indexofcomment);
        };
        users.get((int)index).deleteComments();
    }

}
