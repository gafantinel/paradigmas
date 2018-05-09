public class Array {
  public static void main(String[] args) {
    Point[] p = new Point[5];

    for (int i=0;i<5;i++){
      for (int j=0;j<5;j++) {
        double x = p[j].distanceTo(p[i]);
        System.out.println("distância: " + x);
      }
    }
  }
}
