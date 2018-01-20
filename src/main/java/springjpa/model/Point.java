package springjpa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "point")
public class Point implements Serializable{

    private long id;
    private double x;
    private double y;
    private double r;
    private boolean hit;

    protected Point(){}

    public Point(double x, double y, double r) {
        //this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
        inArea();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "x")
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Basic
    @Column(name = "y")
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Basic
    @Column(name = "r")
    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }
    @Basic
    @Column(name = "hit")
    public boolean getHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (id != point.id) return false;
        if (Double.compare(point.x, x) != 0) return false;
        if (Double.compare(point.y, y) != 0) return false;
        if (Double.compare(point.r, r) != 0) return false;
        return hit == point.hit;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(x);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(r);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (hit ? 1 : 0);
        return result;
    }

   public void inArea(){
        if ( x >= 0 && x <= r/2 && y <= 0 && y <= r) hit = true;
        else
        if ( x <= 0 && x >= -r && y <=0 && y >= -r &&  x*x+y*y<=r*r ) hit = true;
        else
        if ( x <= 0 && x >= -r/2 && y >=0 && y<= r/2 && y<=(x + r/2)) hit = true;
        else hit = false;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit=" + hit +
                '}';
    }
}
