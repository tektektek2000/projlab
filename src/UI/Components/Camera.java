package UI.Components;

public class Camera {
    double x;
    double y;
    double zoom;

    public Camera(double X,double Y,double Zoom){
        x=X;
        y=Y;
        zoom=Zoom;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double X){
        x = X;
    }

    public void setY(double Y){
        y = Y;
    }

    public double getZoom(){
        return zoom;
    }

    public void setZoom(double z){
        zoom = z;
    }

    public double TransformX(double X){
        return (X- x) * zoom;
    }

    public double TransformY(double Y){
        return (Y - y) * zoom;
    }

    public double TransformWidth(double w){
        return w * zoom;
    }

    public double TransformHeight(double h){
        return h * zoom;
    }
}
