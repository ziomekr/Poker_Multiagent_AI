import java.util.ArrayList;

public class CircularList<E> extends ArrayList<E> {

    @Override
    public E get(int index){
        if(index >= this.size()){
            index = index - this.size();
        }
        return super.get(index);
    }



}
