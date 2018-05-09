class Point {
  private double x;
  private double y;

  public Point(){
    x = y = 0.0;
  }

  public Point(double xp, double yp){
    this.x = xp;
    this.y = yp;
  }

  public void move(double dx, double dy){
    this.x = this.x + dx;
    this.y = this.y + dy;
  }

  public double distanceTo(Point p){
    double dx = p.x - x;
    double dy = p.y - y;
    return Math.sqrt(dx*dx + dy*dy);
  }
}
