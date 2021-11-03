package sergey.ermakov.project1;

import java.util.ArrayList;

public class Username {
    private String name;
    private ArrayList<Comment> comments;

    public Username (String name1,ArrayList<Comment> comments1){
        name=name1;
        comments=comments1;
    };

    public String getName() {
        return name;
    };

    public ArrayList<String> getComments() {
        ArrayList<String> a=new ArrayList<>();
        for (Comment c:comments){
            a.add(c.getName());
        };
        return a;
    };
    public ArrayList<Comment> getComments1(){
        return comments;
    };
    public Comment getComment(int i){
        return comments.get((int)i);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    };
    public void deleteComment(int index){
        comments.remove((int) index);
    };
    public void deleteComments(){
        comments.clear();
    };
    public void updateComment (int index, Comment comment){
        comments.remove((int) index);
        comments.add(index,comment);
    }
}
