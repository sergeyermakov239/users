package sergey.ermakov.project1;

public class Comment {
    private String name;
    private int indexOfTopic;
    private int indexOfComment;

    public Comment(String name1,int indexOfTopic1,int indexOfComment1){
        name=name1;
        indexOfTopic=indexOfTopic1;
        indexOfComment=indexOfComment1;
    };

    public String getName() {
        return name;
    };

    public int getIndexOfComment() {
        return indexOfComment;
    };

    public void setIndexOfComment(int indexOfComment) {
        this.indexOfComment = indexOfComment;
    };

    public int getIndexOfTopic() {
        return indexOfTopic;
    }
}
