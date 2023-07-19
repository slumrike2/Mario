package clasesInstanciables;


public class Spawner<E> {

    int x;
    int y;
    E entity;

    public Spawner(int x, int y, int id, E entity) {
        this.x = x;
        this.y = y;
        this.entity = entity;

    }

    public E getEntity() {
        return entity;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



}
