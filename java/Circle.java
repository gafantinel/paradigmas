public class Circle {
   private double x;
   private double y;
   private double r;

   public Circle() {
      x = y = r = 0.0;
      System.out.println("New");
      System.out.println("New Circle");
   }

   public Circle(double x, double y, double r){
      x = y = r = 2.0;
      System.out.println("New Circle");
   }

   public double area() {
      return Math.PI * r * r;
   }

   public void setRadius(double radius){
      radius = radius*2;
   }
   public static void main(String[] args) {
      Circle c = new Circle();
      Circle c2 = new Circle(1.0, 2.0, 3.0);
      System.out.println("Area do circulo: " + c.area());
      System.out.println("Area do circulo: " + c2.area());
   }
}
